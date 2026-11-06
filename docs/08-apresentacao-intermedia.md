# 08 · Apresentação intermédia

**Quando:** o nosso turno de laboratório, na semana de 10 a 16 nov (confirmar o dia).
**O quê:** fases 1 a 3. Breve, presencial, obrigatória e eliminatória.
**Não levar:** servidor, sockets, threads nossas.

## Guião (cerca de 5 minutos)

### 1. O problema (1 min) · Celso
Uma casa inteligente em modelo cliente-servidor. Sensores leem o ambiente, decisores emitem ordens de alto nível, atuadores controlam dispositivos. Nesta entrega, mostramos as três fases sem concorrência nem distribuição.

### 2. Fase 2: hierarquia de eventos (1 min) · Celso
- Mostrar o diagrama de [02-arquitetura](02-arquitetura.md).
- Lançar **`09 Testes`**: todos os eventos, com o formato e o tipo certos.

### 3. Fases 1 e 3: sensores (1 min) · Fredson
- **`01 Sensor luz LivingRoom`**: mexer o slider. A consola mostra `LightSensorEvent [light=320.0, area=LivingRoom, getType()=SENSOR]`.
- **`03 Sensor humidade ar`** e **`04 Sensor humidade solo`**: explicar que são duas leituras diferentes.

### 4. Fases 1 e 3: atuadores (1,5 min) · Fredson
- **`05 Lampada LivingRoom`**, duas vezes: as duas lâmpadas do cenário dos slides. Mostrar o histórico.
- **`07 Ar condicionado`**: a ordem de ventilação é ignorada em modo `Frio`, como pede a secção 1.2.
- **`08 Rega Garden`**.

### 5. Próximos passos e perguntas (0,5 min) · os dois
A seguir: servidor com uma thread por cliente e registo com o `RegistrationEvent`. Perguntas: [07-duvidas](07-duvidas.md), secção B.

## Perguntas prováveis

**O que fizeram em cada fase?**
Fase 1: uma janela por dispositivo com os vossos componentes. Fase 2: a hierarquia descrita no enunciado. Fase 3: os atuadores mudam com ordens criadas localmente, como pede o TODO do `Ventilator`, e os sensores geram eventos para a consola.

**Porque é que a classe de topo é abstrata?**
Não existe um evento "genérico". É abstrata e não interface porque tem estado comum, a área, que as filhas herdam.

**Porque usam `Event` se os slides dizem `Message`?**
Seguimos a versão mais recente do enunciado, que passou a usar "Evento" para evitar ambiguidades. Do slide mantivemos o formato do texto.

**Porque é que as ordens não dizem que dispositivo ligar?**
São de alto nível: o decisor diz o que pretende para a área e não sabe que dispositivos existem. Uma ordem serve às duas lâmpadas da sala.

**Para que serve `Serializable`, se ainda não há rede?**
A fase 5 envia eventos por canais de objetos. Fica na classe de topo e toda a hierarquia a herda, sem custo agora.

**Dizem que não há concorrência, mas usam `invokeLater`. Porquê?**
Não criámos threads, mas já existem duas: a `main` e a thread do Swing. O Swing não é thread-safe, por isso só a thread dele deve mexer nos componentes. É o que faz o vosso `ComponentState.setAtivo`.

**Porque é que o `Thread.sleep` não congela a janela?**
Porque corre na `main`, não na thread do Swing. Num listener, congelava.

**O que acontece quando se mexe no slider?**
O `SensorControl` chama o nosso callback, na thread do Swing, quando o utilizador larga o slider. O callback cria o evento e imprime-o.

**Porque é que o histórico não usa `SimpleDateFormat`?**
*(Se tiverem seguido a sugestão.)* Não é thread-safe; `DateTimeFormatter` é imutável.

**Como dividiram o trabalho?**
Celso: eventos, humidade e rega. Fredson: histórico, luz, ventilador, temperatura e ar condicionado. Cada tarefa é uma branch com pull request revisto pelo outro, que tem de saber explicá-la.

**(Ao Fredson) Explica-me a hierarquia. (Ao Celso) Explica-me a `Light` e o `ActionHistory`.**
Ensaiar na véspera: cada um explica o código do outro.

## Se alguma coisa correr mal

- Projeto compilado nos **dois** computadores, com a tag `entrega-intermedia` no GitHub.
- Se uma janela falhar, passar à seguinte e mostrar `09 Testes`.
- Antes de começar: parar todas as consolas (Remove All Terminated).
- Se o professor pedir uma alteração ao vivo, pensar em voz alta.

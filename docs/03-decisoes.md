# 03 · Decisões de desenho e trade-offs

Cada decisão segue o mesmo formato: **o problema**, **as opções**, **o que ganhamos e perdemos com cada uma**, **o que escolhemos e porquê**. Na oral, o professor quer ouvir sobretudo o "porquê" e o "o que perdemos".

> Trade-off = compromisso. Quase nunca há uma opção melhor em tudo: ganha-se numa coisa e paga-se noutra. Saber dizer o que se paga é o que mostra que se percebeu.

> **Atualização de 3 nov (código base da GUI do professor):** as decisões **D23 a D27**, no fim, substituem partes de decisões anteriores. Estão substituídas: **D10** (o tipo vai como enum `Type`), **D11** e **D21** (o tópico é a área), **D17** e **D22** (as janelas são as do professor; já não há `ClientMain` único nem `DeviceType`). As outras mantêm-se.

---

### D1 · Como criar threads: `extends Thread` ou `implements Runnable`
| | `extends Thread` | `implements Runnable` |
|---|---|---|
| Ganha | código mais curto; acesso direto a `isInterrupted()`, `getName()` | a classe pode herdar de outra; separa "a tarefa" de "quem a corre" |
| Perde | gasta a única herança da classe | um passo extra (`new Thread(r).start()`) |

**Escolha:** `extends Thread` em `ClientHandler` e `TopicDispatcher` (não precisam de herdar de mais nada); `Runnable` anónimo para threads pequenas nos clientes. Ambas são da aula 02.

### D2 · Exclusão mútua: `synchronized` ou `ReentrantLock`
| | `synchronized` | `ReentrantLock` |
|---|---|---|
| Ganha | impossível esquecer de destrancar (sai sozinho, até com exceção); funciona com `wait/notifyAll` | `tryLock` com prazo; várias condições por cadeado |
| Perde | um só "local de espera" por objeto; não dá para desistir de esperar pelo cadeado | é biblioteca de concorrência (proibida?); `unlock` em `finally` obrigatório |

**Escolha:** `synchronized`. O enunciado proíbe os mecanismos da biblioteca, a API do latch imposta já usa `synchronized`, e os 4 mandamentos da aula 04 assumem o cadeado intrínseco.

### D3 · Contador de ids: método `synchronized` ou `AtomicInteger`
| | `synchronized` | `AtomicInteger` |
|---|---|---|
| Ganha | só usa matéria básica, sem dúvidas sobre a proibição | sem cadeado, mais rápido |
| Perde | um cadeado por registo (irrelevante: registos são raros) | pode contar como "equivalente da biblioteca" |

**Escolha:** `synchronized` até o professor responder.

### D4 · Acordar threads: `notify` ou `notifyAll`
| | `notify` | `notifyAll` |
|---|---|---|
| Ganha | acorda só uma (menos trabalho) | nunca acorda "a errada" e deixa a certa a dormir |
| Perde | se houver threads à espera de condições diferentes no mesmo objeto, pode acordar uma que volta a dormir e ninguém avança | acorda threads que voltam logo a dormir (custo pequeno) |

**Escolha:** `notifyAll` (mandamento 4).

### D5 · Quem envia os eventos aos subscritores (a decisão mais importante)
| Opção | Ganha | Perde |
|---|---|---|
| **A** · o próprio `ClientHandler` do subscritor espera no tópico, envia e lê o ack | cumpre à letra "a thread dedicada gere envio e receção"; menos threads | a thread fica presa à espera do ack e não lê mais nada; o decisor (publica e subscreve) complica-se; a decisão "todos consumiram" fica espalhada |
| **B** · dispatcher por tópico + thread escritora por cliente | um cliente lento não atrasa a entrega aos outros; mais robusto | mais uma fila por cliente (feita por nós), o dobro das threads, mais código para explicar |
| **C** · dispatcher por tópico + `send()` sincronizado no handler | o "esperar por todos" está num só sítio, fácil de explicar e testar; poucas classes | se um cliente tiver a rede muito lenta, o `send` atrasa o dispatcher desse tópico |

**Escolha:** C. É a mais simples que cumpre o enunciado. B é a evolução natural se sobrar tempo. **Perguntar ao professor** se a C respeita "thread dedicada por cliente".

### D6 · Fila do tópico: uma fila partilhada ou uma por subscritor
| | uma por tópico | uma por subscritor (como MQTT/RabbitMQ) |
|---|---|---|
| Ganha | é o que o enunciado descreve ("só avança quando todos confirmarem") | cada subscritor anda ao seu ritmo; sem head-of-line blocking |
| Perde | um subscritor lento prende todos até ao timeout | não cumpre o enunciado |

**Escolha:** uma por tópico. Saber explicar o head-of-line blocking na oral.

### D7 · Estrutura da fila: `LinkedList` ou `ArrayDeque`
Ambas fazem inserir no fim e tirar do início em tempo constante. `ArrayDeque` é ligeiramente mais rápida e gasta menos memória; `LinkedList` é a que o professor usa nos slides. **Escolha:** `LinkedList` (familiar ao avaliador). Diferença irrelevante para o nosso volume.

### D8 · Formato das mensagens: objetos ou texto
| | objetos (`ObjectStream`) | texto (linhas, JSON) |
|---|---|---|
| Ganha | sem parsing; tipos verificados pelo compilador; é o que o enunciado pede | legível, depurável com qualquer ferramenta, independente da linguagem |
| Perde | só funciona entre programas Java com as mesmas classes; armadilhas (ordem dos streams, cache) | escrever e manter o parsing; erros só em execução |

**Escolha:** objetos (fase 4: "através de canais de objetos").

### D9 · Hierarquia de mensagens ou uma classe com "tipo"
| | hierarquia (`Message` + filhas) | uma classe com `enum Kind` |
|---|---|---|
| Ganha | cada mensagem só tem os seus campos; `instanceof` claro | menos ficheiros |
| Perde | mais classes | campos vazios; `switch` em todo o lado |

**Escolha:** hierarquia (o enunciado já impõe uma classe própria, `ReceptionConfirmationMessage`).

### D10 · Como enviar o tipo no registo: `String` ou enum
Se o cliente enviasse o enum, um tipo inválido nunca sairia do cliente e o servidor não teria nada para recusar. **Escolha:** `String`, validado no servidor (secção 2.1).

### D11 · Nomes dos tópicos
| Opção | Ganha | Perde |
|---|---|---|
| `area/grandeza/canal` (escolhida) | uma automação por divisão; inspirado em MQTT; fácil de ler | um decisor "da casa toda" precisaria de wildcards |
| `grandeza/canal` | menos tópicos | as luzes da casa toda acendiam ao mesmo tempo |
| wildcards (`+/luz/ordens`) | flexível como o MQTT | correspondência de padrões no `TopicManager`; mais difícil de raciocinar com o latch |

### D12 · Tópicos criados à primeira utilização ou todos no arranque
À primeira utilização evita 40 dispatchers a dormir, mas obriga a proteger o check-then-act no `getOrCreate`. **Escolha:** à primeira utilização, com `synchronized`.

### D13 · Timeout do latch
Sem timeout, um subscritor caído prendia o tópico para sempre. Timeout curto (1 s) pode avançar antes de clientes lentos responderem; longo (30 s) torna a demo lenta quando alguém cai. **Escolha inicial:** 5 s, numa constante, a afinar nos testes.

### D14 · Um projeto Eclipse ou três
| | um projeto com pacotes | três projetos (common/server/client) |
|---|---|---|
| Ganha | zero configuração; o professor corre tudo do mesmo sítio | separação real, como em produção |
| Perde | menos "realista" | Build Path entre projetos; fácil dar `ClassNotFoundException` |

**Escolha:** um projeto.

### D15 · Versão do Java
O projeto da semana 1 do professor está em Java 8. Usar 17/21 daria `record`, `switch` com setas e `instanceof` com padrão, mas arrisca o projeto não compilar na máquina do avaliador. **Escolha:** Java 8 até o professor dizer outra coisa.

### D16 · Testes: `main` de teste ou JUnit
`main` corre sem configurar nada; JUnit dá relatórios e asserções e já vem no Eclipse. **Escolha:** `main` por agora; passar a JUnit se o número de testes crescer. Decidir em conjunto.

### D17 · GUI e lógica separadas
As janelas (`gui`) não sabem nada de rede; os clientes (`client`) não criam componentes. Ganha: a fase 1 pode ser feita em paralelo com o servidor; trocar pelos componentes do professor ("[TBD]") é fácil. Perde: mais classes e uma "cola" entre as duas.

### D18 · Atualizar a GUI a partir de outras threads
Alterar componentes diretamente da thread recetora parece funcionar, mas é uma race condition. `SwingUtilities.invokeLater` entrega a alteração à EDT. **Escolha:** `invokeLater` em todas as atualizações que não venham de um listener.

### D19 · Quantas classes
A primeira versão tinha 29 classes, uma por responsabilidade. Passámos para 23, juntando o que é sempre usado em conjunto:
- `Role` e a regra dos nomes de tópicos foram para dentro do `DeviceType`;
- o histórico foi para dentro do `ActuatorClient`;
- pedido e resposta de registo ficaram numa só mensagem de registo (hoje `RegistrationMessage`, ver D20);
- os três testes ficaram num só `IscotTests`, com três grupos.

| | mais classes | menos classes |
|---|---|---|
| Ganha | uma responsabilidade por classe; mais fácil dividir sem conflitos no Git; peças testáveis sozinhas | menos ficheiros para navegar e menos ligações para explicar |
| Perde | mais "cola" entre classes; parece maior do que é | classes maiores e com mais de um assunto |

O que não juntámos, e porquê:
- **as 6 classes do servidor e o latch**: são onde está a matéria de concorrência, e cada uma tem um papel diferente no produtor-consumidor;
- **as janelas e a lógica dos clientes**: a fase 1 pede GUI antes de haver rede, e os componentes gráficos podem vir a ser dados pelo professor.

Depois da apresentação do projeto pelo professor (D20–D22) voltámos a ter 29 ficheiros: o `Event` genérico deu lugar a 7 classes de mensagens tipadas (pequenas, só com dados) e entrou a GUI opcional do servidor. A estrutura do servidor não mudou.

### D20 · Mensagens tipadas em vez de um `Event` genérico
A apresentação do projeto mostra `LightSensorMessage [light=320.0, area=Sala, getType()=SENSOR]` e `AreaLightingOrderMessage [state=true, area=Sala, getType()=DECIDER]`, e chama `RegistrationMessage` ao registo.

| | `Event(topic, content)` genérico (versão anterior) | mensagens tipadas (escolhida) |
|---|---|---|
| Ganha | uma só classe para tudo | campos com tipo (`double light`, `boolean state`), verificados pelo compilador; sem parsing de texto; é o que o professor mostra |
| Perde | parsing de `"valor=320"`; erros só em execução; o cliente escolhia o tópico | mais classes (uma por grandeza e canal) |

Organização: `Message` (área, `messageId`, `senderId`, `getType()`) → `SensorMessage` e `OrderMessage` (abstratas, cada uma com `getSubject()`) → as concretas. O servidor trata todas as leituras com um só `instanceof SensorMessage` e todas as ordens com um só `instanceof OrderMessage`; acrescentar uma grandeza nova não mexe no servidor.

### D21 · Quem escolhe o tópico: o servidor
Antes, o cliente enviava o nome do tópico dentro do `Event`. Agora o cliente envia só a mensagem tipada, e o servidor calcula o tópico (`area/grandeza/leituras` ou `area/grandeza/ordens`) e valida o papel de quem envia. Ganha: "cada cliente apenas conhece o servidor"; um sensor não consegue publicar no tópico errado; uma lâmpada não consegue dar ordens. Perde: o servidor tem de conhecer as classes de mensagem (o que já acontecia, por serem partilhadas no pacote `common`).

### D22 · Papéis em inglês, GUI do servidor, código base da GUI
- `Role` passa a `SENSOR`, `DECIDER`, `ACTUATOR`, os valores que o professor mostra em `getType()`. Os tipos de dispositivo (`LAMPADA`, `VENTILADOR`, …) continuam em português, porque são o que escrevemos nos argumentos.
- Acrescentámos o atuador `VENTILADOR`, que aparece nas figuras do professor, e a `ServerMonitorFrame`, a GUI opcional do servidor ("mostrando as mensagens trocadas").
- O professor vai dar "código base apenas para a GUI". Fazemos janelas mínimas para a demo e, quando sair, trocamos só o interior das janelas, mantendo os métodos públicos que os clientes usam (D17).

---

## Decisões depois do código base da GUI (3 nov)

### D23 · Usar o código base do professor tal como está
O professor deu `iscot.enums` (`Areas`, `Type`, `AirConditioningMode`), `iscot.client.gui` (ícones e `SensorControl`) e dois exemplos (`HumiditySensor`, `Ventilator`).

| | adaptar o nosso desenho ao código base (escolhido) | manter o nosso desenho e traduzir |
|---|---|---|
| Ganha | o avaliador reconhece o código; menos trabalho; nada de traduções entre os nossos enums e os dele | ficávamos com os nomes em português |
| Perde | refazer parte dos esqueletos | duas versões do mesmo conceito (`Area` e `Areas`), erros de tradução, e o professor estranharia |

Consequências: removemos as nossas `Area` e `DeviceType` e as nossas janelas de sensor, decisor e atuador. Os ficheiros do professor só se mexem nos `TODO` dos exemplos.

### D24 · Um tópico por área
A `RegistrationMessage` leva só tipo e área (apresentação e código base) e o enunciado diz que "um cliente publica e consome mensagens exclusivamente de um tópico".

| | um tópico por área (escolhido) | um tópico por área e grandeza ("LivingRoom/luz") |
|---|---|---|
| Ganha | cumpre as duas frases à letra; o registo não precisa de mais nada; só 5 tópicos | cada cliente só recebe o que lhe interessa; menos acks inúteis |
| Perde | cada cliente recebe também mensagens que ignora (e tem de as confirmar); um decisor recebe as suas próprias ordens de volta | o registo teria de dizer a grandeza que o cliente ouve (o professor não o pede); o decisor usaria dois tópicos |

**Perguntar ao professor no dia 6.** Se ele preferir tópicos por grandeza, muda o `TopicManager` (chave) e a `RegistrationMessage` (mais um campo); o resto fica.

### D25 · Um programa por dispositivo, com 3 argumentos
O código base tem um `main` por dispositivo, com `endereço porto área`. Antes tínhamos um `ClientMain` único com 4 argumentos (o enunciado escreve `java iscot { IP PORT Area Tipo }`).

| | um `main` por dispositivo (escolhido, como o professor) | um `ClientMain` com o tipo como argumento |
|---|---|---|
| Ganha | igual ao código base; cada dispositivo tem a sua janela e a sua lógica, legíveis | menos classes |
| Perde | mais classes pequenas | um `switch` grande; diferente do que o professor deu |

O código repetido (ligar, registar, receber, confirmar) fica numa só classe, a `BrokerConnection`, e o histórico na `ActionHistory`.

### D26 · Que automações fazemos
| Automação | Componentes no código base | Decisão |
|---|---|---|
| luz (cenário do professor) | `SensorControl`, `LightIcon` | **prioridade 1**, primeiro de ponta a ponta |
| humidade → rega | `HumiditySensor`, `IrrigationIcon` | prioridade 2 |
| temperatura → ventilador / ar condicionado | `VentilatorIcon`, `ACIcon` | prioridade 3 |
| chuva → janelas | nenhum | **não fazemos** sem o professor pedir |

Também não usamos o que os componentes têm a mais e o enunciado não pede: intensidade da `LightIcon`, zona e duração da `IrrigationIcon`, temperatura do `ACIcon`.

### D27 · O que é opcional e fica para o fim
- `ServerMonitorFrame`: o professor diz que o servidor "pode funcionar sem GUI".
- `TopicTestFrame`: o enunciado diz que "pode existir" um formulário; o código base propõe outra coisa para a fase 1 (ordens criadas localmente nos atuadores).
- `ReceptionConfirmationMessage`: é o extra (fase 6).

Só se faz depois de as fases 1 a 5 estarem completas.

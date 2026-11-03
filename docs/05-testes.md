# 05 · Como testar

Erros de concorrência não aparecem quando se testa com uma thread nem, muitas vezes, numa execução curta. Por isso testamos em três níveis: **peças sozinhas**, **sistema a correr no Eclipse**, e **carga**.

## Nível 1 · Peças sozinhas (sem rede)

Correr `12 Testes` (`iscot.test.IscotTests`). O comentário da classe descreve cada verificação.

| Teste | Fase | Verifica |
|---|---|---|
| grupo 1 · mensagens e estrutura | 2 | formato do `toString` do professor, FIFO, ids sequenciais, tópicos por área separados, `TopicManager.get` devolve o mesmo objeto |
| grupo 2 · latch | 5 | timeout, libertação, latch a zero, acks a mais, interrupção |
| grupo 3 · carga | 5 | 10 threads × 1000 publicações: nada se perde, nenhum id repete |

**Regra**: quem escreve o teste não é quem escreveu a classe. Assim testamos o que a classe deve fazer, não o que achamos que fizemos.

## Nível 2 · Sistema a correr no Eclipse

### Preparar
- A pasta `launch/` tem configurações prontas: aparecem em **Run › Run Configurations › Java Application** (e no botão Run ▾).
- Correr sempre **o servidor primeiro**.
- Cada programa tem a sua consola. No ícone da consola: **Display Selected Console** para alternar e **Pin Console** para fixar uma.
- Duas lâmpadas iguais: lançar a mesma configuração duas vezes.
- No fim: parar todas as consolas (quadrado vermelho) e **Remove All Terminated**. Se não, o próximo servidor dá `BindException`.

### Cenários por fase

**Fase 1–2 (dia 6)**
- [ ] `04 Lampada LivingRoom`: a lâmpada acende e apaga com uma `AreaLightingOrderMessage` criada localmente; o histórico mostra `Lâmpada ligada - HH:MM:SS` e `Lâmpada desligada - HH:MM:SS`.
- [ ] `02 Sensor luz LivingRoom`: largar o slider imprime `LightSensorMessage [light=..., area=LivingRoom, getType()=SENSOR]` (uma vez por largada, não durante o arrasto).
- [ ] `12 Testes`, grupo 1: todos OK.
- [ ] Argumentos errados (2 argumentos, ou área `sala` em vez de `LivingRoom`) dão uma mensagem clara.

**Fase 3 · ligação**
- [ ] Servidor arranca e escreve que está à escuta.
- [ ] Cenário do professor: `02`, `03` e `04` duas vezes registam-se com 4 ids diferentes.
- [ ] Um registo com tipo `DWC` ou `null` é recusado com motivo e o servidor continua a funcionar.
- [ ] Cliente sem servidor ligado mostra erro claro (não um stack trace).

**Fase 4 · mensagens**
- [ ] Sensor de luz publica → o decisor da mesma área recebe; um decisor do Garden não.
- [ ] As lâmpadas também recebem a leitura do sensor, ignoram-na e confirmam-na.
- [ ] Uma mensagem de ordem enviada por um atuador é ignorada pelo servidor (só decisores dão ordens).
- [ ] Fechar a janela de um cliente não faz o servidor rebentar.

**Fase 5 · latch**
- [ ] Cenário da sala: cada mensagem espera 3 confirmações (2 lâmpadas + decisor); o log do servidor diz 0 em falta.
- [ ] Um cliente que recebe mas não confirma (comentar o envio do ack temporariamente): o tópico avança ao fim do timeout com 1 em falta.
- [ ] Fechar uma lâmpada a meio: o evento seguinte já só espera pela outra.
- [ ] Publicar 20 leituras seguidas: chegam todas, por ordem.

**Fase 6 · extra**
- [ ] Atuador responde OK a ordens conhecidas e NOK a desconhecidas; o servidor regista o resultado.

**Automações (demo final)**
- [ ] Luz abaixo de 500 lux → as duas lâmpadas da LivingRoom acendem (cenário do professor).
- [ ] Humidade baixa no Garden → rega liga; humidade sobe → rega desliga.
- [ ] Temperatura alta → ventilador e ar condicionado ligam (modo `Frio`).

## Nível 3 · Carga e robustez

- O grupo 3 do `IscotTests`, corrido várias vezes seguidas.
- **Experiência didática**: tirar temporariamente o `synchronized` do `Topic.publish`, correr o teste de carga e ver os números a falhar. Voltar a pôr. É a race condition da aula 03 ao vivo; vale a pena mostrar na oral.
- Vários sensores e várias lâmpadas na mesma área, a mexer os sliders depressa: o servidor não pode encravar.

## Quando algo encrava: como investigar

1. **Correr em Debug** (ícone do inseto) em vez de Run.
2. Quando parar, na vista **Debug**, clicar no processo › **Suspend**. Aparecem todas as threads e a linha onde cada uma está. Os nomes das threads (`dispatcher-LivingRoom`, ...) servem exatamente para isto.
3. Ver onde estão:
   - todas em `readObject` → provavelmente ninguém enviou nada (ou ordem dos streams errada);
   - uma em `wait` do `Topic` → fila vazia (normal) ou faltou um `notifyAll`;
   - em `latch.await` → falta algum ack;
   - várias `BLOCKED` à espera de um cadeado → alguém o segura demasiado tempo (I/O dentro de `synchronized`?) ou deadlock.
4. Alternativa fora do Eclipse: `jstack <pid>` (o `pid` vê-se com `jps`) imprime o mesmo e deteta deadlocks automaticamente.
5. Logs: cada `System.out` do servidor deve dizer quem (deviceId ou nome do tópico) e o quê.

## Antes de cada entrega

- [ ] `main` atualizada nos dois PCs, compila sem erros (Project › Clean).
- [ ] Todos os cenários da fase correspondente passaram.
- [ ] README atualizado (como correr, o que está feito, o que falta).
- [ ] Tag criada (`checkpoint-1`, `entrega-final`).

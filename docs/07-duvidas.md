# 07 · Dúvidas

Quatro listas: o que **já está respondido** (e o que mudámos por isso), o que **perguntar ao professor**, o que **temos de saber responder** e o que **vamos investigar**. Quando uma dúvida for respondida, escrever a resposta, a fonte e a data.

## A · Já respondidas

### Pela apresentação do projeto (23 out)

| Dúvida | Resposta | O que mudámos |
|---|---|---|
| Datas | intermédia 10–13 nov (sem entrega formal); final 15–18 dez com entrega no Moodle | plano |
| O servidor deve ter GUI? | "pode funcionar sem GUI; uma GUI minimal pode ajudar" | `ServerMonitorFrame` opcional |
| Um atuador controla vários dispositivos? | não: "dois clientes atuadores, um para cada lâmpada" | um dispositivo por programa |
| Como é o registo? | `RegistrationMessage` com "tipo de cliente e área da casa" | `RegistrationMessage` |
| Como são as mensagens? | `LightSensorMessage [light=320.0, area=Sala, getType()=SENSOR]`, `AreaLightingOrderMessage [state=true, ...]` | mensagens tipadas (D20) |
| Limiar do exemplo | acender abaixo de 500 lux | `LightingDecider` |

### Pelo código base da GUI (3 nov)

| Dúvida | Resposta (no código) | O que mudámos |
|---|---|---|
| Os componentes gráficos "[TBD]" vão ser dados? | sim: `iscot.client.gui` (ícones de luz, ventilador, AC, rega; `SensorControl`) | removemos as nossas janelas de sensor, decisor e atuador (D23) |
| Nomes das áreas | `iscot.enums.Areas`: `Kitchen, WC, LivingRoom, BedRoom, Garden` | removemos a nossa `Area` |
| O "tipo de cliente" no registo é o papel ou o dispositivo? | o papel: `iscot.enums.Type` = `SENSOR, DECIDER, ACTUATOR` (+ `DWC`, "pode ser ignorado") | removemos o `DeviceType`; tópico por área (D24) |
| Um `main` para todos ou um por dispositivo? | um por dispositivo (`HumiditySensor`, `Ventilator`), com 3 argumentos: `endereço porto área` | um programa por dispositivo (D25) |
| O que é a fase 1? | "create an appropriate event hardcoded locally and change the GUI accordingly" | atuadores com ordem criada localmente para o dia 6 |
| O sensor envia a intervalos ou quando muda? | o `SensorControl` avisa quando o utilizador larga o slider | o sensor envia no callback |
| Podemos usar `SwingUtilities.invokeLater`? | o próprio professor usa (`ComponentState.setAtivo`, `SensorControl.setValor`) | usamos no `ActionHistory` |
| Que modos tem o ar condicionado? | `AirConditioningMode`: `Ventilação, Frio, Aquecimento, Desligado` | `AreaClimateOrderMessage` com `mode` |

## B · Perguntar ao professor no dia 6

| # | Pergunta | Porque importa | Resposta |
|---|---|---|---|
| 1 | **Os tópicos são por área** (um tópico `LivingRoom` com leituras e ordens), ou por área e grandeza? Com o registo só com tipo e área, assumimos por área. | é a base da estrutura de tópicos (D24) | |
| 2 | A discussão intermédia continua a ser a 10–13 nov? Esperava a fase 3 nessa altura? | âmbito | |
| 3 | Quantas automações são obrigatórias? Basta a da luz, ou também rega e clima? Chuva/janelas não tem componente no código base: é para fazer? | não fazer o que não é pedido (D26) | |
| 4 | O que significa o tipo `DWC`? O servidor deve recusá-lo, como "tipo que não existe"? | "se o tipo não existir a ligação é recusada" | |
| 5 | O comando do enunciado é `java iscot {IP PORT Area Tipo}`, mas o código base usa 3 argumentos e um programa por dispositivo. Seguimos o código base? | argumentos | |
| 6 | A proibição dos mecanismos de coordenação do Java abrange `AtomicInteger`, `ReentrantLock` e `ConcurrentHashMap`? | foram dados nas aulas | |
| 7 | Uma mensagem num tópico sem subscritores é descartada ou espera? | `Topic` e dispatcher | |
| 8 | O envio para o cliente pode ser feito pela thread do tópico, ou tem de ser a thread dedicada do cliente? | decisão D5 | |
| 9 | Podemos acrescentar `getCount()` ao latch, ou fazer `await` devolver `boolean`? | saber se houve timeout | |
| 10 | O prazo do Moodle é 14 dez às 09:00? | não falhar o prazo | |

## C · Temos de saber responder (discussão e teste)

**Projeto**
1. Porque é que o tópico é a área? Que alternativa havia e porque não a escolhemos?
2. Porque é que uma lâmpada confirma mensagens que ignora (as leituras do sensor)?
3. No cenário do professor, que mensagens circulam desde que a luz baixa de 500 lux até as duas lâmpadas acenderem? Quantas confirmações espera cada latch?
4. Porque é que o código de ligação está na `BrokerConnection` e não em cada dispositivo?
5. O que é do professor e o que é nosso no projeto?

**Threads**
6. Diferença entre processo e thread; onde há cada um no IscoT.
7. Porquê `start()` e não `run()`?
8. Quantas threads tem o servidor no cenário da sala? Onde bloqueia cada uma?
9. Porque é que cada decisor e atuador tem uma thread recetora?

**Sincronização**
10. Que race condition existiria no `newDeviceId` sem `synchronized`?
11. Três secções críticas no projeto.
12. Porque é que o `ComponentState.setAtivo` do professor usa `invokeLater`? E o `ActionHistory`?
13. Porque se usa uma cópia da lista de subscritores?

**Coordenação**
14. Porquê `wait` dentro de `while`? Porquê `notifyAll`?
15. Onde está a coordenação: no `Topic` ou no `TopicDispatcher`?
16. O `await` do latch linha a linha; porquê recalcular o tempo que falta.
17. O que acontece se uma lâmpada nunca confirmar? E se a ligação cair?
18. Pode haver deadlock entre o `Topic` e o latch?

**Distribuída**
19. Socket e porto; porque é que o segundo servidor dá `BindException`.
20. Porque é que criar primeiro o `ObjectInputStream` nos dois lados bloqueia.
21. Serialização, `serialVersionUID`, `reset()`.

**Domínio**
22. O que é um message broker; diferenças entre o MQTT e o IscoT.
23. Head-of-line blocking no nosso desenho.

As respostas às perguntas mais prováveis estão em `08-apresentacao-checkpoint1.txt`.

## D · Investigar

- `wait(0)` espera para sempre: garantir que o latch nunca o chama.
- Duas threads a escrever no mesmo `ObjectOutputStream` sem `synchronized`: provocar num teste.
- Um decisor recebe as suas próprias ordens pelo tópico da área: confirmar que as ignora e confirma, sem criar ciclos.
- Como detetar um cliente que caiu sem fechar a ligação (*keep-alive*).

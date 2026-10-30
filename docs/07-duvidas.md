# 07 · Dúvidas

Três listas: o que **perguntar ao professor**, o que **temos de saber responder** (perguntas prováveis na discussão e no teste) e o que **vamos investigar**. Quando uma dúvida for respondida, escrever a resposta, a fonte e a data.

## A · Já respondidas pela apresentação do projeto (23 out)

| Dúvida | Resposta | O que mudámos |
|---|---|---|
| Quando é a entrega intermédia? | 10–13 nov nos laboratórios; sem entrega formal, "basta comparecer com o projeto desenvolvido" | plano e `08` com as datas novas |
| Quando é a entrega final? | entrega e discussões 15–18 dez; entrega formal no Moodle | plano (confirmar o prazo do Moodle: o enunciado diz 14.12 às 09:00) |
| Os componentes gráficos "[TBD]" vão ser fornecidos? | "há código base apenas para a GUI, ainda por disponibilizar" | janelas mínimas, a trocar pelo código base (D22) |
| O servidor deve ter GUI? | "pode funcionar sem GUI; uma GUI minimal pode ajudar", mostrando as mensagens trocadas | `ServerMonitorFrame` (opcional) |
| Um atuador controla vários dispositivos? | não: na sala, "dois clientes atuadores, um para cada lâmpada" | um dispositivo por cliente |
| Como se chama e o que leva o registo? | `RegistrationMessage`, com "os identificadores do tipo de cliente e área da casa" | `RegistrationMessage` |
| Como são as mensagens? | classes próprias com área e tipo, ex.: `LightSensorMessage [light=320.0, area=Sala, getType()=SENSOR]`, `AreaLightingOrderMessage [state=true, area=Sala, getType()=DECIDER]` | mensagens tipadas (D20); servidor escolhe o tópico (D21) |
| Nomes dos papéis? | `SENSOR`, `DECIDER` (e por analogia `ACTUATOR`) | `Role` em inglês (D22) |
| Limiar do exemplo de luz? | acender abaixo de 500 lux | decisor de luz com 500 lux |

## B · Perguntar ao professor (na entrega intermédia)

| # | Pergunta | Porque importa | Resposta |
|---|---|---|---|
| 1 | O prazo do Moodle é 14 dez às 09:00 (o enunciado diz 2025) e as discussões 15–18 dez? | não falhar o prazo | |
| 2 | Para a entrega intermédia esperava também a ligação ao servidor (fase 3), ou só as fases 1 e 2? | âmbito | |
| 3 | A proibição dos mecanismos de coordenação do Java cobre `AtomicInteger`, `ReentrantLock`, `ConcurrentHashMap`? Podemos usar `SwingUtilities.invokeLater`? | foram dados nas aulas | |
| 4 | O decisor consome leituras e publica ordens, em tópicos diferentes. Está de acordo com "um cliente publica e consome exclusivamente de um tópico"? | base da organização em tópicos | |
| 5 | No registo, o "tipo de cliente" é o papel (SENSOR/DECIDER/ACTUATOR) ou o tipo de dispositivo (LAMPADA, SENSOR_LUZ)? Nós enviamos o tipo de dispositivo, que inclui o papel. | formato da `RegistrationMessage` | |
| 6 | Quando sai o código base da GUI? Devemos usá-lo obrigatoriamente? | fase 1 | |
| 7 | Uma mensagem num tópico sem subscritores é descartada ou espera que alguém subscreva (como as *retained messages* do MQTT)? | `Topic` e dispatcher | |
| 8 | Há um valor sugerido para o timeout do latch? O que se faz depois do timeout? | comportamento em falha | |
| 9 | Podemos fazer `await` devolver `boolean` ou acrescentar `getCount()` ao latch? | saber se houve timeout | |
| 10 | O envio para o cliente pode ser feito pela thread do tópico (dispatcher), ou tem de ser a thread dedicada do cliente? | decisão D5 | |
| 11 | A classe principal do cliente tem de se chamar `iscot` (comando `java iscot {...}`)? | nome do main | |
| 12 | "O decisor deve esperar que todos os atuadores acendam a lâmpada" é obrigatório ou faz parte do extra das confirmações? | âmbito | |
| 13 | O sensor envia só quando o valor muda ("reage a alterações") ou também a intervalos? | `SensorClient` | |
| 14 | Java 8, como no projeto da semana 1, ou podemos usar 17/21? | sintaxe disponível | |
| 15 | O servidor deve recusar combinações sem sentido, como `sala REGA`, e mensagens de um papel errado (uma lâmpada a enviar ordens)? | validação | |

## C · Temos de saber responder (discussão e teste)

**Projeto**
1. Porque é que o cliente não escolhe o tópico? Quem o calcula e com que dados?
2. Porque é que há `SensorMessage` e `OrderMessage` abstratas, em vez de só as concretas?
3. No cenário do professor (1 sensor, 1 decisor, 2 lâmpadas), que mensagens circulam desde que a luz baixa de 500 lux até as duas lâmpadas acenderem?
4. Porque é que a `RegistrationMessage` leva a área e o tipo em texto?

**Threads**
5. Qual a diferença entre processo e thread? Onde há cada um no IscoT?
6. Porquê `start()` e não `run()`? O que acontecia ao servidor com `run()`?
7. Quantas threads tem o servidor com os 4 clientes do cenário da sala? Onde bloqueia cada uma?
8. `extends Thread` ou `Runnable`: porque escolheram um?
9. O que faz `interrupt()`? A thread pára logo?

**Sincronização**
10. Que race condition existiria no `newDeviceId` sem `synchronized`? Mostrar uma intercalação.
11. O que é uma secção crítica? Apontar três no projeto.
12. `synchronized` protege o método ou o objeto? Dois `Topic` diferentes bloqueiam-se um ao outro?
13. Porque não usaram `ReentrantLock` / `AtomicInteger`?
14. Porque se usa um snapshot da lista de subscritores?
15. A `ServerMonitorFrame` é chamada por muitas threads. Porque não precisa de `synchronized`?

**Coordenação**
16. Porquê `wait` dentro de `while` e não de `if`?
17. Porquê `notifyAll` e não `notify`, no vosso `Topic`?
18. Diferença entre `sleep` e `wait`.
19. Onde está a coordenação: no `Topic` ou no `TopicDispatcher`? Porquê?
20. Expliquem o `await` do latch linha a linha. Porque recalculam o tempo que falta?
21. O que acontece se uma lâmpada nunca enviar o ack? E se a ligação cair?
22. Pode haver deadlock entre o `Topic` e o latch? Porque não?

**Distribuída**
23. O que é um socket? E um porto? Porque é que o segundo servidor dá `BindException`?
24. Porque é que criar o `ObjectInputStream` primeiro nos dois lados bloqueia?
25. O que é serialização? Para que serve o `serialVersionUID`? E o `reset()`?
26. Porque estão as mensagens no pacote `common`?

**Domínio**
27. O que é um message broker? O que é MQTT e em que é diferente do IscoT?
28. O que é head-of-line blocking e porque acontece no vosso desenho?
29. O que mudariam para um subscritor lento não atrasar os outros?

As respostas às perguntas mais prováveis da entrega intermédia estão em `08-apresentacao-checkpoint1.txt`.

## D · Investigar (dúvidas nossas, como estudantes)

- Como se comporta `wait(ms)` com valores muito pequenos ou zero? (`wait(0)` espera para sempre!)
- O que acontece a um `ObjectOutputStream` se duas threads escreverem ao mesmo tempo sem `synchronized`? Provocar num teste.
- Faz sentido uma thread escritora por cliente (decisão D5-B)? Medir o efeito com um cliente artificialmente lento.
- Como detetar no servidor que um cliente caiu sem fechar a ligação? Pesquisar *keep-alive* / *heartbeat* (o MQTT tem `keepalive`).
- `volatile`: o que é e se precisamos dele para o limiar do decisor alterado pela GUI.
- Como é que o Mosquitto (broker MQTT real) organiza as threads? Comparar com o nosso desenho.
- Um slider gera dezenas de eventos por segundo: qual a melhor forma de o sensor não inundar o servidor?

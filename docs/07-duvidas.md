# 07 · Dúvidas

Três listas: o que **perguntar ao professor**, o que **temos de saber responder** (perguntas prováveis na oral e no teste) e o que **vamos investigar**. Quando uma dúvida for respondida, escrever a resposta e a data à frente.

## A · Perguntar ao professor

| # | Pergunta | Porque importa | Resposta |
|---|---|---|---|
| 1 | A entrega intercalar é na semana de 10–16 nov ou nesta sexta? A final é 14 dez **2026** às 09:00? | o enunciado tem as datas do ano passado | |
| 2 | "Aspetos ainda sem concorrência" são só as fases 1 e 2, ou também a ligação inicial (fase 3)? | define o que mostramos | |
| 3 | A proibição dos mecanismos da biblioteca cobre `AtomicInteger`, `ReentrantLock`, `ConcurrentHashMap`? | foram dados nas aulas | |
| 4 | O decisor consome um tópico (leituras) e publica noutro (ordens). Está de acordo com "um cliente publica e consome exclusivamente de um tópico"? | é a base da nossa convenção de tópicos | |
| 5 | Um evento num tópico sem subscritores é descartado ou fica à espera de alguém subscrever (como as *retained messages* do MQTT)? | muda o `Topic` e o dispatcher | |
| 6 | Há um valor sugerido para o timeout do latch? O que se faz depois do timeout: remover o evento, registar quem falhou, desligar o cliente? | comportamento em falha | |
| 7 | Podemos fazer `await` devolver `boolean` ou acrescentar `getCount()` à API? | saber se houve timeout | |
| 8 | O envio para o cliente pode ser feito por uma thread do tópico (dispatcher), ou tem de ser a thread dedicada do cliente? | decisão D5 | |
| 9 | Os componentes gráficos "[TBD]" vão ser fornecidos? Quando? | fase 1 | |
| 10 | A classe principal tem de se chamar `iscot` (comando `java iscot {...}`)? | nome do main | |
| 11 | "O decisor deve esperar que todos os atuadores acendam a lâmpada" é obrigatório ou faz parte do extra das confirmações? | âmbito | |
| 12 | Valores dos sensores: manuais na GUI, aleatórios, ou os dois? "Várias horas" é para simular com segundos? | GUI do sensor | |
| 13 | Java 8, como no projeto da semana 1, ou podemos usar 17/21? | sintaxe disponível | |
| 14 | Podemos suportar wildcards na subscrição (`+/luz/ordens`) como no MQTT? Conta como valorização? | melhoria opcional | |
| 15 | O histórico do atuador basta em memória e na GUI, ou deve ir para ficheiro? | secção 1.3 | |
| 16 | Podemos usar `SwingUtilities.invokeLater`? (é Swing, não coordenação) | atualizar a GUI a partir de outras threads | |
| 17 | Um atuador pode controlar vários dispositivos ("o(s) dispositivo(s)")? | modelo do `ActuatorClient` | |
| 18 | Faz sentido recusar combinações sem sentido, como `sala REGA`? | validação no registo | |

## B · Temos de saber responder (oral e teste)

**Threads**
1. Qual a diferença entre processo e thread? Onde há cada um no IscoT?
2. Porquê `start()` e não `run()`? O que acontecia ao servidor com `run()`?
3. Quantas threads tem o servidor com 3 clientes em 2 tópicos? Onde bloqueia cada uma?
4. `extends Thread` ou `Runnable`: porque escolheram um?
5. O que faz `interrupt()`? A thread pára logo?

**Sincronização**
6. Que race condition existiria no `newDeviceId` sem `synchronized`? Mostrar uma intercalação.
7. O que é uma secção crítica? Apontar três no projeto.
8. `synchronized` protege o método ou o objeto? Dois `Topic` diferentes bloqueiam-se um ao outro?
9. Porque não usaram `ReentrantLock` / `AtomicInteger`?
10. Porque se usa um snapshot da lista de subscritores?

**Coordenação**
11. Porquê `wait` dentro de `while` e não de `if`?
12. Porquê `notifyAll` e não `notify`, no vosso `Topic`?
13. Diferença entre `sleep` e `wait`.
14. Onde está a coordenação: no `Topic` ou no `TopicDispatcher`? Porquê?
15. Expliquem o `await` do latch linha a linha. Porque recalculam o tempo que falta?
16. O que acontece se um atuador nunca enviar o ack? E se a ligação cair?
17. Pode haver deadlock entre o `Topic` e o latch? Porque não?

**Distribuída**
18. O que é um socket? E um porto? Porque é que o segundo servidor dá `BindException`?
19. Porque é que criar o `ObjectInputStream` primeiro nos dois lados bloqueia?
20. O que é serialização? Para que serve o `serialVersionUID`? E o `reset()`?
21. Porque estão as mensagens no pacote `common`?

**Domínio**
22. O que é um message broker? O que é MQTT e em que é diferente do IscoT?
23. O que é head-of-line blocking e porque acontece no vosso desenho?
24. O que mudariam para um subscritor lento não atrasar os outros?

## C · Investigar (dúvidas nossas, como estudantes)

- Como se comporta `wait(ms)` com valores muito pequenos ou zero? (`wait(0)` espera para sempre!)
- O que acontece a um `ObjectOutputStream` se duas threads escreverem ao mesmo tempo sem `synchronized`? Provocar num teste.
- Faz sentido uma thread escritora por cliente (decisão D5-B)? Medir o efeito com um cliente artificialmente lento.
- Como detetar no servidor que um cliente caiu sem fechar a ligação (cabo desligado)? Pesquisar *keep-alive* / *heartbeat* (o MQTT tem `keepalive`).
- `volatile`: o que é e se precisamos dele para o limiar do decisor alterado pela GUI.
- Como é que o Mosquitto (broker MQTT real) organiza as threads? Comparar com o nosso desenho.
- Diferenças entre `LinkedList` e `ArrayDeque` em desempenho real com 10 000 eventos.

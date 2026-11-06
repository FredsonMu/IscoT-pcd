# 07 · Dúvidas

Quando uma dúvida for respondida, escrever a resposta, a fonte e a data.

## A · O que a versão 4 do enunciado já esclareceu

| Dúvida | Resposta | O que mudámos |
|---|---|---|
| O que entra na entrega intermédia? | Fases 1 a 3, sem concorrência nem distribuição | Saíram as classes das fases seguintes |
| Quando? | Turno de laboratório da semana de 10 a 16 nov; presencial, obrigatória e eliminatória | Plano |
| `Message` ou `Event`? | `Event`: "para evitar ambiguidades" (histórico de versões) | Toda a hierarquia |
| Que eventos existem? | 4 leituras, 4 ordens, `RegistrationEvent` | Saiu a nossa `AreaClimateOrderMessage`; entraram `AreaVentilationOrderEvent` e `AreaTemperatureOrderEvent` |
| Humidade: ar ou solo? | As duas: ar leva à ventilação, solo leva à rega | Novo `GroundMoistureSensor` |
| Áreas e tipos | Já resolvidos pelos enums `Areas` e `Type` do código base | Usamos os enums tal como estão |
| Um atuador recebe leituras? | Não: "os atuadores apenas consomem eventos Highlevel" | Desenho das fases seguintes a rever |

## B · Perguntar ao professor na apresentação

| # | Pergunta | Porque importa | Resposta |
|---|---|---|---|
| 1 | Um tópico é o par (tipo de evento, área)? Na mesma área, o ventilador também recebe as ordens de iluminação? | Base das fases 6 e 7 | |
| 2 | Seguimos o código base (um programa por dispositivo, 3 argumentos) em vez de `java iscot { IP PORT Area Tipo }`? | Argumentos e classes | |
| 3 | Há uma classe `HighLevelOrderEvent` com `getType()=DECIDER`, como nos slides. Está correto? | Formato | |
| 4 | Os formatos de texto das nossas outras classes (`temperature=`, `moisture=`, `mode=`) são aceitáveis? | Formato | |
| 5 | O sensor de chuva é para fazer? Não tem evento nem componente. | Âmbito | |
| 6 | Quantas automações são obrigatórias no fim? | Âmbito | |
| 7 | O que é o tipo `DWC`? O servidor deve recusá-lo? | Registo, fase 4 | |
| 8 | A entrega final é a 14.12.2026 às 09:00? O enunciado escreve 2025. | Não falhar o prazo | |
| 9 | `AtomicInteger`, `ReentrantLock` e `ConcurrentHashMap` contam como proibidos? | Fases 6 e 7 | |

## C · Temos de saber responder

**Enunciado e âmbito**
1. O que são as fases 1 a 3, e como as cumprimos?
2. Porque usam `Event` se os slides dizem `Message`?
3. Porque é que não há servidor nesta entrega?
4. O que é do professor e o que é nosso?

**Hierarquia**
5. Porquê classe abstrata? Porquê não interface?
6. Onde está definido o tipo de cada evento, e porquê lá?
7. O que é polimorfismo? Dá um exemplo no projeto.
8. Porque é que `RegistrationEvent` não herda de `SensorEvent`?
9. Porque é que os campos são `final`?
10. Para que serve `Serializable`, se ainda não há rede?
11. O que quer dizer "ordem de alto nível"? Porque é que não leva o id do dispositivo?

**GUI**
12. Quantas threads tem um atuador a correr, e quais são?
13. Porque é que o `Thread.sleep` do professor não congela a janela? O que aconteceria dentro de um listener?
14. Porque é que o `ActionHistory` usa `invokeLater`?
15. Em que thread corre o callback do `SensorControl`? Quando é que é chamado?
16. O que é uma expressão lambda, e o que é `DoubleConsumer`?
17. Como funciona a regra do ar condicionado em modo `Frio`?

As respostas às mais prováveis estão em [08-apresentacao-intermedia](08-apresentacao-intermedia.md).

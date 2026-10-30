# 06 · Plano, divisão e checkpoints

## Datas oficiais

Da apresentação do projeto pelo professor (23 out):

| Momento | Data | Como |
|---|---|---|
| **Entrega e discussão intermédia** | **10–13 de novembro**, no nosso turno de laboratório | "Não é necessária nenhuma entrega formal, basta comparecer com o projeto desenvolvido." Obrigatória e eliminatória (enunciado). |
| **Entrega final e discussões** | **15–18 de dezembro** | "Entrega formal no Moodle." O enunciado fala em 14.12 às 09:00 (com o ano errado, 2025): confirmar o prazo exato do Moodle. |

Confirmar em que dia de 10–13 é o nosso turno.

## Duas apresentações

| Data | O que é | O que levar |
|---|---|---|
| **sex 6 nov** | **primeira apresentação do projeto ao professor** (na nossa aula) | fases 1 e 2 a correr (ver abaixo) |
| **10–13 nov** | **entrega e discussão intermédia oficial** (obrigatória e eliminatória) | fases 1 e 2 + fase 3 como objetivo nosso |

Se o professor tratar o dia 6 como a entrega oficial, o que está em "Dia 6" é o mínimo garantido para passar. Perguntar-lhe no dia 6 se a discussão de 10–13 se mantém.

## O que levar a 6 de novembro (fases 1 e 2, sem concorrência)

| O que mostrar | Classes que têm de estar escritas | Quem |
|---|---|---|
| Mensagens no formato do professor: `LightSensorMessage [light=320.0, area=Sala, getType()=SENSOR]` | `Area`, `DeviceType`, `Message`, `SensorMessage`, `LightSensorMessage`, `TemperatureSensorMessage`, `OrderMessage`, `AreaLightingOrderMessage` | Celso |
| Mensagens a entrar em filas separadas por tópico, por ordem (FIFO), com ids | `Topic`, `TopicManager` | Celso |
| Formulário: área + tipo de mensagem + valor → Publicar → ver as filas; "Consumir cabeça" tira a mais antiga | `TopicTestFrame` | Fredson |
| Janela de atuador com o estado e o histórico `Lâmpada ligada - HH:MM:SS` | `ActuatorFrame` + histórico no `ActuatorClient` | Fredson |
| Arquitetura planeada | diagrama do `docs/02-arquitetura.md` | os dois |

Não é preciso para dia 6: servidor, sockets, threads, latch, e as janelas de sensor e decisor (podem esperar pelo código base da GUI do professor).

### Calendário até dia 6

| Data | O quê | Pronto quando |
|---|---|---|
| sáb 31 out | **CP0 · arranque** | os dois clonaram o repositório; Celso começa `common` + `Topic`; Fredson começa o formulário e a janela do atuador |
| seg 2 nov | **CP1 · juntar** | `common` completo com o `toString` do professor; o formulário já publica no `Topic` real |
| qua 4 nov | **CP2 · tudo a correr** | demo completa; `IscotTests` grupo 1 a passar; cada um explica o código do outro |
| qui 5 nov | **CP3 · ensaio** | apresentação de 5 minutos cronometrada; perguntas cruzadas com o `08`; tag `checkpoint-0` |
| sex 6 nov | **apresentação** | demo + plano + perguntas ao professor (anotar as respostas no `07` e no `08`) |

## O que temos de levar a 10–13 de novembro

O enunciado pede as "fases iniciais, referentes a aspetos ainda sem concorrência":

| Fase | Obrigatório | O que mostrar |
|---|---|---|
| 1 · GUI | sim | formulário de teste + janelas de sensor, decisor e atuador (com histórico) |
| 2 · Estrutura de tópicos | sim | mensagens tipadas do professor a entrar em filas separadas por tópico, FIFO, com ids |
| 3 · Servidor e ligação inicial | **objetivo nosso** (temos uma semana a mais) | o cenário da sala do professor (1 sensor, 1 decisor, 2 lâmpadas) a registar-se no servidor, com ids únicos, tipo inválido recusado e a GUI do servidor a mostrar os clientes |
| 4–6 | não | explicar o plano |

Os detalhes da apresentação estão em `docs/08-apresentacao-checkpoint1.txt`.

## Princípio

Cada um **escreve** uma parte, mas os dois **explicam** tudo. Na discussão o professor pode perguntar qualquer linha a qualquer um. Em cada checkpoint, quem não escreveu um ficheiro explica-o em voz alta ao outro. Se não conseguir, ainda não está pronto.

## Quem faz o quê

| | Fredson | Celso |
|---|---|---|
| Fase 1 | `gui/*` (janelas mínimas; trocar pelo código base do professor quando sair), histórico do atuador | — |
| Fase 2 | `TopicTestFrame`, `IscotTests` (grupo 1) | `common/*`: `Area`, `DeviceType` (com `Role` SENSOR/DECIDER/ACTUATOR e tópicos), `Message`, `SensorMessage`, `LightSensorMessage`, `TemperatureSensorMessage`, `OrderMessage`, `AreaLightingOrderMessage`, `AreaClimateOrderMessage`; `Topic`, `TopicManager` |
| Fase 3 | `ClientMain`, `BrokerConnection`, `ServerMonitorFrame` | `Server`, `ClientHandler` (registo), `RegistrationMessage` |
| Fase 4 | `SensorClient`, `DecisorClient`, `ActuatorClient`, `invokeLater` | `AckMessage`, ciclo do `ClientHandler` (tópico calculado a partir da mensagem, validação do papel) |
| Fase 5 | `IscotTests` (grupo 2) | `TopicDispatcher`, `IscotTests` (grupo 3) |
| Fase 5 | **`ModifiedCountdownLatch`: os dois escrevem em separado e comparam** | |
| Fase 6 | dividir por automação (luz/janelas vs clima/rega) | |
| Sempre | revê o código do Celso | revê o código do Fredson |

Porquê esta divisão: cada um trabalha num pacote diferente (menos conflitos no Git), e a fronteira entre os dois é o pacote `common`, que o Celso deve fechar primeiro (até ao CP1), porque o formulário do Fredson depende das mensagens.

## Checkpoints até 10–13 de novembro

Até dia 6, seguir o calendário de cima. Depois:

| Data | Checkpoint | Pronto quando |
|---|---|---|
| sex 6 nov | **apresentação do dia 6** | fases 1 e 2 a correr (ver acima). **Já é o mínimo para passar a entrega intermédia.** |
| seg 9 nov | **CP4 · fase 3** | servidor aceita ligações; os 4 clientes do cenário do professor registam-se com ids únicos; `8 Tipo invalido` recusado; `ServerMonitorFrame` mostra os clientes |
| véspera do turno | **CP5 · ensaio** | apresentação cronometrada, **cada um apresenta a parte do outro**; perguntas cruzadas com o `08`; tag `checkpoint-1` |
| 10–13 nov | **Entrega intermédia** | demo + plano + perguntas ao professor (anotar respostas no `07` e no `08`) |

## Depois da entrega intermédia

| Semana | Objetivo |
|---|---|
| 16–22 nov | Fase 4: mensagens pela rede; clientes ligados às janelas com `invokeLater`; trocar pelas janelas do código base do professor |
| 23–29 nov | Fase 5: latch + dispatcher; testes de timeout, cliente caído e carga |
| 30 nov–6 dez | Fase 6 (extra) e mais automações (humidade/rega, chuva/janelas) |
| 7–13 dez | robustez, limpeza, README final, ensaio da discussão; entrega no Moodle no prazo confirmado · tag `entrega-final` |
| 15–18 dez | discussões finais |

## Ritual de cada checkpoint (30–45 min)

1. **O que cada um fez** (5 min cada), a correr no Eclipse.
2. **Troca de explicações**: escolher 2 classes do outro e explicá-las linha a linha.
3. **Dificuldades**: o que encravou, como se resolveu, o que ficou por perceber. Anotar.
4. **Dúvidas novas** para o professor → `07-duvidas.md`.
5. **Próximo passo** de cada um, com data.

## Registo dos checkpoints

Copiar este bloco para cada checkpoint:

```text
### CPx · dd/mm
Feito (Fredson):
Feito (Celso):
Explicações trocadas:
Dificuldades encontradas:
Decisões tomadas:
Dúvidas para o professor:
Próximo passo (quem / o quê / até quando):
```

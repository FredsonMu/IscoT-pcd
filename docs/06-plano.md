# 06 · Plano, divisão e checkpoints

As tarefas detalhadas (classe a classe, com branch, o que exige e quando está pronta) estão em **[09-tarefas-e-git.md](09-tarefas-e-git.md)**. Este documento dá a visão geral.

## Datas

| Momento | Data | O que levar |
|---|---|---|
| **Primeira apresentação do projeto** | **sex 6 nov**, na nossa aula | fases 1 e 2 (ver abaixo) |
| **Entrega e discussão intermédia oficial** | **10–13 nov**, no nosso turno de laboratório (confirmar o dia) | fases 1, 2 e, se possível, 3. "Não é necessária nenhuma entrega formal, basta comparecer com o projeto desenvolvido." Obrigatória e eliminatória. |
| **Entrega final e discussões** | **15–18 dez** | entrega formal no Moodle (confirmar o prazo; o enunciado fala em 14.12 às 09:00) |

Se o professor tratar o dia 6 como a entrega oficial, o que está em "Dia 6" é o mínimo garantido para passar. Perguntar-lhe no dia 6 se a discussão de 10–13 se mantém.

## Dia 6: o que levar (fases 1 e 2, sem concorrência)

O código base do professor diz, no `Ventilator`: *"for first phase: create an appropriate event hardcoded locally and change the GUI accordingly"*. Ou seja, a fase 1 é **usar os componentes dele e mudá-los com mensagens criadas localmente**, sem servidor.

| O que mostrar | Classes | Quem |
|---|---|---|
| Lâmpada da LivingRoom a acender e apagar com uma `AreaLightingOrderMessage` criada localmente, com histórico `Lâmpada ligada - HH:MM:SS` | `Light`, `ActionHistory` | Fredson |
| Sensor de luz: mexer o slider do `SensorControl` imprime `LightSensorMessage [light=320.0, area=LivingRoom, getType()=SENSOR]` | `LightSensor` | Fredson |
| Mensagens no formato do professor | `Message`, `SensorMessage`, `OrderMessage`, `AckMessage`, `LightSensorMessage`, `AreaLightingOrderMessage` | Celso |
| Estrutura de tópicos: um tópico por área, fila FIFO, ids; consumir a cabeça tira a mais antiga | `Topic`, `TopicManager`, `IscotTests` grupo 1 | Celso (classes), Fredson (testes) |
| Arquitetura planeada | diagrama de `docs/02-arquitetura.md` | os dois |

**Não é preciso no dia 6:** servidor, sockets, threads nossas, latch, decisores, outras automações, GUI do servidor, formulário.

### Até dia 6

| Data | Celso | Fredson | Pronto quando |
|---|---|---|---|
| ter 3 nov (hoje) | mensagens base e de luz (C1, C2), com merge hoje | `ActionHistory` (F1) | o Fredson já tem as mensagens na `main` |
| qua 4 nov | `Topic`, `TopicManager` (C3, C4) | `Light`, `LightSensor` fase 1 (F2, F3) | lâmpada e sensor a correr |
| qui 5 nov | outras mensagens (C5) se der | testes do grupo 1 (F5); `Ventilator` fase 1 (F4) se der | tudo na `main`; cada um explica o código do outro; ensaio à noite; tag `checkpoint-0` |
| sex 6 nov | apresentação | apresentação | perguntas ao professor anotadas no `07` e no `08` |

## Até 10–13 de novembro (fase 3)

| Data | Celso | Fredson | Pronto quando |
|---|---|---|---|
| sáb 7 – seg 9 nov | `RegistrationMessage`, `Server`, `ClientHandler` registo (C6, C7) | `BrokerConnection` ligar e registar (F6) | o cenário do professor (sensor, decisor, 2 lâmpadas) regista-se com 4 ids diferentes |
| véspera do turno | ensaio | ensaio | cada um apresenta a parte do outro; tag `checkpoint-1` |

## Depois (até à entrega final)

| Semana | Objetivo |
|---|---|
| 16–22 nov | Fase 4: mensagens pela rede; cenário da luz completo (C8, F7) |
| 23–29 nov | Fase 5: latch (os dois, A1), dispatcher (C10), testes (C11, F9) |
| 30 nov–6 dez | rega (C9) e clima (F8); extra (A2) |
| 7–13 dez | opcionais se houver tempo (A3, A4); robustez; README final; ensaio |
| até ao prazo do Moodle | entrega final · tag `entrega-final` |
| 15–18 dez | discussões |

## Princípio

Cada um **escreve** uma parte, mas os dois **explicam** tudo. Em cada pull request e em cada checkpoint, quem não escreveu uma classe tem de a conseguir explicar.

## Ritual de cada checkpoint (30–45 min)

1. **O que cada um fez**, a correr no Eclipse.
2. **Troca de explicações**: 2 classes do outro, linha a linha.
3. **Dificuldades** e como se resolveram.
4. **Dúvidas novas** para o professor → `07-duvidas.md`.
5. **Próximo passo** de cada um, com data.

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

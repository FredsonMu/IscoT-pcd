# 06 · Plano, divisão e checkpoints

## Princípio

Cada um **escreve** uma parte, mas os dois **explicam** tudo. Na discussão final o professor pode perguntar qualquer linha a qualquer um. Em cada checkpoint, quem não escreveu um ficheiro explica-o em voz alta ao outro. Se não conseguir, ainda não está pronto.

## Quem faz o quê

| | Fredson | Celso |
|---|---|---|
| Fase 1 | `gui/*`, `ActionHistory` | — |
| Fase 2 | `TopicTestFrame`, `TopicStructureTest` | `common/*` (enums, `Topics`, `Event`), `Topic`, `TopicManager` |
| Fase 3 | `ClientMain`, `BrokerConnection` | `Server`, `ClientHandler` (registo), `RegisterRequest/Reply` |
| Fase 4 | `SensorClient`, `DecisorClient`, `ActuatorClient`, `invokeLater` | `Message`, `AckMessage`, ciclo do `ClientHandler` |
| Fase 5 | `LatchTest` | `TopicDispatcher`, `ConcurrencyStressTest` |
| Fase 5 | **`ModifiedCountdownLatch`: os dois escrevem em separado e comparam** | |
| Fase 6 | dividir por automação (luz/janelas vs rega/clima) | |
| Sempre | revê o código do Celso | revê o código do Fredson |

Porquê esta divisão: cada um trabalha num pacote diferente (menos conflitos no Git), e a fronteira entre os dois é o pacote `common`, que é pequeno e fica definido cedo.

## Até à apresentação intercalar

> O enunciado diz que a entrega intercalar é no laboratório da semana de 10 a 16 de novembro. Planeamos para **sexta, 6 de novembro**, para ter margem. Confirmar a data com o professor.

| Data | Checkpoint | Pronto quando |
|---|---|---|
| sáb 31 out | **CP0 · arranque** (30 min) | os dois leram `docs/01` a `03`; os dois clonaram o repositório e o projeto abre sem erros no Eclipse |
| seg 2 nov | **CP1 · peças separadas** | Fredson: as 3 janelas desenhadas e o histórico a acrescentar linhas com hora. Celso: enums, `Topics`, `Event`, `Topic` com publish/waitForHead/removeHead. Troca de explicações. |
| qua 4 nov | **CP2 · integração fases 1–2** | `TopicTestFrame` usa o `Topic` real; `TopicStructureTest` passa; README atualizado. Bónus: servidor aceita ligações e regista clientes. |
| qui 5 nov | **CP3 · ensaio** | apresentação cronometrada (5–10 min), **cada um apresenta a parte do outro**; perguntas cruzadas com `01-fundamentos.md`; lista de dúvidas fechada |
| sex 6 nov | **Apresentação** | demo + arquitetura planeada + perguntas ao professor; tag `checkpoint-1` |

## Guião da apresentação intercalar (≈ 8 min)

1. **O problema** (1 min): sensores → decisores → atuadores através de um broker com tópicos.
2. **A arquitetura planeada** (2 min): diagrama de `02-arquitetura.md`, pacotes, mapa de threads.
3. **Demo** (3 min): formulário de teste (fila FIFO por tópico) e as janelas de sensor, decisor e atuador com histórico.
4. **Próximos passos e decisões** (1 min): dispatcher por tópico + latch com timeout; porquê.
5. **Perguntas ao professor** (1 min): as mais importantes de `07-duvidas.md`.

## Depois da apresentação (até à entrega final)

| Semana | Objetivo |
|---|---|
| 9–15 nov | Fase 3: servidor, registo, ids únicos, recusa de tipos (acompanhar a aula de sockets) |
| 16–22 nov | Fase 4: eventos por canais de objetos; janelas ligadas à rede com `invokeLater` |
| 23–29 nov | Fase 5: latch + dispatcher; testes de timeout, cliente caído e carga |
| 30 nov–6 dez | Fase 6 (extra) e as 4 automações completas |
| 7–13 dez | robustez, limpeza, README final, ensaio da discussão |
| 14 dez, 09:00 | entrega no Moodle (o enunciado diz 2025: confirmar) · tag `entrega-final` |
| 15–18 dez | discussões nos laboratórios |

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

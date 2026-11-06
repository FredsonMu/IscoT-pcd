# 06 · Plano

As tarefas detalhadas estão em **[09-tarefas-e-git.md](09-tarefas-e-git.md)**. Este documento dá a visão geral.

## O que o enunciado exige nesta entrega

> "apresentação das fases iniciais (fases 1 a 3), referentes a funcionalidades ainda sem concorrência ou distribuição, no turno de laboratório da semana de 10 a 16 de novembro [...] Esta entrega é obrigatória e eliminatória, e feita presencialmente."

| Fase | Pedido | Como cumprimos | Classes |
|---|---|---|---|
| 1 | Conhecer os componentes gráficos | Uma janela para cada sensor e atuador | Componentes do professor; dispositivos |
| 2 | Hierarquia de eventos | `Event` e descendentes, `RegistrationEvent` | `iscot.common`, `IscotTests` |
| 3 | Eventos exemplo nos atuadores; eventos dos sensores na consola | Ordens criadas localmente mudam o ícone e o histórico; o slider gera um evento impresso | Dispositivos, `ActionHistory` |

**Não é preciso:** servidor, sockets, threads nossas, latch, tópicos, decisores.

## Calendário até à entrega

O dia exato depende do nosso turno. Trabalhamos para a pior hipótese, **ter 10 nov**. Se o turno for mais tarde, os dias a mais servem para ensaiar.

| Data | Celso | Fredson | Em conjunto |
|---|---|---|---|
| sex 6 nov | Ler as notas | Ler as notas | **Ponto 0:** preparar e combinar contratos |
| sáb 7 nov | C1, C2 → **Entrega A** | F1 | Revisões |
| dom 8 nov | C3 → **Entrega B**; C4 | F2, F3 → **Entrega C** | **Ponto 1:** primeira demo |
| seg 9 nov | C5, C6 | F4, F5, F6, F7 | **Ponto 2:** tudo a correr, ensaio, tag |
| Turno (10–16 nov) | Apresentação | Apresentação | Anotar as respostas em `07` |

O detalhe das etapas, das entregas e do que se faz em conjunto está em [09-tarefas-e-git](09-tarefas-e-git.md).

### Se o tempo apertar

Fazer por esta ordem. O bloco 1 é o mínimo para a apresentação.

1. **Essencial:** todos os eventos (C1 a C4), `ActionHistory`, `Light`, `LightSensor`, `Ventilator`.
2. **Importante:** `HumiditySensor`, `TemperatureSensor`, `IscotTests`.
3. **Completa o conjunto:** `AirConditioner`, `Irrigation`, `GroundMoistureSensor`.

## Depois da entrega intermédia

| Quando | Fase | Objetivo | Classes a acrescentar |
|---|---|---|---|
| 17–22 nov | 4 | Servidor; registo; identificador único; recusa de tipo inexistente | `Server`, `ClientHandler`, `BrokerConnection` |
| 17–29 nov | 5 | Troca de eventos por canais de objetos | ciclo do `ClientHandler` e da `BrokerConnection` |
| 23–29 nov | 6 | Um sensor, um decisor, um atuador; tópicos e filas | `Topic`, `TopicManager`, decisores |
| 30 nov–6 dez | 7 | Latch próprio; evento só sai quando todos confirmarem ou ao fim do tempo | `ModifiedCountdownLatch`, `TopicDispatcher`, confirmações |
| 7–13 dez | 8 e final | Extra `ReceptionConfirmationMessage`; GUI do servidor, se houver tempo; ensaio | `ReceptionConfirmationMessage`, `ServerMonitorFrame` |
| 14 dez, 09:00 | Entrega | Moodle (confirmar o prazo) | tag `entrega-final` |
| 15–18 dez | Discussão | | |

### Recuperar os esboços anteriores

Os esboços dessas classes, com as notas, estão no histórico do Git, no commit `4150236`:

```bash
git restore --source=4150236 -- src/iscot/server/Topic.java
```

O mesmo comando funciona para qualquer ficheiro ou pasta. **Foram escritos antes da versão 4 do enunciado**, por isso têm de ser adaptados ao voltar:

- `Message` passa a `Event`, e o resto dos nomes segue esse padrão;
- um tópico deixa de ser só por área (ver [02-arquitetura](02-arquitetura.md));
- o decisor de clima divide-se em ventilação e temperatura;
- a rega reage à humidade do solo, e a ventilação à humidade do ar.

## Princípio

Cada um **escreve** uma parte, mas os dois **explicam** tudo. Em cada pull request e em cada ensaio, quem não escreveu uma classe tem de a conseguir explicar.

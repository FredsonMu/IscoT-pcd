# IscoT · message broker para domótica

Projeto de **Programação Concorrente e Distribuída** (ISCTE, 2026/27).
Grupo: **Fredson** e **Celso**.

Rede cliente-servidor com um servidor central, o **IscoT**. Sensores (clientes ativos) leem o ambiente e enviam leituras, como `LightSensorMessage`; decisores (clientes reativos) tomam decisões de alto nível e enviam ordens, como `AreaLightingOrderMessage`; atuadores (clientes passivos) cumprem as ordens no seu dispositivo e guardam um histórico. Cada cliente só conhece o servidor, que organiza as mensagens num tópico por área da casa e só avança em cada tópico quando todos os subscritores confirmaram a mensagem atual (ou passou um tempo limite).

A interface gráfica dos dispositivos usa o **código base fornecido pelo professor** (`iscot.enums`, `iscot.client.gui`, e os exemplos `HumiditySensor` e `Ventilator`).

## Datas

| Momento | Data |
|---|---|
| Primeira apresentação do projeto ao professor | **sex 6 nov 2026** (fases 1 e 2) |
| Entrega e discussão intermédia | **10–13 nov 2026**, no turno de laboratório (sem entrega formal: levar o projeto a correr) |
| Entrega final e discussões | **15–18 dez 2026** (entrega formal no Moodle; confirmar o prazo) |

## Estado

| Fase | Descrição | Estado |
|---|---|---|
| 1 | GUI (componentes do professor + ordens criadas localmente) | por fazer (dia 6) |
| 2 | Estrutura de tópicos | por fazer (dia 6) |
| 3 | Servidor e ligação inicial | por fazer (10–13 nov) |
| 4 | Troca de mensagens (canais de objetos) | por fazer |
| 5 | `ModifiedCountdownLatch` no consumo | por fazer |
| 6 | Extra: confirmações de execução | por fazer |

As nossas classes em `src/` são, por agora, **esqueletos documentados**: cada ficheiro explica o objetivo, o que implementar, com que classes se liga, as questões de concorrência e as dúvidas. **Quem faz cada classe e como: [docs/09-tarefas-e-git.md](docs/09-tarefas-e-git.md).**

## Requisitos

- **JDK 8 ou superior** (compila em modo Java 8; o código base do professor também).
- **Eclipse IDE for Java Developers** (traz Git/EGit e JUnit).
- **Git**. Sem bibliotecas externas.

## Abrir no Eclipse

1. **File › Import › Git › Projects from Git (with smart import) › Clone URI**
2. URI: `https://github.com/FredsonMu/IscoT-pcd.git` › branch `main` › Finish.
3. Se o Eclipse se queixar do JRE 1.8: botão direito no projeto › Properties › Java Build Path › Libraries › editar o JRE System Library e escolher um JDK instalado; em Java Compiler, manter *compliance* 1.8.

## Correr

As configurações estão em `launch/` e aparecem em **Run › Run Configurations**. Cada dispositivo é um programa com **3 argumentos: `endereço porto área`**, como no código base do professor. Áreas (enum `Areas` do professor): `Kitchen`, `WC`, `LivingRoom`, `BedRoom`, `Garden`.

| Configuração | Classe | Argumentos |
|---|---|---|
| 01 Servidor | `iscot.server.Server` | `8080` |
| 01b Servidor com monitor (opcional) | `iscot.server.Server` | `8080 gui` |
| 02 Sensor luz LivingRoom | `iscot.client.LightSensor` | `localhost 8080 LivingRoom` |
| 03 Decisor luz LivingRoom | `iscot.client.LightingDecider` | `localhost 8080 LivingRoom` |
| 04 Lampada LivingRoom | `iscot.client.Light` | `localhost 8080 LivingRoom` |
| 05 Sensor humidade Garden | `iscot.client.HumiditySensor` (professor) | `localhost 8080 Garden` |
| 06 Decisor rega Garden | `iscot.client.IrrigationDecider` | `localhost 8080 Garden` |
| 07 Rega Garden | `iscot.client.Irrigation` | `localhost 8080 Garden` |
| 08 Sensor temperatura LivingRoom | `iscot.client.TemperatureSensor` | `localhost 8080 LivingRoom` |
| 09 Decisor clima LivingRoom | `iscot.client.ClimateDecider` | `localhost 8080 LivingRoom` |
| 10 Ventilador LivingRoom | `iscot.client.Ventilator` (professor) | `localhost 8080 LivingRoom` |
| 11 Ar condicionado LivingRoom | `iscot.client.AirConditioner` | `localhost 8080 LivingRoom` |
| 12 Testes | `iscot.test.IscotTests` | — |
| 13 Formulario de teste (opcional) | `iscot.test.TopicTestFrame` | — |

**Cenário da apresentação do professor** (sala com 1 sensor de iluminação, 1 decisor que acende abaixo de 500 lux e 2 lâmpadas): `01`, depois `02`, `03`, e `04` lançado duas vezes.

## Estrutura

```text
IscoT-pcd/
├── src/iscot/
│   ├── enums/        [professor] Areas, Type, AirConditioningMode
│   ├── client/gui/   [professor] ícones (luz, ventilador, AC, rega) e SensorControl
│   ├── client/       dispositivos: um main cada (HumiditySensor e Ventilator são do
│   │                 professor); BrokerConnection; ActionHistory
│   ├── common/       as mensagens (RegistrationMessage, LightSensorMessage, ...)
│   ├── server/       o broker: Server, ClientHandler, TopicManager, Topic, TopicDispatcher
│   ├── server/gui/   GUI opcional do servidor
│   ├── sync/         o nosso ModifiedCountdownLatch
│   └── test/         IscotTests; formulário opcional
├── launch/           configurações de execução do Eclipse
└── docs/             documentação do grupo
```

## Documentação

| Documento | Conteúdo |
|---|---|
| [01-fundamentos](docs/01-fundamentos.md) | threads, sincronização, coordenação, sockets, serialização, Swing e termos do domínio |
| [02-arquitetura](docs/02-arquitetura.md) | tópico por área, o que é do professor e o que é nosso, dispositivos, mensagens, threads |
| [03-decisoes](docs/03-decisoes.md) | cada decisão com alternativas e trade-offs (D23–D27: código base do professor) |
| [04-git](docs/04-git.md) | conceitos de Git e comandos úteis |
| [05-testes](docs/05-testes.md) | o que testar em cada fase e como investigar quando encrava |
| [06-plano](docs/06-plano.md) | datas e calendário |
| [07-duvidas](docs/07-duvidas.md) | dúvidas respondidas, perguntas ao professor, perguntas que temos de saber responder |
| [08-apresentacao-checkpoint1](docs/08-apresentacao-checkpoint1.txt) | o que levar a 6 nov e a 10–13 nov, guião, perguntas prováveis com respostas |
| [09-tarefas-e-git](docs/09-tarefas-e-git.md) | **quem faz cada classe, o que exige, e o passo a passo branch → pull request → merge** |

## Regras a não esquecer

- **Todos os mecanismos de coordenação são feitos por nós**: nada de `java.util.concurrent`.
- Cada cliente tem **uma thread dedicada no servidor** e **só conhece o servidor**.
- Registo com **`RegistrationMessage`** (tipo de cliente e área).
- **Um tópico por área**; todos os subscritores confirmam todas as mensagens.
- A mensagem só sai da fila quando **todos confirmarem**, ou após um **tempo limite** (`ModifiedCountdownLatch`).
- **Sockets com canais de objetos**; cada dispositivo é uma aplicação independente.
- Histórico dos atuadores no formato `Lâmpada desligada - HH:MM:SS`.
- **Não alterar os ficheiros do professor**, exceto os `TODO` dos exemplos.

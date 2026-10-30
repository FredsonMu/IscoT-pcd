# IscoT · message broker para domótica

Projeto de **Programação Concorrente e Distribuída** (ISCTE, 2026/27).
Grupo: **Fredson** e **Celso**.

Rede cliente-servidor com um servidor central, o **IscoT**. Sensores (clientes ativos) leem o ambiente e enviam leituras, como `LightSensorMessage`; decisores (clientes reativos) tomam decisões de alto nível e enviam ordens, como `AreaLightingOrderMessage`; atuadores (clientes passivos) cumprem as ordens no seu dispositivo (lâmpada, ventilador, ar condicionado) e guardam um histórico. Cada cliente só conhece o servidor, que organiza as mensagens em tópicos e só avança em cada tópico quando todos os subscritores confirmaram a mensagem atual (ou passou um tempo limite).

## Datas

| Momento | Data |
|---|---|
| Primeira apresentação do projeto ao professor | **sex 6 nov 2026** (fases 1 e 2 a correr) |
| Entrega e discussão intermédia | **10–13 nov 2026**, no turno de laboratório (sem entrega formal: levar o projeto a correr) |
| Entrega final e discussões | **15–18 dez 2026** (entrega formal no Moodle; confirmar o prazo) |

O que levar à entrega intermédia: [docs/08-apresentacao-checkpoint1.txt](docs/08-apresentacao-checkpoint1.txt).

## Estado

| Fase | Descrição | Estado |
|---|---|---|
| 1 | GUI | por fazer (obrigatória na entrega intermédia) |
| 2 | Estrutura de tópicos | por fazer (obrigatória na entrega intermédia) |
| 3 | Servidor e ligação inicial | por fazer (objetivo para a entrega intermédia) |
| 4 | Troca de mensagens (canais de objetos) | por fazer |
| 5 | `ModifiedCountdownLatch` no consumo | por fazer |
| 6 | Extra: confirmações de execução | por fazer |

As classes em `src/` são, por agora, **esqueletos documentados**: cada ficheiro explica o objetivo, o que implementar, com que classes se liga, as questões de concorrência, alternativas e dúvidas. O código é escrito por nós, fase a fase.

## Requisitos

- **JDK 8 ou superior** (o projeto compila em modo Java 8, como o projeto de referência das aulas). Qualquer JDK recente serve se o Eclipse tiver um JRE 1.8 ou o *compliance* em 1.8.
- **Eclipse IDE for Java Developers** (traz Git/EGit e JUnit).
- **Git**.
- Não há bibliotecas externas: só Java SE (Swing, `java.net`, `java.io`). O professor vai disponibilizar código base para a GUI.

## Abrir no Eclipse

1. **File › Import › Git › Projects from Git (with smart import) › Clone URI**
2. URI: `https://github.com/FredsonMu/IscoT-pcd.git` › branch `main` › Finish.
3. O projeto `IscoT-pcd` aparece no Package Explorer. Se o Eclipse se queixar do JRE 1.8: botão direito no projeto › Properties › Java Build Path › Libraries › editar o JRE System Library e escolher um JDK instalado; em Java Compiler, manter *compliance* 1.8.

Detalhes e fluxo de trabalho com Git: [docs/04-git.md](docs/04-git.md).

## Correr

As configurações já estão em `launch/` e aparecem em **Run › Run Configurations**. Arrancar sempre o servidor primeiro.

| Configuração | Classe | Argumentos |
|---|---|---|
| 1 Servidor | `iscot.server.Server` | `8080` |
| 1b Servidor com monitor | `iscot.server.Server` | `8080 gui` (abre a GUI opcional do servidor) |
| 2 Lampada sala | `iscot.client.ClientMain` | `localhost 8080 sala LAMPADA` |
| 3 Decisor luz sala | `iscot.client.ClientMain` | `localhost 8080 sala DECISOR_LUZ` |
| 4 Sensor luz sala | `iscot.client.ClientMain` | `localhost 8080 sala SENSOR_LUZ` |
| 5–7 Rega do jardim | `iscot.client.ClientMain` | `localhost 8080 jardim REGA` / `DECISOR_REGA` / `SENSOR_HUMIDADE` |
| 8 Tipo invalido | `iscot.client.ClientMain` | `localhost 8080 sala TORRADEIRA` (deve ser recusado) |
| 9 Formulario de teste | `iscot.gui.TopicTestFrame` | — |
| 10 Sensor temperatura sala | `iscot.client.ClientMain` | `localhost 8080 sala SENSOR_TEMPERATURA` |
| 11 Decisor clima sala | `iscot.client.ClientMain` | `localhost 8080 sala DECISOR_CLIMA` |
| 12 Ventilador sala | `iscot.client.ClientMain` | `localhost 8080 sala VENTILADOR` |

Formato geral do cliente: `IP PORTO Area Tipo`. Áreas: `sala`, `quarto`, `cozinha`, `wc`, `jardim`.

**Cenário da apresentação do professor** (sala com 1 sensor de iluminação, 1 decisor que acende abaixo de 500 lux e 2 lâmpadas): `1b`, depois `4`, `3`, e `2` lançado duas vezes (candeeiro e teto).

## Estrutura

```text
IscoT-pcd/
├── src/iscot/
│   ├── common/   tipos e mensagens partilhados: Area, DeviceType, RegistrationMessage,
│   │             LightSensorMessage, AreaLightingOrderMessage, ...
│   ├── sync/     mecanismos de coordenação feitos por nós (latch)
│   ├── server/   o broker: Server, ClientHandler, TopicManager, Topic, TopicDispatcher
│   ├── client/   lógica dos clientes: ligação, sensor, decisor, atuador (com histórico)
│   ├── gui/      janelas Swing (sem rede), incluindo a GUI opcional do servidor
│   └── test/     testes que se correm como programas
├── launch/       configurações de execução do Eclipse
└── docs/         documentação do grupo
```

## Documentação

| Documento | Conteúdo |
|---|---|
| [01-fundamentos](docs/01-fundamentos.md) | threads, sincronização, coordenação, sockets, serialização, Swing e termos do domínio, do zero |
| [02-arquitetura](docs/02-arquitetura.md) | diagramas, mensagens, pacotes, ligações entre classes, mapa de threads, recursos partilhados, armadilhas |
| [03-decisoes](docs/03-decisoes.md) | cada decisão de desenho com alternativas e trade-offs |
| [04-git](docs/04-git.md) | como trabalhamos com Git e GitHub |
| [05-testes](docs/05-testes.md) | o que testar em cada fase e como investigar quando encrava |
| [06-plano](docs/06-plano.md) | datas, divisão do trabalho e checkpoints |
| [07-duvidas](docs/07-duvidas.md) | dúvidas já respondidas, perguntas ao professor, perguntas que temos de saber responder |
| [08-apresentacao-checkpoint1](docs/08-apresentacao-checkpoint1.txt) | o que levar a 10–13 nov, guião, perguntas prováveis com respostas, checklist |

## Regras do enunciado e da apresentação a não esquecer

- **Todos os mecanismos de coordenação são feitos por nós**: nada de `java.util.concurrent` (`CountDownLatch`, `BlockingQueue`, `Semaphore`, `ExecutorService`...).
- Cada cliente tem **uma thread dedicada no servidor** e **só conhece o servidor**.
- O registo faz-se com uma **`RegistrationMessage`** com o tipo de cliente e a área.
- A mensagem só sai da fila quando **todos os subscritores confirmarem**, ou após um **tempo limite**, usando o nosso `ModifiedCountdownLatch`.
- Comunicação por **sockets com canais de objetos**; cada cliente é uma aplicação independente.
- Atuadores mantêm histórico no formato `Lâmpada desligada - HH:MM:SS`.

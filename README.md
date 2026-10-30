# IscoT · message broker para domótica

Projeto de **Programação Concorrente e Distribuída** (ISCTE, 2026/27).
Grupo: **Fredson** e **Celso**.

Sensores (luz, temperatura, humidade, chuva) publicam leituras; decisores transformam leituras em ordens; atuadores (lâmpadas, ar condicionado, rega, janelas) executam as ordens e guardam um histórico. Tudo passa por um servidor central, o **IscoT**, que organiza as mensagens em tópicos e só avança em cada tópico quando todos os subscritores confirmaram o evento atual (ou passou um tempo limite).

## Estado

| Fase | Descrição | Estado |
|---|---|---|
| 1 | GUI | por fazer |
| 2 | Estrutura de tópicos | por fazer |
| 3 | Servidor e ligação inicial | por fazer |
| 4 | Troca de mensagens (canais de objetos) | por fazer |
| 5 | `ModifiedCountdownLatch` no consumo | por fazer |
| 6 | Extra: confirmações de execução | por fazer |

As classes em `src/` são, por agora, **esqueletos documentados**: cada ficheiro explica o objetivo, o que implementar, com que classes se liga, as questões de concorrência, alternativas e dúvidas. O código é escrito por nós, fase a fase.

## Requisitos

- **JDK 8 ou superior** (o projeto compila em modo Java 8, como o projeto de referência das aulas). Qualquer JDK recente serve se o Eclipse tiver um JRE 1.8 ou o *compliance* em 1.8.
- **Eclipse IDE for Java Developers** (traz Git/EGit e JUnit).
- **Git**.
- Não há bibliotecas externas: só Java SE (Swing, `java.net`, `java.io`).

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
| 2 Lampada sala | `iscot.client.ClientMain` | `localhost 8080 sala LAMPADA` |
| 3 Decisor luz sala | `iscot.client.ClientMain` | `localhost 8080 sala DECISOR_LUZ` |
| 4 Sensor luz sala | `iscot.client.ClientMain` | `localhost 8080 sala SENSOR_LUZ` |
| 5–7 Rega do jardim | `iscot.client.ClientMain` | `localhost 8080 jardim REGA` / `DECISOR_REGA` / `SENSOR_HUMIDADE` |
| 8 Tipo invalido | `iscot.client.ClientMain` | `localhost 8080 sala TORRADEIRA` (deve ser recusado) |
| 9 Formulario de teste | `iscot.gui.TopicTestFrame` | — |

Formato geral do cliente: `IP PORTO Area Tipo`. Áreas: `sala`, `quarto`, `cozinha`, `wc`, `jardim`.

## Estrutura

```text
IscoT-pcd/
├── src/iscot/
│   ├── common/   tipos e mensagens partilhados por servidor e clientes
│   ├── sync/     mecanismos de coordenação feitos por nós (latch)
│   ├── server/   o broker: Server, ClientHandler, TopicManager, Topic, TopicDispatcher
│   ├── client/   lógica dos clientes: ligação, sensor, decisor, atuador, histórico
│   ├── gui/      janelas Swing (sem rede)
│   └── test/     testes que se correm como programas
├── launch/       configurações de execução do Eclipse
└── docs/         documentação do grupo
```

## Documentação

| Documento | Conteúdo |
|---|---|
| [01-fundamentos](docs/01-fundamentos.md) | threads, sincronização, coordenação, sockets, serialização, Swing e termos do domínio, do zero |
| [02-arquitetura](docs/02-arquitetura.md) | diagramas, pacotes, ligações entre classes, mapa de threads, recursos partilhados, armadilhas |
| [03-decisoes](docs/03-decisoes.md) | cada decisão de desenho com alternativas e trade-offs |
| [04-git](docs/04-git.md) | como trabalhamos com Git e GitHub |
| [05-testes](docs/05-testes.md) | o que testar em cada fase e como investigar quando encrava |
| [06-plano](docs/06-plano.md) | divisão do trabalho, checkpoints e guião da apresentação |
| [07-duvidas](docs/07-duvidas.md) | perguntas ao professor, perguntas que temos de saber responder, temas a investigar |

## Regras do enunciado a não esquecer

- **Todos os mecanismos de coordenação são feitos por nós**: nada de `java.util.concurrent` (`CountDownLatch`, `BlockingQueue`, `Semaphore`, `ExecutorService`...).
- Cada cliente tem **uma thread dedicada no servidor**.
- O evento só sai da fila quando **todos os subscritores confirmarem**, ou após um **tempo limite**, usando o nosso `ModifiedCountdownLatch`.
- Comunicação por **canais de objetos**.
- Atuadores mantêm histórico no formato `Lâmpada desligada - HH:MM:SS`.

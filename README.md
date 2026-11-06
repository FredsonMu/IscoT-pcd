# IscoT · message broker para domótica

Projeto de **Programação Concorrente e Distribuída** (ISCTE, 2026/27).
Grupo: **Fredson** e **Celso**.

Sistema de gestão doméstica no modelo cliente-servidor. Sensores leem o ambiente, decisores emitem ordens de alto nível e atuadores controlam os dispositivos, todos ligados apenas a um servidor central, o **IscoT**.

Este repositório está alinhado com o **enunciado versão 4 (2 nov 2026)** e, por agora, contém apenas o necessário para a **entrega intermédia: fases 1 a 3, ainda sem concorrência nem distribuição**.

## Datas

| Momento | Data | Formato |
|---|---|---|
| **Entrega intermédia (fases 1 a 3)** | **turno de laboratório da semana de 10 a 16 nov 2026** | presencial, breve apresentação; **obrigatória e eliminatória** |
| Entrega final | 14 dez, 09:00 (o enunciado escreve 14.12.2025: confirmar) | Moodle |
| Discussões finais | 15 a 18 dez | laboratórios |

Prazo interno do grupo: **tudo na `main` até seg 9 nov**, para estarmos prontos mesmo que o turno seja logo a 10.

## Fases do enunciado

| Fase | Descrição | Estado |
|---|---|---|
| **1** | GUI: conhecer os componentes dados (sensores e atuadores) | por fazer |
| **2** | Hierarquia de eventos: `Event`, `SensorEvent`, `HighLevelOrderEvent`, `RegistrationEvent` e as concretas | por fazer |
| **3** | Simular os eventos na GUI: eventos exemplo nos atuadores; eventos dos sensores na consola | por fazer |
| 4 | Servidor e ligação inicial dos clientes | depois da entrega intermédia |
| 5 | Troca de eventos por canais de objetos | depois |
| 6 | Troca simples: um sensor, um decisor, um atuador | depois |
| 7 | `CountDownLatch` próprio na gestão das consultas | depois |
| 8 | Extra: confirmação de execução pelos atuadores | depois |

As nossas classes em `src/` são **esqueletos documentados**. Cada uma diz, no topo, quem a faz e em que ordem, e divide o trabalho em passos com a lógica sugerida, o porquê e a ligação às outras classes. **Por onde começar, o que entregar ao outro e o que fazer em conjunto: [docs/09-tarefas-e-git.md](docs/09-tarefas-e-git.md).**

## Requisitos

- **JDK 8 ou superior** (o projeto compila em modo Java 8).
- **Eclipse IDE for Java Developers**.
- **Git**. Sem bibliotecas externas.

## Abrir no Eclipse

1. **File › Import › Git › Projects from Git (with smart import) › Clone URI**
2. URI: `https://github.com/FredsonMu/IscoT-pcd.git` › branch `main` › Finish.
3. Se o Eclipse se queixar do JRE 1.8: botão direito no projeto › Properties › Java Build Path › Libraries › editar o JRE System Library e escolher um JDK instalado; em Java Compiler, manter *compliance* 1.8.

## Correr

As configurações estão em `launch/` e aparecem em **Run › Run Configurations**. Cada dispositivo é um programa com **3 argumentos: `endereço porto área`**, como no código base. Nesta entrega, endereço e porto ainda não são usados. Áreas (enum `Areas`): `Kitchen`, `WC`, `LivingRoom`, `BedRoom`, `Garden`.

| Configuração | Classe | Argumentos | Mostra |
|---|---|---|---|
| 01 Sensor luz LivingRoom | `iscot.client.LightSensor` | `localhost 8080 LivingRoom` | `LightSensorEvent` na consola |
| 02 Sensor temperatura LivingRoom | `iscot.client.TemperatureSensor` | `localhost 8080 LivingRoom` | `TemperatureSensorEvent` na consola |
| 03 Sensor humidade ar LivingRoom | `iscot.client.HumiditySensor` (professor) | `localhost 8080 LivingRoom` | `HumiditySensorEvent` na consola |
| 04 Sensor humidade solo Garden | `iscot.client.GroundMoistureSensor` | `localhost 8080 Garden` | `GroundMoistureSensorEvent` na consola |
| 05 Lampada LivingRoom | `iscot.client.Light` | `localhost 8080 LivingRoom` | ordens exemplo; ícone e histórico |
| 06 Ventilador LivingRoom | `iscot.client.Ventilator` (professor) | `localhost 8080 LivingRoom` | ordens exemplo; ícone e histórico |
| 07 Ar condicionado LivingRoom | `iscot.client.AirConditioner` | `localhost 8080 LivingRoom` | ordens exemplo; regra da ventilação |
| 08 Rega Garden | `iscot.client.Irrigation` | `localhost 8080 Garden` | ordens exemplo; ícone e histórico |
| 09 Testes | `iscot.test.IscotTests` | nenhum | hierarquia e formato dos eventos |

## Estrutura

```text
IscoT-pcd/
├── src/iscot/
│   ├── enums/        [professor] Areas, Type, AirConditioningMode
│   ├── client/gui/   [professor] ícones (luz, ventilador, AC, rega) e SensorControl
│   ├── client/       4 sensores e 4 atuadores (HumiditySensor e Ventilator são exemplos
│   │                 do professor) e o ActionHistory
│   ├── common/       a hierarquia de eventos
│   └── test/         IscotTests
├── launch/           configurações de execução do Eclipse
└── docs/             documentação do grupo
```

## Documentação

| Documento | Conteúdo |
|---|---|
| [01-fundamentos](docs/01-fundamentos.md) | conceitos da UC usados nas fases 1 a 3, e porquê |
| [02-arquitetura](docs/02-arquitetura.md) | o que existe agora, a hierarquia, os programas e o que vem depois |
| [03-decisoes](docs/03-decisoes.md) | decisões do grupo, com alternativas e trade-offs |
| [04-git](docs/04-git.md) | conceitos de Git e comandos úteis |
| [05-testes](docs/05-testes.md) | lista de verificação das fases 1 a 3 |
| [06-plano](docs/06-plano.md) | calendário até à entrega intermédia e até ao fim |
| [07-duvidas](docs/07-duvidas.md) | perguntas ao professor e perguntas que temos de saber responder |
| [08-apresentacao-intermedia](docs/08-apresentacao-intermedia.md) | guião da apresentação, perguntas prováveis, plano B |
| [09-tarefas-e-git](docs/09-tarefas-e-git.md) | **roteiro por etapas, entregas entre os dois, pontos conjuntos e passo a passo no GitHub** |

## Regras a não esquecer

- **Termo oficial: Evento.** Os slides usam `Message`, mas são anteriores às versões 2 e 3 do enunciado.
- **Sem servidor, sockets ou threads nossas nesta entrega**: as fases 1 a 3 são locais.
- **Formato do texto dos eventos**: `LightSensorEvent [light=320.0, area=LivingRoom, getType()=SENSOR]`.
- **Histórico dos atuadores**: `Lâmpada desligada - HH:MM:SS`.
- **Não alterar os ficheiros do professor**, exceto os `TODO` dos exemplos.
- **Mecanismos de coordenação feitos por nós**: quando chegarem, nada de equivalentes da biblioteca do Java.

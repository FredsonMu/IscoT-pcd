# 01 · Conceitos usados nas fases 1 a 3

O que precisamos de saber explicar nesta entrega: **que conceito usamos, porque é que serve aqui, onde aparece e o que pesquisar**. As opções de cada classe estão nas notas da própria classe.

## Programação orientada a objetos

| Conceito | Para que nos serve | Onde | Pesquisar |
|---|---|---|---|
| **Classe abstrata** | Junta o que é comum e impede objetos "genéricos" | `Event`, `SensorEvent`, `HighLevelOrderEvent` | abstract class vs interface |
| **Herança** | Cada evento concreto só declara o que tem de seu | toda a hierarquia | herança em vários níveis |
| **Polimorfismo** | Tratar qualquer evento como `Event` sem saber a classe concreta | `IscotTests`; o servidor, mais tarde | ligação dinâmica; `@Override` |
| **Template Method** (opção) | A classe-mãe monta o texto e as filhas dão só os campos | `Event` | padrão Template Method |
| **Imutabilidade** | Um evento é um facto: não muda depois de criado | campos dos eventos | `final`; objetos imutáveis |
| **Enums** | Limitam os valores possíveis de área, tipo e modo | `Areas`, `Type`, `AirConditioningMode` | `valueOf` e a exceção que lança |
| **Separação de responsabilidades** | "De onde vem a ordem" fica separado de "o que faço com ela"; na fase 5 só muda a origem | atuadores | coesão e acoplamento |

## Interface gráfica (aula de Swing)

| Conceito | Para que nos serve | Onde | Pesquisar |
|---|---|---|---|
| **Componentes e layouts** | Montar cada janela | todos os dispositivos | `BorderLayout`, `JScrollPane` |
| **Modelo/vista** | A lista de ações separada de como é desenhada | `ActionHistory` | `JList`, `DefaultListModel` |
| **Eventos e callbacks** | O `SensorControl` avisa quando o valor muda | sensores | padrão Observer; `DoubleConsumer`; lambda |
| **Event Dispatch Thread** | Só a thread do Swing deve mexer nos componentes; nunca a adormecer | todas as janelas | *Concurrency in Swing*; `invokeLater` |

**Mesmo sem concorrência nossa, já há duas threads:** a `main` e a thread do Swing. Isso explica duas coisas no código do professor: o `Thread.sleep` na `main` não congela a janela, e o `ComponentState.setAtivo` usa `invokeLater`. Consequência: logo depois de um `setLigada`, o ícone ainda pode dizer o estado antigo.

## Preparar as fases seguintes, sem as implementar

| Conceito | Porquê já agora | Onde | Pesquisar |
|---|---|---|---|
| **Serialização** | A fase 5 envia eventos por canais de objetos; custa uma linha na classe de topo | `Event` | `Serializable`, `serialVersionUID`, `NotSerializableException` |
| **Thread safety de bibliotecas** | Evita escolher agora algo que falhe depois | `ActionHistory` | `DateTimeFormatter` vs `SimpleDateFormat` |
| **Desacoplamento (publish/subscribe)** | Explica porque é que uma ordem não leva id de dispositivo | `HighLevelOrderEvent` | message broker; MQTT |

## Vocabulário do enunciado

| Termo | Significado aqui |
|---|---|
| Evento | Unidade de informação do sistema; é o termo oficial desde a versão 2 |
| Sensor | Cliente ativo: lê o ambiente e publica eventos `Sensor` |
| Decisor | Cliente reativo: consome eventos `Sensor` e publica ordens de alto nível |
| Atuador | Cliente passivo: consome ordens de alto nível e controla um dispositivo |
| Ordem de alto nível | "A iluminação desta área deve estar ligada", sem referir nenhum dispositivo |
| Tópico | Agrupamento de eventos do mesmo tema (só existe a partir das fases seguintes) |
| Área | `Kitchen`, `WC`, `LivingRoom`, `BedRoom`, `Garden` |

## Fontes para começar

- Oracle Java Tutorials: *Inheritance*, *Abstract Methods and Classes*, *Enum Types*, *How to Use Lists*, *How to Use Sliders*, *Concurrency in Swing*, *Serializable Objects*.
- Slides da UC: aula de Swing.
- Código base do professor: `HumiditySensor`, `Ventilator`, `ComponentState`, `SensorControl`.

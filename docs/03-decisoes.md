# 03 · Decisões de desenho

Cada decisão indica **o problema, as opções, o que se ganha e perde, e o que escolhemos**. Só estão aqui as decisões já tomadas para as fases 1 a 3. As escolhas que cabem a quem implementa estão nas notas de cada classe, em "Opções para começar".

### D1 · Que fonte seguir quando os materiais divergem
| Fonte | Data | Diz |
|---|---|---|
| Slides | 23 out | `LightSensorMessage`, `RegistrationMessage`, discussão a 10–13 nov |
| Enunciado v4 | 2 nov | `LightSensorEvent`, `RegistrationEvent`, turno da semana de 10–16 nov, fases 1 a 3 |

**Escolha:** o enunciado v4, por ser mais recente e por o seu histórico de versões registar a mudança para "Evento". Do slide guardamos o formato do texto, a que só muda o nome da classe.

### D2 · Quanto do sistema fica já no projeto
| | só as fases 1 a 3 (escolhida) | esqueletos de tudo |
|---|---|---|
| Ganha | foco; o que se mostra é o que existe; menos classes a explicar | visão global no próprio código |
| Perde | as classes das fases seguintes têm de voltar | classes vazias sem função, e notas desatualizadas quando o enunciado muda |

### D3 · Hierarquia ou uma classe com um campo "tipo"
| | hierarquia (escolhida) | uma classe com um enum de tipo |
|---|---|---|
| Ganha | cada evento só tem os seus campos; o compilador apanha erros; é o que o enunciado descreve | menos ficheiros |
| Perde | mais classes | campos vazios; `switch` em todo o lado |

### D4 · Um programa por dispositivo
O enunciado escreve `java iscot { IP PORT AreaDispositivo TipoDispositivo }`, mas o código base tem um `main` por dispositivo com `endereço porto área`.

| | um `main` por dispositivo (escolhida) | um `main` com o tipo como argumento |
|---|---|---|
| Ganha | igual ao código base; cada dispositivo tem a sua janela legível | menos classes; igual ao comando do enunciado |
| Perde | mais classes pequenas | um `switch` grande; diferente do que o professor deu |

A confirmar com o professor.

### D5 · Que janelas fazemos
O enunciado pede "as várias janelas existentes" e a hierarquia tem 4 leituras e 4 ordens. **Escolha:** uma janela por leitura e atuadores para as 4 ordens, porque assim nenhum evento fica sem uso.

| Evento | Janela |
|---|---|
| `LightSensorEvent` | `LightSensor` |
| `TemperatureSensorEvent` | `TemperatureSensor` |
| `HumiditySensorEvent` | `HumiditySensor` (professor) |
| `GroundMoistureSensorEvent` | `GroundMoistureSensor` |
| `AreaLightingOrderEvent` | `Light` |
| `AreaVentilationOrderEvent` | `Ventilator` (professor) e `AirConditioner` |
| `AreaTemperatureOrderEvent` | `AirConditioner` |
| `AreaIrrigationOrderEvent` | `Irrigation` |

Se o tempo apertar, a ordem de prioridade está em [06-plano](06-plano.md).

### D6 · Sensor de chuva
A fase 1 cita "chuva" entre os sensores, mas o enunciado não tem nenhum evento de chuva, e o código base não tem nenhum componente de janelas. **Escolha:** não fazer, salvo indicação do professor.

### D7 · Decisores fora desta entrega
A fase 3 só refere janelas de atuadores e de sensores, e os decisores não têm janela no código base. Decidir exige eventos a circular, que só existem a partir da fase 5. **Escolha:** entram com a fase 6.

### D8 · Usar o código base tal como está
Os ícones, o `SensorControl` e os enums não se alteram. Nos exemplos, só se mexe nos `TODO`, e nos da fase 3. **Ganha:** o professor reconhece o código e não há traduções entre enums nossos e dele. **Perde:** herdamos pormenores como as marcas do slider e a posição fixa das janelas.

### D9 · Histórico numa classe própria
Os 4 atuadores precisam do mesmo. **Ganha:** um só sítio para o formato e para a regra da thread do Swing. **Perde:** mais uma classe.

### D10 · Testes num `main`
| | `main` com OK/FALHOU (escolhida) | JUnit |
|---|---|---|
| Ganha | corre sem configurar nada; bom para mostrar na apresentação | relatórios e asserções prontas |
| Perde | contagem feita à mão | configurar o Build Path |

Revemos quando houver testes de concorrência.

### D11 · Java 8
O código base compila em Java 8. Usar uma versão mais recente daria `record` e outras facilidades, mas arrisca falhar na máquina de avaliação. **Escolha:** manter Java 8.

### D12 · Um projeto Eclipse
**Ganha:** zero configuração, e tudo corre do mesmo sítio. **Perde:** menos separação entre cliente e servidor; os pacotes compensam.

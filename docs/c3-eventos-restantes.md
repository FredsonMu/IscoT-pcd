# C3 · Eventos restantes

Esta tarefa completa a hierarquia de eventos da entrega intermédia. Cada evento é imutável: recebe os seus dados no construtor, guarda-os em campos `final` e só os expõe por getters. O texto comum é produzido por `Event.toString()`; cada classe concreta fornece apenas os seus campos próprios.

## Leituras de sensores

| Classe | O que representa | Como funciona |
|---|---|---|
| `TemperatureSensorEvent` | Temperatura em graus Celsius | Guarda `temperature`; o futuro sensor cria-o e o decisor pode consultar a leitura. |
| `HumiditySensorEvent` | Humidade do ar em percentagem | Guarda `humidity`; é diferente da humidade do solo. |
| `GroundMoistureSensorEvent` | Humidade do solo em percentagem | Guarda `moisture`; será a leitura usada pela automação de rega. |

As três estendem `SensorEvent`, por isso herdam a área, o formato do texto e `getType() = SENSOR`.

## Ordens de alto nível

| Classe | O que representa | Como funciona |
|---|---|---|
| `AreaVentilationOrderEvent` | Ligar ou parar ventilação | Guarda `state`: `true` ventila, `false` para. |
| `AreaTemperatureOrderEvent` | Aquecer, arrefecer ou desligar | Guarda um `AirConditioningMode`; recusa `Ventilação`, que tem uma ordem própria. |
| `AreaIrrigationOrderEvent` | Ligar ou parar a rega | Guarda `state`: `true` rega, `false` para. |

As três estendem `HighLevelOrderEvent`, por isso herdam a área, o formato do texto e `getType() = DECIDER`.

## Conceitos usados

- **Herança:** as classes reutilizam o comportamento comum de `SensorEvent` e `HighLevelOrderEvent`.
- **Polimorfismo:** no futuro, qualquer uma pode ser tratada como `Event` pelo código de comunicação.
- **Encapsulamento:** os campos são privados e são consultados através de getters.
- **Imutabilidade:** valores `final` não mudam após a criação do evento.
- **Enums:** `Areas` e `AirConditioningMode` limitam os valores a opções válidas.
- **Sobrescrita (`@Override`):** cada classe implementa `getEventFields()` para personalizar apenas a parte variável do texto.

Exemplo:

```text
TemperatureSensorEvent [temperature=27.0, area=LivingRoom, getType()=SENSOR]
AreaIrrigationOrderEvent [state=true, area=Garden, getType()=DECIDER]
```

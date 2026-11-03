package iscot.common;

/**
 * HumiditySensorMessage — leitura de humidade da terra.
 *
 * OBJETIVO
 *   Enunciado 1.2: "se o sensor de humidade avisar que a terra está seca, o agente
 *   decisor da rega define uma ordem de irrigação, e os atuadores ligam a irrigação do
 *   jardim até o nível desejado de humidade ser atingido". O código base do professor
 *   traz o HumiditySensor (SensorControl de 0 a 100 %, passo 1, inicial 50).
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que LightSensorMessage)
 *   - extends SensorMessage.
 *   - Campo final double humidity (%). Construtor (area, humidity), getHumidity().
 *   - toString(): HumiditySensorMessage [humidity=35.0, area=Garden, getType()=SENSOR]
 *
 * LIGAÇÕES
 *   Criada por: HumiditySensor (código base). Consumida por: IrrigationDecider.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class HumiditySensorMessage {
}

package iscot.common;

/**
 * TemperatureSensorMessage — leitura de temperatura.
 *
 * OBJETIVO
 *   Enunciado 1.2: "se o termómetro deteta a ultrapassagem de dada temperatura,
 *   espoleta a ligação de um ar condicionado". A apresentação mostra um sensor de
 *   temperatura e o código base traz VentilatorIcon e ACIcon.
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que LightSensorMessage)
 *   - extends SensorMessage.
 *   - Campo final double temperature (ºC). Construtor (area, temperature).
 *   - toString(): TemperatureSensorMessage [temperature=27.5, area=LivingRoom,
 *     getType()=SENSOR]
 *
 * LIGAÇÕES
 *   Criada por: TemperatureSensor. Consumida por: ClimateDecider.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class TemperatureSensorMessage {
}

package iscot.common;

/**
 * TemperatureSensorMessage — leitura de temperatura.
 *
 * OBJETIVO
 *   A apresentação mostra a GUI de um "sensor de temperatura" ao lado do de iluminação,
 *   e o enunciado dá o exemplo "se o termómetro deteta a ultrapassagem de dada
 *   temperatura, espoleta a ligação de um ar condicionado".
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que LightSensorMessage)
 *   - extends SensorMessage.
 *   - Campo final double temperature (em ºC).
 *   - Construtor (area, temperature), getTemperature().
 *   - getSubject() -> "temperatura".
 *   - toString(): TemperatureSensorMessage [temperature=27.5, area=Sala, getType()=SENSOR]
 *
 * LIGAÇÕES
 *   Criada por: SensorClient (tipo SENSOR_TEMPERATURA). Tópico: <area>/temperatura/leituras.
 *   Consumida por: DecisorClient (tipo DECISOR_CLIMA).
 *
 * PORQUÊ JÁ ESTA E NÃO AS OUTRAS
 *   Luz e temperatura são as duas que o professor mostra na GUI; com elas a demo tem duas
 *   automações diferentes. Humidade e chuva seguem o mesmo padrão e ficam para depois.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class TemperatureSensorMessage {
}

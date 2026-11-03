package iscot.common;

/**
 * LightSensorMessage — leitura de luminosidade. Nome e formato dados pelo professor.
 *
 * EXEMPLO DA APRESENTAÇÃO (com as áreas do código base)
 *   LightSensorMessage [light=320.0, area=LivingRoom, getType()=SENSOR]
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends SensorMessage.
 *   - Campo final double light (lux). Construtor (area, light), getLight().
 *   - toString() exatamente no formato acima.
 *
 * QUANDO É ENVIADA
 *   "Reage a alterações na luminosidade": o LightSensor envia-a no callback do
 *   SensorControl, que só dispara quando o utilizador larga o slider.
 *
 * LIGAÇÕES
 *   Criada por: LightSensor. Consumida por: LightingDecider (acende abaixo de 500 lux).
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class LightSensorMessage {
}

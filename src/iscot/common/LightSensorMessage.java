package iscot.common;

/**
 * LightSensorMessage — leitura de luminosidade. Nome e formato dados pelo professor.
 *
 * EXEMPLO DA APRESENTAÇÃO
 *   LightSensorMessage [light=320.0, area=Sala, getType()=SENSOR]
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends SensorMessage.
 *   - Campo final double light (em lux).
 *   - Construtor (area, light), getLight().
 *   - getSubject() -> "luz".
 *   - toString() exatamente no formato acima (ajuda a comparar com o que o professor
 *     espera e aparece na GUI do servidor).
 *
 * QUANDO É ENVIADA
 *   Apresentação: o sensor "reage a alterações na luminosidade". Ou seja, o sensor envia
 *   quando o valor muda (o utilizador mexe no slider da GUI, ou a simulação automática
 *   muda o valor), não necessariamente a intervalos fixos.
 *
 * LIGAÇÕES
 *   Criada por: SensorClient (tipo SENSOR_LUZ). Tópico: <area>/luz/leituras.
 *   Consumida por: DecisorClient (tipo DECISOR_LUZ), que decide com o limiar de 500 lux.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class LightSensorMessage {
}

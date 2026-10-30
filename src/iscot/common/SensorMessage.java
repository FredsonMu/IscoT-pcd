package iscot.common;

/**
 * SensorMessage — classe-mãe (abstrata) das leituras enviadas pelos sensores.
 *
 * OBJETIVO NA APRESENTAÇÃO DO PROFESSOR
 *   "sensores: clientes ativos, que leem dados do ambiente: temperatura, humidade, chuva";
 *   o sensor de luminosidade "envia para tratamento no servidor mensagens da classe
 *   LightSensorMessage", com getType()=SENSOR.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message, abstrata.
 *   - getType() devolve sempre Role.SENSOR (final: as subclasses não o mudam).
 *   - abstract String getSubject(): a grandeza ("luz", "temperatura", ...). É com isto
 *     e com a área que o servidor sabe em que tópico publicar:
 *         area + "/" + getSubject() + "/leituras"      ex.: sala/luz/leituras
 *     (o cálculo do nome fica em DeviceType/Topics, num único sítio)
 *
 * PORQUÊ UMA CLASSE-MÃE PARA OS SENSORES
 *   O servidor trata todas as leituras da mesma forma: publica-as no tópico de leituras
 *   da sua área e grandeza. Com a classe-mãe faz-se um único "instanceof SensorMessage"
 *   no ClientHandler, em vez de um por cada tipo de sensor; acrescentar um sensor novo
 *   não obriga a mexer no servidor.
 *
 * LIGAÇÕES
 *   Subclasses: LightSensorMessage, TemperatureSensorMessage (e mais tarde humidade,
 *   chuva). Criadas por: SensorClient. Publicadas por: ClientHandler -> Topic.
 *   Consumidas por: DecisorClient.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public abstract class SensorMessage {
}

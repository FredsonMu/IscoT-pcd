package iscot.common;

/**
 * SensorMessage — classe-mãe (abstrata) das leituras enviadas pelos sensores.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C1 · feature/mensagens-base
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        SIM (fase 2). Merge HOJE, ter 3 nov.
 *   No dia 6:     Classe-mãe da LightSensorMessage que o LightSensor imprime no dia 6.
 *   Depende de:   Message.
 *   Liga-se a:    Estendida por LightSensorMessage, HumiditySensorMessage, TemperatureSensorMessage.
 *   Código base:  getType() devolve Type.SENSOR (enum do professor).
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Apresentação: "sensores: clientes ativos, que leem dados do ambiente"; o sensor de
 *   luminosidade "envia para tratamento no servidor mensagens da classe
 *   LightSensorMessage", com getType()=SENSOR.
 *   Código base: o SensorControl chama um callback com o novo valor quando o
 *   utilizador larga o slider ("TODO handle change in sensor"); é aí que se cria e
 *   envia a SensorMessage.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message, abstrata.
 *   - getType() devolve sempre Type.SENSOR (final).
 *   - Construtor (area).
 *
 * PORQUÊ UMA CLASSE-MÃE
 *   O servidor trata todas as leituras da mesma forma (só aceita SensorMessage de
 *   clientes SENSOR e publica-as no tópico da área). Um "instanceof SensorMessage" chega
 *   para todas; cada decisor reage só às suas subclasses e ignora (mas confirma) as
 *   outras.
 *
 * LIGAÇÕES
 *   Subclasses: LightSensorMessage, HumiditySensorMessage, TemperatureSensorMessage.
 *   Criadas por: LightSensor, HumiditySensor, TemperatureSensor.
 *   Consumidas por: LightingDecider, IrrigationDecider, ClimateDecider.
 *
 */
public abstract class SensorMessage {
}

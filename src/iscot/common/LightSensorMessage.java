package iscot.common;

/**
 * LightSensorMessage — leitura de luminosidade. Nome e formato dados pelo professor.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C2 · feature/mensagens-luz
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        SIM (fase 2). Merge HOJE, ter 3 nov.
 *   No dia 6:     O LightSensor cria-a e imprime-a: LightSensorMessage [light=320.0, area=LivingRoom, getType()=SENSOR]. O IscotTests verifica este formato.
 *   Depende de:   C1 (SensorMessage).
 *   Liga-se a:    Criada pelo LightSensor; consumida pelo LightingDecider (fase 4).
 *   Código base:  Nome e formato do toString dados pelo professor na apresentação.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
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
 */
public class LightSensorMessage {
}

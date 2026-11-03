package iscot.common;

/**
 * HumiditySensorMessage — leitura de humidade da terra.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C5 · feature/mensagens-outras
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        OPCIONAL no dia 6 (só se sobrar tempo, qui 5 nov).
 *   No dia 6:     Nada obrigatório.
 *   Depende de:   C1.
 *   Liga-se a:    Criada pelo HumiditySensor; consumida pelo IrrigationDecider (fase 4).
 *   Código base:  Corresponde ao HumiditySensor do código base (0–100 %).
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
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
 */
public class HumiditySensorMessage {
}

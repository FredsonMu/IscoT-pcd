package iscot.common;

/**
 * AreaIrrigationOrderMessage — ordem para a rega de uma área.
 *
 * OBJETIVO
 *   Enunciado 1.2: o decisor da rega "define uma ordem de irrigação, e os atuadores
 *   ligam a irrigação do jardim até o nível desejado de humidade ser atingido".
 *   Código base: IrrigationIcon (setAtiva).
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que AreaLightingOrderMessage)
 *   - extends OrderMessage.
 *   - Campo final boolean state (true = regar, false = parar). Construtor (area, state).
 *   - toString(): AreaIrrigationOrderMessage [state=true, area=Garden, getType()=DECIDER]
 *
 * "ATÉ O NÍVEL DESEJADO"
 *   O decisor manda state=true quando a humidade desce abaixo do limiar e state=false
 *   quando volta a passar o nível desejado. O IrrigationIcon tem também zona e duração;
 *   não os usamos, porque o enunciado não os pede.
 *
 * LIGAÇÕES
 *   Criada por: IrrigationDecider. Consumida por: Irrigation.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class AreaIrrigationOrderMessage {
}

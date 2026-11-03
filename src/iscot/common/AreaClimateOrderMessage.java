package iscot.common;

/**
 * AreaClimateOrderMessage — ordem para a climatização de uma área (ventilador e ar
 * condicionado).
 *
 * OBJETIVO
 *   Enunciado 1.2: "se o termómetro deteta a ultrapassagem de dada temperatura,
 *   espoleta a ligação de um ar condicionado". Código base: VentilatorIcon (ligado/
 *   desligado) e ACIcon com o enum AirConditioningMode (Ventilação, Frio, Aquecimento,
 *   Desligado).
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends OrderMessage.
 *   - Campo final AirConditioningMode mode (enum do professor). Construtor (area, mode).
 *   - toString(): AreaClimateOrderMessage [mode=Frio, area=LivingRoom, getType()=DECIDER]
 *
 * COMO CADA ATUADOR A INTERPRETA (ordem de alto nível)
 *   AirConditioner -> ACIcon.setModo(mode)
 *   Ventilator     -> VentilatorIcon.setLigado(mode != Desligado)
 *   O decisor não precisa de saber que dispositivos existem.
 *
 * PORQUÊ "mode" E NÃO "state"
 *   O ACIcon do professor trabalha com modos, não com ligado/desligado. Usar o mesmo enum
 *   evita traduções. O ACIcon também aceita uma temperatura (16–30 ºC); só a usamos se
 *   o professor pedir.
 *
 * LIGAÇÕES
 *   Criada por: ClimateDecider. Consumida por: Ventilator, AirConditioner.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class AreaClimateOrderMessage {
}

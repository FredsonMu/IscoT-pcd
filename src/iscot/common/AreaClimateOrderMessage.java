package iscot.common;

/**
 * AreaClimateOrderMessage — ordem para a climatização de uma área (ventilador e ar
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C5 · feature/mensagens-outras
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        OPCIONAL no dia 6. Necessária se o Fredson fizer a fase 1 do Ventilator (F4).
 *   No dia 6:     Se houver tempo: o Ventilator cria-a localmente e liga o VentilatorIcon.
 *   Depende de:   C1.
 *   Liga-se a:    Criada pelo ClimateDecider; consumida pelo Ventilator e AirConditioner.
 *   Código base:  Usa o enum AirConditioningMode do professor.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
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
 */
public class AreaClimateOrderMessage {
}

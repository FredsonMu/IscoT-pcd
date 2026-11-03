package iscot.common;

/**
 * TemperatureSensorMessage — leitura de temperatura.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C5 · feature/mensagens-outras
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        OPCIONAL no dia 6.
 *   No dia 6:     Nada obrigatório.
 *   Depende de:   C1.
 *   Liga-se a:    Criada pelo TemperatureSensor; consumida pelo ClimateDecider (fase 4).
 *   Código base:  —
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Enunciado 1.2: "se o termómetro deteta a ultrapassagem de dada temperatura,
 *   espoleta a ligação de um ar condicionado". A apresentação mostra um sensor de
 *   temperatura e o código base traz VentilatorIcon e ACIcon.
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que LightSensorMessage)
 *   - extends SensorMessage.
 *   - Campo final double temperature (ºC). Construtor (area, temperature).
 *   - toString(): TemperatureSensorMessage [temperature=27.5, area=LivingRoom,
 *     getType()=SENSOR]
 *
 * LIGAÇÕES
 *   Criada por: TemperatureSensor. Consumida por: ClimateDecider.
 *
 */
public class TemperatureSensorMessage {
}

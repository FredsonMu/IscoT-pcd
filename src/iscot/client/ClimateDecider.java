package iscot.client;

/**
 * ClimateDecider — programa do decisor de climatização.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       F8 · feature/clima
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        NÃO. Fase 4.
 *   No dia 6:     Nada.
 *   Depende de:   C5, F6.
 *   Liga-se a:    Recebe do TemperatureSensor; envia para Ventilator e AirConditioner.
 *   Código base:  Modos do AirConditioningMode do professor.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Enunciado 1.2: "se o termómetro deteta a ultrapassagem de dada temperatura,
 *   espoleta a ligação de um ar condicionado".
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que LightingDecider)
 *   - main(args): endereço, porto, área.
 *   - register(Type.DECIDER, area); startReceiving:
 *       TemperatureSensorMessage acima de 26 ºC -> AreaClimateOrderMessage(Frio)
 *       abaixo de 24 ºC                         -> AreaClimateOrderMessage(Desligado)
 *       (só envia quando muda; os modos são o enum AirConditioningMode do professor)
 *   - Sem janela: decisões na consola.
 *
 * LIGAÇÕES
 *   Recebe de: TemperatureSensor. Envia para: Ventilator e AirConditioner.
 *
 */
public class ClimateDecider {
}

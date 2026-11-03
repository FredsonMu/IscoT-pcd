package iscot.client;

/**
 * ClimateDecider — programa do decisor de climatização.
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
 * Prioridade 3 (automação de clima) · Responsável: Fredson · Revisor: Celso
 */
public class ClimateDecider {
}

package iscot.client;

/**
 * TemperatureSensor — programa do sensor de temperatura.
 *
 * OBJETIVO
 *   Enunciado 1.2: "se o termómetro deteta a ultrapassagem de dada temperatura,
 *   espoleta a ligação de um ar condicionado". A apresentação mostra um sensor de
 *   temperatura.
 *
 * SEGUIR O MOLDE DO PROFESSOR: HumiditySensor
 *   - main(args): endereço, porto, área.
 *   - SensorControl(ícone, "Temperatura - <área>", "ºC", 0, 40, 0.5, 22, callback).
 *   - Fase 1: o callback cria TemperatureSensorMessage(area, valor) e mostra-a.
 *   - Fase 3–4: register(Type.SENSOR, area) e send no callback.
 *
 * LIGAÇÕES
 *   Usa: SensorControl, BrokerConnection, TemperatureSensorMessage.
 *   Do outro lado: ClimateDecider.
 *
 * Prioridade 3 (automação de clima) · Responsável: Fredson · Revisor: Celso
 */
public class TemperatureSensor {
}

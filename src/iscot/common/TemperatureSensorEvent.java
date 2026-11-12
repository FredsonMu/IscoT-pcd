package iscot.common;

import iscot.enums.Areas;

/**
 * Leitura de temperatura, em graus Celsius.
 */
public class TemperatureSensorEvent extends SensorEvent {

    private final double temperature;

    /** Cria uma leitura de temperatura em graus Celsius. */
    public TemperatureSensorEvent(Areas area, double temperature) {
        super(area);
        this.temperature = temperature;
    }

    /** Devolve a temperatura medida. */
    public double getTemperature() {
        return temperature;
    }

    /** Fornece a parte variável do texto deste evento. */
    @Override
    protected String getEventFields() {
        return "temperature=" + temperature;
    }
}

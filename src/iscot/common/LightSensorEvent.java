package iscot.common;

import iscot.enums.Areas;

/**
 * Leitura de luminosidade, em lux.
 */
public class LightSensorEvent extends SensorEvent {

    private final double light;

    /** Cria uma leitura de luminosidade em lux. */
    public LightSensorEvent(Areas area, double light) {
        super(area);
        this.light = light;
    }

    /** Devolve a luminosidade medida em lux. */
    public double getLight() {
        return light;
    }

    /** Fornece a parte variável do texto deste evento. */
    @Override
    protected String getEventFields() {
        return "light=" + light;
    }
}

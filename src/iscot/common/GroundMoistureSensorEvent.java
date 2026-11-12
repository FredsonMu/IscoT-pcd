package iscot.common;

import iscot.enums.Areas;

/**
 * Leitura da humidade do solo, em percentagem.
 */
public class GroundMoistureSensorEvent extends SensorEvent {

    private final double moisture;

    /** Cria uma leitura de humidade do solo em percentagem. */
    public GroundMoistureSensorEvent(Areas area, double moisture) {
        super(area);
        this.moisture = moisture;
    }

    /** Devolve a humidade do solo medida. */
    public double getMoisture() {
        return moisture;
    }

    /** Fornece a parte variável do texto deste evento. */
    @Override
    protected String getEventFields() {
        return "moisture=" + moisture;
    }
}

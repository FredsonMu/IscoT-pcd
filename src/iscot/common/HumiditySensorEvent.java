package iscot.common;

import iscot.enums.Areas;

/**
 * Leitura da humidade do ar, em percentagem.
 */
public class HumiditySensorEvent extends SensorEvent {

    private final double humidity;

    /** Cria uma leitura de humidade do ar em percentagem. */
    public HumiditySensorEvent(Areas area, double humidity) {
        super(area);
        this.humidity = humidity;
    }

    /** Devolve a humidade do ar medida. */
    public double getHumidity() {
        return humidity;
    }

    /** Fornece a parte variável do texto deste evento. */
    @Override
    protected String getEventFields() {
        return "humidity=" + humidity;
    }
}

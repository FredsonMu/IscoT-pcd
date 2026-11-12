package iscot.common;

import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * Classe-mãe, abstrata, das leituras feitas pelos sensores. O tipo é sempre SENSOR.
 */
public abstract class SensorEvent extends Event {

    /** Cria uma leitura de sensor para a área indicada. */
    protected SensorEvent(Areas area) {
        super(area);
    }

    /** Identifica todas as leituras como eventos de sensor. */
    @Override
    public final Type getType() {
        return Type.SENSOR;
    }
}

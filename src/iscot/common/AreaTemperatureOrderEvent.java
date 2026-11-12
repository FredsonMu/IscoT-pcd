package iscot.common;

import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;

/**
 * Ordem de temperatura para uma área: Frio, Aquecimento ou Desligado.
 */
public class AreaTemperatureOrderEvent extends HighLevelOrderEvent {

    private final AirConditioningMode mode;

    /** Cria uma ordem para aquecer, arrefecer ou desligar. */
    public AreaTemperatureOrderEvent(Areas area, AirConditioningMode mode) {
        super(area);
        if (mode == null || mode == AirConditioningMode.Ventilação) {
            throw new IllegalArgumentException("O modo deve ser Frio, Aquecimento ou Desligado.");
        }
        this.mode = mode;
    }

    /** Devolve o modo pretendido para o ar condicionado. */
    public AirConditioningMode getMode() {
        return mode;
    }

    /** Fornece a parte variável do texto desta ordem. */
    @Override
    protected String getEventFields() {
        return "mode=" + mode;
    }
}

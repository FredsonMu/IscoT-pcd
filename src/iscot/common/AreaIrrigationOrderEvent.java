package iscot.common;

import iscot.enums.Areas;

/**
 * Ordem de rega para uma área: regar ou parar.
 */
public class AreaIrrigationOrderEvent extends HighLevelOrderEvent {

    private final boolean state;

    /** Cria uma ordem para ligar ou desligar a rega. */
    public AreaIrrigationOrderEvent(Areas area, boolean state) {
        super(area);
        this.state = state;
    }

    /** Devolve true para regar e false para parar. */
    public boolean getState() {
        return state;
    }

    /** Fornece a parte variável do texto desta ordem. */
    @Override
    protected String getEventFields() {
        return "state=" + state;
    }
}

package iscot.common;

import iscot.enums.Areas;

/**
 * Ordem de ventilação para uma área: ventilar ou parar.
 */
public class AreaVentilationOrderEvent extends HighLevelOrderEvent {

    private final boolean state;

    /** Cria uma ordem para ligar ou desligar a ventilação. */
    public AreaVentilationOrderEvent(Areas area, boolean state) {
        super(area);
        this.state = state;
    }

    /** Devolve true para ventilar e false para parar. */
    public boolean getState() {
        return state;
    }

    /** Fornece a parte variável do texto desta ordem. */
    @Override
    protected String getEventFields() {
        return "state=" + state;
    }
}

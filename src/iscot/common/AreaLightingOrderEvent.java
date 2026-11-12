package iscot.common;

import iscot.enums.Areas;

/**
 * Ordem de iluminação para uma área: ligar ou desligar.
 */
public class AreaLightingOrderEvent extends HighLevelOrderEvent {

    private final boolean state;

    /** Cria uma ordem para ligar ou desligar a iluminação. */
    public AreaLightingOrderEvent(Areas area, boolean state) {
        super(area);
        this.state = state;
    }

    /** Devolve true para ligar e false para desligar. */
    public boolean getState() {
        return state;
    }

    /** Fornece a parte variável do texto desta ordem. */
    @Override
    protected String getEventFields() {
        return "state=" + state;
    }
}

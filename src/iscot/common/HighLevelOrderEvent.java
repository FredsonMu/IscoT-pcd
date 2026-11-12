package iscot.common;

import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * Classe-mãe, abstrata, das ordens de alto nível dos decisores. O tipo é sempre DECIDER.
 * Uma ordem é para a área inteira: não identifica nenhum dispositivo.
 */
public abstract class HighLevelOrderEvent extends Event {

    /** Cria uma ordem destinada à área indicada. */
    protected HighLevelOrderEvent(Areas area) {
        super(area);
    }

    /** Identifica todas as ordens como eventos de decisor. */
    @Override
    public final Type getType() {
        return Type.DECIDER;
    }
}

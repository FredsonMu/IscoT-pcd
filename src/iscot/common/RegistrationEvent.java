package iscot.common;

import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * Pedido de registo de um cliente no servidor, com o tipo de cliente e a área.
 */
public class RegistrationEvent extends Event {

    private final Type type;

    /** Cria um pedido de registo para uma área e tipo de cliente. */
    public RegistrationEvent(Areas area, Type type) {
        super(area);
        if (type == null) {
            throw new IllegalArgumentException("O tipo não pode ser null.");
        }
        this.type = type;
    }

    /** Devolve o tipo do cliente que se pretende registar. */
    @Override
    public Type getType() {
        return type;
    }
}

package iscot.common;

import java.io.Serializable;

import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * Classe de topo, abstrata, de todos os eventos do sistema. Guarda a área e monta o texto
 * no formato NomeDaClasse [campos, area=..., getType()=...]; cada ramo diz o seu tipo.
 */
public abstract class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Areas area;

    /** Cria um evento associado a uma área. */
    protected Event(Areas area) {
        if (area == null) {
            throw new IllegalArgumentException("A área não pode ser null.");
        }
        this.area = area;
    }

    /** Devolve a área a que o evento pertence. */
    public Areas getArea() {
        return area;
    }

    /** Devolve o tipo de cliente que emite este evento. */
    public abstract Type getType();

    /**
     * Devolve apenas os campos próprios de cada evento concreto.
     * Exemplo: "light=320.0" ou "state=true".
     */
    protected String getEventFields() {
        return "";
    }

    /** Produz o texto comum usado na consola e nos testes. */
    @Override
    public final String toString() {
        String eventFields = getEventFields();
        String prefix = eventFields.isEmpty() ? "" : eventFields + ", ";

        return getClass().getSimpleName() + " [" + prefix + "area=" + area
                + ", getType()=" + getType() + "]";
    }
}

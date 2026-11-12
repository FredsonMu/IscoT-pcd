package iscot.client;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * Histórico das ações de um atuador, com a hora de cada uma (ex.: "Lâmpada ligada - 21:43:05").
 */
public final class ActionHistory extends JPanel {

    private static final long serialVersionUID = 1L;

    /** Formato da hora pedido no enunciado (1.3): HH:MM:SS. "mm" são minutos; "MM" seria o mês. */
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    /** Os dados: uma linha por ação, pela ordem em que aconteceram. */
    private final DefaultListModel<String> actions = new DefaultListModel<>();

    /** A vista dos dados; guardada para conseguir mostrar sempre a ação mais recente. */
    private final JList<String> list = new JList<>(actions);

    /** Cria um histórico vazio: cada arranque do atuador começa sem ações. */
    public ActionHistory() {
        super(new BorderLayout());
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    /**
     * Regista uma ação do atuador, com a hora em que aconteceu.
     * Exemplo: "Lâmpada ligada" fica "Lâmpada ligada - 21:43:05".
     * Pode ser chamado de qualquer thread: a lista só é alterada pela thread do Swing.
     */
    public void addAction(String description) {
        String line = description + " - " + LocalTime.now().format(TIME_FORMAT);
        SwingUtilities.invokeLater(() -> {
            actions.addElement(line);
            list.ensureIndexIsVisible(actions.getSize() - 1);
        });
    }
}

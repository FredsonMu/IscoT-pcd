package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.LightIcon;
import iscot.common.AreaLightingOrderEvent;
import iscot.enums.Areas;

/**
 * Atuador de uma lâmpada: aplica ordens de iluminação e regista cada ação no histórico.
 */
public class Light {

    /** A lâmpada do professor (começa apagada). */
    private final LightIcon icon = new LightIcon();

    /** O nosso histórico (F1). */
    private final ActionHistory history = new ActionHistory();

    /** O estado atual. Guardado aqui, e não lido do ícone: o setLigada só muda o ícone mais tarde. */
    private boolean ligada = false;

    /** Cria a lâmpada e mostra a sua janela: o ícone ao centro e o histórico em baixo. */
    public Light(Areas area) {
        JFrame janela = new JFrame("Lâmpada em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.add(icon, BorderLayout.CENTER);
        painel.add(history, BorderLayout.SOUTH);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.setMinimumSize(janela.getSize());
        janela.pack();
        janela.setVisible(true);
    }

    /**
     * Cumpre uma ordem de iluminação: muda o ícone e regista a ação no histórico.
     * Se a lâmpada já estiver no estado pedido, nada muda e não há ação a registar.
     * Fase 3: as ordens são criadas no main. Fase 6: chegam do decisor, pelo servidor.
     */
    public void aplicarOrdem(AreaLightingOrderEvent ordem) {
        if (ordem.getState() == ligada) {
            return;
        }
        ligada = ordem.getState();
        icon.setLigada(ligada);
        history.addAction(ligada ? "Lâmpada ligada" : "Lâmpada desligada");
    }

    /**
     * Argumentos, como no código base: endereço e porto do servidor, e a área.
     * Exemplo: localhost 8080 LivingRoom. Endereço e porto só são usados na fase 4.
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        } else {
            Areas area = Areas.valueOf(args[2]);
            Light lampada = new Light(area);

            // Fase 3: simula as ordens que, na fase 6, vão chegar do decisor de iluminação.
            // As pausas são na main e não na thread do Swing, por isso a janela não congela.
            boolean[] ordens = {true, false, true};
            for (boolean acender : ordens) {
                Thread.sleep(3000);
                lampada.aplicarOrdem(new AreaLightingOrderEvent(area, acender));
            }
        }
    }
}

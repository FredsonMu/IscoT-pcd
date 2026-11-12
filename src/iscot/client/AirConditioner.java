package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.ACIcon;
import iscot.common.AreaTemperatureOrderEvent;
import iscot.common.AreaVentilationOrderEvent;
import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;

/**
 * Atuador de ar condicionado: aplica ordens de temperatura e de ventilação. As de ventilação
 * são ignoradas em Frio ou Aquecimento (enunciado, 1.2).
 */
public class AirConditioner {

    /** O ar condicionado do professor (começa Desligado). */
    private final ACIcon ac = new ACIcon();

    /** O nosso histórico (F1). */
    private final ActionHistory history = new ActionHistory();

    /**
     * O modo atual. Ao contrário da Light, não basta ligado/desligado: a regra da ventilação
     * (enunciado 1.2) precisa de saber se está a aquecer ou a arrefecer.
     */
    private AirConditioningMode modo = AirConditioningMode.Desligado;

    /** Cria o ar condicionado e mostra a sua janela: o ícone ao centro e o histórico em baixo. */
    public AirConditioner(Areas area) {
        JFrame janela = new JFrame("Ar condicionado em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.add(ac, BorderLayout.CENTER);
        painel.add(history, BorderLayout.SOUTH);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.setMinimumSize(janela.getSize());
        janela.pack();
        janela.setSize(420, janela.getHeight());    // largura para caber "Ar condicionado em modo Ventilação - HH:MM:SS"
        janela.setVisible(true);
    }

    /**
     * Cumpre uma ordem de temperatura (Frio, Aquecimento ou Desligado). Obedece sempre:
     * a regra 1.2 só limita as ordens de ventilação.
     * Fase 3: as ordens são criadas no main. Fase 6: chegam do decisor de temperatura.
     */
    public void aplicarOrdem(AreaTemperatureOrderEvent ordem) {
        mudarModo(ordem.getMode());
    }

    /**
     * Cumpre uma ordem de ventilação, com a regra do enunciado (1.2): só passa ao modo Ventilação
     * "se não estiver já em algum dos modos aquecimento ou arrefecimento". Nesses modos a ordem
     * é ignorada, também a de parar: quem desliga o frio ou o aquecimento é a ordem de temperatura.
     * Fase 3: as ordens são criadas no main. Fase 6: chegam do decisor de ventilação.
     */
    public void aplicarOrdem(AreaVentilationOrderEvent ordem) {
        if (modo == AirConditioningMode.Frio || modo == AirConditioningMode.Aquecimento) {
            return;
        }
        mudarModo(ordem.getState() ? AirConditioningMode.Ventilação : AirConditioningMode.Desligado);
    }

    /**
     * Passa ao modo indicado: muda o ícone e regista a ação no histórico.
     * Se já estiver nesse modo, nada muda e não há ação a registar.
     */
    private void mudarModo(AirConditioningMode novoModo) {
        if (novoModo == modo) {
            return;
        }
        modo = novoModo;
        ac.setModo(modo);
        history.addAction(modo == AirConditioningMode.Desligado
                ? "Ar condicionado desligado"
                : "Ar condicionado em modo " + modo);
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
            AirConditioner arCondicionado = new AirConditioner(area);

            // Fase 3: simula as ordens que, na fase 6, vão chegar dos decisores de temperatura e
            // de ventilação. A sequência mostra a regra 1.2: em Frio, a ordem de ventilação é ignorada;
            // depois de desligado, a mesma ordem já é aplicada.
            // As pausas são na main e não na thread do Swing, por isso a janela não congela.
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaTemperatureOrderEvent(area, AirConditioningMode.Frio));
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaVentilationOrderEvent(area, true));    // ignorada: está em Frio
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaTemperatureOrderEvent(area, AirConditioningMode.Desligado));
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaVentilationOrderEvent(area, true));    // aplicada: passa a Ventilação
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaVentilationOrderEvent(area, false));   // pára: passa a Desligado
        }
    }
}

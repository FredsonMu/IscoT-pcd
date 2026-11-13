package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.IrrigationIcon;
import iscot.common.AreaIrrigationOrderEvent;
import iscot.enums.Areas;

/**
 * Irrigation — programa do atuador de rega.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C6 · feature/irrigation · CELSO, 15.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          1 (GUI) e 3 (eventos exemplo na janela)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    C3 (AreaIrrigationOrderEvent); ENTREGA C do Fredson (ActionHistory e Light).
 *   Molde:         Light; usa IrrigationIcon.
 * ==================================================================================
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Ler a Light do Fredson
 *     Porquê: é o modelo. Ao percebê-la, o Celso também fica preparado para a explicar na apresentação.
 *     Liga-se a: Light (F3).
 *
 *   Passo 2 · Montar a janela
 *     Lógica sugerida: a área no título; o IrrigationIcon no centro; o ActionHistory em baixo.
 *     Liga-se a: IrrigationIcon; ActionHistory; launch "08 Rega Garden".
 *
 *   Passo 3 · Escrever o que acontece ao receber UMA ordem
 *     Lógica sugerida: um método que recebe um AreaIrrigationOrderEvent, muda o ícone e regista "Rega
 *       ligada" ou "Rega desligada". Sem ação se o estado não mudar.
 *     Porquê: mesma separação da Light, para esta parte se manter na fase 5.
 *     Liga-se a: AreaIrrigationOrderEvent; Light, Passo 3.
 *
 *   Passo 4 · Gerar ordens de exemplo
 *     Lógica sugerida: usar a mesma técnica combinada no Ponto 0.
 *     Liga-se a: Light, Passo 4.
 *
 *   Passo 5 · Confirmar
 *     A rega liga e desliga sozinha, com a hora no histórico. Abrir o pull request C6.
 *
 * CONCEITOS DA UC A USAR
 *   Os mesmos da Light: Swing, thread do Swing, separar a origem da ordem do que se faz com ela.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Zona e duração do IrrigationIcon:
 *     A) Ignorar. O enunciado não as pede. [sugestão]
 *     B) Usar, se a ordem as trouxer. Ver as opções em AreaIrrigationOrderEvent.
 *
 * ARMADILHA
 *   Os nomes dos métodos variam entre ícones: setLigada, setLigado, setAtiva, setModo.
 */
public class Irrigation {

    /** O ícone do professor que representa o estado da rega. */
    private final IrrigationIcon icon = new IrrigationIcon();

    /** O histórico reutilizável das ações deste atuador. */
    private final ActionHistory history = new ActionHistory();

    /** Estado lógico da rega, separado do desenho do ícone. */
    private boolean ativa = false;

    /** Cria a janela da rega para a área indicada. */
    public Irrigation(Areas area) {
        JFrame janela = new JFrame("Rega em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(icon, BorderLayout.CENTER);
        painel.add(history, BorderLayout.SOUTH);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.setMinimumSize(janela.getSize());
        janela.pack();
        janela.setVisible(true);
    }

    /** Aplica uma ordem de rega e regista-a quando o estado muda. */
    public void aplicarOrdem(AreaIrrigationOrderEvent ordem) {
        if (ordem.getState() == ativa) {
            return;
        }

        ativa = ordem.getState();
        icon.setAtiva(ativa);
        history.addAction(ativa ? "Rega ligada" : "Rega desligada");
    }

    /** Cria ordens locais de exemplo para a demonstração da fase 3. */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        } else {
            Areas area = Areas.valueOf(args[2]);
            Irrigation irrigation = new Irrigation(area);

            boolean[] ordens = {true, false, true};
            for (boolean ligar : ordens) {
                Thread.sleep(3000);
                irrigation.aplicarOrdem(new AreaIrrigationOrderEvent(area, ligar));
            }
        }
    }
}

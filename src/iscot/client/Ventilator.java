package iscot.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.LightIcon;
import iscot.client.gui.VentilatorIcon;
import iscot.common.AreaVentilationOrderEvent;
import iscot.enums.Areas;
import iscot.enums.Type;

public class Ventilator {

	/** O ícone do professor (começa desligado). */
	private final VentilatorIcon ventilator = new VentilatorIcon();

	/** O nosso histórico (F1). */
	private final ActionHistory history = new ActionHistory();

	/** O estado atual. Guardado aqui, e não lido do ícone: o setLigado só muda o ícone mais tarde. */
	private boolean ligado = false;

	/** Cria o ventilador e mostra a janela do professor, com o histórico por baixo do ícone. */
	public Ventilator(Areas area) {
		JFrame janela = new JFrame("Ventilador em "+area);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Criar os componentes gráficos
		JPanel painel=new JPanel();
		painel.setLayout(new BorderLayout());
		painel.add(ventilator,BorderLayout.CENTER);
		painel.add(history, BorderLayout.SOUTH);
		janela.setContentPane(painel);
		janela.setLocation(600, 400);
		janela.setMinimumSize(janela.getSize());
		janela.pack();
		janela.setVisible(true);
	}

	/**
	 * Cumpre uma ordem de ventilação: muda o ícone e regista a ação no histórico.
	 * Se o ventilador já estiver no estado pedido, nada muda e não há ação a registar.
	 * Fase 3: as ordens são criadas no main. Fase 6: chegam do decisor, pelo servidor.
	 */
	public void aplicarOrdem(AreaVentilationOrderEvent ordem) {
		if (ordem.getState() == ligado) {
			return;
		}
		ligado = ordem.getState();
		ventilator.setLigado(ligado);
		history.addAction(ligado ? "Ventilador ligado" : "Ventilador desligado");
	}

	/**
	 *
	 * @param server address and port
	 * @param roomName: string
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws NumberFormatException
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		if(args.length!=3) {
			System.err.println("Invalid arguments!");
		}
		else {
			// TODO connect to server

			// TODO register with the server
			Areas area=Areas.valueOf(args[2]);

			Ventilator ventilador = new Ventilator(area);

			// TODO: receive and handle messages from server

			// TODO: for first phase: create an appropriate event hardcoded locally and change the GUI accordingly
			// Fase 3: simula as ordens que, na fase 6, vão chegar do decisor de ventilação.
			// As pausas são na main e não na thread do Swing, por isso a janela não congela.
			boolean[] ordens = {true, false, true};
			for (boolean ventilar : ordens) {
				Thread.sleep(3000);
				ventilador.aplicarOrdem(new AreaVentilationOrderEvent(area, ventilar));
			}
		}
	}
}

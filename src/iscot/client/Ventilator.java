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

/**
 * Ventilator — CÓDIGO BASE DO PROFESSOR (exemplo de atuador). Notas nossas:
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F4 · feature/ventilator · FREDSON, 4.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          1 (GUI) e 3 (eventos exemplo na janela)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    ENTREGA B do Celso (AreaVentilationOrderEvent); F1; F3 (Light, como guia).
 *   Regra:         ficheiro do professor: só se mexe nos TODO.
 * ==================================================================================
 *
 * O QUE O PROFESSOR JÁ DÁ
 *   - main com 3 argumentos: endereço, porto e área.
 *   - Janela com o VentilatorIcon, que liga ao fim de 5 s, só para mostrar o componente a mudar.
 *   - A main não é a thread do Swing: é por isso que o Thread.sleep não congela a janela.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Juntar o histórico à janela
 *     Lógica sugerida: igual à Light: ActionHistory em baixo, antes de mostrar a janela.
 *     Liga-se a: ActionHistory; Light, Passo 2.
 *
 *   Passo 2 · Escrever o que acontece ao receber UMA ordem
 *     Lógica sugerida: um método que recebe um AreaVentilationOrderEvent, muda o ícone e regista
 *       "Ventilador ligado" ou "Ventilador desligado". Sem ação se o estado não mudar.
 *     Porquê: mesma separação da Light, para esta parte se manter na fase 5.
 *     Liga-se a: AreaVentilationOrderEvent; Light, Passo 3.
 *
 *   Passo 3 · Tratar o "TODO: for first phase"
 *     Lógica sugerida: substituir a demonstração (sleep + setAtivo) por ordens de exemplo criadas
 *       localmente, com a mesma técnica da Light.
 *     Porquê: é o TODO do professor; o resultado passa a vir de um evento, não de uma chamada solta.
 *     Liga-se a: Light, Passo 4.
 *
 *   Passo 4 · Confirmar
 *     O ventilador liga e desliga sozinho, com a hora no histórico.
 *
 * O QUE AINDA NÃO SE FAZ
 *   Os TODO de ligar e registar são da fase 4; receber do servidor é da fase 5. Ficam como estão.
 *
 * CONCEITOS E OPÇÕES
 *   Os mesmos da Light. Usar a mesma opção para gerar a sequência nos dois programas.
 *
 * É o molde da Light, do AirConditioner e da Irrigation.
 */
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

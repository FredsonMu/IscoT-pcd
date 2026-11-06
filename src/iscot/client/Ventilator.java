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
			
			JFrame janela = new JFrame("Ventilador em "+area);
			janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
			
			// Criar os componentes gráficos
			JPanel painel=new JPanel();
			painel.setLayout(new BorderLayout());
			VentilatorIcon ventilator = new VentilatorIcon();
			painel.add(ventilator,BorderLayout.CENTER);
			janela.setContentPane(painel);
			janela.setLocation(600, 400);
			janela.setMinimumSize(janela.getSize());
			janela.pack();
			janela.setVisible(true);
			
			Thread.sleep(5000);
			ventilator.setAtivo(true);

			// TODO: receive and handle messages from server
			
			// TODO: for first phase: create an appropriate event hardcoded locally and change the GUI accordingly
		}
	}
}

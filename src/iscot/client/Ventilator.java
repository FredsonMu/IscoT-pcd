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
 *   Tarefa:       F4 (fase 1) e F8 (rede) · feature/ventilador-fase1, feature/clima
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        OPCIONAL no dia 6 (F4, só se sobrar tempo, qui 5 nov): a fase 1 que o professor pede no TODO.
 *   No dia 6:     Se houver tempo: cria AreaClimateOrderMessage localmente, liga o VentilatorIcon e escreve no histórico.
 *   Depende de:   C5 (AreaClimateOrderMessage), F1.
 *   Liga-se a:    Fase 4: recebe do ClimateDecider.
 *   Código base:  FICHEIRO DO PROFESSOR: só se mexe nos TODO.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * O QUE O PROFESSOR JÁ DÁ
 *   - main com 3 argumentos: endereço, porto, área (ex.: "localhost 8080 LivingRoom").
 *   - Janela com o VentilatorIcon; ao fim de 5 s liga-o (Thread.sleep + setAtivo),
 *     só para mostrar o componente a mudar.
 *   - setAtivo usa SwingUtilities.invokeLater (ComponentState): pode ser chamado de
 *     qualquer thread.
 *
 * O QUE NOS FALTA FAZER (os TODO do professor)
 *   - "for first phase: create an appropriate event hardcoded locally and change the
 *     GUI accordingly": É A FASE 1. Criar localmente
 *     new AreaClimateOrderMessage(area, AirConditioningMode.Ventilação), aplicá-la
 *     (ventilator.setLigado(true)) e escrever no histórico "Ventilador ligado - HH:MM:SS";
 *     depois Desligado. Juntar um ActionHistory à janela (BorderLayout.SOUTH).
 *   - "TODO connect to server" / "TODO register with the server":
 *       BrokerConnection + register(Type.ACTUATOR, area).                  [fase 3]
 *   - "TODO: receive and handle messages from server":
 *       startReceiving: se for AreaClimateOrderMessage,
 *       setLigado(mode != Desligado) e histórico.                           [fase 4]
 *   - Tirar o Thread.sleep(5000) de demonstração quando a fase 1 estiver feita.
 *
 * Esta é a classe-molde para Light, Irrigation e AirConditioner.
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

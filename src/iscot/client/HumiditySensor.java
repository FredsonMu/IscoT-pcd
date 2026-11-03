package iscot.client;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.LightIcon;
import iscot.client.gui.SensorControl;
import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * HumiditySensor — CÓDIGO BASE DO PROFESSOR (exemplo de sensor). Notas nossas:
 *
 * O QUE O PROFESSOR JÁ DÁ
 *   - main com 3 argumentos: endereço, porto, área (Areas.valueOf(args[2]),
 *     ex.: "localhost 8080 Garden"). Se não forem 3, escreve "Invalid arguments!".
 *   - Janela com um SensorControl de 0 a 100 %, passo 1, valor inicial 50.
 *   - O callback do SensorControl só é chamado quando o utilizador LARGA o slider.
 *
 * O QUE NOS FALTA FAZER (os TODO do professor)
 *   - "TODO connect to the server" / "TODO register with the server":
 *       BrokerConnection + register(Type.SENSOR, area).                   [fase 3]
 *   - "TODO handle change in sensor": no callback,
 *       fase 1: criar new HumiditySensorMessage(area, valor) e imprimi-la;
 *       fase 4: connection.send(new HumiditySensorMessage(area, valor)).
 *   - O ícone "☀" e o import de LightIcon não são usados para humidade: podemos trocar
 *     o ícone (ex.: "💧") ou deixar.
 *
 * Esta é a classe-molde para LightSensor e TemperatureSensor.
 * Prioridade 2 (automação de rega) · Responsável: Celso · Revisor: Fredson
 */
public class HumiditySensor {

	/**
	 * 
	 * @param server address and port
	 * @param roomName: string
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws NumberFormatException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		if(args.length!=3) {
			System.err.println("Invalid arguments!");
		}
		else {
			// TODO connect to the server

			// TODO register with the server
			Areas area=Areas.valueOf(args[2]);
			

			JFrame janela = new JFrame("Humidade em "+area);
			janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			
			// Criar os componentes gráficos
			JPanel painel=new JPanel();
			painel.setLayout(new BorderLayout());
			SensorControl sensorControl= new SensorControl( "☀", "Humidade - "+area, "%",
				    0, 100, 1, 50,
				    valor -> {
				    	// TODO handle change in sensor
				    	System.out.println("Mudou para:"+valor);
				    });
			painel.add(sensorControl,BorderLayout.CENTER);
			janela.setContentPane(painel);
			janela.setLocation(600, 400);
			janela.setMinimumSize(janela.getSize());
			janela.pack();
			janela.setVisible(true);
			
		}
	}

}

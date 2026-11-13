package iscot.client;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.LightIcon;
import iscot.client.gui.SensorControl;
import iscot.common.HumiditySensorEvent;
import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * HumiditySensor — CÓDIGO BASE DO PROFESSOR (exemplo de sensor). Notas nossas:
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C5 · feature/sensores-humidade · CELSO, 13.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          1 (GUI) e 3 (gerar eventos, a exibir na consola)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    C3 (HumiditySensorEvent). Não espera pelo Fredson.
 *   Regra:         ficheiro do professor: só se mexe nos TODO.
 * ==================================================================================
 *
 * O QUE O PROFESSOR JÁ DÁ
 *   - main com 3 argumentos: endereço, porto e área. Se não forem 3: "Invalid arguments!".
 *   - Janela com um SensorControl de 0 a 100 %, passo 1, valor inicial 50.
 *   - O callback do SensorControl, que hoje só escreve "Mudou para:" na consola.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Correr tal como está e perceber cada bloco
 *     Porquê: é o molde dos outros sensores. O Fredson faz o mesmo no LightSensor.
 *     Liga-se a: SensorControl; launch "03 Sensor humidade ar LivingRoom".
 *
 *   Passo 2 · Tratar o "TODO handle change in sensor"
 *     Lógica sugerida: no callback, em vez do texto atual, criar um HumiditySensorEvent com o valor e
 *       a área, e imprimi-lo.
 *     Porquê: é o pedido da fase 3. Na fase 5, a única diferença será enviar em vez de imprimir.
 *     Liga-se a: HumiditySensorEvent.
 *     Formato: HumiditySensorEvent [humidity=72.0, area=LivingRoom, getType()=SENSOR]
 *
 *   Passo 3 · Confirmar
 *     Cada largada do slider imprime uma linha. Opcional: trocar o ícone, que é um sol.
 *
 * O QUE AINDA NÃO SE FAZ
 *   "TODO connect to the server" e "TODO register with the server" são da fase 4. Ficam como estão.
 *
 * ESTE SENSOR MEDE O AR
 *   Enunciado v4: humidade do ar -> ventilação; humidade do solo -> rega. O solo fica no
 *   GroundMoistureSensor. Por isso a configuração de lançamento usa a LivingRoom.
 *
 * CONCEITOS DA UC
 *   Callback, expressão lambda, thread do Swing (ver as notas do LightSensor).
 *
 * É o molde do LightSensor, do TemperatureSensor e do GroundMoistureSensor.
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
			        // Fase 3: mostra na consola a leitura criada pelo sensor.
			        HumiditySensorEvent evento = new HumiditySensorEvent(area, valor);
			        System.out.println(evento);
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

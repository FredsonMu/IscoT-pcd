package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.SensorControl;
import iscot.common.TemperatureSensorEvent;
import iscot.enums.Areas;

/**
 * Sensor de temperatura: ao largar o slider, gera um TemperatureSensorEvent.
 */
public class TemperatureSensor {

    /**
     * Argumentos, como no código base: endereço e porto do servidor, e a área.
     * Exemplo: localhost 8080 LivingRoom. Endereço e porto só são usados na fase 4.
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        } else {
            Areas area = Areas.valueOf(args[2]);

            JFrame janela = new JFrame("Temperatura em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // 0 a 40 °C, passo 1 (as marcas do slider ficam 0, 10, 20, 30, 40), começa em 20.
            // Fase 3: cada leitura é mostrada na consola. Fase 5: passa a ser enviada ao servidor.
            SensorControl sensorControl = new SensorControl("🌡", "Temperatura - " + area, "°C",
                    0, 40, 1, 20,
                    valor -> {
                        TemperatureSensorEvent evento = new TemperatureSensorEvent(area, valor);
                        System.out.println(evento);
                    });

            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());
            painel.add(sensorControl, BorderLayout.CENTER);
            janela.setContentPane(painel);
            janela.setLocation(600, 400);
            janela.setMinimumSize(janela.getSize());
            janela.pack();
            janela.setVisible(true);
        }
    }
}

package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.SensorControl;
import iscot.common.TemperatureSensorEvent;
import iscot.enums.Areas;

/**
 * TemperatureSensor — programa do termómetro.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F5 · feature/temperature-sensor · FREDSON, 5.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          1 (GUI) e 3 (gerar eventos, a exibir na consola)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    ENTREGA B do Celso (TemperatureSensorEvent); F2 (LightSensor, como guia).
 *   Molde:         LightSensor.
 * ==================================================================================
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Partir do LightSensor
 *     Lógica sugerida: mesma estrutura; mudam o título, a unidade e o intervalo.
 *     Porquê: os slides mostram este sensor na figura de GUI, ao lado do de iluminação.
 *     Liga-se a: LightSensor; launch "02 Sensor temperatura LivingRoom".
 *
 *   Passo 2 · Escolher o intervalo
 *     Lógica sugerida: ver as opções.
 *     Liga-se a: SensorControl (marcas do slider).
 *
 *   Passo 3 · No callback, criar e mostrar o evento
 *     Lógica sugerida: criar um TemperatureSensorEvent e imprimi-lo.
 *     Liga-se a: TemperatureSensorEvent.
 *     Formato: TemperatureSensorEvent [temperature=27.0, area=LivingRoom, getType()=SENSOR]
 *
 *   Passo 4 · Confirmar
 *     Cada largada do slider imprime uma linha com o valor da etiqueta.
 *
 * CONCEITOS DA UC A USAR
 *   Os mesmos do LightSensor: callback, lambda, thread do Swing.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Intervalo do SensorControl:
 *     A) 0 a 40 com passo 1. As marcas ficam 0, 10, 20, 30, 40. [escolhida]
 *        (Com 0 a 50 as marcas ficariam 0, 12, 24, 36, 48: o slider marca de 1/4 em 1/4 dos passos.)
 *     B) Começar abaixo de zero (ex.: -10 a 50). Mais realista, mas as marcas mostram o número do
 *        passo e deixam de coincidir com os graus (a marca 0 representa -10).
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

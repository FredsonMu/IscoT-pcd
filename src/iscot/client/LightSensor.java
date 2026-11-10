package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.SensorControl;
import iscot.common.LightSensorEvent;
import iscot.enums.Areas;

/**
 * LightSensor — programa do sensor de luminosidade. É O MODELO DOS NOSSOS SENSORES.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F2 · feature/light-sensor · FREDSON, 2.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          1 (GUI) e 3 (gerar eventos, a exibir na consola)
 *   Etapa:         2 (dom 8 nov)
 *   Precisa de:    ENTREGA A do Celso (LightSensorEvent na main).
 *                  Os passos 1 a 3 podem fazer-se antes de ela chegar.
 *   Molde:         HumiditySensor do professor.
 *   Quem espera:   TemperatureSensor (F5).
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   Fase 3: "gerar eventos, a exibir na consola, a partir das janelas dos sensores".
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Ler e correr o molde
 *     Lógica sugerida: correr o HumiditySensor e perceber cada bloco antes de copiar.
 *     Porquê: temos de o saber explicar; é o código que o professor reconhece.
 *     Liga-se a: HumiditySensor; SensorControl.
 *
 *   Passo 2 · Validar os argumentos e obter a área
 *     Lógica sugerida: igual ao molde: 3 argumentos; o 3.º é convertido para Areas. Endereço e
 *       porto ainda não são usados, mas mantêm-se.
 *     Porquê: não mudam na fase 4, quando o programa se ligar ao servidor.
 *     Liga-se a: launch "01 Sensor luz LivingRoom"; área do evento.
 *
 *   Passo 3 · Montar a janela com o SensorControl
 *     Lógica sugerida: janela com a área no título e um SensorControl em lux, com o intervalo
 *       escolhido nas opções. De início, o callback pode só imprimir o valor.
 *     Porquê: permite ver a janela a funcionar antes de haver eventos.
 *     Liga-se a: SensorControl.
 *
 *   Passo 4 · No callback, criar e mostrar o evento
 *     Lógica sugerida: com o valor recebido e a área, criar um LightSensorEvent e imprimi-lo.
 *     Porquê: é o ponto central da fase 3. Na fase 5, a única diferença será enviar em vez de
 *       imprimir.
 *     Liga-se a: LightSensorEvent (Passo 3 dele, o contrato); Event (texto).
 *     Formato: LightSensorEvent [light=320.0, area=LivingRoom, getType()=SENSOR]
 *
 *   Passo 5 · Confirmar
 *     Cada largada do slider imprime uma linha, e o valor é igual ao da etiqueta.
 *
 * CONCEITOS DA UC A USAR
 *   - Eventos em Swing e callbacks: o SensorControl recebe "o que fazer" quando o valor muda
 *     (interface funcional DoubleConsumer, expressão lambda). É o padrão Observer.
 *   - EDT: esse callback corre na thread do Swing, por isso tem de ser rápido.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Parâmetros do SensorControl:
 *     A) 0 a 1000 com passo 1. As marcas do slider coincidem com os lux. [sugestão]
 *     B) 0 a 1000 com passo 10. Valores redondos, mas ver a armadilha abaixo.
 *   Código repetido entre os sensores (decidir no Ponto 0):
 *     A) Copiar o molde para cada sensor. Igual ao professor e fácil de ler. [sugestão]
 *     B) Uma classe comum com a janela. Menos repetição, mas mais uma abstração.
 *
 * ARMADILHAS
 *   - As marcas do slider mostram o NÚMERO DO PASSO, não o valor: com passo 10, a marca "100"
 *     representa 1000 lux. A etiqueta por baixo mostra o valor certo.
 *   - O callback corre quando o utilizador larga o slider; com as setas do teclado, a cada toque.
 *   - A leitura inicial não gera evento: só há evento quando o valor muda.
 *
 * NA APRESENTAÇÃO
 *   Lançar "01 Sensor luz LivingRoom", mexer no slider e mostrar a consola.
 */
public class LightSensor {

    /**
     * Argumentos, como no código base: endereço e porto do servidor, e a área.
     * Exemplo: localhost 8080 LivingRoom. Endereço e porto só são usados na fase 4.
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        } else {
            Areas area = Areas.valueOf(args[2]);

            JFrame janela = new JFrame("Luminosidade em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // 0 a 1000 lux, passo 1 (as marcas do slider coincidem com os lux), começa em 500.
            // Fase 3: cada leitura é mostrada na consola. Fase 5: passa a ser enviada ao servidor.
            SensorControl sensorControl = new SensorControl("☀", "Luminosidade - " + area, "lux",
                    0, 1000, 1, 500,
                    valor -> {
                        LightSensorEvent evento = new LightSensorEvent(area, valor);
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

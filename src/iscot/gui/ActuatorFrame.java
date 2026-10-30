package iscot.gui;

/**
 * ActuatorFrame — janela que simula um dispositivo (lâmpada, AC, rega, janela) e o seu
 * histórico.
 *
 * OBJETIVO NO ENUNCIADO
 *   Fase 1: componentes que simulam "os atuadores (luzes, ar condicionado, rega, etc)".
 *   Secção 1.3: histórico "verificável pelo utilizador".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Título com id e área.
 *   - Indicador de estado bem visível: JLabel grande com fundo de cor
 *     (amarelo = lâmpada ligada, cinzento = desligada; azul = rega ligada, ...).
 *   - JList com DefaultListModel para o histórico ("Lâmpada ligada - 21:43:05").
 *   - Métodos para o ActuatorClient mudar o estado e acrescentar linhas.
 *
 * THREADS (a armadilha principal desta classe)
 *   As ordens chegam na thread recetora, não na thread do Swing. O Swing NÃO é
 *   thread-safe: mexer num JLabel ou DefaultListModel a partir de outra thread pode
 *   funcionar 99 vezes e falhar à centésima (race condition). Regra:
 *     toda a alteração da GUI vinda de fora vai dentro de SwingUtilities.invokeLater.
 *   O slide 01b refere a "event-dispatch thread"; invokeLater é a forma de lhe entregar
 *   trabalho. Perguntar ao professor se podemos usar (não é coordenação, é Swing).
 *
 * LIGAÇÕES
 *   Criada e controlada por: ActuatorClient. Mostra o histórico mantido pelo ActuatorClient.
 *
 * Fase 1 · Responsável: Fredson · Revisor: Celso
 */
public class ActuatorFrame {
}

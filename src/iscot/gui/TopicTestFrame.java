package iscot.gui;

/**
 * TopicTestFrame — janela de teste das fases 1 e 2. É A DEMO PRINCIPAL DO CHECKPOINT 1.
 *
 * OBJETIVO NO ENUNCIADO
 *   Fase 1: "Para teste das funcionalidades iniciais, pode existir um formulário com
 *   campos de texto para o tópico e conteúdo da mensagem."
 *   Fase 2: mostrar a estrutura de tópicos a funcionar.
 *   Entrega intercalar (10–13 nov): "fases iniciais, referentes a aspetos ainda sem
 *   concorrência"; "basta comparecer com o projeto desenvolvido".
 *
 * O QUE VAMOS IMPLEMENTAR (seguir o molde FrameTest do professor)
 *   - Construtor: cria JFrame, EXIT_ON_CLOSE, addFrameContent(), pack().
 *   - open(): setVisible(true).
 *   - addFrameContent(), com BorderLayout:
 *       NORTE  : painel GridLayout com
 *                  JComboBox "Área"     (Area.values())
 *                  JComboBox "Mensagem" (LightSensorMessage, TemperatureSensorMessage,
 *                                        AreaLightingOrderMessage, AreaClimateOrderMessage)
 *                  JTextField "Valor"   (lux / ºC para leituras; true/false para ordens)
 *                  JLabel com o tópico calculado (ex.: "sala/luz/leituras"), para se ver
 *                  que é o sistema que escolhe o tópico a partir da mensagem
 *                  botões "Publicar" e "Consumir cabeça"
 *       CENTRO : JTextArea (dentro de JScrollPane) com todos os tópicos e as suas filas,
 *                cada mensagem no formato toString do professor
 *   - Publicar: constrói a mensagem tipada, calcula o tópico (DeviceType.readingsTopic /
 *     ordersTopic), obtém-no no TopicManager e chama publish.
 *   - Consumir cabeça: removeHead no tópico mostrado (simula "todos confirmaram").
 *   - Depois de cada ação, reescrever a JTextArea a partir de snapshot() de cada tópico.
 *   - Valor inválido (texto em vez de número): aviso com JOptionPane, nada é publicado.
 *   - main próprio para correr sozinha.
 *
 * PORQUÊ COMBOS EM VEZ DE "TÓPICO EM TEXTO LIVRE"
 *   O enunciado sugere campos de texto para tópico e conteúdo, mas a apresentação mostra
 *   mensagens tipadas com área e tipo, e o cliente não escolhe o tópico. Com combos o
 *   formulário fala a mesma língua que o resto do sistema e não deixa criar mensagens
 *   que o servidor nunca aceitaria. Se o professor preferir o texto livre, é trocar dois
 *   componentes.
 *
 * GUIÃO DA DEMO (ver docs/08-apresentacao-checkpoint1.txt)
 *   1. LightSensorMessage na Sala com 320, 450 e 700 lux; TemperatureSensorMessage no
 *      Quarto com 27.5.
 *   2. Mostrar: filas separadas (sala/luz/leituras, quarto/temperatura/leituras), por
 *      ordem, com ids 1, 2, 3 e 1.
 *   3. "Consumir cabeça" em sala/luz/leituras: sai a #1 (FIFO).
 *   4. AreaLightingOrderMessage true na Sala: aparece em sala/luz/ordens.
 *   5. Explicar que na fase 5 quem remove a cabeça é o dispatcher, quando o latch chegar
 *      a zero.
 *
 * LIGAÇÕES
 *   Usa: Topic, TopicManager, Area, DeviceType, as mensagens tipadas.
 *   Não usa rede nem threads nossas: só a thread do Swing.
 *
 * Fase 1–2 · Responsável: Fredson · Revisor: Celso
 */
public class TopicTestFrame {
}

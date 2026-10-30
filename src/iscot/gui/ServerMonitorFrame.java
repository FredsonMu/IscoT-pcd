package iscot.gui;

/**
 * ServerMonitorFrame — GUI opcional do servidor: mostra os clientes ligados e as
 * mensagens trocadas.
 *
 * OBJETIVO NA APRESENTAÇÃO DO PROFESSOR
 *   "o servidor pode funcionar sem GUI; uma GUI minimal pode ajudar a monitorizar o
 *   estado do sistema, p.ex. mostrando as mensagens trocadas" (figura "GUI para exibição
 *   das mensagens transmitidas", marcada como opcional).
 *
 * O QUE VAMOS IMPLEMENTAR (molde FrameTest)
 *   - BorderLayout:
 *       NORTE  : JLabel com o porto e o número de clientes ligados
 *       OESTE  : JList com os dispositivos registados ("sala-lampada-1", ...)
 *       CENTRO : JTextArea (com scroll) com uma linha por mensagem:
 *                "21:43:05  sala-sensor_luz-3 -> sala/luz/leituras
 *                 LightSensorMessage [light=320.0, area=Sala, getType()=SENSOR]"
 *   - Métodos públicos, chamados pelo servidor:
 *       clientConnected(deviceId), clientDisconnected(deviceId),
 *       messageReceived(deviceId, topic, mensagem), messageDelivered(topic, faltaram)
 *   - Cada um destes métodos faz o seu trabalho dentro de SwingUtilities.invokeLater.
 *
 * THREADS (o ponto mais importante desta classe)
 *   Os métodos são chamados por MUITAS threads ao mesmo tempo: todos os ClientHandler e
 *   todos os TopicDispatcher. O Swing não é thread-safe. Como cada método só agenda o
 *   trabalho na EDT (invokeLater), os componentes só são tocados por uma thread
 *   (confinamento) e não é preciso synchronized aqui.
 *   NUNCA fazer trabalho lento dentro destes métodos: atrasaria a thread do servidor que
 *   os chamou (e um dispatcher atrasado atrasa um tópico inteiro).
 *
 * PORQUÊ A FAZER, SE É OPCIONAL
 *   Na demo do checkpoint 1 e na final, mostra ao professor o fluxo inteiro sem saltar
 *   entre consolas. E quando algo encrava, vê-se logo que mensagem chegou e qual não.
 *
 * ALTERNATIVA
 *   Só System.out na consola do servidor: zero código extra, mas com 5 clientes a
 *   correr no Eclipse é difícil de seguir.
 *
 * LIGAÇÕES
 *   Criada por: Server (se arrancar com "gui"). Chamada por: ClientHandler,
 *   TopicDispatcher.
 *
 * Fase 3 (ligações) e 4 (mensagens) · Responsável: Fredson · Revisor: Celso
 */
public class ServerMonitorFrame {
}

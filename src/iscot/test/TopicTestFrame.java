package iscot.test;

/**
 * TopicTestFrame — OPCIONAL: formulário para publicar mensagens nos tópicos sem rede.
 *
 * OBJETIVO NO ENUNCIADO
 *   Fase 1: "Para teste das funcionalidades iniciais, PODE existir um formulário com
 *   campos de texto para o tópico e conteúdo da mensagem." É uma sugestão, não uma
 *   obrigação. O código base do professor propõe outra forma para a fase 1: cada
 *   atuador cria localmente uma ordem e muda a GUI (ver Ventilator e Light).
 *
 * PRIORIDADE
 *   Fazer só se sobrar tempo antes do dia 6. Para mostrar a fase 2, o grupo 1 do
 *   IscotTests chega (imprime as filas na consola).
 *
 * O QUE VAMOS IMPLEMENTAR (se o fizermos; molde FrameTest)
 *   - NORTE: JComboBox "Área" (Areas.values()), JComboBox "Mensagem" (LightSensorMessage,
 *     HumiditySensorMessage, TemperatureSensorMessage, AreaLightingOrderMessage, ...),
 *     JTextField "Valor", botões "Publicar" e "Consumir cabeça".
 *   - CENTRO: JTextArea com as filas de todos os tópicos (toString do professor).
 *   - Publicar: constrói a mensagem, topics.get(área).publish(msg).
 *   - Consumir cabeça: removeHead no tópico da área escolhida.
 *
 * LIGAÇÕES
 *   Usa: Topic, TopicManager, Areas, as mensagens. Não usa rede nem threads nossas.
 *
 * Opcional · Responsável: Fredson · Revisor: Celso
 */
public class TopicTestFrame {
}

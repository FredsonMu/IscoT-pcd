package iscot.common;

/**
 * Event — uma mensagem publicada num tópico: leitura de sensor ou ordem de decisor.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1: "filas de mensagens (também chamadas eventos)". Secção 1.1: o servidor
 *   "recebe as leituras de estado dos sensores, bem como ordens de alto nível (dos
 *   decisores), e publica-as como eventos nos tópicos correspondentes".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - eventId (long): NÃO é final, porque quem o atribui é o servidor (Topic.publish),
 *     não o cliente. É com este id que os AckMessage dizem "consumi o evento X".
 *   - topic, senderId, content (String, final).
 *       content de leitura: "valor=120"   content de ordem: "LIGAR", "DESLIGAR", ...
 *   - Construtor, getters, setter só para o eventId, e um toString legível
 *     (usado nos logs e no formulário de teste).
 *
 * LIGAÇÕES
 *   Criado por: SensorClient, DecisorClient, TopicTestFrame.
 *   Guardado em: Topic (na fila).  Entregue por: TopicDispatcher.
 *   Consumido por: DecisorClient, ActuatorClient.
 *
 * CONCORRÊNCIA
 *   Depois de publicado, o mesmo objeto Event é lido pelo dispatcher e escrito em
 *   vários sockets. Como os outros campos são final e o eventId é posto antes de entrar
 *   na fila (dentro de um método synchronized), é seguro.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Subclasses SensorReading(double valor, String unidade) e Order(String acao):
 *     mais tipado e evita parsing de "valor=120". Custo: mais classes e mais
 *     "instanceof". Boa evolução se o content em texto começar a dar problemas.
 *   - content como Object/Serializable: flexível mas perde-se toda a verificação
 *     do compilador.
 *
 * DÚVIDAS
 *   - O id do evento deve ser único por tópico ou em todo o sistema? Por tópico chega,
 *     porque os acks chegam sempre ao tópico certo.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class Event {
}

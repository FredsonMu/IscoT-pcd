package iscot.client;

/**
 * BrokerConnection — o código de ligação ao servidor, partilhado por todos os
 * dispositivos (sensores, decisores, atuadores).
 *
 * OBJETIVO
 *   Código base: em cada main o professor deixou "TODO connect to the server" e
 *   "TODO register with the server". Em vez de repetir isso em 10 classes, fica aqui.
 *   Apresentação: os clientes "comunicam com o servidor através de sockets, usando
 *   canais de objetos"; "cada cliente apenas conhece o servidor".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Construtor(host, porto): Socket; ObjectOutputStream + flush; depois
 *     ObjectInputStream (mesma ordem que no servidor, senão deadlock).
 *   - register(Type type, Areas area): envia RegistrationMessage.request(type, area),
 *     lê a resposta; se recusada, lança exceção com o motivo; se aceite, guarda e
 *     devolve o deviceId.
 *   - send(Message), synchronized: preenche o senderId, writeObject + flush + reset.
 *   - startReceiving(handler): arranca UMA thread recetora que, em ciclo, faz
 *     readObject, chama handler.accept(mensagem) e depois envia
 *     AckMessage(messageId) (decisores e atuadores confirmam TUDO o que recebem).
 *     "handler" pode ser um java.util.function.Consumer<Message>, como o
 *     DoubleConsumer que o professor usa no SensorControl.
 *   - close(), getDeviceId().
 *
 * PORQUÊ UMA THREAD RECETORA
 *   readObject bloqueia. Se corresse na thread main ou na do Swing, o programa (ou a
 *   janela) ficava parado à espera de mensagens.
 *
 * CONCORRÊNCIA
 *   send é synchronized: a thread recetora (acks, ordens do decisor) e a thread do
 *   Swing (callback do SensorControl) podem enviar ao mesmo tempo.
 *
 * LIGAÇÕES
 *   Usada por: todos os mains de dispositivo. Do outro lado: um ClientHandler.
 *
 * Fase 3 (ligação e registo) e 4 (envio/receção) · Responsável: Fredson · Revisor: Celso
 */
public class BrokerConnection {
}

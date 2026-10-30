package iscot.client;

/**
 * BrokerConnection — esconde o socket e os canais de objetos do resto do cliente.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Fases 3 e 4: ligação inicial e troca de mensagens. Apresentação: os clientes
 *   "funcionarão em aplicações independentes, e comunicam com o servidor através de
 *   sockets, usando canais de objetos"; "cada cliente apenas conhece o servidor".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Construtor(host, porto): abre o Socket; cria ObjectOutputStream + flush; depois
 *     o ObjectInputStream (mesma ordem que no servidor, senão deadlock).
 *   - register(área, tipo): envia RegistrationMessage.request(área, tipo), lê a
 *     RegistrationMessage de resposta; se recusada, lança exceção com o motivo; se
 *     aceite, guarda e devolve o deviceId.
 *   - send(Message), synchronized: preenche o senderId, writeObject + flush + reset.
 *   - receive(): readObject (bloqueia até chegar algo). Só a thread recetora o chama.
 *   - close() e getDeviceId().
 *
 * LIGAÇÕES
 *   Criada por: ClientMain. Usada por: SensorClient, DecisorClient, ActuatorClient.
 *   Do outro lado da ligação está um ClientHandler.
 *
 * CONCORRÊNCIA
 *   send é synchronized porque pode ser chamado por duas threads ao mesmo tempo: no
 *   decisor, a thread recetora envia o Ack e a ordem; no sensor, o slider da GUI (thread
 *   do Swing) e a simulação automática podem enviar ao mesmo tempo.
 *
 * PORQUÊ ESTA CLASSE (encapsulamento)
 *   Se um dia mudássemos de objetos para texto, ou acrescentássemos reconexão
 *   automática, só esta classe mudava. É a mesma ideia de separar a GUI da lógica.
 *
 * DÚVIDAS
 *   - Se o servidor for abaixo, o cliente deve tentar religar sozinho? Não é pedido;
 *     por agora mostra erro e termina.
 *
 * Fase 3/4 · Responsável: Fredson · Revisor: Celso
 */
public class BrokerConnection {
}

package iscot.server;

/**
 * ClientHandler — a thread dedicada que o servidor cria para cada cliente ligado.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Enunciado 1.1: "Cada cliente deve ter uma thread dedicada no servidor, que gere as
 *   interações com ele, incluindo (mas não limitado a) envio e receção de mensagens."
 *   Enunciado 2.1: registo, id único, recusa de tipos inexistentes.
 *   Apresentação: depois da RegistrationMessage "passa a estar estabelecida uma ligação
 *   conceptual entre o cliente e o servidor, em ambos os sentidos".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Thread. Recebe no construtor o Socket (do accept) e o Server.
 *   - Campos: socket, ObjectOutputStream, ObjectInputStream, deviceId, area, tipo,
 *     tópico que subscreve (decisor/atuador).
 *   - run():
 *       1. criar o ObjectOutputStream, fazer flush, SÓ DEPOIS o ObjectInputStream
 *          (ver armadilha abaixo);
 *       2. registo: ler a RegistrationMessage (pedido); tentar converter área e tipo
 *          (Area.parse, DeviceType.parse); se falhar, responder com
 *          RegistrationMessage.reply(false, null, motivo) e terminar; se der, pedir id
 *          ao Server, subscrever o tópico de tipo.subscribeTopic(area) (se não for null)
 *          e responder com RegistrationMessage.reply(true, id, null);
 *       3. ciclo: ler objeto e decidir pelo tipo:
 *            SensorMessage -> só aceite se este cliente for SENSOR; tópico =
 *                             readingsTopic(msg.area, msg.subject); publish
 *            OrderMessage  -> só aceite se este cliente for DECIDER; tópico =
 *                             ordersTopic(msg.area, msg.subject); publish
 *            AckMessage    -> subscribeTopic.ack(messageId, deviceId)
 *            (extra) ReceptionConfirmationMessage -> registar confirmação
 *          Cada mensagem recebida é também registada no ServerMonitor (GUI opcional).
 *       4. finally: unsubscribe e fechar o socket (cliente saiu ou caiu).
 *   - send(Message), synchronized: writeObject, flush, reset.
 *
 * PORQUÊ VALIDAR O PAPEL DE QUEM PUBLICA
 *   "Cada cliente apenas conhece o servidor": é o servidor que garante a organização.
 *   Uma lâmpada (ACTUATOR) que enviasse uma AreaLightingOrderMessage não deve conseguir
 *   dar ordens. E a área da mensagem deve ser a área registada pelo cliente.
 *
 * LIGAÇÕES
 *   Criado por: Server (um por accept). Usa: Server (ids, TopicManager, monitor), Topic,
 *   Area, DeviceType, todas as mensagens.
 *   O seu send é chamado por: TopicDispatcher (outra thread!) e por ele próprio.
 *
 * CONCORRÊNCIA
 *   - send é synchronized porque o socket de saída é partilhado: o dispatcher de um
 *     tópico pode estar a enviar uma mensagem ao mesmo tempo que esta thread envia uma
 *     resposta. Dois writeObject intercalados corrompiam o stream.
 *   - A leitura (readObject) só é feita por esta thread: confinamento, não precisa
 *     de cadeado.
 *
 * ARMADILHAS
 *   - ORDEM DOS STREAMS: o construtor de ObjectInputStream bloqueia à espera do
 *     cabeçalho que o outro lado envia ao criar o seu ObjectOutputStream. Se os dois
 *     lados criarem primeiro o Input, ficam os dois parados para sempre (deadlock).
 *     Regra: Output + flush primeiro, nos dois lados.
 *   - reset() depois de cada envio: o ObjectOutputStream guarda os objetos já
 *     enviados e, se mandarmos o mesmo objeto alterado, envia só uma referência
 *     à versão antiga.
 *   - Um cliente que fecha a janela provoca EOFException/SocketException no readObject:
 *     é o fim normal, não um erro a mostrar ao utilizador.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - implements Runnable + new Thread(handler).start(): equivalente; extends Thread
 *     dá acesso direto a isInterrupted() e é mais curto. Ambas são da aula 02.
 *   - Thread pool (ExecutorService): limita threads, mas é proibido e contraria
 *     "uma thread dedicada por cliente".
 *   - Duas threads por cliente (leitora + escritora): ver TopicDispatcher, opção B.
 *
 * DÚVIDAS
 *   - Um cliente pode registar-se duas vezes na mesma ligação? Decidimos que não:
 *     o registo é só a primeira mensagem.
 *
 * Fase 3 (ligação/registo) e 4 (mensagens) · Responsável: Celso · Revisor: Fredson
 */
public class ClientHandler {
}

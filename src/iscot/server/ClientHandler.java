package iscot.server;

/**
 * ClientHandler — a thread dedicada que o servidor cria para cada cliente ligado.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C7 (registo) e C8 (mensagens) · feature/server, feature/client-handler
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        NÃO. Registo na fase 3 (10–13 nov); mensagens na fase 4.
 *   No dia 6:     Nada.
 *   Depende de:   C3, C4, C6.
 *   Liga-se a:    Criado pelo Server; fala com a BrokerConnection do cliente; publica no Topic.
 *   Código base:  Valida o Type e a Areas do professor no registo; recusa null e DWC.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Enunciado 1.1: "Cada cliente deve ter uma thread dedicada no servidor, que gere as
 *   interações com ele, incluindo (mas não limitado a) envio e receção de mensagens."
 *   Enunciado 2.1: registo, id único, recusa de tipos inexistentes.
 *   Apresentação: depois da RegistrationMessage, ligação "em ambos os sentidos".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Thread. Recebe o Socket (do accept) e o Server.
 *   - Campos: socket, ObjectOutputStream, ObjectInputStream, deviceId, type, area.
 *   - run():
 *       1. ObjectOutputStream + flush, SÓ DEPOIS ObjectInputStream;
 *       2. registo: ler a RegistrationMessage; se type ou area forem null, ou type for
 *          DWC, responder reply(false, null, motivo) e terminar; senão pedir id ao
 *          Server, e se type for DECIDER ou ACTUATOR subscrever o Topic da área;
 *          responder reply(true, id, null);
 *       3. ciclo: ler objeto:
 *            SensorMessage -> só se este cliente for SENSOR e a área for a sua;
 *                             topics.get(area).publish(msg)
 *            OrderMessage  -> só se este cliente for DECIDER e a área for a sua; publish
 *            AckMessage    -> topics.get(area).ack(messageId, deviceId)
 *            (extra) ReceptionConfirmationMessage -> registar
 *          Mensagens que não cumpram as regras: ignorar e registar no log.
 *       4. finally: unsubscribe e fechar o socket.
 *   - send(Message), synchronized: writeObject, flush, reset.
 *
 * PORQUÊ VALIDAR O TIPO E A ÁREA DE QUEM PUBLICA
 *   "Cada cliente apenas conhece o servidor": o servidor garante as regras. Uma lâmpada
 *   (ACTUATOR) não pode dar ordens, e um cliente não publica em áreas que não são a sua.
 *
 * CONCORRÊNCIA
 *   - send é synchronized: o dispatcher do tópico e esta thread podem escrever ao mesmo
 *     tempo; dois writeObject intercalados corrompiam o stream.
 *   - readObject só por esta thread: confinamento.
 *
 * ARMADILHAS
 *   - Ordem dos streams: se os dois lados criarem primeiro o Input, deadlock.
 *   - reset() depois de cada envio (cache de objetos do ObjectOutputStream).
 *   - EOFException/SocketException no readObject = cliente saiu; não é um erro.
 *
 * ALTERNATIVAS
 *   - implements Runnable: equivalente. Thread pool: proibido e contraria "thread
 *     dedicada". Leitora + escritora por cliente: ver TopicDispatcher, opção B.
 *
 */
public class ClientHandler {
}

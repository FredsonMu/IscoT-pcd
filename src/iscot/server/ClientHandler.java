package iscot.server;

/**
 * ClientHandler — a thread dedicada que o servidor cria para cada cliente ligado.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1.1: "Cada cliente deve ter uma thread dedicada no servidor, que gere as
 *   interações com ele, incluindo (mas não limitado a) envio e receção de mensagens."
 *   Secção 2.1: registo, id único, recusa de tipos inexistentes.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Thread. Recebe no construtor o Socket (do accept) e o Server.
 *   - Campos: socket, ObjectOutputStream, ObjectInputStream, deviceId,
 *     tópico onde publica, tópico que subscreve.
 *   - run():
 *       1. criar o ObjectOutputStream, fazer flush, SÓ DEPOIS o ObjectInputStream
 *          (ver armadilha abaixo);
 *       2. registo: ler a RegisterMessage (pedido); tentar converter área e tipo (Area.parse,
 *          DeviceType.parse); se falhar, responder com RegisterMessage recusada e terminar;
 *          se der, pedir id ao Server, obter os tópicos via DeviceType + TopicManager,
 *          subscrever se for decisor/atuador, responder com RegisterMessage aceite;
 *       3. ciclo: ler objeto; se for Event -> tópico de publicação.publish;
 *          se for AckMessage -> tópico subscrito.ack(eventId, deviceId);
 *          (extra) se for ReceptionConfirmationMessage -> registar confirmação;
 *       4. finally: unsubscribe e fechar o socket (cliente saiu ou caiu).
 *   - send(Message), synchronized: writeObject, flush, reset.
 *
 * LIGAÇÕES
 *   Criado por: Server (um por accept). Usa: Server (ids, TopicManager), Topic,
 *   Area, DeviceType, todas as mensagens.
 *   O seu send é chamado por: TopicDispatcher (outra thread!) e por ele próprio.
 *
 * CONCORRÊNCIA
 *   - send é synchronized porque o socket de saída é partilhado: o dispatcher de um
 *     tópico pode estar a enviar um evento ao mesmo tempo que esta thread envia uma
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

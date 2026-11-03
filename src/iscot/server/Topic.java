package iscot.server;

/**
 * Topic — a fila FIFO de mensagens de UMA ÁREA da casa e a lista de quem a subscreve.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C3 · feature/topic
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        SIM (fase 2). Merge até qua 4 nov. No dia 6 basta: publish, removeHead, snapshot (e waitForHead escrito). setPending/ack/subscribe ficam para a fase 4–5.
 *   No dia 6:     O IscotTests publica mensagens no Topic da LivingRoom e mostra a fila FIFO com ids 1, 2, 3; removeHead tira a mais antiga.
 *   Depende de:   C1, C2.
 *   Liga-se a:    Gerido pelo TopicManager; testado pelo IscotTests (F5); usado pelo ClientHandler e pelo TopicDispatcher depois.
 *   Código base:  Um tópico por área (Areas do professor), porque o registo só leva tipo e área.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 * É O PRINCIPAL RECURSO PARTILHADO DO SERVIDOR.
 *
 * OBJETIVO NO ENUNCIADO
 *   Fase 2: "desenvolvimento de uma estrutura adequada para manter as mensagens
 *   correspondentes aos tópicos de eventos".
 *   Secção 1: "As mensagens são separadas no servidor em tópicos (...). Um cliente
 *   publica e consome mensagens exclusivamente de um tópico."
 *   Ciclo de vida: a mensagem "só é consumida quando chega à cabeça desta fila"; "só
 *   deve ser removida da fila quando todos os clientes subscritos confirmarem o seu
 *   consumo, ou após um tempo de espera"; "em caso de esgotamento da fila, os clientes
 *   esperam até que haja novo evento".
 *
 * UM TÓPICO = UMA ÁREA (iscot.enums.Areas)
 *   O cliente regista-se só com tipo e área (RegistrationMessage, código base). Com essa
 *   informação, a forma de cumprir "um cliente publica e consome exclusivamente de um
 *   tópico" é o tópico ser a ÁREA: na LivingRoom, o sensor publica no tópico LivingRoom,
 *   o decisor consome e publica no tópico LivingRoom, as lâmpadas consomem do tópico
 *   LivingRoom. Cada cliente reage às mensagens que lhe dizem respeito (pela classe) e
 *   confirma todas. (Alternativa e dúvida: docs/03-decisoes.md, D24.)
 *
 * É A "MESA" DA AULA 04 (produtor-consumidor)
 *   Produtores: ClientHandler de sensores e decisores (publish). Consumidor: o
 *   TopicDispatcher. Diferença para a mesa: o consumidor ESPREITA a cabeça sem a tirar,
 *   porque só sai depois dos acks.
 *
 * ESTADO INTERNO
 *   - área (final)
 *   - fila de Message (LinkedList: addLast / getFirst / removeFirst são O(1))
 *   - lista de ClientHandler subscritos (deciders e actuators da área)
 *   - contador para o próximo messageId
 *   - mensagem pendente: id da cabeça, o seu latch e os deviceIds que já confirmaram
 *
 * O QUE VAMOS IMPLEMENTAR (TODOS synchronized)
 *   - publish(msg): atribui messageId, põe no fim da fila, notifyAll().
 *   - waitForHead(): enquanto a fila estiver vazia, wait(); devolve a cabeça sem remover.
 *   - removeHead(): tira a cabeça e limpa os dados da mensagem pendente.
 *   - subscribe(handler) / unsubscribe(handler).
 *   - subscribersSnapshot(): devolve uma CÓPIA da lista de subscritores.
 *   - setPending(messageId, latch) e ack(messageId, deviceId): só desconta uma vez por
 *     dispositivo e só para a mensagem pendente.
 *   - snapshot(): cópia da fila, para testes e para a TopicTestFrame.
 *
 * CONCORRÊNCIA: PORQUÊ CADA DECISÃO
 *   - synchronized em tudo: vários ClientHandler publicam ao mesmo tempo; "fila.add" e
 *     "proximoId++" não são atómicos (race condition, aula 03).
 *   - wait em while: se acordar e a fila continuar vazia, volta a dormir.
 *   - notifyAll: mandamento 4 da aula 04.
 *   - Cópia dos subscritores: o dispatcher envia pela rede SEM segurar o cadeado do
 *     tópico (enviar é lento; segurar o cadeado bloquearia todos os publicadores).
 *   - setPending ANTES de enviar: o ack pode chegar quase de imediato.
 *   - Ordem de cadeados: ack() segura Topic e depois o latch; ninguém segura o latch e
 *     depois pede o Topic. Ordem fixa = sem deadlock.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Uma fila por subscritor (como MQTT/RabbitMQ): cada cliente ao seu ritmo, mas o
 *     enunciado exige que o tópico só avance quando TODOS confirmarem.
 *   - BlockingQueue do Java: proibida.
 *
 * DÚVIDAS
 *   - Tópico sem subscritores: a mensagem é descartada (latch de 0) ou espera?
 *
 * NA FASE 2 (sem concorrência)
 *   Pode já ter synchronized (não faz mal com uma thread). A demo usa publish,
 *   snapshot e removeHead (IscotTests e, opcionalmente, TopicTestFrame).
 *
 * COMO TESTAR
 *   Fase 2: publicar 3 LightSensorMessage na LivingRoom: ordem FIFO e ids 1, 2, 3.
 *   Fase 5: 10 threads a publicar 1000 cada: 10 000 mensagens, ids sem repetição.
 *
 */
public class Topic {
}

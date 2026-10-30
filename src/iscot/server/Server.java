package iscot.server;

/**
 * Server — o message broker IscoT. Ponto de entrada do servidor.
 *
 * OBJETIVO NO ENUNCIADO
 *   Resumo: "um servidor central, que fará a gestão de toda a comunicação no sistema
 *   distribuído". Fase 3: "Criação do servidor e ligação inicial dos clientes".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - main(args): lê o porto de args[0] (com um valor por omissão, ex.: 8080) e
 *     arranca o servidor.
 *   - Campos: um TopicManager; um contador para ids de dispositivo.
 *   - start(porto): cria um ServerSocket; em ciclo infinito faz accept() e, por cada
 *     Socket devolvido, cria um ClientHandler e chama start().
 *   - newDeviceId(area, tipo), synchronized: devolve algo como "sala-lampada-3"
 *     e incrementa o contador.
 *   - getTopics(): dá acesso ao TopicManager aos ClientHandler.
 *
 * LIGAÇÕES
 *   Cria: TopicManager (1), ClientHandler (1 por cliente).
 *   É usado por: ClientHandler (ids e tópicos).
 *
 * THREADS
 *   A thread main fica sempre presa no accept(). Por isso o atendimento TEM de ser
 *   noutra thread: se chamássemos handler.run() em vez de start(), o servidor
 *   atenderia um cliente de cada vez e os outros ficavam à espera.
 *
 * CONCORRÊNCIA
 *   - newDeviceId é synchronized: dois clientes a registar-se ao mesmo tempo correm em
 *     duas threads; "contador++" é ler + somar + escrever (não atómico) e os dois
 *     podiam receber o mesmo id. O enunciado exige id "único em todo o sistema".
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - AtomicInteger.incrementAndGet() para o contador: dado na aula 03, sem cadeado.
 *     Perguntar se conta como "equivalente da biblioteca" proibido.
 *   - UUID.randomUUID(): único sem coordenação, mas ilegível nos logs e na GUI.
 *
 * DÚVIDAS
 *   - O servidor também deve ter GUI (ver tópicos e filas em tempo real)? Não é pedido,
 *     mas ajudava na demo. A consola pode chegar.
 *
 * COMO TESTAR
 *   Arrancar no Eclipse com o argumento 8080; ligar 3 clientes; verificar 3 ids
 *   diferentes. Arrancar duas vezes no mesmo porto deve dar BindException.
 *
 * Fase 3 · Responsável: Celso · Revisor: Fredson
 */
public class Server {
}

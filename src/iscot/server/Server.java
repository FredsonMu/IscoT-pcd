package iscot.server;

/**
 * Server — o message broker IscoT. Ponto de entrada do servidor.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C7 · feature/server
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        NÃO. Fase 3, para 10–13 nov (começar sáb 7).
 *   No dia 6:     Nada.
 *   Depende de:   C4, C6.
 *   Liga-se a:    Cria os ClientHandler; recebe as ligações das BrokerConnection.
 *   Código base:  —
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Apresentação: "Rede segundo o modelo Cliente-Servidor, com servidor central para
 *   gerir a comunicação"; "o servidor pode funcionar sem GUI".
 *   Fase 3: "Criação do servidor e ligação inicial dos clientes".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - main(args): porto em args[0] (por omissão 8080); se houver "gui" em args[1],
 *     abre a ServerMonitorFrame (opcional).
 *   - Campos: um TopicManager; um contador para ids de dispositivo.
 *   - start(porto): ServerSocket; em ciclo, accept() e, por cada Socket, um
 *     ClientHandler com start().
 *   - newDeviceId(area, type), synchronized: ex.: "LivingRoom-ACTUATOR-3".
 *   - getTopics(), getMonitor() (pode ser null).
 *
 * THREADS
 *   A thread main fica presa no accept(). Por isso o atendimento TEM de ser noutra
 *   thread: com run() em vez de start(), o servidor atendia um cliente de cada vez.
 *
 * CONCORRÊNCIA
 *   newDeviceId é synchronized: dois registos ao mesmo tempo correm em duas threads, e
 *   "contador++" não é atómico; podiam receber o mesmo id. O enunciado exige id
 *   "único em todo o sistema".
 *
 * ALTERNATIVA
 *   AtomicInteger.incrementAndGet(): sem cadeado; perguntar se é permitido.
 *
 * COMO TESTAR
 *   Arrancar com 8080; ligar 3 clientes; 3 ids diferentes. Segundo servidor no mesmo
 *   porto: BindException.
 *
 */
public class Server {
}

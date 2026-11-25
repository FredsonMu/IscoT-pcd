package iscot.server;

/**
 * Server — o programa do servidor IscoT: aceita as ligações e dá a cada cliente a sua thread.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F9 · feature/server · FREDSON, 2.ª CLASSE DAS FASES 4 E 5
 *   Revisor:       Celso
 *   Fase:          4 (servidor e ligação inicial)
 *   Etapa:         1
 *   Precisa de:    F8 (RegistrationReplyEvent); ClientHandler (F10), que se faz em paralelo.
 *   Quem espera:   Celso, para testar a BrokerConnection contra um servidor a sério.
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   1.1: "Cada cliente deve ter uma thread dedicada no servidor, que gere as interações com ele."
 *   2.1: o identificador de dispositivo "deve ser único em todo o sistema".
 *   Slides: "o servidor pode funcionar sem GUI".
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · O main e o argumento
 *     Lógica sugerida: um argumento, o porto (8080). Se faltar ou não for um número, mensagem clara.
 *     Porquê: os clientes já recebem "localhost 8080 <área>"; o porto tem de ser o mesmo.
 *     Liga-se a: os argumentos dos 8 aparelhos.
 *
 *   Passo 2 · Ficar à escuta
 *     Lógica sugerida: abrir um ServerSocket nesse porto e escrever na consola que está à escuta.
 *     Porquê: só um programa pode escutar num porto. Um segundo servidor dá BindException.
 *     Pesquisar: ServerSocket, BindException.
 *
 *   Passo 3 · O ciclo de ligações
 *     Lógica sugerida: um ciclo infinito: accept() → criar um ClientHandler para esse socket →
 *       arrancá-lo com start() → voltar ao accept().
 *     Porquê: o accept() BLOQUEIA até alguém ligar. Se o servidor tratasse o cliente nesta mesma thread,
 *       ficava preso nele e mais ninguém se conseguia ligar. É a razão da "thread dedicada".
 *     Atenção: start(), e não run(). O run() corria o handler nesta thread e o servidor parava.
 *     Liga-se a: ClientHandler.
 *
 *   Passo 4 · Dar ids únicos
 *     Lógica sugerida: um contador e um método que devolve o id seguinte (por exemplo,
 *       "LivingRoom-ACTUATOR-3"), chamado pelos ClientHandler.
 *     Porquê: dois clientes a registar-se ao mesmo tempo correm em DUAS threads. "contador++" não é
 *       atómico (ler, somar, escrever): sem proteção, os dois podiam receber o mesmo número.
 *       É a race condition da aula 03.
 *     Solução: método synchronized.
 *
 *   Passo 5 · A lista de clientes ligados (para a fase 5)
 *     Lógica sugerida: guardar os ClientHandler registados (com o tipo e a área de cada um); entrar
 *       quando o registo é aceite e sair quando o cliente se desliga.
 *     Porquê: na fase 5, o servidor reencaminha as ordens aos atuadores da mesma área.
 *     Atenção: vários handlers entram e saem ao mesmo tempo → acessos synchronized; para percorrer
 *       a lista (e enviar), percorrer uma CÓPIA, feita dentro do cadeado.
 *
 * CONCEITOS DA CADEIRA
 *   Sockets (ServerSocket, accept); thread por cliente; start() vs run(); race condition; synchronized;
 *   check-then-act (entrar e sair da lista).
 *
 * OPÇÕES
 *   A) Os ids e a lista ficam no Server. [sugestão] Simples; o handler recebe o Server no construtor.
 *   B) Uma classe à parte (ClientRegistry). Mais organizado, mas mais uma classe para as fases 4 e 5.
 *
 * ARMADILHAS
 *   - Esquecer o start() e chamar o run(): o servidor só atende um cliente.
 *   - Lançar o servidor duas vezes: BindException. Fechar o anterior antes.
 *   - Um erro num cliente não pode deitar abaixo o servidor: os erros tratam-se no ClientHandler.
 *
 * PRONTA QUANDO
 *   Arranca, diz que está à escuta, aceita vários clientes ao mesmo tempo e dá ids diferentes
 *   a cada um (mesmo lançando vários quase ao mesmo tempo).
 *
 * NAS FASES SEGUINTES
 *   Fase 6: ganha o TopicManager (os tópicos), e o reencaminhamento direto da fase 5 sai.
 */
public class Server {
}

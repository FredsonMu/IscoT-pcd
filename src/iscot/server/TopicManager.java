package iscot.server;

/**
 * TopicManager — o "índice" de todos os tópicos: nome -> Topic.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1.1: o servidor publica eventos "nos tópicos correspondentes" e gere "as
 *   filas associadas a cada tópico". Alguém tem de saber que tópicos existem.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Um Map<String, Topic> (HashMap).
 *   - getOrCreate(nome), synchronized: se o tópico não existe, cria-o, guarda-o e
 *     arranca o seu TopicDispatcher (a partir da fase 5); devolve-o.
 *   - Opcional: lista de nomes, para logs ou para a GUI de teste.
 *
 * PORQUÊ CRIAR À PRIMEIRA UTILIZAÇÃO (lazy)
 *   Não sabemos de antemão que combinações área/grandeza vão aparecer. Criar os
 *   tópicos todos (5 áreas x 4 grandezas x 2 canais = 40) também funcionaria, mas
 *   criaria 40 threads de dispatcher a dormir sem necessidade.
 *
 * CONCORRÊNCIA
 *   Dois clientes a registar-se ao mesmo tempo no mesmo tópico novo: sem synchronized,
 *   os dois viam "não existe", criavam dois Topic diferentes, e um deles perdia-se
 *   (o sensor publicava num e as lâmpadas ouviam no outro). Clássico check-then-act.
 *
 * LIGAÇÕES
 *   Criado por: Server (um só). Usado por: ClientHandler no registo.
 *   Cria: Topic e TopicDispatcher.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - ConcurrentHashMap.computeIfAbsent: elegante, mas é biblioteca de concorrência
 *     do Java (perguntar se é permitido). O synchronized é simples e chega.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class TopicManager {
}

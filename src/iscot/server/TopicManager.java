package iscot.server;

/**
 * TopicManager — guarda um Topic por área da casa.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1.1: o servidor publica as mensagens "nos tópicos correspondentes" e gere "as
 *   filas associadas a cada tópico". Alguém tem de saber que tópicos existem.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Um Map<Areas, Topic> (HashMap ou EnumMap).
 *   - get(Areas area), synchronized: se o tópico da área ainda não existe, cria-o,
 *     guarda-o e (a partir da fase 5) arranca o seu TopicDispatcher; devolve-o.
 *
 * PORQUÊ CRIAR À PRIMEIRA UTILIZAÇÃO
 *   Só há 5 áreas, por isso também se podiam criar todos no arranque. Criar quando
 *   alguém precisa evita dispatchers a dormir em áreas sem clientes. Qualquer das duas
 *   está certa; esta obriga a pensar no check-then-act (ver abaixo).
 *
 * CONCORRÊNCIA
 *   Dois clientes da mesma área a registar-se ao mesmo tempo: sem synchronized, os dois
 *   viam "não existe", criavam dois Topic, e um perdia-se (o sensor publicava num e as
 *   lâmpadas ouviam no outro). Clássico check-then-act (aula 03).
 *
 * LIGAÇÕES
 *   Criado por: Server (um só). Usado por: ClientHandler (registo e publicação).
 *   Cria: Topic e TopicDispatcher.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class TopicManager {
}

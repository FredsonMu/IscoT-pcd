package iscot.server;

/**
 * TopicManager — guarda um Topic por área da casa.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C4 · feature/topic-manager
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        SIM (fase 2). Merge até qua 4 nov. No dia 6: get(area) sem arrancar dispatchers.
 *   No dia 6:     O IscotTests pede o tópico da LivingRoom e do Garden e mostra que são diferentes, e que pedir duas vezes dá o mesmo.
 *   Depende de:   C3.
 *   Liga-se a:    Usado pelo IscotTests (dia 6) e pelo ClientHandler (fase 3–4).
 *   Código base:  Chave = Areas do professor.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
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
 */
public class TopicManager {
}

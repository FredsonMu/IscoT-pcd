package iscot.server.gui;

/**
 * ServerMonitorFrame — GUI OPCIONAL do servidor: clientes ligados e mensagens trocadas.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       A3 · feature/monitor
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        NÃO. Opcional, só depois das fases 1–5.
 *   No dia 6:     Nada.
 *   Depende de:   C7, C8.
 *   Liga-se a:    Chamada pelo ClientHandler e pelo TopicDispatcher.
 *   Código base:  O professor diz que o servidor "pode funcionar sem GUI"; o código base não a traz.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO NA APRESENTAÇÃO DO PROFESSOR
 *   "o servidor pode funcionar sem GUI; uma GUI minimal pode ajudar a monitorizar o
 *   estado do sistema, p.ex. mostrando as mensagens trocadas" (slide "GUI: opcional").
 *   O código base do professor NÃO traz esta janela: é nossa, e é opcional.
 *
 * PRIORIDADE
 *   Só depois de as fases 1–5 estarem feitas. Até lá, a consola do servidor chega.
 *
 * O QUE VAMOS IMPLEMENTAR (molde FrameTest)
 *   - BorderLayout: NORTE porto e nº de clientes; OESTE JList com os dispositivos;
 *     CENTRO JTextArea com scroll, uma linha por mensagem:
 *     "21:43:05  LivingRoom-SENSOR-3 -> LightSensorMessage [light=320.0, ...]"
 *   - Métodos chamados pelo servidor: clientConnected, clientDisconnected,
 *     messageReceived, messageDelivered. Cada um só faz SwingUtilities.invokeLater.
 *
 * THREADS
 *   Chamada por todos os ClientHandler e TopicDispatcher ao mesmo tempo. Como cada
 *   método só agenda trabalho na thread do Swing, os componentes só são tocados por
 *   ela (confinamento): não é preciso synchronized. Nada lento lá dentro.
 *
 */
public class ServerMonitorFrame {
}

package iscot.gui;

/**
 * TopicTestFrame — janela de teste das fases 1 e 2. É A DEMO DA ENTREGA INTERCALAR.
 *
 * OBJETIVO NO ENUNCIADO
 *   Fase 1: "Para teste das funcionalidades iniciais, pode existir um formulário com
 *   campos de texto para o tópico e conteúdo da mensagem."
 *   Fase 2: mostrar a estrutura de tópicos a funcionar.
 *   Entrega intercalar: "fases iniciais, referentes a aspetos ainda sem concorrência".
 *
 * O QUE VAMOS IMPLEMENTAR (seguir o molde FrameTest do professor)
 *   - Construtor: cria JFrame, EXIT_ON_CLOSE, addFrameContent(), pack().
 *   - open(): setVisible(true).
 *   - addFrameContent(), com BorderLayout:
 *       NORTE  : painel GridLayout com JTextField "Tópico" (ex.: sala/luz/leituras),
 *                JTextField "Conteúdo" (ex.: valor=120), botões "Publicar" e
 *                "Consumir cabeça"
 *       CENTRO : JTextArea (dentro de JScrollPane) com todos os tópicos e as suas filas
 *   - Publicar: obtém ou cria o Topic (localmente, sem servidor) e chama publish.
 *   - Consumir cabeça: chama removeHead no tópico escrito (simula "todos confirmaram").
 *   - Depois de cada ação, reescrever a JTextArea a partir de snapshot() de cada tópico.
 *   - main próprio para correr sozinha.
 *
 * GUIÃO DA DEMO (2 minutos)
 *   1. Publicar 3 leituras em sala/luz/leituras e 1 em jardim/humidade/leituras.
 *   2. Mostrar que ficam em filas separadas, por ordem, com ids 1, 2, 3.
 *   3. "Consumir cabeça" em sala/luz: sai a #1 (FIFO).
 *   4. Explicar o que vai acontecer na fase 5: em vez do botão, quem remove é o
 *      dispatcher, quando o latch chegar a zero.
 *
 * LIGAÇÕES
 *   Usa: Topic, Event (e opcionalmente DeviceType para sugerir nomes).
 *   Não usa rede nem threads nossas: só a thread do Swing.
 *
 * ALTERNATIVAS
 *   - Teste só na consola (main com prints): mais rápido, mas o enunciado pede
 *     explicitamente GUI e esta janela vai servir de ferramenta de depuração depois.
 *   - Mais tarde, a mesma janela pode ligar-se ao servidor real e publicar por socket.
 *
 * Fase 1–2 · Responsável: Fredson · Revisor: Celso
 */
public class TopicTestFrame {
}

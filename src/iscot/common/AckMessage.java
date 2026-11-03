package iscot.common;

/**
 * AckMessage — o cliente avisa o servidor de que consumiu uma mensagem do tópico.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C1 · feature/mensagens-base
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        SÓ A CLASSE (compila). Entra na tarefa C1 por ser pequena, mas só é usada na fase 4.
 *   No dia 6:     Nada a mostrar.
 *   Depende de:   Message.
 *   Liga-se a:    Criada pela BrokerConnection; lida pelo ClientHandler (fase 4).
 *   Código base:  —
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Enunciado 2.2: "O consumo do evento por cada cliente deve ser registado pelo
 *   servidor, com o auxílio de um CountDownLatch. Só após todos os clientes terem
 *   consultado a mensagem é que o servidor disponibilizará a próxima mensagem do tópico."
 *   Cada AckMessage faz o latch descer 1.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Campo final long messageId (o id da mensagem consumida).
 *     Não precisa de deviceId: o servidor sabe quem é pelo ClientHandler.
 *   - getType(): o Type de quem confirma (DECIDER ou ACTUATOR).
 *
 * REGRA IMPORTANTE
 *   Todos os subscritores confirmam TODAS as mensagens do tópico da área, mesmo as que
 *   ignoram (uma lâmpada recebe as leituras do sensor de luz e confirma-as sem fazer
 *   nada). Se não confirmassem, o tópico ficava preso até ao timeout em cada mensagem.
 *
 * LIGAÇÕES
 *   Criada por: deciders e actuators (via BrokerConnection), logo depois de receberem
 *   uma mensagem. Lida por: ClientHandler, que chama Topic.ack(messageId, deviceId).
 *
 */
public class AckMessage {
}

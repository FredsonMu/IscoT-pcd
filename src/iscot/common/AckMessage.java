package iscot.common;

/**
 * AckMessage — o cliente avisa o servidor de que consumiu uma mensagem de um tópico.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.2: "O consumo do evento por cada cliente deve ser registado pelo servidor,
 *   com o auxílio de um CountDownLatch. Só após todos os clientes terem consultado a
 *   mensagem é que o servidor disponibilizará a próxima mensagem do tópico."
 *   Cada AckMessage que chega faz o latch descer 1.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Campo final long messageId (o id que o servidor deu à mensagem consumida).
 *     Não precisa de deviceId: o servidor já sabe quem é, porque cada cliente tem o seu
 *     ClientHandler.
 *   - getType(): o Role de quem confirma (DECIDER ou ACTUATOR).
 *
 * LIGAÇÕES
 *   Criada por: DecisorClient (depois de receber uma SensorMessage) e ActuatorClient
 *   (depois de receber uma OrderMessage). Lida por: ClientHandler, que chama
 *   Topic.ack(messageId, deviceId).
 *
 * COMPARAÇÃO COM MQTT
 *   É a mesma ideia do PUBACK do MQTT QoS 1: "recebi". A diferença: no MQTT o broker não
 *   espera por todos os subscritores; no IscoT espera.
 *
 * ACK vs CONFIRMAÇÃO DE EXECUÇÃO
 *   AckMessage = "recebi e consumi" (obrigatório, fases 4–5).
 *   ReceptionConfirmationMessage = "executei a ordem, OK/NOK" (extra, fase 6).
 *
 * Fase 4 · Responsável: Celso · Revisor: Fredson
 */
public class AckMessage {
}

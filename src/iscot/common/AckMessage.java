package iscot.common;

/**
 * AckMessage — o cliente avisa o servidor de que consumiu um evento.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.2: "O consumo do evento por cada cliente deve ser registado pelo servidor,
 *   com o auxílio de um CountDownLatch. Só após todos os clientes terem consultado a
 *   mensagem é que o servidor disponibilizará a próxima mensagem do tópico."
 *   Cada AckMessage que chega faz o latch descer 1.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Campo final long eventId. Não precisa de deviceId: o servidor já sabe quem é,
 *     porque cada cliente tem o seu ClientHandler.
 *
 * LIGAÇÕES
 *   Criada por: DecisorClient e ActuatorClient, logo depois de receberem um Event.
 *   Lida por: ClientHandler, que chama Topic.ack(eventId, deviceId).
 *
 * COMPARAÇÃO COM MQTT
 *   É a mesma ideia do PUBACK do MQTT QoS 1: "recebi". A diferença é que no MQTT o
 *   broker não espera por todos os subscritores; no IscoT espera.
 *
 * ACK vs CONFIRMAÇÃO DE EXECUÇÃO
 *   AckMessage = "recebi e consumi" (obrigatório, fases 4–5).
 *   ReceptionConfirmationMessage = "executei a ordem, correu OK/NOK" (extra, fase 6).
 *
 * Fase 4 · Responsável: Celso · Revisor: Fredson
 */
public class AckMessage {
}

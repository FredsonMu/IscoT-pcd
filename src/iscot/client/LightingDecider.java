package iscot.client;

/**
 * LightingDecider — programa do decisor de iluminação.
 *
 * OBJETIVO
 *   Apresentação: "um cliente monitoriza a informação do(s) sensor(es) e toma decisões,
 *   neste caso de acender a(s) luz(es) sempre que a iluminação está abaixo de 500lux";
 *   envia AreaLightingOrderMessage.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - main(args): endereço, porto, área (mesmo formato do código base).
 *   - BrokerConnection + register(Type.DECIDER, area).
 *   - startReceiving(msg -> ...):
 *       se msg for LightSensorMessage:
 *         decisão = light < 500
 *         se a decisão for diferente da última ordem enviada:
 *           send(new AreaLightingOrderMessage(area, decisão))
 *       qualquer outra mensagem (incluindo as suas próprias ordens, que voltam pelo
 *       tópico da área): ignorar. O ack é enviado pela BrokerConnection em todos os
 *       casos.
 *   - Sem janela: o professor não deu componente de decisor e o enunciado mostra as
 *     automações pelos sensores e atuadores. Basta escrever as decisões na consola.
 *
 * PORQUÊ SÓ QUANDO A DECISÃO MUDA
 *   Se a luz continuar baixa em 10 leituras, mandar 10 vezes "acender" enche o tópico de
 *   ordens iguais, e cada uma espera pelos acks de todos. Opcional: histerese (acende
 *   abaixo de 500, apaga acima de 600) para não oscilar à volta de 500.
 *
 * THREADS
 *   A lógica corre na thread recetora da BrokerConnection.
 *
 * LIGAÇÕES
 *   Recebe de: LightSensor (via servidor). Envia para: Light (via servidor).
 *
 * Prioridade 1 (cenário do professor) · Responsável: Fredson · Revisor: Celso
 */
public class LightingDecider {
}

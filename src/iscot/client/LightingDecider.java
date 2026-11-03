package iscot.client;

/**
 * LightingDecider — programa do decisor de iluminação.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       F7 · feature/luz-rede
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        NÃO. Fase 4.
 *   No dia 6:     Nada (no dia 6 só se explica a regra dos 500 lux).
 *   Depende de:   F6 (BrokerConnection), C2.
 *   Liga-se a:    Recebe do LightSensor; envia para as Light.
 *   Código base:  Regra dos 500 lux da apresentação do professor.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
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
 */
public class LightingDecider {
}

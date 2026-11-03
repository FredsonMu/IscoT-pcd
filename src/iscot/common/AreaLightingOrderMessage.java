package iscot.common;

/**
 * AreaLightingOrderMessage — ordem para a iluminação de uma área. Nome e formato dados
 * pelo professor.
 *
 * EXEMPLO DA APRESENTAÇÃO (com as áreas do código base)
 *   AreaLightingOrderMessage [state=true, area=LivingRoom, getType()=DECIDER]
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends OrderMessage.
 *   - Campo final boolean state (true = acender). Construtor (area, state).
 *   - toString() exatamente no formato acima.
 *
 * O CENÁRIO DO PROFESSOR (sala de jantar)
 *   1 sensor de iluminação, 1 decisor, 2 lâmpadas (candeeiro e teto) = 4 clientes.
 *   O decisor acende "sempre que a iluminação está abaixo de 500 lux": envia UMA
 *   AreaLightingOrderMessage(true); o servidor entrega-a às DUAS lâmpadas (e ao
 *   próprio decisor, que também subscreve o tópico da área e só a confirma). Com o
 *   latch, o tópico só avança quando todos confirmarem.
 *
 * LIGAÇÕES
 *   Criada por: LightingDecider. Consumida por: Light, que faz LightIcon.setLigada(state)
 *   e escreve no histórico "Lâmpada ligada - HH:MM:SS".
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class AreaLightingOrderMessage {
}

package iscot.common;

/**
 * AreaLightingOrderMessage — ordem para a iluminação de uma área. Nome e formato dados
 * pelo professor.
 *
 * EXEMPLO DA APRESENTAÇÃO
 *   AreaLightingOrderMessage [state=true, area=Sala, getType()=DECIDER]
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends OrderMessage.
 *   - Construtor (area, state). state = true -> acender; false -> apagar.
 *   - getSubject() -> "luz".
 *   - toString() exatamente no formato acima.
 *
 * O CENÁRIO DO PROFESSOR (sala de jantar)
 *   1 sensor de iluminação, 1 decisor, 2 atuadores (candeeiro e teto), 4 clientes.
 *   O decisor acende as luzes "sempre que a iluminação está abaixo de 500 lux": envia UMA
 *   AreaLightingOrderMessage(state=true) e o servidor entrega-a às DUAS lâmpadas.
 *   Com o latch, o tópico só avança quando as duas confirmarem.
 *
 * LIGAÇÕES
 *   Criada por: DecisorClient (DECISOR_LUZ). Tópico: <area>/luz/ordens.
 *   Consumida por: ActuatorClient (LAMPADA), que muda o estado e escreve no histórico
 *   "Lâmpada ligada - HH:MM:SS".
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class AreaLightingOrderMessage {
}

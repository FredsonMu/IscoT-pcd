package iscot.common;

/**
 * OrderMessage — classe-mãe (abstrata) das ordens de alto nível enviadas pelos decisores.
 *
 * OBJETIVO
 *   Apresentação: "decisores: clientes reativos que reagem a leituras dos sensores e
 *   tomam decisões de alto nível"; "ordens para todas as lâmpadas ou outras fontes de
 *   iluminação"; o atuador "recebe do servidor mensagens da classe
 *   AreaLightingOrderMessage", com getType()=DECIDER.
 *
 * "DE ALTO NÍVEL" E "DA ÁREA"
 *   A ordem não diz "liga a lâmpada 3"; diz "a iluminação da LivingRoom deve estar
 *   ligada". O decisor não sabe quantas lâmpadas existem: o servidor entrega a ordem a
 *   todos os subscritores do tópico da área, e cada atuador só obedece às ordens do
 *   seu tipo (uma lâmpada ignora AreaClimateOrderMessage, mas confirma-a).
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message, abstrata.
 *   - getType() devolve sempre Type.DECIDER (final).
 *   - Construtor (area). Os campos da ordem ficam nas subclasses.
 *
 * LIGAÇÕES
 *   Subclasses: AreaLightingOrderMessage, AreaIrrigationOrderMessage,
 *   AreaClimateOrderMessage. Criadas por: LightingDecider, IrrigationDecider,
 *   ClimateDecider. Consumidas por: Light, Irrigation, Ventilator, AirConditioner.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public abstract class OrderMessage {
}

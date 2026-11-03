package iscot.common;

/**
 * OrderMessage — classe-mãe (abstrata) das ordens de alto nível enviadas pelos decisores.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C1 · feature/mensagens-base
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        SIM (fase 2). Merge HOJE, ter 3 nov.
 *   No dia 6:     Classe-mãe da AreaLightingOrderMessage que a Light cria localmente no dia 6.
 *   Depende de:   Message.
 *   Liga-se a:    Estendida por AreaLightingOrderMessage, AreaIrrigationOrderMessage, AreaClimateOrderMessage.
 *   Código base:  getType() devolve Type.DECIDER (enum do professor).
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
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
 */
public abstract class OrderMessage {
}

package iscot.common;

/**
 * AreaIrrigationOrderEvent — ordem de rega para uma área.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C3 · feature/eventos-restantes · CELSO, 11.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         2 (dom 8 nov)
 *   Precisa de:    AreaLightingOrderEvent, como modelo.
 *   Quem espera:   o próprio Celso, para a Irrigation (C6).
 *   A seguir:      fechar o pull request C3: é a ENTREGA B ao Fredson.
 * ==================================================================================
 *
 * FORMATO
 *   AreaIrrigationOrderEvent [state=true, area=Garden, getType()=DECIDER]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Repetir o modelo do AreaLightingOrderEvent
 *     Lógica sugerida: mesma estrutura; o estado diz se é para regar ou parar.
 *     Liga-se a: AreaLightingOrderEvent; Irrigation.
 *
 *   Passo 2 · Confirmar e entregar
 *     O texto corresponde ao formato acima. Abrir o pull request C3 com as 6 classes e avisar o
 *     Fredson: fica desbloqueado o TemperatureSensor, o Ventilator e o AirConditioner.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *     A) Só o estado. Chega para o que o enunciado pede. [sugestão]
 *     B) Estado, zona e duração, que o IrrigationIcon também suporta. Não é pedido.
 */
public class AreaIrrigationOrderEvent {
}

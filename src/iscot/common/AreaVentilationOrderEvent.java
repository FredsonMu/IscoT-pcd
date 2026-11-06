package iscot.common;

/**
 * AreaVentilationOrderEvent — ordem de ventilação para uma área.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C3 · feature/eventos-restantes · CELSO, 9.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         2 (dom 8 nov). Parte da ENTREGA B ao Fredson.
 *   Precisa de:    AreaLightingOrderEvent, como modelo.
 *   Quem espera:   Fredson, para o Ventilator (F4) e o AirConditioner (F6).
 * ==================================================================================
 *
 * FORMATO
 *   AreaVentilationOrderEvent [state=true, area=LivingRoom, getType()=DECIDER]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Repetir o modelo do AreaLightingOrderEvent
 *     Lógica sugerida: mesma estrutura; o estado diz se é para ventilar ou não.
 *     Liga-se a: AreaLightingOrderEvent; Ventilator; AirConditioner.
 *
 *   Passo 2 · Não colocar aqui a regra do ar condicionado
 *     Lógica sugerida: o evento só transporta a ordem.
 *     Porquê: o enunciado (1.2) diz que os atuadores ligam em modo ventilação "apenas se não
 *       estiver já em algum dos modos aquecimento ou arrefecimento". Só o AirConditioner sabe o
 *       modo em que está, portanto a regra é dele.
 *     Liga-se a: AirConditioner, Passo 4.
 *
 *   Passo 3 · Confirmar
 *     O texto corresponde ao formato acima.
 */
public class AreaVentilationOrderEvent {
}

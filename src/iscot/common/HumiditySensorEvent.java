package iscot.common;

/**
 * HumiditySensorEvent — leitura da humidade do AR, em percentagem.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C3 · feature/eventos-restantes · CELSO, 7.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         2 (dom 8 nov)
 *   Precisa de:    LightSensorEvent, como modelo.
 *   Quem espera:   o próprio Celso, para o HumiditySensor (C5).
 * ==================================================================================
 *
 * FORMATO
 *   HumiditySensorEvent [humidity=72.0, area=LivingRoom, getType()=SENSOR]
 *
 * NÃO CONFUNDIR COM O SOLO
 *   Na versão 4 do enunciado há DUAS humidades:
 *     - Humidade do ar (esta classe): leva a uma ordem de VENTILAÇÃO.
 *     - Humidade do solo (GroundMoistureSensorEvent): leva a uma ordem de REGA.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Repetir o modelo do LightSensorEvent
 *     Lógica sugerida: mesma estrutura e mesma ordem no construtor; muda o nome do campo.
 *     Liga-se a: LightSensorEvent; HumiditySensor.
 *
 *   Passo 2 · Confirmar
 *     O texto corresponde ao formato acima.
 */
public class HumiditySensorEvent {
}

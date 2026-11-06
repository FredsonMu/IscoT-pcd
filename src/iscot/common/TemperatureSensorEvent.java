package iscot.common;

/**
 * TemperatureSensorEvent — leitura de temperatura, em graus Celsius.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C3 · feature/eventos-restantes · CELSO, 6.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         2 (dom 8 nov). Parte da ENTREGA B ao Fredson.
 *   Precisa de:    LightSensorEvent, como modelo.
 *   Quem espera:   Fredson, para o TemperatureSensor (F5).
 * ==================================================================================
 *
 * FORMATO
 *   TemperatureSensorEvent [temperature=27.0, area=LivingRoom, getType()=SENSOR]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Repetir o modelo do LightSensorEvent
 *     Lógica sugerida: mesma estrutura e mesma ordem no construtor; muda o nome do campo.
 *     Porquê: o contrato é igual em todas as leituras, portanto quem usa uma sabe usar as outras.
 *     Liga-se a: LightSensorEvent; TemperatureSensor.
 *
 *   Passo 2 · Não limitar o valor
 *     Lógica sugerida: aceitar valores negativos e com casas decimais.
 *     Porquê: uma leitura é um facto. Quem limita o intervalo é o SensorControl, na janela.
 *
 *   Passo 3 · Confirmar
 *     O texto corresponde ao formato acima.
 *
 * NAS FASES SEGUINTES (enunciado, 1.2)
 *   "se o termómetro deteta a ultrapassagem de dada temperatura, espoleta a ligação de um ar
 *   condicionado, para aquecer ou arrefecer".
 */
public class TemperatureSensorEvent {
}

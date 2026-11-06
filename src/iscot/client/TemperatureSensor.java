package iscot.client;

/**
 * TemperatureSensor — programa do termómetro.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F5 · feature/temperature-sensor · FREDSON, 5.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          1 (GUI) e 3 (gerar eventos, a exibir na consola)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    ENTREGA B do Celso (TemperatureSensorEvent); F2 (LightSensor, como guia).
 *   Molde:         LightSensor.
 * ==================================================================================
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Partir do LightSensor
 *     Lógica sugerida: mesma estrutura; mudam o título, a unidade e o intervalo.
 *     Porquê: os slides mostram este sensor na figura de GUI, ao lado do de iluminação.
 *     Liga-se a: LightSensor; launch "02 Sensor temperatura LivingRoom".
 *
 *   Passo 2 · Escolher o intervalo
 *     Lógica sugerida: ver as opções.
 *     Liga-se a: SensorControl (marcas do slider).
 *
 *   Passo 3 · No callback, criar e mostrar o evento
 *     Lógica sugerida: criar um TemperatureSensorEvent e imprimi-lo.
 *     Liga-se a: TemperatureSensorEvent.
 *     Formato: TemperatureSensorEvent [temperature=27.0, area=LivingRoom, getType()=SENSOR]
 *
 *   Passo 4 · Confirmar
 *     Cada largada do slider imprime uma linha com o valor da etiqueta.
 *
 * CONCEITOS DA UC A USAR
 *   Os mesmos do LightSensor: callback, lambda, thread do Swing.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Intervalo do SensorControl:
 *     A) 0 a 50 com passo 1. As marcas do slider coincidem com os graus. [sugestão]
 *     B) Começar abaixo de zero (ex.: -10 a 50). Mais realista, mas as marcas mostram o número do
 *        passo e deixam de coincidir com os graus (a marca 0 representa -10).
 */
public class TemperatureSensor {
}

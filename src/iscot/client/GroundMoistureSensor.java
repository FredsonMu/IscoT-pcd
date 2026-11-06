package iscot.client;

/**
 * GroundMoistureSensor — programa do sensor de humidade do solo.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C5 · feature/sensores-humidade · CELSO, 14.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          1 (GUI) e 3 (gerar eventos, a exibir na consola)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    C3 (GroundMoistureSensorEvent); HumiditySensor concluído.
 *   Molde:         HumiditySensor do professor.
 * ==================================================================================
 *
 * PORQUÊ ESTA CLASSE
 *   A versão 4 do enunciado separa a humidade do ar da do solo. O código base só traz o
 *   HumiditySensor (ar). Sem esta classe, o GroundMoistureSensorEvent não teria janela.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Partir do HumiditySensor já tratado
 *     Lógica sugerida: mesma estrutura, de 0 a 100 %.
 *     Liga-se a: HumiditySensor; launch "04 Sensor humidade solo Garden".
 *
 *   Passo 2 · Deixar claro que é o solo
 *     Lógica sugerida: título, nome e ícone diferentes da humidade do ar.
 *     Porquê: na demo, as duas janelas não podem ser confundidas.
 *
 *   Passo 3 · No callback, criar e mostrar o evento
 *     Lógica sugerida: criar um GroundMoistureSensorEvent e imprimi-lo.
 *     Liga-se a: GroundMoistureSensorEvent.
 *     Formato: GroundMoistureSensorEvent [moisture=18.0, area=Garden, getType()=SENSOR]
 *
 *   Passo 4 · Confirmar
 *     Cada largada do slider imprime uma linha. Abrir o pull request C5.
 *
 * CONCEITOS DA UC A USAR
 *   Callback, lambda e thread do Swing. Ver as notas do LightSensor.
 */
public class GroundMoistureSensor {
}

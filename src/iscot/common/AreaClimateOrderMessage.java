package iscot.common;

/**
 * AreaClimateOrderMessage — ordem para a climatização de uma área (ar condicionado e
 * ventilador).
 *
 * OBJETIVO
 *   Enunciado: "se o termómetro deteta a ultrapassagem de dada temperatura, espoleta a
 *   ligação de um ar condicionado". A apresentação mostra a GUI de um "ventilador" como
 *   atuador e refere "ar condicionado, ventilador" como dispositivos.
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que AreaLightingOrderMessage)
 *   - extends OrderMessage.
 *   - Construtor (area, state). state = true -> arrefecer; false -> desligar.
 *   - getSubject() -> "temperatura".
 *   - toString(): AreaClimateOrderMessage [state=true, area=Sala, getType()=DECIDER]
 *
 * PORQUÊ UMA ORDEM PARA DOIS TIPOS DE ATUADOR
 *   É uma ordem de alto nível: "arrefecer a sala". O ar condicionado e o ventilador
 *   subscrevem o mesmo tópico (<area>/temperatura/ordens) e cada um executa-a à sua
 *   maneira. O decisor não precisa de saber que dispositivos existem.
 *
 * ALTERNATIVA
 *   Uma ordem por dispositivo (ArCondicionadoOrder, VentiladorOrder): o decisor teria de
 *   conhecer os dispositivos, o que contraria "ordens de alto nível".
 *
 * LIGAÇÕES
 *   Criada por: DecisorClient (DECISOR_CLIMA). Consumida por: ActuatorClient
 *   (AR_CONDICIONADO, VENTILADOR).
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public class AreaClimateOrderMessage {
}

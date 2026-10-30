package iscot.common;

/**
 * Role — o papel de um cliente no sistema: SENSOR, DECISOR ou ATUADOR.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1: "clientes, que podem ser sensores, decisores ou atuadores".
 *   O papel decide o que o cliente faz com os tópicos:
 *     SENSOR  -> só publica (leituras)
 *     DECISOR -> subscreve leituras e publica ordens
 *     ATUADOR -> só subscreve (ordens)
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - As 3 constantes. Nada mais.
 *
 * LIGAÇÕES
 *   É usada por: DeviceType (cada tipo tem um papel), Topics (decide pub/sub),
 *   ClientMain (decide que classe de cliente arrancar).
 *
 * PORQUÊ SEPARAR Role DE DeviceType
 *   Há muitos tipos (SENSOR_LUZ, LAMPADA...) mas só 3 comportamentos. Separar evita
 *   "if (tipo == SENSOR_LUZ || tipo == SENSOR_TEMPERATURA || ...)" espalhados pelo
 *   código: pergunta-se só "tipo.getRole() == SENSOR".
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public enum Role {
}

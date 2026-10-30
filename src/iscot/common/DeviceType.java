package iscot.common;

/**
 * DeviceType — todos os tipos de dispositivo que o servidor aceita, o papel de cada um
 * e a regra que dá nome aos tópicos.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Enunciado 2.1: "Se o tipo não existir a ligação é recusada." Precisamos de uma lista
 *   fechada de tipos válidos. Apresentação: "Tipos de clientes: sensores, decisores e
 *   atuadores", e nas mensagens getType() devolve SENSOR ou DECIDER.
 *
 * O QUE VAMOS IMPLEMENTAR
 *
 *   1) Enum interno Role { SENSOR, DECIDER, ACTUATOR }
 *      Nomes em inglês, iguais aos que o professor mostra em getType().
 *        SENSOR   -> cliente ativo: só publica (leituras)
 *        DECIDER  -> cliente reativo: subscreve leituras e publica ordens
 *        ACTUATOR -> cliente passivo: só subscreve (ordens)
 *      Usa-se de fora como DeviceType.Role.SENSOR.
 *
 *   2) As constantes, agrupadas por grandeza (o "assunto" que liga os três papéis):
 *        luz          : SENSOR_LUZ, DECISOR_LUZ, LAMPADA
 *        temperatura  : SENSOR_TEMPERATURA, DECISOR_CLIMA, AR_CONDICIONADO, VENTILADOR
 *        humidade     : SENSOR_HUMIDADE, DECISOR_REGA, REGA            (depois)
 *        chuva        : SENSOR_CHUVA, DECISOR_JANELAS, JANELA          (depois)
 *      Cada constante guarda dois campos final: o Role e a grandeza ("luz", ...),
 *      passados a um construtor privado do enum. Getters para os dois.
 *      Os nomes das constantes são os que se escrevem nos argumentos do cliente
 *      (ex.: "localhost 8080 sala LAMPADA").
 *
 *   3) parse(String): trim + toUpperCase + valueOf. Um tipo inexistente lança
 *      IllegalArgumentException, que o ClientHandler apanha para recusar a ligação.
 *
 *   4) Nomes dos tópicos (convenção inspirada no MQTT: area/grandeza/canal), métodos
 *      estáticos usados no registo e na publicação:
 *        readingsTopic(area, grandeza) -> "sala/luz/leituras"
 *        ordersTopic(area, grandeza)   -> "sala/luz/ordens"
 *      e de instância, usados no registo:
 *        subscribeTopic(area) -> o que este tipo subscreve, ou null
 *                                DECIDER: leituras da sua grandeza; ACTUATOR: ordens
 *      Na publicação, o servidor calcula o tópico a partir da MENSAGEM (área + grandeza
 *      + SensorMessage/OrderMessage), não de um tópico escrito pelo cliente.
 *
 * PORQUÊ O CLIENTE NÃO ESCREVE O TÓPICO
 *   Apresentação: "cada cliente apenas conhece o servidor a que se liga". O cliente diz
 *   o que é (tipo e área) e envia mensagens tipadas; o servidor é que organiza tópicos.
 *   Assim um sensor não consegue publicar por engano no tópico errado.
 *
 * PORQUÊ TUDO NUMA CLASSE
 *   Papel, grandeza e tópicos são três faces da mesma informação: "o que é este
 *   dispositivo e com quem fala". Acrescentar um tipo novo é acrescentar uma linha.
 *
 * LIGAÇÕES
 *   Usa: Area. É usada por: ClientHandler (valida e obtém tópicos), Server (gera ids),
 *   ClientMain (decide que cliente arrancar), Message e filhas (getType), TopicTestFrame.
 *
 * CONCORRÊNCIA
 *   Nenhuma: enums são imutáveis e únicos na JVM (estratégia "imutabilidade").
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Role e nomes de tópicos em classes separadas: cada uma mais pequena, mas mais
 *     ficheiros para algo que é sempre usado em conjunto.
 *   - Hierarquia de classes (class Lampada extends Atuador): mais OO, mas o servidor só
 *     precisa de dados, não de comportamento.
 *   - Tópico sem área ("luz/ordens"): acender as luzes da sala acendia as do quarto.
 *   - Wildcards como no MQTT ("+/luz/ordens"): flexível, mas obriga a correspondência
 *     de padrões no TopicManager. Melhoria opcional.
 *
 * DÚVIDAS
 *   - O decisor consome um tópico e publica noutro; confirmar que é aceitável.
 *   - O tipo enviado no registo é o tipo de dispositivo (LAMPADA) ou só o papel
 *     (ACTUATOR)? A apresentação diz "identificadores do tipo de cliente e área".
 *     Nós enviamos o tipo de dispositivo, que inclui o papel. Confirmar.
 *
 * COMO TESTAR
 *   readingsTopic(SALA, "luz") == "sala/luz/leituras"; LAMPADA.subscribeTopic(SALA) ==
 *   "sala/luz/ordens"; SENSOR_LUZ.subscribeTopic(SALA) == null; parse("torradeira") falha.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public enum DeviceType {
}

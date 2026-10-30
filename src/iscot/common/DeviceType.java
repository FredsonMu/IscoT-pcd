package iscot.common;

/**
 * DeviceType — todos os tipos de dispositivo que o servidor aceita, o papel de cada um
 * e a regra que dá nome aos tópicos.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.1: "Se o tipo não existir a ligação é recusada." Precisamos de uma lista
 *   fechada de tipos válidos. Secção 1: "clientes, que podem ser sensores, decisores ou
 *   atuadores". Secção 1: as mensagens "são separadas no servidor em tópicos".
 *
 * O QUE VAMOS IMPLEMENTAR
 *
 *   1) Enum interno Role { SENSOR, DECISOR, ATUADOR }
 *      O papel decide o que o cliente faz com os tópicos:
 *        SENSOR  -> só publica (leituras)
 *        DECISOR -> subscreve leituras e publica ordens
 *        ATUADOR -> só subscreve (ordens)
 *      Fica dentro do DeviceType porque só faz sentido associado a um tipo.
 *      Usa-se de fora como DeviceType.Role.SENSOR.
 *
 *   2) As constantes, agrupadas por grandeza (o "assunto" que liga os três papéis):
 *        luz          : SENSOR_LUZ, DECISOR_LUZ, LAMPADA
 *        temperatura  : SENSOR_TEMPERATURA, DECISOR_CLIMA, AR_CONDICIONADO
 *        humidade     : SENSOR_HUMIDADE, DECISOR_REGA, REGA
 *        chuva        : SENSOR_CHUVA, DECISOR_JANELAS, JANELA
 *      Cada constante guarda dois campos final: o Role e a grandeza ("luz", ...),
 *      passados a um construtor privado do enum. Getters para os dois.
 *
 *   3) parse(String): trim + toUpperCase + valueOf. Um tipo inexistente lança
 *      IllegalArgumentException, que o ClientHandler apanha para recusar a ligação.
 *
 *   4) Nomes dos tópicos (convenção inspirada no MQTT: area/grandeza/canal):
 *        publishTopic(area)   -> onde este tipo publica, ou null
 *                                SENSOR: "sala/luz/leituras"; DECISOR: "sala/luz/ordens"
 *        subscribeTopic(area) -> o que este tipo subscreve, ou null
 *                                DECISOR: "sala/luz/leituras"; ATUADOR: "sala/luz/ordens"
 *      Métodos de instância: LAMPADA.subscribeTopic(Area.SALA) -> "sala/luz/ordens".
 *
 * PORQUÊ TUDO NUMA CLASSE
 *   Papel, grandeza e tópicos são três faces da mesma informação: "o que é este
 *   dispositivo e com quem fala". Juntos, acrescentar um tipo novo (SENSOR_FUMO) é
 *   acrescentar uma linha; o nome dos tópicos é calculado num único sítio, e um "/"
 *   esquecido não faz o sensor publicar num tópico que ninguém ouve.
 *
 * LIGAÇÕES
 *   Usa: Area. É usada por: ClientHandler (valida e obtém tópicos), Server (gera ids),
 *   ClientMain (decide que cliente arrancar), clientes (onde publicar),
 *   TopicTestFrame (sugestões de tópicos).
 *
 * CONCORRÊNCIA
 *   Nenhuma: enums são imutáveis e únicos na JVM (estratégia "imutabilidade").
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Role e Topics como classes separadas: cada uma mais pequena e com um só assunto,
 *     mas mais ficheiros para algo que é sempre usado em conjunto.
 *   - Hierarquia de classes (class Lampada extends Atuador): mais OO, mas o servidor só
 *     precisa de dados, não de comportamento.
 *   - Strings soltas ("LAMPADA"): sem lista única de valores válidos; erros de escrita
 *     só aparecem em execução.
 *   - Tópico sem área ("luz/ordens"): acender as luzes da sala acendia as do quarto.
 *   - Wildcards como no MQTT ("+/luz/ordens"): flexível, mas obriga a fazer
 *     correspondência de padrões no TopicManager. Melhoria opcional.
 *
 * DÚVIDAS
 *   - Enunciado: "Um cliente publica e consome mensagens exclusivamente de um tópico".
 *     O decisor usa dois (lê leituras, publica ordens). Confirmar se é aceitável.
 *   - Recusar combinações sem sentido, como "sala REGA"? Por agora aceitamos.
 *   - O professor espera nomes de tipos em particular na linha de comandos?
 *
 * COMO TESTAR
 *   SENSOR_LUZ.publishTopic(SALA) == "sala/luz/leituras"; SENSOR_LUZ.subscribeTopic(SALA)
 *   == null; LAMPADA.subscribeTopic(SALA) == "sala/luz/ordens"; parse("torradeira") falha.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public enum DeviceType {
}

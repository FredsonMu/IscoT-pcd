package iscot.common;

/**
 * Topics — a regra única que dá nome aos tópicos.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1: "As mensagens são separadas no servidor em tópicos, que são agrupamentos
 *   de mensagens de um mesmo tema". Alguém tem de decidir que tema é cada mensagem.
 *
 * CONVENÇÃO (inspirada no MQTT)
 *   area/grandeza/canal
 *     sala/luz/leituras      <- o sensor de luz da sala publica aqui
 *     sala/luz/ordens        <- o decisor de luz da sala publica aqui
 *   O decisor subscreve "leituras", os atuadores subscrevem "ordens".
 *
 * O QUE VAMOS IMPLEMENTAR (métodos estáticos, a classe nunca é instanciada)
 *   - readings(area, grandeza) -> "sala/luz/leituras"
 *   - orders(area, grandeza)   -> "sala/luz/ordens"
 *   - publishTopic(area, tipo) -> o tópico onde o dispositivo publica, ou null
 *   - subscribeTopic(area, tipo) -> o tópico que o dispositivo subscreve, ou null
 *     (as duas últimas fazem switch pelo Role do tipo)
 *   - Construtor privado para ninguém fazer "new Topics()".
 *
 * LIGAÇÕES
 *   Usa: Area, DeviceType, Role. É usada por: ClientHandler (no registo),
 *   clientes (para saber onde publicar), TopicTestFrame (sugestões de tópicos).
 *
 * PORQUÊ UMA CLASSE SÓ PARA ISTO
 *   Se o nome dos tópicos estiver montado em vários sítios, basta um esquecer-se do "/"
 *   para o sensor publicar num tópico que ninguém subscreve, e o erro é silencioso.
 *   Um único sítio = uma única fonte de verdade.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Tópico por grandeza sem área ("luz/ordens"): um decisor controla a casa toda,
 *     mas acender as luzes da sala acendia também as do quarto.
 *   - Wildcards como no MQTT ("+/luz/ordens"): muito útil, mas obriga a fazer
 *     correspondência de padrões no TopicManager. Fica como melhoria opcional.
 *
 * DÚVIDAS
 *   - Enunciado: "Um cliente publica e consome mensagens exclusivamente de um tópico".
 *     O decisor usa dois (lê leituras, publica ordens). Confirmar se é aceitável.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public final class Topics {
}

package iscot.common;

/**
 * RegisterRequest — a primeira mensagem que um cliente envia depois de ligar.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.1: "A área da casa, bem como o tipo de cliente devem ser enviados ao
 *   servidor aquando da ligação inicial. Se o tipo não existir a ligação é recusada."
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Dois campos final do tipo String: area e tipo, exatamente como vieram do main.
 *   - Construtor e getters.
 *
 * PORQUÊ STRING E NÃO OS ENUMS
 *   Se o cliente convertesse para DeviceType antes de enviar, um tipo inválido
 *   rebentava no próprio cliente e o servidor nunca teria nada para recusar. O
 *   enunciado quer que seja o servidor a recusar, por isso a validação é lá.
 *
 * LIGAÇÕES
 *   Criada por: BrokerConnection.register. Lida por: ClientHandler, logo a seguir
 *   a abrir os streams, antes do ciclo principal.
 *
 * Fase 3 · Responsável: Celso · Revisor: Fredson
 */
public class RegisterRequest {
}

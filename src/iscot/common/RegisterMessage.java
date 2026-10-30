package iscot.common;

/**
 * RegisterMessage — o registo do cliente: o pedido (cliente -> servidor) e a resposta
 * (servidor -> cliente), na mesma classe.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.1: "Ao iniciar a aplicação, o cliente inscreve-se no servidor, que criará
 *   para ele um identificador de dispositivo, que deve ser único em todo o sistema. A
 *   área da casa, bem como o tipo de cliente devem ser enviados ao servidor aquando da
 *   ligação inicial. Se o tipo não existir a ligação é recusada."
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Campos final:
 *       area, tipo (String)  -> preenchidos no pedido
 *       aceite (boolean), deviceId, motivo (String) -> preenchidos na resposta
 *   - Dois métodos estáticos de fábrica, para nunca se confundir o que é preenchido:
 *       pedido(area, tipo)
 *       resposta(aceite, deviceId, motivo)
 *     (construtor privado com todos os campos)
 *   - Getters, e um isPedido() (true se tiver area/tipo).
 *
 * PORQUÊ STRING PARA A ÁREA E O TIPO
 *   Se o cliente convertesse para enum antes de enviar, um tipo inválido rebentava no
 *   próprio cliente e o servidor nunca teria nada para recusar. O enunciado quer que
 *   seja o servidor a recusar, por isso a validação é lá (Area.parse, DeviceType.parse).
 *
 * PORQUÊ RESPONDER EM VEZ DE SÓ FECHAR O SOCKET
 *   Fechar sem resposta deixa o cliente com um "Connection reset" sem saber porquê.
 *   Uma resposta com motivo ("Tipo inexistente: TORRADEIRA") ajuda o utilizador e a
 *   depurar.
 *
 * LIGAÇÕES
 *   Pedido criado por: BrokerConnection.register. Lido por: ClientHandler, logo depois
 *   de abrir os streams, antes do ciclo principal.
 *   Resposta criada por: ClientHandler. Lida por: BrokerConnection.register, que lança
 *   uma exceção com o motivo se não for aceite.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Duas classes (RegisterRequest e RegisterReply): cada uma só com os seus campos e
 *     mais explícita no "instanceof"; custo: mais um ficheiro para um passo que acontece
 *     uma vez por ligação. Escolhemos uma só porque o registo é sempre um par
 *     pedido/resposta e nunca aparece mais nada pelo meio.
 *
 * Fase 3 · Responsável: Celso · Revisor: Fredson
 */
public class RegisterMessage {
}

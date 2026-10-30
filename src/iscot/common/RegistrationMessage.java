package iscot.common;

/**
 * RegistrationMessage — o registo do cliente no servidor: o pedido (cliente -> servidor)
 * e a resposta (servidor -> cliente). Nome dado pelo professor na apresentação do projeto.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Apresentação: "Ligação resulta de um pedido explícito de um cliente ao servidor,
 *   através do envio de uma mensagem RegistrationMessage, que inclui os identificadores
 *   do tipo de cliente e área da casa; após recebido o pedido, passa a estar estabelecida
 *   uma ligação conceptual entre o cliente e o servidor, em ambos os sentidos."
 *   Enunciado 2.1: o servidor cria um identificador "único em todo o sistema";
 *   "Se o tipo não existir a ligação é recusada."
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Campos final:
 *       areaText, typeText (String)            -> preenchidos no pedido
 *       accepted (boolean), deviceId, reason   -> preenchidos na resposta
 *   - Dois métodos estáticos de fábrica, para nunca se confundir o que é preenchido:
 *       request(areaText, typeText)
 *       reply(accepted, deviceId, reason)
 *     (construtor privado com todos os campos)
 *   - isRequest(), getters.
 *   - getType(): devolve o Role do pedido, se for válido, ou null (a mensagem de registo
 *     não é publicada em tópicos, por isso o tipo aqui é só informativo).
 *
 * PORQUÊ TEXTO PARA A ÁREA E O TIPO (e não os enums)
 *   Se o cliente convertesse para enum antes de enviar, um tipo inválido rebentava no
 *   próprio cliente e o servidor nunca teria nada para recusar. O enunciado quer que seja
 *   o servidor a recusar, por isso a validação é lá (Area.parse, DeviceType.parse).
 *   Nota: por isso esta é a única mensagem que não preenche o campo "area" da classe-mãe
 *   no cliente; o servidor preenche-o (ou recusa) ao validar.
 *
 * PORQUÊ RESPONDER EM VEZ DE SÓ FECHAR O SOCKET
 *   Fechar sem resposta deixa o cliente com "Connection reset" sem saber porquê. Uma
 *   resposta com motivo ("Tipo inexistente: TORRADEIRA") ajuda o utilizador e a depurar.
 *
 * LIGAÇÕES
 *   Pedido criado por: BrokerConnection.register. Lido por: ClientHandler, logo depois
 *   de abrir os streams, antes do ciclo principal.
 *   Resposta criada por: ClientHandler. Lida por: BrokerConnection.register, que lança
 *   uma exceção com o motivo se não for aceite.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Duas classes (pedido e resposta): cada uma só com os seus campos; mas o professor
 *     fala numa só RegistrationMessage, e o registo é sempre um par pedido/resposta.
 *
 * Fase 3 · Responsável: Celso · Revisor: Fredson
 */
public class RegistrationMessage {
}

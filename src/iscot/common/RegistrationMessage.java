package iscot.common;

/**
 * RegistrationMessage — o registo do cliente no servidor: o pedido (cliente -> servidor)
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C6 · feature/registration
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        NÃO. Fase 3, para 10–13 nov.
 *   No dia 6:     Nada.
 *   Depende de:   C1.
 *   Liga-se a:    Enviada pela BrokerConnection; lida pelo ClientHandler.
 *   Código base:  Nome dado pelo professor; leva Type e Areas do código base.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 * e a resposta (servidor -> cliente). Nome dado pelo professor na apresentação.
 *
 * OBJETIVO
 *   Apresentação: "Ligação resulta de um pedido explícito de um cliente ao servidor,
 *   através do envio de uma mensagem RegistrationMessage, que inclui os identificadores
 *   do tipo de cliente e área da casa; após recebido o pedido, passa a estar
 *   estabelecida uma ligação conceptual entre o cliente e o servidor, em ambos os
 *   sentidos."
 *   Enunciado 2.1: o servidor cria um identificador "único em todo o sistema";
 *   "Se o tipo não existir a ligação é recusada."
 *   Código base: os mains recebem a área em args[2] e fazem Areas.valueOf(args[2]);
 *   o tipo é o do próprio programa (um Ventilator é ACTUATOR).
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Pedido: type (iscot.enums.Type) e area (iscot.enums.Areas, na classe-mãe).
 *   - Resposta: accepted (boolean), deviceId (String), reason (String).
 *   - Métodos de fábrica: request(type, area) e reply(accepted, deviceId, reason);
 *     construtor privado. isRequest(), getters.
 *
 * "SE O TIPO NÃO EXISTIR A LIGAÇÃO É RECUSADA"
 *   Com os enums do professor, o tipo é sempre um valor do enum Type. O servidor
 *   recusa: type == null, area == null, e o tipo DWC (o professor escreve "DWC pode ser
 *   ignorado"). Responde com reply(false, null, motivo) e fecha a ligação.
 *
 * LIGAÇÕES
 *   Pedido criado por: BrokerConnection.register. Lido por: ClientHandler, primeira
 *   mensagem depois de abrir os streams.
 *   Resposta criada por: ClientHandler. Lida por: BrokerConnection.register, que lança
 *   uma exceção com o motivo se não for aceite.
 *
 */
public class RegistrationMessage {
}

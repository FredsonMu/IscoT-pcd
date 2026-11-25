package iscot.common;

/**
 * RegistrationReplyEvent — a resposta do servidor ao RegistrationEvent: aceite (com o id) ou recusado
 * (com o motivo).
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F8 · feature/registo-resposta · FREDSON, 1.ª CLASSE DAS FASES 4 E 5
 *   Revisor:       Celso
 *   Fase:          4 (servidor e ligação inicial)
 *   Etapa:         1. É a primeira a fazer: o Celso precisa dela para a BrokerConnection (C7).
 *   Precisa de:    Event (já na main); o contrato combinado no Ponto 0.
 *   Quem espera:   Server e ClientHandler (Fredson), BrokerConnection (Celso).
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE (2.1)
 *   "o cliente inscreve-se no servidor, que criará para ele um identificador de dispositivo, que deve
 *   ser único em todo o sistema." / "Se o tipo não existir a ligação é recusada."
 *   O cliente precisa de saber se entrou e com que id. É esta classe que leva essa resposta.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Decidir onde fica na hierarquia
 *     Lógica sugerida: estender o Event, como o RegistrationEvent (área + tipo).
 *     Porquê: assim viaja pelo mesmo canal de objetos e herda o Serializable e o formato do texto.
 *     Liga-se a: Event; RegistrationEvent (o pedido).
 *
 *   Passo 2 · Os dados da resposta
 *     Lógica sugerida: três campos imutáveis: se foi aceite, o id atribuído (só quando aceite) e o
 *       motivo (só quando recusado).
 *     Porquê: o cliente decide o que fazer só com isto: continuar com o id, ou mostrar o motivo e sair.
 *     Liga-se a: BrokerConnection, que lê a resposta.
 *
 *   Passo 3 · Criar de forma clara
 *     Lógica sugerida: duas formas de criar, uma para "aceite" e outra para "recusado" (pesquisar
 *       "métodos de fábrica estáticos"), em vez de um construtor com campos que ficam a null.
 *     Porquê: não se consegue criar por engano uma resposta "aceite" sem id.
 *
 *   Passo 4 · Texto e confirmação
 *     Formato sugerido: RegistrationReplyEvent [accepted=true, id=LivingRoom-ACTUATOR-3, area=..., ...]
 *     Acrescentar ao IscotTests a verificação do texto e da serialização.
 *
 * CONCEITOS DA CADEIRA
 *   Herança; imutabilidade (final); serialização (vai pelo socket).
 *
 * OPÇÕES (escolher no Ponto 0)
 *   A) Classe nova, como aqui. Cada classe só tem o que usa. [sugestão]
 *   B) Acrescentar os campos ao RegistrationEvent. Menos classes, mas o pedido fica com campos vazios.
 *   Formato do id: "LivingRoom-ACTUATOR-3" (legível nos logs) ou só um número.
 *
 * PRONTA QUANDO
 *   As duas formas de criar funcionam, o texto está no formato combinado e o IscotTests confirma que
 *   serializa.
 */
public class RegistrationReplyEvent {
}

package iscot.common;

/**
 * OrderMessage — classe-mãe (abstrata) das ordens de alto nível enviadas pelos decisores.
 *
 * OBJETIVO NA APRESENTAÇÃO DO PROFESSOR
 *   "decisores: clientes reativos que reagem a leituras dos sensores e tomam decisões de
 *   alto nível: gerir iluminação, ar condicionado ou rega"; "ordens para todas as
 *   lâmpadas ou outras fontes de iluminação". O atuador "recebe do servidor mensagens da
 *   classe AreaLightingOrderMessage", com getType()=DECIDER.
 *
 * "DE ALTO NÍVEL" E "DA ÁREA"
 *   A ordem não diz "liga a lâmpada 3"; diz "a iluminação da sala deve estar ligada".
 *   O decisor não sabe quantas lâmpadas existem nem quais são: o servidor entrega a ordem
 *   a todos os atuadores subscritos no tópico de ordens daquela área e grandeza. É isto
 *   que o "Area" do nome AreaLightingOrderMessage quer dizer.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message, abstrata.
 *   - getType() devolve sempre Role.DECIDER.
 *   - Campo final boolean state (true = ligar / ativar; false = desligar).
 *     Todas as ordens que temos são ligar/desligar; se uma precisar de mais (ex.: abrir
 *     janela a 50%), a subclasse acrescenta campos.
 *   - abstract String getSubject(): a grandeza; o servidor publica em
 *         area + "/" + getSubject() + "/ordens"       ex.: sala/luz/ordens
 *
 * LIGAÇÕES
 *   Subclasses: AreaLightingOrderMessage, AreaClimateOrderMessage (e mais tarde rega,
 *   janelas). Criadas por: DecisorClient. Publicadas por: ClientHandler -> Topic.
 *   Consumidas por: ActuatorClient.
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public abstract class OrderMessage {
}

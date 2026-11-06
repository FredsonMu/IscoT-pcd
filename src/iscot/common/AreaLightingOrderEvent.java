package iscot.common;

/**
 * AreaLightingOrderEvent — ordem para a iluminação de uma área. É O MODELO DAS OUTRAS ORDENS.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C2 · feature/eventos-modelo · CELSO, 5.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         1 (sáb 7 nov). ENTREGA A AO FREDSON, com o LightSensorEvent.
 *   Precisa de:    C1 na main.
 *   Quem espera:   Fredson, para a Light (F3).
 * ==================================================================================
 *
 * FORMATO OBRIGATÓRIO (slides, com o novo nome e as áreas do código base)
 *   AreaLightingOrderEvent [state=true, area=LivingRoom, getType()=DECIDER]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Estender o HighLevelOrderEvent
 *     Lógica sugerida: classe concreta; o tipo e a área já vêm de cima.
 *     Liga-se a: HighLevelOrderEvent.
 *
 *   Passo 2 · Guardar o estado pretendido
 *     Lógica sugerida: um campo imutável chamado state, verdadeiro para ligar.
 *     Porquê: os slides mostram state=true, e uma ordem de alto nível só diz o que se pretende.
 *     Liga-se a: Light, que transforma este estado no ícone e no histórico.
 *
 *   Passo 3 · Construtor e consulta, conforme o contrato
 *     Lógica sugerida: primeiro a área, depois o estado, e um método para consultar o estado.
 *     Porquê: é a mesma ordem das leituras; o Fredson escreve a Light a contar com esta forma.
 *     Liga-se a: Light (F3); as outras ordens seguem a mesma forma.
 *
 *   Passo 4 · Representação em texto
 *     Lógica sugerida: conforme a opção escolhida no Event.
 *     Liga-se a: IscotTests.
 *
 *   Passo 5 · Confirmar e entregar
 *     Experimentar o evento dos slides. Fechar o pull request C2 e avisar o Fredson: é a
 *     ENTREGA A, e a partir daqui ele consegue fazer as janelas.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Estado da ordem:
 *     A) Um boolean. É o que os slides mostram. [sugestão]
 *     B) A intensidade da lâmpada, que o LightIcon também suporta. Mais rico, mas não é pedido.
 *
 * NAS FASES SEGUINTES
 *   Cenário dos slides: uma única ordem é entregue às duas lâmpadas da sala.
 */
public class AreaLightingOrderEvent {
}

package iscot.common;

/**
 * HighLevelOrderEvent — classe-mãe, abstrata, das ordens de alto nível dos decisores.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C1 · feature/eventos-base · CELSO, 3.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         1 (sáb 7 nov)
 *   Precisa de:    Event.
 *   A seguir:      fechar o pull request C1 e passar a LightSensorEvent (C2).
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   "classes AreaIrrigationOrderEvent, AreaLightingOrderEvent, AreaTemperatureOrderEvent e
 *   AreaVentilationOrderEvent, todas subclasse de HighLevelOrderEvent".
 *   Nos slides, estas ordens aparecem com getType()=DECIDER: quem as emite é o decisor.
 *
 * O QUE SIGNIFICA "ALTO NÍVEL"
 *   A ordem não diz "liga a lâmpada 3"; diz "a iluminação desta área deve estar ligada". Quem
 *   a emite não sabe quantos dispositivos existem.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Estender o Event, continuando abstrata
 *     Lógica sugerida: igual ao SensorEvent; o construtor entrega a área à classe-mãe.
 *     Porquê: as duas classes são os dois ramos da mesma ideia. Manter a mesma forma torna a
 *       hierarquia fácil de explicar.
 *     Liga-se a: Event; SensorEvent, como modelo.
 *
 *   Passo 2 · Resolver o tipo
 *     Lógica sugerida: getType() responde sempre DECIDER.
 *     Porquê: é o valor que os slides mostram nas ordens. O enum Type não tem um valor
 *       "HIGHLEVEL".
 *     Liga-se a: formato do texto, IscotTests.
 *
 *   Passo 3 · Não acrescentar identificador de dispositivo
 *     Lógica sugerida: a classe não ganha campos próprios; o estado de cada ordem fica nas filhas.
 *     Porquê: uma ordem é para a área inteira, por isso uma só ordem serve às duas lâmpadas.
 *     Liga-se a: Light, Ventilator, AirConditioner e Irrigation, que decidem como a cumprir.
 *
 *   Passo 4 · Fechar a tarefa C1
 *     Abrir o pull request com as 3 classes. Ainda não há nada para executar: o Fredson revê
 *     lendo, e tem de conseguir explicar a hierarquia.
 *
 * CONCEITOS DA UC / DE POO A USAR
 *   Herança e especialização; desacoplamento: quem emite não conhece quem executa, a ideia
 *   central de um message broker.
 */
public abstract class HighLevelOrderEvent {
}

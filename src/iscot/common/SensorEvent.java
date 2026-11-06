package iscot.common;

/**
 * SensorEvent — classe-mãe, abstrata, das leituras feitas pelos sensores.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C1 · feature/eventos-base · CELSO, 2.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         1 (sáb 7 nov)
 *   Precisa de:    Event (Passos 1 a 4 concluídos).
 *   A seguir:      HighLevelOrderEvent.
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   "classes GroundMoistureSensorEvent, HumiditySensorEvent, LightSensorEvent e
 *   TemperatureSensorEvent, todas subclasses de SensorEvent".
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Estender o Event, continuando abstrata
 *     Lógica sugerida: o construtor recebe a área e entrega-a à classe-mãe.
 *     Porquê: não existe uma leitura "genérica", e a área já é guardada no Event.
 *     Liga-se a: Event, Passo 2.
 *
 *   Passo 2 · Resolver o tipo
 *     Lógica sugerida: getType() responde sempre SENSOR.
 *     Porquê: é verdade para todas as leituras; as 4 filhas deixam de ter de pensar nisto.
 *     Pesquisar: o modificador final num método, para impedir que uma filha o altere.
 *     Liga-se a: Event, Passo 3; formato do texto.
 *
 *   Passo 3 · Confirmar
 *     Compila. Só se confirma a sério quando existir o LightSensorEvent (tarefa C2).
 *
 * CONCEITOS DA UC / DE POO A USAR
 *   Herança em dois níveis; especialização de um comportamento; polimorfismo (mais tarde, o
 *   servidor trata todas as leituras da mesma forma).
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Onde guardar o valor lido:
 *     A) Em cada filha, com nome próprio (light, temperature...). É o que os slides mostram.
 *        [sugestão]
 *     B) Aqui, num campo genérico "valor". Menos código, mas o texto do evento deixa de
 *        corresponder ao formato do professor.
 */
public abstract class SensorEvent {
}

package iscot.test;

/**
 * IscotTests — todos os testes que se correm sem rede, num só programa.
 * Um main que corre cada grupo e imprime OK / FALHOU por verificação, e no fim o total.
 *
 * PORQUÊ UM MAIN E NÃO JUNIT
 *   Não obriga a configurar bibliotecas e corre-se como qualquer programa. JUnit vem com
 *   o Eclipse (Build Path > Add Libraries > JUnit) e é melhor se os testes crescerem;
 *   decidir em conjunto.
 *
 * REGRA
 *   Quem escreve o teste de uma classe é quem NÃO a escreveu: assim testamos o que a
 *   classe deve fazer, não o que achamos que fizemos.
 *
 * ------------------------------------------------------------------------------------
 * GRUPO 1 · Estrutura de tópicos (fase 2, sem threads) · escreve: Fredson
 *   1. FIFO: publicar A, B, C num Topic; a cabeça é A; depois de removeHead é B.
 *   2. Ids: os eventos recebem ids 1, 2, 3 por ordem de publicação.
 *   3. Tópicos separados: publicar em sala/luz/leituras não aparece em jardim/...
 *   4. TopicManager: pedir o mesmo nome duas vezes devolve o MESMO objeto Topic.
 *   5. DeviceType: SENSOR_LUZ.publishTopic(SALA) == "sala/luz/leituras";
 *      LAMPADA.subscribeTopic(SALA) == "sala/luz/ordens"; sensor não subscreve nada.
 *   6. Parse: "Sala " é aceite; "garagem" e "TORRADEIRA" são recusados.
 *
 * ------------------------------------------------------------------------------------
 * GRUPO 2 · ModifiedCountdownLatch (fase 5) · escrevem: os dois
 *   Medir tempos com System.currentTimeMillis.
 *   1. Timeout: Latch(3), 2 countdowns, await(800) -> volta em ~800 ms, getCount() == 1.
 *   2. Libertação: Latch(2); duas threads dormem 200 ms e fazem countdown;
 *      await(5000) -> volta em ~200 ms, getCount() == 0.
 *   3. Zero: Latch(0), await(5000) -> volta logo.
 *   4. Acks a mais: Latch(1), 3 countdowns -> count fica 0, nunca negativo.
 *   5. Interrupção: thread em await(10000) interrompida ao fim de 100 ms ->
 *      InterruptedException quase imediata.
 *   O que ensina: 1 e 2 só passam se o await usar wait com tempo e recalcular o que
 *   falta; 2 fica à espera os 5 s se o countdown não fizer notifyAll.
 *
 * ------------------------------------------------------------------------------------
 * GRUPO 3 · Carga (fase 5) · escreve: Celso
 *   - 10 threads publicam 1000 eventos cada no mesmo Topic; join() em todas.
 *   - Verificar: exatamente 10 000 eventos e nenhum id repetido.
 *   - Experiência didática: tirar temporariamente o synchronized do publish e correr
 *     várias vezes; aparecem menos eventos, ids repetidos ou exceções da LinkedList.
 *     É a race condition da aula 03 ao vivo. (Voltar a pôr o synchronized!)
 *   - Opcional: 50 registos paralelos no Server.newDeviceId -> 50 ids diferentes.
 *   Porquê: erros de concorrência não aparecem com uma thread nem, muitas vezes, em
 *   execuções curtas. Um teste de carga repetido é a forma prática de os provocar.
 */
public class IscotTests {
}

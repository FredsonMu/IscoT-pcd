package iscot.test;

/**
 * IscotTests — todos os testes que se correm sem rede, num só programa.
 * Um main que corre cada grupo e imprime OK / FALHOU por verificação, e no fim o total.
 *
 * PORQUÊ UM MAIN E NÃO JUNIT
 *   Não obriga a configurar bibliotecas e corre-se como qualquer programa. JUnit vem com
 *   o Eclipse e é melhor se os testes crescerem; decidir em conjunto.
 *
 * REGRA
 *   Quem escreve o teste de uma classe é quem NÃO a escreveu.
 *
 * ------------------------------------------------------------------------------------
 * GRUPO 1 · Mensagens e estrutura de tópicos (fase 2, sem threads) · escreve: Fredson
 *   1. Formato do professor:
 *      new LightSensorMessage(Areas.LivingRoom, 320).toString() ==
 *      "LightSensorMessage [light=320.0, area=LivingRoom, getType()=SENSOR]";
 *      new AreaLightingOrderMessage(Areas.LivingRoom, true).getType() == Type.DECIDER.
 *   2. FIFO: publicar A, B, C no Topic da LivingRoom; a cabeça é A; depois de
 *      removeHead é B.
 *   3. Ids: as mensagens recebem messageId 1, 2, 3 por ordem de publicação.
 *   4. Tópicos separados: publicar na LivingRoom não aparece no Garden.
 *   5. TopicManager: get(LivingRoom) duas vezes devolve o MESMO objeto Topic.
 *
 * ------------------------------------------------------------------------------------
 * GRUPO 2 · ModifiedCountdownLatch (fase 5) · escrevem: os dois
 *   1. Timeout: Latch(3), 2 countdowns, await(800) -> ~800 ms, getCount() == 1.
 *   2. Libertação: Latch(2); duas threads dormem 200 ms e fazem countdown;
 *      await(5000) -> ~200 ms, getCount() == 0.
 *   3. Zero: Latch(0), await(5000) -> volta logo.
 *   4. Acks a mais: Latch(1), 3 countdowns -> count fica 0.
 *   5. Interrupção: await(10000) interrompido ao fim de 100 ms -> InterruptedException.
 *
 * ------------------------------------------------------------------------------------
 * GRUPO 3 · Carga (fase 5) · escreve: Celso
 *   - 10 threads publicam 1000 mensagens cada no mesmo Topic; join() em todas.
 *   - Verificar: exatamente 10 000 mensagens e nenhum messageId repetido.
 *   - Experiência: tirar temporariamente o synchronized do publish e ver a falhar.
 */
public class IscotTests {
}

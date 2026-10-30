package iscot.test;

/**
 * TopicStructureTest — testes da fase 2, sem rede e sem threads.
 *
 * O QUE VAMOS IMPLEMENTAR (um main que imprime OK/FALHOU por cada verificação)
 *   1. FIFO: publicar A, B, C num Topic; a cabeça é A; depois de removeHead é B.
 *   2. Ids: os eventos recebem ids 1, 2, 3 por ordem de publicação.
 *   3. Tópicos separados: publicar em sala/luz/leituras não aparece em jardim/...
 *   4. TopicManager: pedir o mesmo nome duas vezes devolve o MESMO objeto Topic.
 *   5. Topics: publishTopic(SALA, SENSOR_LUZ) == "sala/luz/leituras";
 *      subscribeTopic(SALA, LAMPADA) == "sala/luz/ordens"; sensor não subscreve nada.
 *   6. Parse: "Sala " é aceite; "garagem" e "TORRADEIRA" são recusados.
 *
 * PORQUÊ UM MAIN E NÃO JUNIT
 *   Não obriga a configurar bibliotecas e corre-se como qualquer programa. JUnit 4/5
 *   vem com o Eclipse (botão direito > Build Path > Add Libraries > JUnit) e é melhor
 *   se quisermos muitos testes; decidir em conjunto.
 *
 * Fase 2 · Quem escreve: quem NÃO escreveu o Topic (Fredson), para testar sem viés.
 */
public class TopicStructureTest {
}

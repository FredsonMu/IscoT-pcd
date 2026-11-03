package iscot.test;

/**
 * IscotTests — todos os testes que se correm sem rede, num só programa.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       F5 (grupo 1), F9 (grupo 2), C11 (grupo 3) · feature/teste-estrutura, feature/teste-latch, feature/teste-carga
 *   Responsável:  Fredson (grupos 1 e 2), Celso (grupo 3)   ·   Revisor: o outro
 *   Dia 6:        SIM, o GRUPO 1 (fase 2). Merge até qui 5 nov. Grupos 2 e 3: fase 5.
 *   No dia 6:     Lançar "12 Testes": mostra o formato das mensagens, a fila FIFO da LivingRoom com ids 1, 2, 3, consumir a cabeça e os tópicos por área separados. É a demo da fase 2.
 *   Depende de:   C1–C4.
 *   Liga-se a:    Usa Topic, TopicManager e as mensagens.
 *   Código base:  —
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
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

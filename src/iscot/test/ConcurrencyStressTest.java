package iscot.test;

/**
 * ConcurrencyStressTest — prova que o Topic aguenta várias threads ao mesmo tempo.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - 10 threads que publicam 1000 eventos cada no mesmo Topic; join() em todas.
 *   - Verificar: há exatamente 10 000 eventos na fila e nenhum id repetido.
 *   - Experiência didática: tirar temporariamente o synchronized do publish e correr
 *     várias vezes. Deve aparecer um número menor que 10 000, ids repetidos ou
 *     exceções da LinkedList. É a race condition da aula 03 a acontecer de verdade.
 *     (Voltar a pôr o synchronized depois!)
 *   - Opcional: o mesmo para Server.newDeviceId (ids únicos com 50 registos paralelos).
 *
 * PORQUÊ ESTE TESTE IMPORTA
 *   Erros de concorrência não aparecem em testes com uma thread nem, muitas vezes, em
 *   execuções curtas. Um teste de carga repetido é a forma prática de os provocar.
 *   Mostrar este teste na oral é uma boa forma de provar que percebemos a matéria.
 *
 * Fase 5 · Quem escreve: Celso (Fredson revê)
 */
public class ConcurrencyStressTest {
}

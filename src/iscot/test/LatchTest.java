package iscot.test;

/**
 * LatchTest — testes do ModifiedCountdownLatch, antes de o ligar ao servidor.
 *
 * O QUE VAMOS IMPLEMENTAR (main que mede tempos com System.currentTimeMillis)
 *   1. Timeout: Latch(3), 2 countdowns, await(800). Esperado: volta ao fim de ~800 ms,
 *      getCount() == 1.
 *   2. Libertação: Latch(2); duas threads que dormem 200 ms e fazem countdown;
 *      await(5000). Esperado: volta ao fim de ~200 ms, getCount() == 0.
 *   3. Zero: Latch(0), await(5000). Esperado: volta logo.
 *   4. Acks a mais: Latch(1), 3 countdowns. Esperado: count fica 0, nunca negativo.
 *   5. Interrupção: uma thread em await(10000) é interrompida por outra ao fim de
 *      100 ms. Esperado: InterruptedException quase imediata.
 *
 * O QUE ESTE TESTE ENSINA
 *   Os testes 1 e 2 só passam se o await usar wait com tempo e recalcular o que falta.
 *   O teste 2 falha (fica à espera os 5 s) se o countdown não fizer notifyAll.
 *
 * Fase 5 · Quem escreve: os dois.
 */
public class LatchTest {
}

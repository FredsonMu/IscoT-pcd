package iscot.sync;

/**
 * ModifiedCountdownLatch — contador que faz uma thread esperar até N confirmações
 * chegarem, ou até passar um tempo limite.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.2, API imposta:
 *     public ModifiedCountdownLatch(int count);
 *     public synchronized void countdown();
 *     public synchronized void await(int waitPeriod) throws InterruptedException;
 *   Secção 2: "Todos os mecanismos de coordenação devem ser desenvolvidos pelo próprio
 *   grupo" -> é PROIBIDO usar java.util.concurrent.CountDownLatch.
 *
 * O QUE É UM LATCH (trinco)
 *   Um portão que começa fechado com um contador N. Cada countdown() desconta 1.
 *   Quem chama await() fica parado enquanto o contador for > 0. Quando chega a 0 o
 *   portão abre e não volta a fechar (um latch não se reutiliza: cria-se um por evento).
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Campo int count (estado partilhado, protegido pelo cadeado do próprio objeto).
 *   - countdown(): se count > 0, decrementa; se chegou a 0, notifyAll().
 *     Nunca deixar ficar negativo (acks a mais não devem estragar nada).
 *   - await(waitPeriod):
 *       1) calcular o instante-limite = agora + waitPeriod;
 *       2) enquanto count > 0:
 *            calcular o tempo que falta; se <= 0, sair (timeout);
 *            wait(tempo que falta).
 *   - Opcional: getCount() para o dispatcher saber quantos não responderam.
 *
 * MATÉRIA APLICADA (aula 04, "mandamentos")
 *   - A coordenação está no recurso partilhado (o latch), não na thread que espera.
 *   - wait/notifyAll dentro de métodos synchronized (senão IllegalMonitorStateException).
 *   - wait SEMPRE dentro de while, nunca de if (spurious wakeups).
 *   - notifyAll e não notify.
 *   - Não é espera ativa: a thread dorme e não gasta CPU.
 *
 * PORQUÊ RECALCULAR O TEMPO QUE FALTA
 *   wait(t) pode acordar antes de t (spurious wakeup ou outro notifyAll). Se
 *   voltássemos a fazer wait(waitPeriod) inteiro, o timeout total podia crescer sem
 *   limite. Recalcular garante que nunca se espera mais do que o pedido.
 *   Nota: wait(long) não foi dado nas aulas; é o mesmo Object.wait com limite de tempo,
 *   está na API do Java.
 *
 * LIGAÇÕES
 *   Criado por: TopicDispatcher (um por evento, com count = nº de subscritores).
 *   Espera nele: TopicDispatcher (await).
 *   Desconta nele: Topic.ack, chamado pelos ClientHandler quando chega um AckMessage.
 *   Extra: pode ser reutilizado para contar ReceptionConfirmationMessage.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - await a devolver boolean (true = todos responderam, false = timeout), como o
 *     CountDownLatch do Java. Mais informativo, mas muda a API do enunciado.
 *     Perguntar ao professor; até lá usamos void + getCount().
 *   - Espera ativa com sleep em ciclo ("while(count>0) sleep(10)"): funciona mas gasta
 *     CPU e reage com atraso. Proibido pelo espírito da aula 04.
 *
 * DÚVIDAS PARA DISCUTIR ENTRE NÓS
 *   - O que acontece se count começa a 0 (tópico sem subscritores)? await deve
 *     voltar logo. Confirmar que o nosso while trata isso.
 *   - E se uma thread for interrompida durante o await? A InterruptedException sobe
 *     para quem chamou (o dispatcher), que deve terminar de forma limpa.
 *
 * COMO TESTAR (antes de ligar à rede)
 *   - Latch(3), 2 countdowns, await(800): deve voltar ao fim de ~800 ms com count 1.
 *   - Latch(2), duas threads que dormem 200 ms e fazem countdown, await(5000):
 *     deve voltar ao fim de ~200 ms com count 0.
 *   - Latch(0), await(5000): deve voltar imediatamente.
 *
 * Fase 5 · Escrito pelos DOIS em separado e depois comparado (é matéria de exame).
 */
public class ModifiedCountdownLatch {
}

package iscot.common;

/**
 * ReceptionConfirmationMessage — EXTRA: o atuador confirma que executou a ordem.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       A2 · feature/confirmacoes
 *   Responsável:  os dois (dividir no momento)   ·   Revisor: o outro
 *   Dia 6:        NÃO. Extra (fase 6), só no fim.
 *   No dia 6:     Nada.
 *   Depende de:   C1, fase 5 completa.
 *   Liga-se a:    Enviada pelos atuadores; contada pelo servidor.
 *   Código base:  Nome imposto pelo enunciado.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.3 e fase 6. O nome da classe é imposto pelo enunciado. Formato pedido:
 *     message:  "mensagem-recebida"
 *     deviceId: "id-do-cliente-atuador"
 *     status:   "OK" / "NOK"
 *   "O servidor deverá aguardar pela receção de todas as mensagens de confirmação
 *   relacionadas a cada tópico antes de registar o pleno sucesso da interação."
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Os três campos do enunciado (String, final). O status pode ser um enum
 *     interno OK/NOK ou String; o enunciado mostra String.
 *   - Juntar o messageId da OrderMessage a que a confirmação diz respeito (sem ele o
 *     servidor não sabe a que ordem se refere). Perguntar se "message" deve ser esse id.
 *   - getType() -> ACTUATOR (só atuadores confirmam execução).
 *
 * COMO ENCAIXA
 *   Ack: atuador recebe -> envia Ack -> latch desce -> tópico avança.
 *   Confirmação: atuador executa (liga a lâmpada) -> envia esta mensagem -> o servidor
 *   conta as confirmações (pode reutilizar um ModifiedCountdownLatch por evento) e,
 *   quando tiver todas, regista sucesso e pode avisar o decisor.
 *   Isto também resolve o "o decisor deve esperar que todos os atuadores acendam a
 *   lâmpada" da secção 1.2.
 *
 * TRADE-OFF
 *   Esperar por confirmações antes de avançar o tópico torna tudo mais lento
 *   (o tópico fica preso enquanto a rega enche). Alternativa: avançar o tópico com o
 *   Ack e contar as confirmações em paralelo, noutro latch. Discutir com o professor.
 *
 */
public class ReceptionConfirmationMessage {
}

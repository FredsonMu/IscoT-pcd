package iscot.client;

/**
 * ActionHistory — a lista de ações tomadas por um atuador desde que foi ligado.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1.3: "A cada ação tomada por um cliente atuador, este adiciona a ação tomada
 *   a um histórico verificável pelo utilizador de todas as ações tomadas pelo atuador
 *   desde a última vez que foi ligado. Por exemplo: uma lâmpada que tenha sido desligada,
 *   deverá conter na listagem de ações tomadas uma mensagem como
 *   'Lâmpada desligada - HH:MM:SS'".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Uma lista de String (só em memória: "desde a última vez que foi ligado" significa
 *     que pode começar vazia em cada arranque).
 *   - add(descrição): junta " - " e a hora atual formatada HH:mm:ss.
 *     Java 8: SimpleDateFormat("HH:mm:ss") ou LocalTime + DateTimeFormatter.
 *   - getAll(): devolve uma cópia (para a GUI).
 *
 * CONCORRÊNCIA
 *   Escrita pela thread recetora (ordens do servidor) e, se houver botão manual, pela
 *   thread do Swing; lida pela GUI. Duas threads -> métodos synchronized.
 *   Alternativa: fazer TODAS as alterações dentro de invokeLater, na thread do Swing,
 *   e então só uma thread lhe toca (confinamento) e não precisa de cadeado.
 *   Escolher uma das duas e saber explicar porquê.
 *
 * PORQUÊ SEPARADA DA GUI
 *   O histórico é um requisito do enunciado, não um detalhe visual. Separado, pode ser
 *   testado sem abrir janelas e mostrado de outras formas (consola, ficheiro).
 *
 * ARMADILHA
 *   Não usar "mm" para minutos e "MM" para mês trocados: HH:mm:ss (MM é o mês).
 *
 * DÚVIDAS
 *   - Deve ser também gravado em ficheiro? O enunciado diz "verificável pelo
 *     utilizador"; na GUI parece chegar.
 *
 * Fase 1 (versão local) / 4 · Responsável: Fredson · Revisor: Celso
 */
public class ActionHistory {
}

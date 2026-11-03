package iscot.client;

/**
 * ActionHistory — o histórico de ações de um atuador, com a sua lista na janela.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       F1 · feature/action-history
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        SIM (fase 1). Começar HOJE (não depende de ninguém); merge até qua 4 nov.
 *   No dia 6:     Aparece na janela da Light, por baixo do LightIcon, com "Lâmpada ligada - HH:MM:SS".
 *   Depende de:   — (só Swing).
 *   Liga-se a:    Usado pela Light (dia 6) e depois por Irrigation, Ventilator e AirConditioner.
 *   Código base:  O código base não tem histórico; é nosso. Segue o padrão do ComponentState (invokeLater).
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1.3: "A cada ação tomada por um cliente atuador, este adiciona a ação tomada
 *   a um histórico verificável pelo utilizador de todas as ações tomadas pelo atuador
 *   desde a última vez que foi ligado. Por exemplo: (...) 'Lâmpada desligada -
 *   HH:MM:SS'". O código base NÃO traz este componente: é nosso.
 *
 * PORQUÊ UMA CLASSE PRÓPRIA
 *   Os 4 atuadores (Light, Irrigation, Ventilator, AirConditioner) precisam todos do
 *   mesmo histórico. Uma classe evita repetir o código 4 vezes.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends JPanel (para se pôr na janela ao lado do ícone do professor, por exemplo
 *     em BorderLayout.SOUTH), com uma JList sobre um DefaultListModel dentro de um
 *     JScrollPane.
 *   - add(String acao): junta " - " e a hora HH:mm:ss (SimpleDateFormat ou
 *     LocalTime + DateTimeFormatter) e acrescenta a linha.
 *   - Começa vazio em cada arranque ("desde a última vez que foi ligado").
 *
 * CONCORRÊNCIA
 *   add é chamado pela thread recetora, não pela do Swing. O DefaultListModel não é
 *   thread-safe, por isso add faz o trabalho dentro de SwingUtilities.invokeLater
 *   (é o que o professor faz em ComponentState.setAtivo). Assim só a thread do Swing
 *   mexe na lista (confinamento) e não é preciso synchronized.
 *
 * ARMADILHA
 *   "HH:mm:ss": mm são minutos; MM é o mês.
 *
 * LIGAÇÕES
 *   Usada por: Light, Irrigation, Ventilator, AirConditioner.
 *
 */
public class ActionHistory {
}

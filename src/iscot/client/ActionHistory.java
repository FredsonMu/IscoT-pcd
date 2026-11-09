package iscot.client;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * ActionHistory — histórico de ações de um atuador, visível na sua janela.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F1 · feature/action-history · FREDSON, 1.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          3 (mostrar os eventos nas janelas). Requisito da secção 1.3.
 *   Etapa:         1 (sáb 7 nov). Começa logo depois do Ponto 0.
 *   Precisa de:    nada, só Swing. Não espera por ninguém.
 *   Quem espera:   Light, Ventilator, AirConditioner, Irrigation (Celso).
 *   Roteiro geral: docs/09-tarefas-e-git.md
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE (1.3)
 *   Um "histórico verificável pelo utilizador de todas as ações tomadas pelo atuador desde a
 *   última vez que foi ligado". Exemplo: "Lâmpada desligada - HH:MM:SS".
 *   O código base não traz este componente: é nosso.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Ser um componente que se coloca numa janela
 *     Lógica sugerida: estender um painel de Swing, tal como os ícones do professor.
 *     Porquê: assim os atuadores só têm de o adicionar à janela, por baixo do ícone.
 *     Liga-se a: BorderLayout dos atuadores; ComponentState, como exemplo.
 *
 *   Passo 2 · Criar a lista visível
 *     Lógica sugerida: um modelo com os dados, a lista a mostrá-lo, e uma barra de deslocamento.
 *     Porquê: "todas as ações" podem não caber; o modelo separa os dados do desenho.
 *     Liga-se a: aula de Swing; opção A abaixo.
 *
 *   Passo 3 · Preparar a formatação da hora
 *     Lógica sugerida: um formatador HH:mm:ss, guardado uma vez.
 *     Porquê: o formato vem do enunciado. Ver a escolha entre bibliotecas nas opções.
 *     Liga-se a: Passo 4; a verificação é à vista (docs/05-testes.md).
 *
 *   Passo 4 · Método público para acrescentar uma ação (o contrato)
 *     Lógica sugerida: recebe só a descrição ("Lâmpada ligada") e devolve nada. Lê a hora logo à
 *       entrada, compõe a linha "descrição - hora", e só depois a entrega à lista.
 *     Porquê: quem chama não tem de saber o formato, que fica num só sítio. A hora é a da ação,
 *       não a do momento em que o Swing conseguiu desenhar.
 *     Liga-se a: Light (F3), Ventilator (F4), AirConditioner (F6), Irrigation (C6): é o
 *       contrato combinado no Ponto 0.
 *
 *   Passo 5 · Entregar a alteração à thread do Swing
 *     Lógica sugerida: acrescentar ao modelo dentro de invokeLater.
 *     Porquê: os atuadores chamam isto a partir da main, e o Swing não é thread-safe. É o mesmo
 *       que ComponentState.setAtivo faz.
 *     Liga-se a: ComponentState; fases 5 e seguintes, quando a chamada vier da thread recetora.
 *
 *   Passo 6 · Confirmar e entregar
 *     Numa janela de teste (não vai para o commit), acrescentar umas linhas: aparece a hora certa
 *     e, com muitas, a barra. Abrir o pull request F1 e avisar o Celso.
 *
 * CONCEITOS DA UC A USAR
 *   Swing e layouts; modelo/vista; EDT e confinamento; reutilização (uma classe, 4 atuadores).
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Como mostrar as linhas:
 *     A) JList com DefaultListModel. Cada ação é um item. [sugestão]
 *     B) JTextArea não editável. Mais curto, mas o texto é um bloco único.
 *   Como obter a hora:
 *     A) LocalTime e DateTimeFormatter (java.time). Imutáveis e thread-safe. [sugestão]
 *     B) SimpleDateFormat. Muito usado, mas NÃO é thread-safe.
 *
 * ARMADILHA
 *   No padrão da hora, "mm" são minutos e "MM" é o mês.
 *
 * PERGUNTA PROVÁVEL
 *   "Porquê invokeLater, se ainda não têm concorrência?" Porque a main e a thread do Swing já são
 *   duas threads, mesmo sem servidor.
 */
public final class ActionHistory extends JPanel {

    private static final long serialVersionUID = 1L;

    /** Formato da hora pedido no enunciado (1.3): HH:MM:SS. "mm" são minutos; "MM" seria o mês. */
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    /** Os dados: uma linha por ação, pela ordem em que aconteceram. */
    private final DefaultListModel<String> actions = new DefaultListModel<>();

    /** A vista dos dados; guardada para conseguir mostrar sempre a ação mais recente. */
    private final JList<String> list = new JList<>(actions);

    /** Cria um histórico vazio: cada arranque do atuador começa sem ações. */
    public ActionHistory() {
        super(new BorderLayout());
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    /**
     * Regista uma ação do atuador, com a hora em que aconteceu.
     * Exemplo: "Lâmpada ligada" fica "Lâmpada ligada - 21:43:05".
     * Pode ser chamado de qualquer thread: a lista só é alterada pela thread do Swing.
     */
    public void addAction(String description) {
        String line = description + " - " + LocalTime.now().format(TIME_FORMAT);
        SwingUtilities.invokeLater(() -> {
            actions.addElement(line);
            list.ensureIndexIsVisible(actions.getSize() - 1);
        });
    }
}

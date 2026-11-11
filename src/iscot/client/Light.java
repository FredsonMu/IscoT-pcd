package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.LightIcon;
import iscot.common.AreaLightingOrderEvent;
import iscot.enums.Areas;

/**
 * Light — programa de uma lâmpada (atuador). É O MODELO DOS NOSSOS ATUADORES.
 * Cada lâmpada é uma execução deste programa.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F3 · feature/light · FREDSON, 3.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          1 (GUI) e 3 (eventos exemplo na janela)
 *   Etapa:         2 (dom 8 nov). ENTREGA C ao Celso (ele usa-a como modelo da Irrigation).
 *   Precisa de:    ENTREGA A do Celso (AreaLightingOrderEvent); F1 (ActionHistory).
 *   Molde:         Ventilator do professor.
 *   Quem espera:   Ventilator (F4), AirConditioner (F6), Irrigation (C6, Celso).
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   Fase 3: "exibir eventos exemplo nas várias janelas existentes, no caso dos atuadores".
 *   Código base, no Ventilator: "create an appropriate event hardcoded locally and change the GUI
 *   accordingly". Ou seja: SEM servidor, o próprio programa cria as ordens.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Validar os argumentos e obter a área
 *     Lógica sugerida: igual ao molde (3 argumentos; o 3.º é convertido para Areas).
 *     Liga-se a: launch "05 Lampada LivingRoom".
 *
 *   Passo 2 · Montar a janela
 *     Lógica sugerida: a área no título; o LightIcon no centro; o ActionHistory em baixo. Mostrar
 *       a janela só depois de ter os componentes.
 *     Porquê: nesta altura já se vê tudo, sem nenhuma ordem.
 *     Liga-se a: LightIcon (professor); ActionHistory (F1).
 *
 *   Passo 3 · Escrever o que acontece ao receber UMA ordem
 *     Lógica sugerida: um método que recebe um AreaLightingOrderEvent, muda o ícone conforme o
 *       estado e regista "Lâmpada ligada" ou "Lâmpada desligada". Se o estado for igual ao atual,
 *       não há ação a registar.
 *     Porquê: é O passo a acertar. Separa "o que faço com a ordem" de "de onde vem a ordem": na
 *       fase 5 este método mantém-se e só muda quem o chama.
 *     Liga-se a: AreaLightingOrderEvent (consulta do estado); ActionHistory (Passo 4 dele); fase 5.
 *
 *   Passo 4 · Gerar ordens de exemplo
 *     Lógica sugerida: criar localmente ordens para ligar e desligar, com pausas, e passar cada uma
 *       ao método do Passo 3 (ver as opções; a escolha é do Ponto 0).
 *     Porquê: é literalmente o TODO do professor para esta fase.
 *     Liga-se a: Ventilator, Irrigation e AirConditioner, que usam a mesma técnica.
 *
 *   Passo 5 · Confirmar e entregar
 *     Lançar duas vezes: as duas lâmpadas acendem e apagam, com hora no histórico, e as janelas
 *     respondem durante a sequência. Abrir o pull request F3 e avisar o Celso: é a ENTREGA C.
 *
 * CONCEITOS DA UC A USAR
 *   Argumentos do main e conversão para enum; Swing (JFrame, JPanel, BorderLayout); EDT (nunca
 *   adormecer a thread do Swing); separação de responsabilidades.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Como gerar a sequência de ordens de exemplo:
 *     A) Na main, com pausas (Thread.sleep), como faz o molde. A main não é a thread do Swing, logo
 *        a janela não congela. Simples e fácil de explicar. [sugestão]
 *     B) javax.swing.Timer. Corre na thread do Swing, sem pausas nem thread extra, mas a sequência
 *        fica mais espalhada no código.
 *     C) Botões na janela para "simular ordem". Dá controlo na demo, mas acrescenta algo que sai
 *        depois.
 *   Isto não é coordenação entre threads, por isso não entra na proibição do enunciado.
 *
 * ARMADILHAS
 *   - setLigada usa invokeLater: logo depois de o chamar, isLigada pode ainda dar o valor antigo.
 *     Para o Passo 3, guardar o estado no próprio programa.
 *   - O molde abre as janelas todas em (600, 400): as duas lâmpadas ficam sobrepostas.
 *   - Área mal escrita (ex.: sala) dá IllegalArgumentException: são os nomes exatos do enum.
 *
 * NA APRESENTAÇÃO
 *   Lançar "05 Lampada LivingRoom" duas vezes: as duas lâmpadas do cenário dos slides.
 */
public class Light {

    /** A lâmpada do professor (começa apagada). */
    private final LightIcon icon = new LightIcon();

    /** O nosso histórico (F1). */
    private final ActionHistory history = new ActionHistory();

    /** O estado atual. Guardado aqui, e não lido do ícone: o setLigada só muda o ícone mais tarde. */
    private boolean ligada = false;

    /** Cria a lâmpada e mostra a sua janela: o ícone ao centro e o histórico em baixo. */
    public Light(Areas area) {
        JFrame janela = new JFrame("Lâmpada em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.add(icon, BorderLayout.CENTER);
        painel.add(history, BorderLayout.SOUTH);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.setMinimumSize(janela.getSize());
        janela.pack();
        janela.setVisible(true);
    }

    /**
     * Cumpre uma ordem de iluminação: muda o ícone e regista a ação no histórico.
     * Se a lâmpada já estiver no estado pedido, nada muda e não há ação a registar.
     * Fase 3: as ordens são criadas no main. Fase 6: chegam do decisor, pelo servidor.
     */
    public void aplicarOrdem(AreaLightingOrderEvent ordem) {
        if (ordem.getState() == ligada) {
            return;
        }
        ligada = ordem.getState();
        icon.setLigada(ligada);
        history.addAction(ligada ? "Lâmpada ligada" : "Lâmpada desligada");
    }

    /**
     * Argumentos, como no código base: endereço e porto do servidor, e a área.
     * Exemplo: localhost 8080 LivingRoom. Endereço e porto só são usados na fase 4.
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        } else {
            Areas area = Areas.valueOf(args[2]);
            Light lampada = new Light(area);

            // Fase 3: simula as ordens que, na fase 6, vão chegar do decisor de iluminação.
            // As pausas são na main e não na thread do Swing, por isso a janela não congela.
            boolean[] ordens = {true, false, true};
            for (boolean acender : ordens) {
                Thread.sleep(3000);
                lampada.aplicarOrdem(new AreaLightingOrderEvent(area, acender));
            }
        }
    }
}

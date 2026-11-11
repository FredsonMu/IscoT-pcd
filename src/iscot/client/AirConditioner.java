package iscot.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.ACIcon;
import iscot.common.AreaTemperatureOrderEvent;
import iscot.common.AreaVentilationOrderEvent;
import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;

/**
 * AirConditioner — programa do atuador de ar condicionado. O atuador mais exigente.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F6 · feature/air-conditioner · FREDSON, 6.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          1 (GUI) e 3 (eventos exemplo na janela)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    ENTREGA B do Celso (AreaTemperatureOrderEvent e AreaVentilationOrderEvent);
 *                  F1; F3 (Light, como guia).
 *   Molde:         Light; usa ACIcon.
 * ==================================================================================
 *
 * É O ÚNICO ATUADOR QUE REAGE A DOIS TIPOS DE ORDEM
 *   - AreaTemperatureOrderEvent: aquecer, arrefecer ou desligar.
 *   - AreaVentilationOrderEvent: ventilar, MAS "apenas se não estiver já em algum dos modos
 *     aquecimento ou arrefecimento" (enunciado, 1.2).
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Montar a janela
 *     Lógica sugerida: igual à Light, com o ACIcon e o ActionHistory.
 *     Liga-se a: Light, Passo 2; launch "07 Ar condicionado LivingRoom".
 *
 *   Passo 2 · Guardar o modo atual
 *     Lógica sugerida: uma variável no programa, que começa em Desligado.
 *     Porquê: a regra do Passo 4 precisa de saber em que modo está.
 *     Liga-se a: enum AirConditioningMode.
 *
 *   Passo 3 · Reagir a uma ordem de temperatura
 *     Lógica sugerida: atualizar o modo, o ícone e o histórico (ex.: "Ar condicionado em modo Frio").
 *     Liga-se a: AreaTemperatureOrderEvent, com a decisão que o Celso tomou sobre o modo Ventilação.
 *
 *   Passo 4 · Reagir a uma ordem de ventilação, com a regra
 *     Lógica sugerida: se estiver a aquecer ou a arrefecer, não fazer nada. Caso contrário, ordem de
 *       ligar passa a Ventilação, ordem de desligar passa a Desligado.
 *     Porquê: é a regra da secção 1.2, e a prova de que a lemos.
 *     Liga-se a: AreaVentilationOrderEvent, que só transporta a ordem.
 *
 *   Passo 5 · Gerar ordens de exemplo que provem a regra
 *     Lógica sugerida: Frio -> ordem de ventilação (ignorada) -> Desligado -> ordem de ventilação
 *       (aplicada).
 *     Liga-se a: Light, Passo 4 (mesma técnica).
 *
 *   Passo 6 · Confirmar
 *     A sequência corre sozinha e a ordem de ventilação em modo Frio não muda nada. Decidir se uma
 *     ordem ignorada aparece no histórico: não é uma ação; saber explicar a escolha.
 *
 * CONCEITOS DA UC A USAR
 *   Polimorfismo e/ou sobrecarga, para tratar duas classes de evento; estado do programa.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Como reagir a duas classes de ordem:
 *     A) Um método para cada classe (sobrecarga). Claro nesta fase, em que o tipo é conhecido.
 *     B) Um só método que recebe o evento e distingue com instanceof. Será o mais natural quando os
 *        eventos vierem da rede. [sugestão]
 *     C) Padrão Visitor. É "como manda o livro", mas exagerado para este projeto.
 *   Onde guardar o modo atual:
 *     A) Numa variável do próprio programa. [sugestão]
 *     B) Perguntar ao ACIcon (getModo). Funciona, mas mistura a lógica com a GUI.
 *
 * ARMADILHA
 *   O ACIcon desenha o mesmo símbolo em Frio, Aquecimento e Ventilação. É o histórico que mostra a
 *   diferença na demo.
 *
 * NA APRESENTAÇÃO
 *   "07 Ar condicionado LivingRoom". Boa oportunidade para mostrar a regra 1.2.
 */
public class AirConditioner {

    /** O ar condicionado do professor (começa Desligado). */
    private final ACIcon ac = new ACIcon();

    /** O nosso histórico (F1). */
    private final ActionHistory history = new ActionHistory();

    /**
     * O modo atual. Ao contrário da Light, não basta ligado/desligado: a regra da ventilação
     * (enunciado 1.2) precisa de saber se está a aquecer ou a arrefecer.
     */
    private AirConditioningMode modo = AirConditioningMode.Desligado;

    /** Cria o ar condicionado e mostra a sua janela: o ícone ao centro e o histórico em baixo. */
    public AirConditioner(Areas area) {
        JFrame janela = new JFrame("Ar condicionado em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.add(ac, BorderLayout.CENTER);
        painel.add(history, BorderLayout.SOUTH);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.setMinimumSize(janela.getSize());
        janela.pack();
        janela.setSize(420, janela.getHeight());    // largura para caber "Ar condicionado em modo Ventilação - HH:MM:SS"
        janela.setVisible(true);
    }

    /**
     * Cumpre uma ordem de temperatura (Frio, Aquecimento ou Desligado). Obedece sempre:
     * a regra 1.2 só limita as ordens de ventilação.
     * Fase 3: as ordens são criadas no main. Fase 6: chegam do decisor de temperatura.
     */
    public void aplicarOrdem(AreaTemperatureOrderEvent ordem) {
        mudarModo(ordem.getMode());
    }

    /**
     * Cumpre uma ordem de ventilação, com a regra do enunciado (1.2): só passa ao modo Ventilação
     * "se não estiver já em algum dos modos aquecimento ou arrefecimento". Nesses modos a ordem
     * é ignorada, também a de parar: quem desliga o frio ou o aquecimento é a ordem de temperatura.
     * Fase 3: as ordens são criadas no main. Fase 6: chegam do decisor de ventilação.
     */
    public void aplicarOrdem(AreaVentilationOrderEvent ordem) {
        if (modo == AirConditioningMode.Frio || modo == AirConditioningMode.Aquecimento) {
            return;
        }
        mudarModo(ordem.getState() ? AirConditioningMode.Ventilação : AirConditioningMode.Desligado);
    }

    /**
     * Passa ao modo indicado: muda o ícone e regista a ação no histórico.
     * Se já estiver nesse modo, nada muda e não há ação a registar.
     */
    private void mudarModo(AirConditioningMode novoModo) {
        if (novoModo == modo) {
            return;
        }
        modo = novoModo;
        ac.setModo(modo);
        history.addAction(modo == AirConditioningMode.Desligado
                ? "Ar condicionado desligado"
                : "Ar condicionado em modo " + modo);
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
            AirConditioner arCondicionado = new AirConditioner(area);

            // Fase 3: simula as ordens que, na fase 6, vão chegar dos decisores de temperatura e
            // de ventilação. A sequência mostra a regra 1.2: em Frio, a ordem de ventilação é ignorada;
            // depois de desligado, a mesma ordem já é aplicada.
            // As pausas são na main e não na thread do Swing, por isso a janela não congela.
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaTemperatureOrderEvent(area, AirConditioningMode.Frio));
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaVentilationOrderEvent(area, true));    // ignorada: está em Frio
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaTemperatureOrderEvent(area, AirConditioningMode.Desligado));
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaVentilationOrderEvent(area, true));    // aplicada: passa a Ventilação
            Thread.sleep(3000);
            arCondicionado.aplicarOrdem(new AreaVentilationOrderEvent(area, false));   // pára: passa a Desligado
        }
    }
}

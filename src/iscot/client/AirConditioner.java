package iscot.client;

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
}

package iscot.client;

/**
 * Irrigation — programa do atuador de rega.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C6 · feature/irrigation · CELSO, 15.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          1 (GUI) e 3 (eventos exemplo na janela)
 *   Etapa:         3 (seg 9 nov)
 *   Precisa de:    C3 (AreaIrrigationOrderEvent); ENTREGA C do Fredson (ActionHistory e Light).
 *   Molde:         Light; usa IrrigationIcon.
 * ==================================================================================
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Ler a Light do Fredson
 *     Porquê: é o modelo. Ao percebê-la, o Celso também fica preparado para a explicar na apresentação.
 *     Liga-se a: Light (F3).
 *
 *   Passo 2 · Montar a janela
 *     Lógica sugerida: a área no título; o IrrigationIcon no centro; o ActionHistory em baixo.
 *     Liga-se a: IrrigationIcon; ActionHistory; launch "08 Rega Garden".
 *
 *   Passo 3 · Escrever o que acontece ao receber UMA ordem
 *     Lógica sugerida: um método que recebe um AreaIrrigationOrderEvent, muda o ícone e regista "Rega
 *       ligada" ou "Rega desligada". Sem ação se o estado não mudar.
 *     Porquê: mesma separação da Light, para esta parte se manter na fase 5.
 *     Liga-se a: AreaIrrigationOrderEvent; Light, Passo 3.
 *
 *   Passo 4 · Gerar ordens de exemplo
 *     Lógica sugerida: usar a mesma técnica combinada no Ponto 0.
 *     Liga-se a: Light, Passo 4.
 *
 *   Passo 5 · Confirmar
 *     A rega liga e desliga sozinha, com a hora no histórico. Abrir o pull request C6.
 *
 * CONCEITOS DA UC A USAR
 *   Os mesmos da Light: Swing, thread do Swing, separar a origem da ordem do que se faz com ela.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Zona e duração do IrrigationIcon:
 *     A) Ignorar. O enunciado não as pede. [sugestão]
 *     B) Usar, se a ordem as trouxer. Ver as opções em AreaIrrigationOrderEvent.
 *
 * ARMADILHA
 *   Os nomes dos métodos variam entre ícones: setLigada, setLigado, setAtiva, setModo.
 */
public class Irrigation {
}

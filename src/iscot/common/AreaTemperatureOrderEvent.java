package iscot.common;

import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;

/**
 * AreaTemperatureOrderEvent — ordem para aquecer ou arrefecer uma área.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C3 · feature/eventos-restantes · CELSO, 10.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         2 (dom 8 nov). Parte da ENTREGA B ao Fredson.
 *   Precisa de:    AreaLightingOrderEvent, como modelo; enum AirConditioningMode (professor).
 *   Quem espera:   Fredson, para o AirConditioner (F6).
 * ==================================================================================
 *
 * FORMATO
 *   AreaTemperatureOrderEvent [mode=Frio, area=LivingRoom, getType()=DECIDER]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Seguir o modelo, com um modo em vez de um estado
 *     Lógica sugerida: mesma estrutura; o campo é o modo pretendido (ver as opções).
 *     Porquê: aqui não basta ligado/desligado: é preciso distinguir aquecer de arrefecer.
 *     Liga-se a: AreaLightingOrderEvent; ACIcon, que já trabalha com modos.
 *
 *   Passo 2 · Decidir o que fazer com o modo Ventilação
 *     Lógica sugerida: escolher uma das opções abaixo e combinar com o Fredson.
 *     Porquê: o AirConditioner depende desta decisão.
 *     Liga-se a: AreaVentilationOrderEvent; AirConditioner, Passo 3.
 *
 *   Passo 3 · Confirmar
 *     O texto corresponde ao formato acima.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   O que a ordem transporta:
 *     A) O enum AirConditioningMode do professor. Sem traduções. [sugestão]
 *     B) Só um boolean ligar/desligar. Não distingue aquecer de arrefecer.
 *     C) O modo e uma temperatura-alvo (o ACIcon aceita 16 a 30 graus). Não é pedido.
 *   Modo Ventilação nesta ordem (quem ventila é a AreaVentilationOrderEvent):
 *     A) Recusar no construtor. O compilador não apanha o erro, mas a execução sim.
 *     B) Aceitar e deixar o atuador decidir. Há duas maneiras de pedir a mesma coisa.
 */
public class AreaTemperatureOrderEvent extends HighLevelOrderEvent {

    private final AirConditioningMode mode;

    /** Cria uma ordem para aquecer, arrefecer ou desligar. */
    public AreaTemperatureOrderEvent(Areas area, AirConditioningMode mode) {
        super(area);
        if (mode == null || mode == AirConditioningMode.Ventilação) {
            throw new IllegalArgumentException("O modo deve ser Frio, Aquecimento ou Desligado.");
        }
        this.mode = mode;
    }

    /** Devolve o modo pretendido para o ar condicionado. */
    public AirConditioningMode getMode() {
        return mode;
    }

    /** Fornece a parte variável do texto desta ordem. */
    @Override
    protected String getEventFields() {
        return "mode=" + mode;
    }
}

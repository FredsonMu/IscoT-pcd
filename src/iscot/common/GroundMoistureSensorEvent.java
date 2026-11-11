package iscot.common;

import iscot.enums.Areas;

/**
 * GroundMoistureSensorEvent — leitura da humidade do SOLO, em percentagem.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C3 · feature/eventos-restantes · CELSO, 8.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         2 (dom 8 nov)
 *   Precisa de:    LightSensorEvent, como modelo.
 *   Quem espera:   o próprio Celso, para o GroundMoistureSensor (C5).
 * ==================================================================================
 *
 * FORMATO
 *   GroundMoistureSensorEvent [moisture=18.0, area=Garden, getType()=SENSOR]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Repetir o modelo do LightSensorEvent
 *     Lógica sugerida: mesma estrutura e mesma ordem no construtor; muda o nome do campo.
 *     Liga-se a: LightSensorEvent; GroundMoistureSensor.
 *
 *   Passo 2 · Confirmar
 *     O texto corresponde ao formato acima.
 *
 * NAS FASES SEGUINTES (enunciado, 1.2)
 *   "se o sensor de humidade no solo avisar que a terra está seca, o agente decisor da rega
 *   define uma ordem de irrigação, e os atuadores ligam a irrigação do jardim".
 */
public class GroundMoistureSensorEvent extends SensorEvent {

    private final double moisture;

    /** Cria uma leitura de humidade do solo em percentagem. */
    public GroundMoistureSensorEvent(Areas area, double moisture) {
        super(area);
        this.moisture = moisture;
    }

    /** Devolve a humidade do solo medida. */
    public double getMoisture() {
        return moisture;
    }

    /** Fornece a parte variável do texto deste evento. */
    @Override
    protected String getEventFields() {
        return "moisture=" + moisture;
    }
}

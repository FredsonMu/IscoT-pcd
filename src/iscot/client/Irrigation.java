package iscot.client;

/**
 * Irrigation — programa do atuador de rega.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C9 · feature/rega
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        NÃO. Fase 4.
 *   No dia 6:     Nada.
 *   Depende de:   C5, F1, F6.
 *   Liga-se a:    Recebe do IrrigationDecider.
 *   Código base:  Molde: Ventilator; usa IrrigationIcon.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Enunciado 1.2: "os atuadores ligam a irrigação do jardim até o nível desejado de
 *   humidade ser atingido". Código base: IrrigationIcon (setAtiva).
 *
 * SEGUIR O MOLDE DO PROFESSOR: Ventilator
 *   - main(args): endereço, porto, área (normalmente Garden).
 *   - JFrame "Rega em <área>": IrrigationIcon no CENTER, ActionHistory no SOUTH.
 *   - Fase 1: ordem criada localmente (AreaIrrigationOrderMessage true, depois false).
 *   - Fase 3–4: register(Type.ACTUATOR, area); ao receber AreaIrrigationOrderMessage:
 *       icon.setAtiva(state); history.add(state ? "Rega ligada" : "Rega desligada").
 *   - Zona e duração do IrrigationIcon: não usamos (o enunciado não pede).
 *
 * LIGAÇÕES
 *   Usa: IrrigationIcon, ActionHistory, BrokerConnection, AreaIrrigationOrderMessage.
 *
 */
public class Irrigation {
}

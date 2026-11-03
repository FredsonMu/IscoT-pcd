package iscot.client;

/**
 * AirConditioner — programa do atuador de ar condicionado.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       F8 · feature/clima
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        NÃO. Fase 4.
 *   No dia 6:     Nada.
 *   Depende de:   C5, F1, F6.
 *   Liga-se a:    Recebe do ClimateDecider.
 *   Código base:  Molde: Ventilator; usa ACIcon.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Enunciado 1.2: a temperatura alta "espoleta a ligação de um ar condicionado".
 *   Código base: ACIcon com setModo(AirConditioningMode).
 *
 * SEGUIR O MOLDE DO PROFESSOR: Ventilator
 *   - main(args): endereço, porto, área.
 *   - JFrame "Ar condicionado em <área>": ACIcon no CENTER, ActionHistory no SOUTH.
 *   - Fase 1: ordem criada localmente (AreaClimateOrderMessage Frio, depois Desligado).
 *   - Fase 3–4: register(Type.ACTUATOR, area); ao receber AreaClimateOrderMessage:
 *       icon.setModo(mode); history.add("Ar condicionado em modo " + mode).
 *   - Temperatura do ACIcon (16–30 ºC): não usamos, a menos que o professor peça.
 *
 * ATENÇÃO
 *   ACIcon.setModo altera o campo "mode" fora da thread do Swing (só o setAtivo usa
 *   invokeLater). Para ficar seguro, chamar setModo dentro de SwingUtilities.invokeLater.
 *
 * LIGAÇÕES
 *   Usa: ACIcon, AirConditioningMode, ActionHistory, BrokerConnection,
 *   AreaClimateOrderMessage.
 *
 */
public class AirConditioner {
}

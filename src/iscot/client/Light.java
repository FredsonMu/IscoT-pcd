package iscot.client;

/**
 * Light — programa de uma lâmpada (um processo por lâmpada).
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       F2 · feature/light
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        SIM (fase 1). Merge até qua 4 nov. No dia 6: só a versão local (sem servidor).
 *   No dia 6:     Lançar "04 Lampada LivingRoom" duas vezes: cada lâmpada cria AreaLightingOrderMessage(true), acende o LightIcon e escreve no histórico; depois false, apaga.
 *   Depende de:   C2 (AreaLightingOrderMessage), F1 (ActionHistory).
 *   Liga-se a:    Fase 4: recebe as ordens do LightingDecider pela BrokerConnection.
 *   Código base:  Molde: Ventilator do professor; usa LightIcon; é exatamente o TODO da fase 1 do professor.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Apresentação: "existirão dois clientes atuadores, um para cada lâmpada, que
 *   executarão as ordens do decisor"; o atuador "recebe do servidor mensagens da classe
 *   AreaLightingOrderMessage". Enunciado 1.3: histórico "Lâmpada desligada - HH:MM:SS".
 *
 * SEGUIR O MOLDE DO PROFESSOR: Ventilator
 *   Mesma estrutura, com LightIcon em vez de VentilatorIcon:
 *   - main(args): endereço, porto, área (Areas.valueOf(args[2])).
 *   - JFrame "Lâmpada em <área>": LightIcon no CENTER e ActionHistory no SOUTH.
 *   - FASE 1 (dia 6), como o professor pede no Ventilator ("for first phase: create an
 *     appropriate event hardcoded locally and change the GUI accordingly"):
 *       criar localmente new AreaLightingOrderMessage(area, true), aplicá-la
 *       (icon.setLigada(true); history.add("Lâmpada ligada")), esperar uns segundos e
 *       fazer o mesmo com false.
 *   - FASE 3–4: register(Type.ACTUATOR, area) e startReceiving:
 *       se msg for AreaLightingOrderMessage e state for diferente do atual:
 *         icon.setLigada(state); history.add(state ? "Lâmpada ligada" : "Lâmpada desligada")
 *       qualquer outra mensagem: ignorar (o ack é enviado na mesma).
 *
 * THREADS
 *   As ordens chegam na thread recetora. LightIcon.setLigada já usa invokeLater
 *   (ComponentState do professor) e ActionHistory.add também: é seguro.
 *
 * LIGAÇÕES
 *   Usa: LightIcon (professor), ActionHistory, BrokerConnection, AreaLightingOrderMessage.
 *
 */
public class Light {
}

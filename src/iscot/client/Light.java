package iscot.client;

/**
 * Light — programa de uma lâmpada (um processo por lâmpada).
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
 * Prioridade 1 (cenário do professor) · Responsável: Fredson · Revisor: Celso
 */
public class Light {
}

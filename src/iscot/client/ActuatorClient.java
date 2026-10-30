package iscot.client;

/**
 * ActuatorClient — recebe ordens e muda o estado de um dispositivo simulado.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1: "os atuadores devem subscrever um tópico adequado para receber os eventos
 *   relacionados com o(s) dispositivo(s) que estão a controlar"; as ações originam
 *   "mudanças no estado de componentes físicos como lâmpadas, ar condicionado ou
 *   sistemas de rega". Secção 1.3: histórico de ações.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Recebe: BrokerConnection, deviceId, área, tipo.
 *   - Estado do dispositivo (ex.: ligada/desligada, aberta/fechada).
 *   - Thread recetora: em ciclo, receive(); se for Event:
 *       1. interpretar a ordem ("LIGAR" -> estado = ligado);
 *       2. acrescentar ao ActionHistory: "Lâmpada ligada - HH:MM:SS";
 *       3. atualizar a ActuatorFrame (via invokeLater);
 *       4. enviar AckMessage(eventId);
 *       5. (extra) enviar ReceptionConfirmationMessage com OK ou NOK.
 *   - Ordem desconhecida: não muda o estado, mas confirma o consumo (senão o tópico
 *     fica preso até ao timeout) e, no extra, responde NOK.
 *
 * QUANDO ENVIAR O ACK
 *   Depois de aplicar a ordem: "consumi" deve significar que o evento foi tratado.
 *   Se a ação demorar (rega a encher), o tópico fica preso; aí faz sentido Ack logo e
 *   confirmação de execução (extra) mais tarde.
 *
 * LIGAÇÕES
 *   Usa: BrokerConnection, Event, AckMessage, ReceptionConfirmationMessage,
 *   ActionHistory, ActuatorFrame.
 *   Recebe de: DecisorClient (via servidor).
 *
 * DÚVIDAS
 *   - Um atuador controla um só dispositivo ou vários ("o(s) dispositivo(s)")?
 *     Por agora, um por processo; para ter duas lâmpadas, lançam-se dois clientes.
 *   - Podemos acrescentar um botão manual na GUI (o utilizador desliga a luz à mão)?
 *     Isso também iria para o histórico.
 *
 * Fase 4 · Responsável: Fredson · Revisor: Celso
 */
public class ActuatorClient {
}

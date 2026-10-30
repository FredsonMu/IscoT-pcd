package iscot.client;

/**
 * ActuatorClient — recebe ordens, muda o estado de um dispositivo simulado e mantém o
 * histórico de ações.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1: "os atuadores devem subscrever um tópico adequado para receber os eventos
 *   relacionados com o(s) dispositivo(s) que estão a controlar"; as ações originam
 *   "mudanças no estado de componentes físicos como lâmpadas, ar condicionado ou
 *   sistemas de rega".
 *   Secção 1.3: "A cada ação tomada por um cliente atuador, este adiciona a ação tomada
 *   a um histórico verificável pelo utilizador de todas as ações tomadas pelo atuador
 *   desde a última vez que foi ligado", ex.: "Lâmpada desligada - HH:MM:SS".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Recebe: BrokerConnection, deviceId, área, tipo.
 *   - Estado do dispositivo (ex.: ligada/desligada, aberta/fechada).
 *   - Histórico: lista de String só em memória ("desde a última vez que foi ligado"
 *     significa que começa vazia em cada arranque).
 *       addHistory(descrição): junta " - " e a hora atual formatada HH:mm:ss
 *         (Java 8: SimpleDateFormat("HH:mm:ss") ou LocalTime + DateTimeFormatter).
 *       getHistory(): devolve uma cópia (para a GUI).
 *   - Thread recetora: em ciclo, receive(); se for Event:
 *       1. interpretar a ordem ("LIGAR" -> estado = ligado);
 *       2. addHistory("Lâmpada ligada");
 *       3. atualizar a ActuatorFrame (via SwingUtilities.invokeLater);
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
 * CONCORRÊNCIA DO HISTÓRICO
 *   É escrito pela thread recetora (e, se houver botão manual, pela thread do Swing) e
 *   lido pela GUI. Duas opções, escolher uma e saber explicar:
 *     a) addHistory/getHistory synchronized;
 *     b) fazer todas as alterações dentro de invokeLater, na thread do Swing: só uma
 *        thread lhe toca (confinamento) e não precisa de cadeado.
 *
 * ARMADILHA
 *   No formato da hora, "mm" são minutos e "MM" é o mês: usar HH:mm:ss.
 *
 * LIGAÇÕES
 *   Usa: BrokerConnection, Event, AckMessage, ReceptionConfirmationMessage,
 *   ActuatorFrame. Recebe de: DecisorClient (via servidor).
 *
 * ALTERNATIVA
 *   Histórico numa classe própria (ActionHistory): testável sem rede e reutilizável,
 *   mas é usado só aqui; dentro do atuador há menos uma classe para ligar.
 *
 * DÚVIDAS
 *   - Um atuador controla um só dispositivo ou vários ("o(s) dispositivo(s)")?
 *     Por agora, um por processo; para ter duas lâmpadas, lançam-se dois clientes.
 *   - Podemos acrescentar um botão manual na GUI? Também iria para o histórico.
 *   - O histórico deve ser também gravado em ficheiro? Na GUI parece chegar.
 *
 * Fase 1 (histórico local) e 4 · Responsável: Fredson · Revisor: Celso
 */
public class ActuatorClient {
}

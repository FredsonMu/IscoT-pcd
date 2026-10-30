package iscot.client;

/**
 * ActuatorClient — recebe ordens, muda o estado de um dispositivo simulado e mantém o
 * histórico de ações.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Apresentação: "atuadores: clientes passivos que cumprem ordens de alto nível,
 *   controlando consequentemente o dispositivo associado: interruptor de luz, ventilador,
 *   ar condicionado"; "recebe do servidor mensagens da classe AreaLightingOrderMessage".
 *   No cenário da sala: "existirão dois clientes atuadores, um para cada lâmpada".
 *   Enunciado 1.3: histórico "de todas as ações tomadas pelo atuador desde a última
 *   vez que foi ligado", ex.: "Lâmpada desligada - HH:MM:SS".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Recebe: BrokerConnection, deviceId, área, tipo (LAMPADA, AR_CONDICIONADO,
 *     VENTILADOR, ...). UM atuador controla UM dispositivo (é o que o professor mostra:
 *     duas lâmpadas = dois clientes).
 *   - Estado do dispositivo: boolean ligado.
 *   - Histórico: lista de String só em memória ("desde a última vez que foi ligado"
 *     significa que começa vazia em cada arranque).
 *       addHistory(descrição): junta " - " e a hora atual formatada HH:mm:ss
 *         (Java 8: SimpleDateFormat("HH:mm:ss") ou LocalTime + DateTimeFormatter).
 *       getHistory(): devolve uma cópia (para a GUI).
 *   - Thread recetora: em ciclo, receive(); se for OrderMessage:
 *       1. ler state (AreaLightingOrderMessage, AreaClimateOrderMessage);
 *       2. se o estado mudar: aplicar e addHistory("Lâmpada ligada") /
 *          ("Ventilador desligado") / ...; se já estava nesse estado, não repetir
 *          no histórico (decisão nossa; o histórico regista ações tomadas);
 *       3. atualizar a ActuatorFrame (via SwingUtilities.invokeLater);
 *       4. enviar AckMessage(messageId);
 *       5. (extra) enviar ReceptionConfirmationMessage com OK ou NOK.
 *
 * QUANDO ENVIAR O ACK
 *   Depois de aplicar a ordem: "consumi" deve significar que a mensagem foi tratada.
 *   Se a ação demorar (rega a encher), o tópico fica preso; aí faz sentido Ack logo e
 *   confirmação de execução (extra) mais tarde.
 *
 * CONCORRÊNCIA DO HISTÓRICO
 *   É escrito pela thread recetora (e, se houver interruptor manual na GUI, pela thread
 *   do Swing) e lido pela GUI. Duas opções, escolher uma e saber explicar:
 *     a) addHistory/getHistory synchronized;
 *     b) fazer todas as alterações dentro de invokeLater, na thread do Swing: só uma
 *        thread lhe toca (confinamento) e não precisa de cadeado.
 *
 * ARMADILHA
 *   No formato da hora, "mm" são minutos e "MM" é o mês: usar HH:mm:ss.
 *
 * LIGAÇÕES
 *   Usa: BrokerConnection, OrderMessage e filhas, AckMessage,
 *   ReceptionConfirmationMessage, ActuatorFrame. Recebe de: DecisorClient (via servidor).
 *
 * ALTERNATIVA
 *   Histórico numa classe própria: testável sem rede e reutilizável, mas é usado só aqui;
 *   dentro do atuador há menos uma classe para ligar.
 *
 * DÚVIDAS
 *   - Podemos acrescentar um interruptor manual na GUI? Também iria para o histórico.
 *   - O histórico deve ser também gravado em ficheiro? Na GUI parece chegar.
 *
 * Fase 1 (histórico local) e 4 · Responsável: Fredson · Revisor: Celso
 */
public class ActuatorClient {
}

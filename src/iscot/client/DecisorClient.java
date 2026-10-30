package iscot.client;

/**
 * DecisorClient — recebe leituras, aplica uma regra e envia ordens de alto nível.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Apresentação: "decisores: clientes reativos que reagem a leituras dos sensores e
 *   tomam decisões de alto nível"; "ordens para todas as lâmpadas ou outras fontes de
 *   iluminação"; "envia para tratamento no servidor mensagens da classe
 *   AreaLightingOrderMessage". No cenário da sala: "acender a(s) luz(es) sempre que a
 *   iluminação está abaixo de 500 lux".
 *   Enunciado 1.2, outras regras: terra seca -> regar; chuva -> fechar janelas;
 *   temperatura acima de X -> ar condicionado.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Recebe: BrokerConnection, deviceId, área, tipo.
 *   - Thread recetora: em ciclo, receive(); se for SensorMessage:
 *       1. ler o valor (instanceof LightSensorMessage -> getLight(), ...);
 *       2. aplicar a regra do seu tipo:
 *            DECISOR_LUZ   : luz < 500 lux       -> AreaLightingOrderMessage(area, true)
 *                            luz >= 500 lux      -> AreaLightingOrderMessage(area, false)
 *            DECISOR_CLIMA : temperatura > limiar -> AreaClimateOrderMessage(area, true)
 *       3. enviar AckMessage(messageId) ao servidor;
 *       4. enviar a ordem SÓ se o estado decidido mudou em relação à última ordem;
 *       5. atualizar a DecisorFrame (sempre via SwingUtilities.invokeLater).
 *
 * PORQUÊ "SÓ QUANDO O ESTADO MUDA" (e histerese)
 *   Se a luz estiver baixa durante 10 leituras, mandar 10 vezes "acender" enche o tópico
 *   de ordens inúteis e, como cada uma espera pelos acks de todas as lâmpadas, atrasa
 *   tudo. Melhor ainda: dois limiares (acende abaixo de 500, apaga acima de 600) para
 *   não oscilar quando a luz anda à volta de 500.
 *
 * THREADS
 *   - Thread recetora (readObject bloqueia: não pode ser na thread do Swing).
 *   - Atualização da GUI: invokeLater, porque o Swing não é thread-safe.
 *
 * ORDEM ACK / ORDEM
 *   Enviar o Ack primeiro liberta o tópico de leituras mais cedo. Enviar a ordem
 *   primeiro garante que a decisão já saiu antes de dizer "consumi". Para o enunciado
 *   tanto faz; escolhemos Ack primeiro e explicamos porquê.
 *
 * LIGAÇÕES
 *   Usa: BrokerConnection, SensorMessage e filhas, OrderMessage e filhas, AckMessage,
 *   DecisorFrame. Recebe de: SensorClient (via servidor). Envia para: ActuatorClient
 *   (via servidor).
 *
 * DÚVIDAS
 *   - "Deve esperar que todos os atuadores encarregues da iluminação acendam a
 *     lâmpada": o decisor deve bloquear até receber confirmação? Isso é o extra
 *     (ReceptionConfirmationMessage)? Perguntar.
 *   - A rega "até o nível desejado de humidade": é o decisor que manda parar quando a
 *     humidade sobe? Propomos que sim.
 *
 * Fase 4 · Responsável: Fredson · Revisor: Celso
 */
public class DecisorClient {
}

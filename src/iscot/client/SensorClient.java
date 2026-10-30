package iscot.client;

/**
 * SensorClient — lógica de um sensor: produz leituras e publica-as.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1: "Receção das leituras dos sensores: o servidor recebe mensagens que contêm
 *   sinais emitidos pelos sensores em intervalos de tempo arbitrários (entre 1 segundo
 *   e várias horas)."
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Recebe: BrokerConnection, deviceId, área, tipo; calcula o tópico de publicação
 *     com Topics.publishTopic.
 *   - Abre a SensorFrame e liga-lhe um "ouvinte": quando o utilizador carrega em
 *     "Publicar", cria um Event com o valor atual e envia-o.
 *   - Modo automático (opcional, ativado na GUI): uma thread que, em ciclo, dorme um
 *     intervalo aleatório (Thread.sleep) e publica o valor atual. Desligar o modo =
 *     interrupt() dessa thread.
 *   - Formato do conteúdo: "valor=123" (combinar com o DecisorClient).
 *
 * THREADS
 *   - Publicação manual: corre na thread do Swing (actionPerformed). Enviar pela rede
 *     é rápido em localhost, mas em rigor não devia bloquear a GUI; se der problemas,
 *     passar o envio para outra thread.
 *   - Publicação automática: thread própria. Uso típico de sleep + interrupt (aula 02).
 *
 * LIGAÇÕES
 *   Usa: BrokerConnection, Event, Topics, SensorFrame.
 *   Do outro lado: o DecisorClient da mesma área e grandeza recebe estas leituras.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Só manual: mais fácil de demonstrar ("carrego aqui e a luz acende ali").
 *   - Só aleatório: mais realista, mas a demo fica imprevisível.
 *   - Os dois (escolhido): manual para demonstrar, automático para testes de carga.
 *   - javax.swing.Timer em vez de thread com sleep: corre na thread do Swing; simples,
 *     mas mistura temporização com GUI. A thread mostra melhor a matéria.
 *
 * DÚVIDAS
 *   - Os valores devem ser realistas (lux, ºC, %)? Vamos usar unidades reais na GUI.
 *   - "Várias horas" de intervalo: basta simular com segundos?
 *
 * Fase 4 · Responsável: Fredson · Revisor: Celso
 */
public class SensorClient {
}

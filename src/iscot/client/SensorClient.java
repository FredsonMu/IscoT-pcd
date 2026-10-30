package iscot.client;

/**
 * SensorClient — lógica de um sensor: lê (simula) o ambiente e envia leituras.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Apresentação: "sensores: clientes ativos, que leem dados do ambiente"; o sensor de
 *   luminosidade "reage a alterações na luminosidade" e "envia para tratamento no
 *   servidor mensagens da classe LightSensorMessage".
 *   Enunciado: sinais "em intervalos de tempo arbitrários (entre 1 segundo e várias horas)".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Recebe: BrokerConnection, deviceId, área, tipo.
 *   - Abre a SensorFrame (ou o componente de sensor do código base do professor) e
 *     reage a ALTERAÇÕES do valor: quando o valor muda, cria a mensagem do seu tipo e
 *     envia-a:
 *         SENSOR_LUZ          -> new LightSensorMessage(area, lux)
 *         SENSOR_TEMPERATURA  -> new TemperatureSensorMessage(area, graus)
 *   - Evitar inundar o servidor: um slider gera dezenas de eventos por segundo; enviar
 *     só quando o utilizador larga o slider, ou só se a diferença for relevante.
 *   - Modo automático (opcional): uma thread que, em ciclo, dorme um intervalo aleatório
 *     (Thread.sleep), muda o valor (simulação) e envia. Desligar = interrupt().
 *   - O sensor não subscreve nada e não recebe mensagens de tópicos.
 *
 * THREADS
 *   - Envio por alteração na GUI: corre na thread do Swing (listener). Enviar em
 *     localhost é rápido, mas em rigor não devia bloquear a GUI; se der problemas,
 *     passar o envio para outra thread.
 *   - Simulação automática: thread própria. Uso típico de sleep + interrupt (aula 02).
 *
 * LIGAÇÕES
 *   Usa: BrokerConnection, LightSensorMessage, TemperatureSensorMessage, SensorFrame.
 *   Do outro lado: o DecisorClient da mesma área e grandeza recebe estas leituras.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Enviar a intervalos fixos, mesmo sem mudança: mais simples, mas o professor diz
 *     "reage a alterações", e enche os tópicos de leituras iguais.
 *   - Só manual / só automático / os dois (escolhido): manual para a demo, automático
 *     para testes de carga.
 *   - javax.swing.Timer em vez de thread com sleep: corre na thread do Swing; simples,
 *     mas mistura temporização com GUI. A thread mostra melhor a matéria.
 *
 * DÚVIDAS
 *   - O código base da GUI do professor (ainda por disponibilizar) já trata as
 *     alterações de valor? Adaptar quando sair.
 *
 * Fase 4 · Responsável: Fredson · Revisor: Celso
 */
public class SensorClient {
}

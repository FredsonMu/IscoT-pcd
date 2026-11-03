package iscot.client;

/**
 * LightSensor — programa do sensor de luminosidade (um processo por sensor).
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       F3 · feature/light-sensor
 *   Responsável:  Fredson   ·   Revisor: Celso
 *   Dia 6:        SIM (fase 1). Merge até qua 4 nov. No dia 6: só a versão local (imprime a mensagem).
 *   No dia 6:     Lançar "02 Sensor luz LivingRoom": largar o slider imprime LightSensorMessage [light=..., area=LivingRoom, getType()=SENSOR].
 *   Depende de:   C2 (LightSensorMessage).
 *   Liga-se a:    Fase 4: envia pela BrokerConnection para o LightingDecider.
 *   Código base:  Molde: HumiditySensor do professor; usa SensorControl.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 *
 * OBJETIVO
 *   Apresentação: o sensor de luminosidade "reage a alterações na luminosidade" e envia
 *   LightSensorMessage. Cenário da sala: 1 sensor de iluminação.
 *
 * SEGUIR O MOLDE DO PROFESSOR: HumiditySensor
 *   Mesma estrutura, mudando só o que é da luz:
 *   - main(args) com 3 argumentos: endereço, porto, área (Areas.valueOf(args[2]));
 *     ex.: localhost 8080 LivingRoom
 *   - JFrame "Luminosidade em <área>" com um SensorControl(ícone, "Luminosidade -
 *     <área>", "lux", 0, 1000, 10, 500, callback)
 *   - FASE 1 (dia 6): o callback cria uma LightSensorMessage(area, valor) e mostra-a
 *     na consola (System.out.println(msg)) — ainda sem servidor.
 *   - FASE 3–4: no arranque, BrokerConnection + register(Type.SENSOR, area); o callback
 *     passa a fazer connection.send(new LightSensorMessage(area, valor)).
 *   - O sensor não subscreve nada e não precisa de thread recetora.
 *
 * THREADS
 *   O callback corre na thread do Swing (vem do ChangeListener do slider). O
 *   SensorControl só o chama quando o utilizador larga o slider, por isso não inunda o
 *   servidor.
 *
 * LIGAÇÕES
 *   Usa: SensorControl (professor), BrokerConnection, LightSensorMessage.
 *   Do outro lado: LightingDecider da mesma área.
 *
 */
public class LightSensor {
}

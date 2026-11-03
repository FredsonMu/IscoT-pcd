package iscot.common;

/**
 * Message — classe-mãe de tudo o que viaja pelo socket.
 *
 * OBJETIVO (enunciado, apresentação e código base do professor)
 *   Fase 4: mensagens "através de canais de objetos". A apresentação mostra que cada
 *   mensagem leva a ÁREA e o TIPO de quem a enviou:
 *     LightSensorMessage [light=320.0, area=LivingRoom, getType()=SENSOR]
 *     AreaLightingOrderMessage [state=true, area=LivingRoom, getType()=DECIDER]
 *   O código base do professor dá os enums a usar: iscot.enums.Areas (Kitchen, WC,
 *   LivingRoom, BedRoom, Garden) e iscot.enums.Type (SENSOR, DECIDER, ACTUATOR).
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Classe abstrata, implements Serializable, serialVersionUID = 1L.
 *   - Campos:
 *       area (Areas, final)        a divisão a que a mensagem diz respeito; é também
 *                                  o TÓPICO onde o servidor a publica (ver Topic)
 *       messageId (long)           NÃO é final: atribuído pelo servidor em
 *                                  Topic.publish; é com ele que o AckMessage diz
 *                                  "consumi a mensagem X". 0 = ainda não publicada.
 *       senderId (String)          id do dispositivo que enviou (dado no registo)
 *       timestamp (long, final)    hora de criação (logs, GUI do servidor)
 *   - abstract Type getType()      SENSOR, DECIDER ou ACTUATOR (enum do professor)
 *   - toString() no formato do professor:
 *       "NomeDaClasse [campos, area=LivingRoom, getType()=SENSOR]"
 *
 * HIERARQUIA (pacote common)
 *   Message
 *    ├── RegistrationMessage             registo: pedido (tipo + área) e resposta (id)
 *    ├── SensorMessage (abstrata)        getType() = SENSOR
 *    │    ├── LightSensorMessage         light (lux)          [nome do professor]
 *    │    ├── HumiditySensorMessage      humidity (%)         [sensor do código base]
 *    │    └── TemperatureSensorMessage   temperature (ºC)
 *    ├── OrderMessage (abstrata)         getType() = DECIDER
 *    │    ├── AreaLightingOrderMessage   state                [nome do professor]
 *    │    ├── AreaIrrigationOrderMessage state
 *    │    └── AreaClimateOrderMessage    mode (AirConditioningMode do professor)
 *    ├── AckMessage                      "consumi a mensagem X" (faz o latch descer)
 *    └── ReceptionConfirmationMessage    extra: "executei a ordem, OK/NOK"
 *   Uma automação por par sensor/ordem, todas com componentes no código base:
 *   luz (LightIcon), humidade/rega (HumiditySensor, IrrigationIcon), temperatura
 *   (VentilatorIcon, ACIcon). Chuva/janelas não tem componente no código base:
 *   não fazemos, a menos que o professor peça.
 *
 * LIGAÇÕES
 *   É usada por: BrokerConnection (send/receive), ClientHandler (decide com
 *   instanceof), Topic (guarda a fila), TopicDispatcher (entrega).
 *
 * CONCORRÊNCIA
 *   A mesma mensagem é lida pelo dispatcher e escrita em vários sockets. Os campos são
 *   final, exceto messageId, atribuído dentro de Topic.publish (synchronized) ANTES
 *   de a mensagem entrar na fila. Assim é seguro.
 *
 * REGRA DA SERIALIZAÇÃO
 *   A classe tem de existir com o mesmo nome completo nos dois lados (por isso fica
 *   num pacote partilhado). Campos não serializáveis (Socket, Swing) dão
 *   NotSerializableException. Os enums do professor são serializáveis.
 *
 * Fase 2 (classes) e 4 (envio) · Responsável: Celso · Revisor: Fredson
 */
public abstract class Message {
}

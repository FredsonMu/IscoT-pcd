package iscot.common;

/**
 * Message — classe-mãe de tudo o que viaja pelo socket.
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO DO PROFESSOR
 *   Fase 4: mensagens "através de canais de objetos". A apresentação do projeto mostra
 *   que cada mensagem leva a ÁREA e o TIPO de quem a enviou:
 *     LightSensorMessage [light=320.0, area=Sala, getType()=SENSOR]
 *     AreaLightingOrderMessage [state=true, area=Sala, getType()=DECIDER]
 *   Por isso estes dois campos vivem aqui, na classe-mãe.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Classe abstrata (nunca se envia uma "Message" genérica, só as filhas).
 *   - implements Serializable + "private static final long serialVersionUID = 1L;".
 *   - Campos:
 *       area (Area, final)          a divisão a que a mensagem diz respeito
 *       messageId (long)            NÃO é final: atribuído pelo servidor ao publicar
 *                                   (Topic.publish); é com ele que o AckMessage diz
 *                                   "consumi a mensagem X". 0 = ainda não publicada.
 *       senderId (String)           id do dispositivo que enviou (preenchido pelo
 *                                   cliente depois do registo)
 *       timestamp (long, final)     hora de criação, para logs e para a GUI do servidor
 *   - abstract Role getType()       SENSOR, DECIDER ou ACTUATOR: cada subclasse responde
 *   - toString() no formato do professor: "NomeDaClasse [campos, area=Sala, getType()=SENSOR]"
 *
 * HIERARQUIA (todas no pacote common)
 *   Message
 *    ├── RegistrationMessage           registo: pedido do cliente e resposta do servidor
 *    ├── SensorMessage (abstrata)      getType() = SENSOR      -> tópico area/grandeza/leituras
 *    │    ├── LightSensorMessage       light (lux)            [nome dado pelo professor]
 *    │    └── TemperatureSensorMessage temperature (ºC)
 *    ├── OrderMessage (abstrata)       getType() = DECIDER     -> tópico area/grandeza/ordens
 *    │    ├── AreaLightingOrderMessage state (ligar/desligar) [nome dado pelo professor]
 *    │    └── AreaClimateOrderMessage  state (AC / ventilador)
 *    ├── AckMessage                    "consumi a mensagem X" (faz o latch descer)
 *    └── ReceptionConfirmationMessage  extra: "executei a ordem, OK/NOK"
 *   Humidade/rega e chuva/janelas seguem exatamente o mesmo padrão
 *   (HumiditySensorMessage, AreaIrrigationOrderMessage, ...) e entram quando fizermos
 *   essas automações.
 *
 * LIGAÇÕES
 *   É usada por: BrokerConnection.send/receive, ClientHandler (lê Object e decide com
 *   instanceof), Topic (guarda SensorMessage/OrderMessage na fila), TopicDispatcher.
 *
 * CONCORRÊNCIA
 *   Depois de publicada, a mesma mensagem é lida pelo dispatcher e escrita em vários
 *   sockets. Os campos são final, exceto messageId, que é atribuído dentro de
 *   Topic.publish (synchronized) ANTES de a mensagem entrar na fila. Assim é seguro.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Uma só classe Event com o conteúdo em texto ("valor=320", "LIGAR"): menos classes,
 *     mas obriga a fazer parsing, o compilador não apanha erros, e não é o que o
 *     professor mostrou. Era a nossa primeira versão; mudámos (docs/03-decisoes, D20).
 *   - Texto/JSON no socket: legível e independente de linguagem, mas o enunciado pede
 *     canais de objetos.
 *
 * REGRA DE OURO DA SERIALIZAÇÃO
 *   A classe tem de existir com o mesmo nome completo (iscot.common.X) nos dois lados.
 *   Por isso todas as mensagens vivem neste pacote, partilhado por servidor e clientes.
 *   Campos não serializáveis (Socket, componentes Swing) dão NotSerializableException.
 *
 * Fase 2 (classes) e 4 (envio) · Responsável: Celso · Revisor: Fredson
 */
public abstract class Message {
}

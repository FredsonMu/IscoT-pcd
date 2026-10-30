package iscot.common;

/**
 * Message — classe-mãe de tudo o que viaja pelo socket.
 *
 * OBJETIVO NO ENUNCIADO
 *   Fase 4: "desenvolvimento das classes necessárias ao envio de mensagens, através de
 *   canais de objetos". Para enviar um objeto num ObjectOutputStream, a classe tem de
 *   implementar java.io.Serializable.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Classe abstrata (nunca se envia uma "Message" genérica, só as filhas).
 *   - implements Serializable.
 *   - Campo "private static final long serialVersionUID = 1L;" (a "versão" da classe;
 *     se for diferente nos dois lados a leitura falha com InvalidClassException).
 *   - Campo final com o timestamp de criação (útil para logs e para o histórico).
 *
 * HIERARQUIA (todas no pacote common)
 *   Message
 *    ├── RegisterMessage                 registo: pedido do cliente e resposta do servidor
 *    ├── Event                           leitura ou ordem, num tópico
 *    ├── AckMessage                      "consumi o evento X" (faz o latch descer)
 *    └── ReceptionConfirmationMessage    extra: "executei a ordem, OK/NOK"
 *
 * LIGAÇÕES
 *   É usada por: BrokerConnection.send/receive, ClientHandler.send e o seu ciclo de
 *   leitura (que pergunta "instanceof Event? instanceof AckMessage?").
 *
 * CONCORRÊNCIA
 *   As mensagens devem ser, sempre que possível, imutáveis (campos final, sem setters).
 *   Assim podem ser lidas por várias threads sem cadeado. Exceção: Event.eventId,
 *   atribuído pelo servidor dentro do Topic (que já está sincronizado).
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Uma só classe Message com um enum "Kind" e campos para tudo: menos ficheiros,
 *     mas cada mensagem carrega campos que não usa e o código enche-se de switch.
 *   - Texto (JSON, "TIPO;a;b"): legível e independente de linguagem, mas temos de
 *     escrever o parsing e o enunciado pede canais de objetos. Fica a hierarquia.
 *
 * REGRA DE OURO DA SERIALIZAÇÃO
 *   A classe tem de existir com o mesmo nome completo (iscot.common.X) nos dois lados.
 *   Por isso todas as mensagens vivem neste pacote, partilhado por servidor e clientes.
 *   Campos que não sejam serializáveis (Socket, componentes Swing) dão
 *   NotSerializableException: nunca os pôr numa mensagem.
 *
 * Fase 4 (pode ser escrita já na fase 2) · Responsável: Celso · Revisor: Fredson
 */
public abstract class Message {
}

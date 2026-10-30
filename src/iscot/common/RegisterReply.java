package iscot.common;

/**
 * RegisterReply — resposta do servidor ao pedido de registo.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.1: "o cliente inscreve-se no servidor, que criará para ele um
 *   identificador de dispositivo, que deve ser único em todo o sistema".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends Message.
 *   - Campos final: boolean aceite, String deviceId (null se recusado),
 *     String motivo (null se aceite; ex.: "Tipo inexistente: TORRADEIRA").
 *   - Construtor e getters.
 *
 * LIGAÇÕES
 *   Criada por: ClientHandler. Lida por: BrokerConnection.register, que lança uma
 *   exceção se não for aceite (o ClientMain mostra o motivo e termina).
 *
 * ALTERNATIVA
 *   Fechar simplesmente o socket quando o tipo é inválido. Funciona, mas o cliente
 *   recebe só um "Connection reset" sem saber porquê. Uma resposta explícita é
 *   melhor para o utilizador e para depurar.
 *
 * Fase 3 · Responsável: Celso · Revisor: Fredson
 */
public class RegisterReply {
}

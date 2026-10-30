package iscot.common;

/**
 * DeviceType — todos os tipos de dispositivo que o servidor aceita.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 2.1: "Se o tipo não existir a ligação é recusada." Precisamos de uma lista
 *   fechada de tipos válidos. Secção 1.2 dá os exemplos de automação que definem os tipos.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Constantes agrupadas por grandeza (o "assunto" que liga sensor, decisor e atuador):
 *       luz          : SENSOR_LUZ, DECISOR_LUZ, LAMPADA
 *       temperatura  : SENSOR_TEMPERATURA, DECISOR_CLIMA, AR_CONDICIONADO
 *       humidade     : SENSOR_HUMIDADE, DECISOR_REGA, REGA
 *       chuva        : SENSOR_CHUVA, DECISOR_JANELAS, JANELA
 *   - Cada constante guarda dois campos final: o Role e a grandeza (String "luz", ...).
 *     Isto faz-se com um construtor privado do enum.
 *   - Getters para o papel e para a grandeza.
 *   - Método estático "parse", igual ao de Area.
 *
 * LIGAÇÕES
 *   Usa: Role. É usada por: Topics, ClientHandler, Server, ClientMain.
 *
 * COMO LIGA AO RESTO
 *   O servidor nunca precisa de saber "o que é uma lâmpada". Só pergunta ao tipo:
 *   "qual é o teu papel e a tua grandeza?" e a classe Topics devolve o tópico.
 *   Acrescentar um tipo novo (ex.: SENSOR_FUMO) é só acrescentar uma linha aqui.
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Hierarquia de classes (class Lampada extends Atuador): mais OO, mas o servidor
 *     não precisa de comportamento, só de dados. Seria complexidade sem ganho.
 *   - Ficheiro de configuração com os tipos: flexível, mas o enunciado não pede e
 *     obriga a ler e validar ficheiros.
 *
 * DÚVIDAS
 *   - Podem existir tipos que só fazem sentido numa área (REGA só no JARDIM)?
 *     Devemos recusar "sala REGA"? Perguntar ao professor; por agora aceitamos.
 *   - Os nomes dos tipos na linha de comandos têm de ser estes? O professor espera
 *     algum nome em particular?
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public enum DeviceType {
}

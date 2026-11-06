package iscot.common;

/**
 * LightSensorEvent — leitura de luminosidade, em lux. É O MODELO DAS OUTRAS LEITURAS.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C2 · feature/eventos-modelo · CELSO, 4.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         1 (sáb 7 nov). ENTREGA A AO FREDSON, com o AreaLightingOrderEvent.
 *   Precisa de:    C1 na main.
 *   Quem espera:   Fredson, para o LightSensor (F2).
 * ==================================================================================
 *
 * FORMATO OBRIGATÓRIO (slides, com o novo nome e as áreas do código base)
 *   LightSensorEvent [light=320.0, area=LivingRoom, getType()=SENSOR]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Estender o SensorEvent
 *     Lógica sugerida: classe concreta; o tipo e a área já vêm de cima.
 *     Porquê: é aqui que se prova que a hierarquia funciona: quase nada é repetido.
 *     Liga-se a: SensorEvent.
 *
 *   Passo 2 · Guardar o valor
 *     Lógica sugerida: um campo imutável com casas decimais, chamado light.
 *     Porquê: o nome tem de ser este, porque aparece no texto. Tem casas decimais porque o
 *       SensorControl entrega um double.
 *     Liga-se a: callback do LightSensor, de onde vem o valor.
 *
 *   Passo 3 · Construtor e consulta, conforme o contrato
 *     Lógica sugerida: primeiro a área, depois o valor, e um método para consultar o valor. É
 *       o contrato combinado no Ponto 0.
 *     Porquê: o Fredson escreve o LightSensor a contar com esta forma. Se mudar, avisar.
 *     Liga-se a: LightSensor (F2); todos os outros eventos seguem a mesma ordem.
 *
 *   Passo 4 · Representação em texto
 *     Lógica sugerida: conforme a opção escolhida no Event (Passo 4).
 *     Porquê: é o que o professor vai ler na consola.
 *     Liga-se a: IscotTests, que compara o texto exato.
 *
 *   Passo 5 · Confirmar e entregar
 *     Experimentar criar o evento dos slides e imprimi-lo (num main temporário, que não vai
 *     para o commit). Abrir o pull request C2 com o AreaLightingOrderEvent e avisar o Fredson.
 *
 * CONCEITOS DA UC / DE POO A USAR
 *   Herança e redefinição de métodos (@Override); imutabilidade (campos final).
 *
 * ARMADILHA
 *   Formatadores como String.format usam a língua do computador: num Windows em português sai
 *   320,0 em vez de 320.0. Confirmar neste passo, numa máquina em português.
 *
 * NAS FASES SEGUINTES
 *   Fase 6: é consumida por um decisor de iluminação, que acende abaixo de 500 lux.
 */
public class LightSensorEvent {
}

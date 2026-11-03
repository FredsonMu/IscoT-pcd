package iscot.common;

/**
 * AreaLightingOrderMessage — ordem para a iluminação de uma área. Nome e formato dados
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:       C2 · feature/mensagens-luz
 *   Responsável:  Celso   ·   Revisor: Fredson
 *   Dia 6:        SIM (fase 2). Merge HOJE, ter 3 nov.
 *   No dia 6:     A Light cria-a localmente (state=true, depois false) e acende/apaga o LightIcon.
 *   Depende de:   C1 (OrderMessage).
 *   Liga-se a:    Criada pela Light no dia 6 e pelo LightingDecider depois; consumida pela Light.
 *   Código base:  Nome e formato dados pelo professor na apresentação.
 *   (Lista completa de tarefas e passo a passo no Git: docs/09-tarefas-e-git.md)
 * ==================================================================================
 * pelo professor.
 *
 * EXEMPLO DA APRESENTAÇÃO (com as áreas do código base)
 *   AreaLightingOrderMessage [state=true, area=LivingRoom, getType()=DECIDER]
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - extends OrderMessage.
 *   - Campo final boolean state (true = acender). Construtor (area, state).
 *   - toString() exatamente no formato acima.
 *
 * O CENÁRIO DO PROFESSOR (sala de jantar)
 *   1 sensor de iluminação, 1 decisor, 2 lâmpadas (candeeiro e teto) = 4 clientes.
 *   O decisor acende "sempre que a iluminação está abaixo de 500 lux": envia UMA
 *   AreaLightingOrderMessage(true); o servidor entrega-a às DUAS lâmpadas (e ao
 *   próprio decisor, que também subscreve o tópico da área e só a confirma). Com o
 *   latch, o tópico só avança quando todos confirmarem.
 *
 * LIGAÇÕES
 *   Criada por: LightingDecider. Consumida por: Light, que faz LightIcon.setLigada(state)
 *   e escreve no histórico "Lâmpada ligada - HH:MM:SS".
 *
 */
public class AreaLightingOrderMessage {
}

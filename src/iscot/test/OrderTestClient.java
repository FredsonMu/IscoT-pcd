package iscot.test;

/**
 * OrderTestClient — cliente de teste da fase 5: regista-se como DECIDER e envia ordens de iluminação
 * pela rede, de 3 em 3 segundos.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C11 · feature/cliente-teste-ordens · CELSO, ÚLTIMA CLASSE DAS FASES 4 E 5
 *   Revisor:       Fredson
 *   Fase:          5 (troca de eventos). TEMPORÁRIA: na fase 6 é substituída pelos decisores.
 *   Precisa de:    BrokerConnection (C7, C9); ClientHandler a reencaminhar ordens (F11).
 *   Argumentos:    localhost 8080 LivingRoom, como os aparelhos.
 * ==================================================================================
 *
 * PORQUÊ ESTA CLASSE
 *   Os decisores só chegam na fase 6. Sem eles, ninguém envia ordens às lâmpadas pela rede, e não se
 *   prova que o canal funciona no sentido servidor → atuador. É a mesma ideia das ordens simuladas da
 *   fase 3, mas agora as ordens viajam pelo servidor.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Ligar e registar como DECIDER
 *     Lógica sugerida: igual aos aparelhos, com a BrokerConnection e o tipo DECIDER.
 *     Porquê: o servidor só aceita ordens de decisores (ClientHandler, Passo 6).
 *
 *   Passo 2 · Enviar ordens com pausas
 *     Lógica sugerida: no main, uma sequência ligar, desligar, ligar, com Thread.sleep entre cada uma,
 *       e um AreaLightingOrderEvent enviado de cada vez.
 *     Porquê: dá tempo para ver as lâmpadas mudar. O sleep está na main, por isso não há janela a
 *       congelar (este cliente nem tem janela).
 *
 * TESTE DA FASE 5 (Ponto 2)
 *   Servidor + 2 lâmpadas (LivingRoom) + este cliente: as duas lâmpadas acendem e apagam ao mesmo tempo,
 *   com o histórico. Uma lâmpada noutra área (ex.: Kitchen) não reage.
 *
 * PRONTA QUANDO
 *   As ordens enviadas chegam às lâmpadas da mesma área, e só a essas.
 */
public class OrderTestClient {
}

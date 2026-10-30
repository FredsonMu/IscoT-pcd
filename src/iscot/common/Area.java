package iscot.common;

/**
 * Area — as divisões da casa onde um dispositivo pode operar.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1.2: o cliente indica "a área da casa onde opera (valores possíveis:
 *   sala, quarto, cozinha, wc e jardim)". É uma lista fechada, por isso um enum.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - As 5 constantes: SALA, QUARTO, COZINHA, WC, JARDIM.
 *   - Um método estático "parse" que recebe o texto vindo dos argumentos do main
 *     (ex.: "sala", "Sala ", "SALA") e devolve a constante. Ideia: trim + toUpperCase
 *     + valueOf. Se o texto não existir, valueOf lança IllegalArgumentException,
 *     e é essa exceção que o servidor apanha para recusar a ligação.
 *
 * LIGAÇÕES
 *   É usada por: ClientHandler (valida o registo), Topics (monta o nome do tópico),
 *   Server (gera o id do dispositivo), ClientMain (lê args[2]).
 *
 * CONCORRÊNCIA
 *   Nenhuma preocupação: enums são imutáveis e únicos na JVM, por isso podem ser
 *   partilhados entre threads sem cadeado (estratégia "imutabilidade").
 *
 * ALTERNATIVAS E TRADE-OFFS
 *   - Usar String solta ("sala"): mais simples, mas um erro de escrita ("salla")
 *     só aparece em execução e não há lista única de valores válidos.
 *   - Enum (escolhido): o compilador conhece os valores, switch fica legível,
 *     e a validação vem de graça com valueOf.
 *
 * DÚVIDAS
 *   - O enunciado escreve "querto": assumimos que é "quarto" (gralha).
 *   - Devemos aceitar "WC" e "wc" indiferentemente? Sim, o parse normaliza.
 *
 * COMO TESTAR
 *   Um main de teste que faz parse de "sala", " JARDIM ", e "garagem" (deve falhar).
 *
 * Fase 2 · Responsável: Celso · Revisor: Fredson
 */
public enum Area {
}

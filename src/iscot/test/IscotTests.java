package iscot.test;

/**
 * IscotTests — verificação da hierarquia de eventos, sem janelas nem rede.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        F7 · feature/testes-eventos · FREDSON, 7.ª CLASSE A FAZER
 *   Revisor:       Celso
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         3 (seg 9 nov). Corrida em conjunto no Ponto 2.
 *   Precisa de:    ENTREGAS A e B do Celso (C1 a C4). Os passos 1 e 2 podem começar logo com os
 *                  eventos-modelo.
 * ==================================================================================
 *
 * PARA QUE SERVE
 *   Mostra numa só execução toda a hierarquia a funcionar, ao contrário das janelas. Regra do grupo:
 *   quem escreve o teste não escreveu a classe.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Criar a ferramenta de verificação
 *     Lógica sugerida: um método auxiliar que compara o esperado com o obtido, escreve OK ou FALHOU e
 *       conta falhas.
 *     Porquê: todos os passos seguintes o usam, e a saída fica boa para mostrar na apresentação.
 *
 *   Passo 2 · Verificar o formato do texto
 *     Lógica sugerida: criar cada um dos 9 eventos e comparar com o texto das notas da sua classe.
 *     Porquê: os dois formatos dos slides são os mais importantes. Se falhar, avisar o Celso.
 *     Liga-se a: todos os eventos; Event, Passo 4.
 *
 *   Passo 3 · Verificar o tipo
 *     Lógica sugerida: 4 leituras dizem SENSOR, 4 ordens dizem DECIDER; o RegistrationEvent diz o tipo
 *       que recebeu.
 *     Liga-se a: SensorEvent, HighLevelOrderEvent, RegistrationEvent.
 *
 *   Passo 4 · Verificar a hierarquia e a área
 *     Lógica sugerida: guardar os eventos numa coleção de Event, percorrê-la e testar com instanceof.
 *     Porquê: percorrer sem saber a classe concreta é polimorfismo; é uma boa resposta na apresentação.
 *     Liga-se a: Event, SensorEvent, HighLevelOrderEvent.
 *
 *   Passo 5 · Verificar a serialização
 *     Lógica sugerida: escrever cada evento num stream de objetos em memória, voltar a lê-lo e comparar
 *       o texto.
 *     Porquê: prepara a fase 5 sem precisar de rede.
 *     Liga-se a: Event, Passo 1.
 *
 *   Passo 6 · Resumo
 *     Escrever quantas verificações passaram e falharam. "09 Testes" fica todo OK.
 *
 * CONCEITOS DA UC A USAR
 *   Polimorfismo; instanceof; streams de objetos.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Como organizar os testes:
 *     A) Um main que escreve OK ou FALHOU. Corre sem configurar nada. [sugestão]
 *     B) JUnit, que já vem no Eclipse. Melhores relatórios, mas exige alterar o Build Path.
 *   Como comparar os textos:
 *     A) O método auxiliar do Passo 1. [sugestão]
 *     B) A palavra-chave assert. Atenção: vem desligada por defeito e exige a opção -ea.
 *
 * NA APRESENTAÇÃO
 *   É a prova da fase 2: lançar "09 Testes" e mostrar a consola.
 */
public class IscotTests {
}

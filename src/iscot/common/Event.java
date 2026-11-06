package iscot.common;

/**
 * Event — classe de topo, abstrata, de todos os eventos do sistema.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C1 · feature/eventos-base · CELSO, 1.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (criação da hierarquia de eventos)
 *   Etapa:         1 (sáb 7 nov). Começa logo depois do Ponto 0.
 *   Precisa de:    enums Areas e Type (código base). Não espera por ninguém.
 *   A seguir:      SensorEvent e HighLevelOrderEvent, na mesma tarefa.
 *   Roteiro geral: docs/09-tarefas-e-git.md
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   Fase 2: "Sugere-se a criação de uma classe de topo Event, abstrata, que seja
 *   superclasse de todas as outras". Cada evento identifica a área e o tipo.
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Declarar a classe como base de tudo
 *     Lógica sugerida: abstrata, e marcada como serializável aqui mesmo.
 *     Porquê: abstrata porque não existe um evento "genérico". A marca colocada no topo é
 *       herdada por todas as filhas, evitando repeti-la 9 vezes.
 *     Liga-se a: fase 5, quando os eventos viajarem por canais de objetos, e ao teste de
 *       serialização do IscotTests.
 *
 *   Passo 2 · Guardar a área
 *     Lógica sugerida: um campo imutável do tipo Areas, recebido no construtor, com um
 *       método de consulta.
 *     Porquê: todos os eventos têm área, portanto fica no único sítio comum. Imutável porque
 *       um evento é um facto que já aconteceu.
 *     Liga-se a: construtores de SensorEvent e HighLevelOrderEvent, que a passam para cima;
 *       3.º argumento de todos os programas do Fredson.
 *
 *   Passo 3 · Obrigar cada evento a dizer o seu tipo
 *     Lógica sugerida: declarar getType() aqui, sem o implementar (ver as opções abaixo).
 *     Porquê: o formato dos slides mostra getType() em todos os eventos. Aqui fica a promessa
 *       e cada ramo da hierarquia cumpre-a.
 *     Liga-se a: SensorEvent, HighLevelOrderEvent e RegistrationEvent.
 *
 *   Passo 4 · Montar a representação em texto
 *     Lógica sugerida: escolher uma opção abaixo; a parte final (area=..., getType()=...)
 *       é igual em todos os eventos, por isso compensa ficar num só sítio.
 *     Porquê: é o que aparece na consola dos sensores e o que o IscotTests compara.
 *     Liga-se a: todos os eventos concretos.
 *     Formato: NomeDaClasse [campos próprios, area=..., getType()=...]
 *
 *   Passo 5 · Confirmar
 *     Compila; tentar criar diretamente um Event dá erro de compilação (depois apagar a
 *     tentativa). Só depois passar ao SensorEvent.
 *
 * CONCEITOS DA UC / DE POO A USAR
 *   - Classe abstrata e herança: juntam num só sítio o que é comum.
 *   - Polimorfismo: quem recebe um Event não precisa de saber a classe concreta.
 *   - Serialização, e imutabilidade (uma das 4 estratégias de segurança entre threads).
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Tipo do evento:
 *     A) Método abstrato, resolvido uma vez em cada ramo. Sem dados repetidos e o compilador
 *        obriga a implementar. [sugestão]
 *     B) Valor passado ao construtor e guardado num campo. Menos métodos, mas é possível
 *        criar um evento com o tipo errado.
 *   Representação em texto:
 *     A) Cada classe concreta escreve a sua. Simples, mas repete o final do formato.
 *     B) A classe de topo monta o formato e pede às filhas só a parte dos campos (Template
 *        Method). Mais elegante, um pouco mais difícil de explicar.
 *     C) Obter o nome pela própria classe (getClass) em vez de o escrever à mão. Evita
 *        gralhas e combina bem com B.
 *   Área nula:
 *     A) Recusar logo no construtor (falha cedo, com erro claro).
 *     B) Não validar agora. Código mais curto.
 *
 * O QUE NÃO COLOCAR
 *   Id do evento e id do remetente só entram com o servidor. Nada de campos "para o futuro".
 *
 * PARA PESQUISAR
 *   abstract class vs interface · Serializable e serialVersionUID · Template Method
 *
 * PERGUNTA PROVÁVEL
 *   "Porquê classe abstrata e não interface?" Tem estado (a área) que as filhas herdam.
 */
public abstract class Event {
}

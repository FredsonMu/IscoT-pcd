package iscot.common;

import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * RegistrationEvent — o pedido de registo de um cliente no servidor.
 *
 * ==================================================================================
 * PLANO
 *   Tarefa:        C4 · feature/evento-registo · CELSO, 12.ª CLASSE A FAZER
 *   Revisor:       Fredson
 *   Fase:          2 (só a classe). O registo a funcionar é da fase 4.
 *   Etapa:         2 (dom 8 nov). Com o C3, liberta o IscotTests completo (F7).
 *   Precisa de:    C1.
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   Fase 2: "a criação de RegistrationEvent para o registo inicial dos clientes".
 *   Secção 2.1: "A área da casa, bem como o tipo de cliente devem ser enviados ao servidor
 *   aquando da ligação inicial."
 *   Os slides chamam-lhe RegistrationMessage, mas são anteriores: desde a versão 2, o enunciado
 *   usa o termo Evento. Seguimos o enunciado.
 *
 * FORMATO
 *   RegistrationEvent [area=LivingRoom, getType()=ACTUATOR]
 *
 * PASSOS (por esta ordem)
 *
 *   Passo 1 · Estender diretamente o Event
 *     Lógica sugerida: não passar por SensorEvent nem por HighLevelOrderEvent.
 *     Porquê: um registo não é uma leitura nem uma ordem. É a diferença a saber explicar.
 *     Liga-se a: Event.
 *
 *   Passo 2 · Guardar o tipo do cliente
 *     Lógica sugerida: um campo imutável do tipo Type, recebido no construtor com a área.
 *     Porquê: ao contrário dos outros eventos, aqui o tipo varia de objeto para objeto.
 *     Liga-se a: Event, Passo 3.
 *
 *   Passo 3 · Responder ao tipo
 *     Lógica sugerida: getType() devolve o campo do Passo 2.
 *     Porquê: é o mesmo método com outra implementação: um bom exemplo de polimorfismo.
 *     Liga-se a: IscotTests, que confirma o tipo recebido.
 *
 *   Passo 4 · Confirmar
 *     O texto corresponde ao formato acima, com tipos diferentes.
 *
 *   Passo 5 · Só pensar na resposta do servidor (NÃO implementar)
 *     Ver as opções abaixo. É da fase 4; fica a ideia para a apresentação.
 *
 * CONCEITOS DA UC / DE POO A USAR
 *   Polimorfismo; enums, que impedem à partida valores que não existem.
 *
 * OPÇÕES PARA COMEÇAR (escolher e saber dizer porquê)
 *   Resposta do servidor (id atribuído):
 *     A) Na mesma classe. Menos classes, mas alguns campos ficam vazios no pedido.
 *     B) Numa classe separada. Cada classe só tem o que usa, mas é mais uma classe.
 *   Tipo DWC ("pode ser ignorado", diz o professor):
 *     A) Recusar no servidor, na fase 4. É onde o enunciado coloca a recusa. [sugestão]
 *     B) Recusar logo aqui. Falha mais cedo, mas o servidor fica sem nada para recusar.
 */
public class RegistrationEvent extends Event {

    private final Type type;

    /** Cria um pedido de registo para uma área e tipo de cliente. */
    public RegistrationEvent(Areas area, Type type) {
        super(area);
        if (type == null) {
            throw new IllegalArgumentException("O tipo não pode ser null.");
        }
        this.type = type;
    }

    /** Devolve o tipo do cliente que se pretende registar. */
    @Override
    public Type getType() {
        return type;
    }
}

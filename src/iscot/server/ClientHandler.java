package iscot.server;

/**
 * ClientHandler — a thread do servidor dedicada a um cliente: regista-o, lê os seus eventos e envia-lhe
 * o que for preciso.
 *
 * ==================================================================================
 * PLANO
 *   Tarefas:       F10 (registo, fase 4) · F11 (ciclo de eventos, fase 5) · F12 (envio seguro e saída)
 *                  branches feature/client-handler-registo e feature/client-handler-eventos
 *                  FREDSON, 3.ª CLASSE DAS FASES 4 E 5
 *   Revisor:       Celso
 *   Fase:          4 (Passos 1 a 4) e 5 (Passos 5 a 8)
 *   Precisa de:    F8 (RegistrationReplyEvent), F9 (Server: ids e lista de clientes).
 *   Quem espera:   Celso, para o Ponto 1 (registo) e o Ponto 2 (troca de eventos).
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   1.1: "Cada cliente deve ter uma thread dedicada no servidor, que gere as interações com ele,
 *   incluindo (mas não limitado a) envio e receção de eventos."
 *   2.1: "Se o tipo não existir a ligação é recusada."
 *   Fase 5: "envio de eventos, através de canais de objetos".
 *
 * ---------------------------------------------------------------------------------- FASE 4
 *
 *   Passo 1 · Ser uma thread
 *     Lógica sugerida: estender Thread (ou implementar Runnable); recebe no construtor o socket e o Server.
 *     Porquê: cada cliente precisa de alguém só para ele, à espera do que ele envia.
 *     Pesquisar: extends Thread vs implements Runnable (aula 02).
 *
 *   Passo 2 · Criar os streams pela ordem certa
 *     Lógica sugerida: PRIMEIRO o ObjectOutputStream, com flush(); SÓ DEPOIS o ObjectInputStream.
 *     Porquê: o construtor do ObjectInputStream fica à espera do "cabeçalho" que o outro lado envia ao
 *       criar o seu ObjectOutputStream. Se os dois lados criassem primeiro o input, ficavam os dois à
 *       espera um do outro, para sempre: um DEADLOCK entre dois processos.
 *     Liga-se a: BrokerConnection (Celso), que faz o mesmo do lado do cliente (contrato do Ponto 0).
 *
 *   Passo 3 · Ler e validar o registo
 *     Lógica sugerida: a primeira coisa a ler tem de ser um RegistrationEvent. Validar o tipo:
 *       SENSOR, DECIDER ou ACTUATOR são aceites; DWC (ou outra coisa) é recusado.
 *     Porquê: é a regra "se o tipo não existir a ligação é recusada". O professor diz que o DWC
 *       "pode ser ignorado": tratamo-lo como tipo que não existe.
 *     Liga-se a: RegistrationEvent (Celso, C4); Server (ids).
 *
 *   Passo 4 · Responder
 *     Lógica sugerida: se for válido, pedir o id ao Server, entrar na lista de clientes e responder
 *       "aceite" com o id. Se não, responder "recusado" com o motivo e fechar o socket.
 *     Porquê: o cliente fica a saber o seu id; um cliente recusado não fica a ocupar uma thread.
 *     Log sugerido: "[LivingRoom-ACTUATOR-3] registado" / "registo recusado: tipo DWC".
 *
 * ---------------------------------------------------------------------------------- FASE 5
 *
 *   Passo 5 · O ciclo de leitura
 *     Lógica sugerida: enquanto a ligação estiver aberta, ler um evento e tratá-lo.
 *     Porquê: o readObject() BLOQUEIA até chegar algo. Como cada cliente tem a sua thread, esperar por
 *       um cliente não impede os outros de serem atendidos.
 *
 *   Passo 6 · Validar quem envia o quê
 *     Lógica sugerida: o servidor recebe um Event e pergunta-lhe o que é (instanceof). Um SENSOR só
 *       pode enviar SensorEvent; um DECIDER só HighLevelOrderEvent; um ACTUATOR não publica nada.
 *       A área do evento tem de ser a área do cliente. O que não cumprir: escrever um aviso e ignorar.
 *     Porquê: enunciado, secção 1: "os clientes sensores apenas publicam eventos do tipo Sensor, os
 *       decisores [...] publicam do tipo Highlevel". O servidor é quem garante as regras.
 *     Conceito: polimorfismo; o servidor não sabe a classe concreta do que recebe.
 *
 *   Passo 7 · Reencaminhar (TEMPORÁRIO, só na fase 5)
 *     Lógica sugerida: uma ordem recebida é enviada a todos os atuadores da mesma área (pela lista do
 *       Server). Uma leitura só é escrita na consola, porque ainda não há decisores.
 *     Porquê: prova que o canal funciona nos dois sentidos (servidor → atuador).
 *     Na fase 6: isto é substituído pelos tópicos e pelo TopicDispatcher.
 *
 *   Passo 8 · Enviar com segurança e sair de forma limpa (F12)
 *     Lógica sugerida: um método send synchronized, usado sempre que alguém envia a este cliente; e um
 *       finally que, quando a ligação acaba, tira o cliente da lista e fecha o socket.
 *     Porquê: com o reencaminhamento, a thread de OUTRO cliente (o decisor) pode estar a enviar para
 *       este no mesmo instante em que este responde a algo: os bytes misturavam-se no socket
 *       (StreamCorruptedException). E quando o cliente fecha a janela, o readObject() lança uma exceção
 *       (EOFException, SocketException): o servidor não pode rebentar.
 *     Conceitos: race condition no stream; synchronized; try/finally.
 *     Pesquisar: ObjectOutputStream.reset() (a cache de objetos já enviados).
 *
 * CONCEITOS DA CADEIRA
 *   Threads (aula 02); streams de objetos e serialização; race condition e synchronized (aula 03);
 *   deadlock (ordem dos streams); polimorfismo e instanceof.
 *
 * OPÇÕES
 *   Quem envia ao cliente:
 *     A) Qualquer thread chama o send synchronized deste handler. [sugestão] Simples.
 *     B) Este handler tem uma fila de saída e só a sua thread envia. Cumpre à letra "a thread
 *        dedicada gere o envio", mas precisa de mais uma thread e de uma fila feita por nós.
 *        Perguntar ao professor.
 *
 * PRONTA QUANDO
 *   Fase 4: aceita e recusa registos corretamente, com o log certo. Fase 5: recebe os eventos de cada
 *   cliente, reencaminha as ordens e sobrevive a um cliente que fecha a janela.
 */
public class ClientHandler {
}

package iscot.client;

/**
 * BrokerConnection — a ligação de um aparelho ao servidor: ligar, registar, enviar e receber.
 *
 * ==================================================================================
 * PLANO
 *   Tarefas:       C7 (ligar e registar, fase 4) · C8 (os 8 aparelhos registam-se) ·
 *                  C9 (enviar e receber, fase 5) · C10 (sensores enviam, atuadores recebem)
 *                  branches feature/broker-registo e feature/broker-eventos
 *                  CELSO, 1.ª CLASSE DAS FASES 4 E 5
 *   Revisor:       Fredson
 *   Fase:          4 (Passos 1 a 4) e 5 (Passos 5 a 8)
 *   Precisa de:    F8 (RegistrationReplyEvent, do Fredson); o contrato do Ponto 0.
 *                  Pode começar antes de o servidor estar pronto, a contar com o contrato.
 *   Quem espera:   os 8 aparelhos.
 * ==================================================================================
 *
 * O QUE O ENUNCIADO PEDE
 *   1.2: "No arranque do cliente, este liga-se automaticamente ao servidor, indicando o seu tipo e a
 *   área da casa onde opera."
 *   Slides: "Cada cliente apenas conhece o servidor a que se liga."
 *   Código do professor: os TODO "connect to the server" e "register with the server".
 *
 * PORQUÊ UMA CLASSE PRÓPRIA
 *   O código de ligação é igual nos 8 aparelhos. Numa só classe, escreve-se uma vez (como o
 *   ActionHistory). Os aparelhos só dizem "liga-me a este servidor, sou um ACTUATOR da LivingRoom".
 *
 * ---------------------------------------------------------------------------------- FASE 4
 *
 *   Passo 1 · Ligar
 *     Lógica sugerida: recebe o endereço e o porto (args[0] e args[1] dos aparelhos) e abre o Socket.
 *     Porquê: é a primeira vez que estes argumentos servem para alguma coisa.
 *     Armadilha: com o servidor desligado dá ConnectException: mensagem clara, sem stack trace.
 *
 *   Passo 2 · Criar os streams pela ordem certa
 *     Lógica sugerida: PRIMEIRO o ObjectOutputStream, com flush(); SÓ DEPOIS o ObjectInputStream.
 *     Porquê: se os dois lados criassem primeiro o input, ficavam os dois à espera do cabeçalho um do
 *       outro: DEADLOCK. É o contrato do Ponto 0; o ClientHandler (Fredson) faz o mesmo.
 *
 *   Passo 3 · Registar
 *     Lógica sugerida: enviar o RegistrationEvent (tipo do aparelho + área) e ler a resposta
 *       (RegistrationReplyEvent). Se aceite, guardar o id. Se recusado, mostrar o motivo e terminar.
 *     Porquê: sem registo o servidor não sabe quem é o aparelho.
 *     Liga-se a: RegistrationEvent (C4, Celso); RegistrationReplyEvent (F8, Fredson).
 *
 *   Passo 4 · Os 8 aparelhos registam-se (C8)
 *     Lógica sugerida: em cada main, logo a seguir a ler a área, criar a BrokerConnection e registar.
 *       Nos ficheiros do professor (HumiditySensor, Ventilator), é exatamente onde estão os TODO.
 *     Tipos: sensores → SENSOR; Light, Ventilator, AirConditioner, Irrigation → ACTUATOR;
 *       cliente de teste (C11) → DECIDER.
 *     Porquê: o tipo é fixo em cada programa (um por aparelho, como no código base).
 *
 * ---------------------------------------------------------------------------------- FASE 5
 *
 *   Passo 5 · Enviar
 *     Lógica sugerida: um método send synchronized.
 *     Porquê: num sensor, a thread do Swing envia quando o slider é largado; noutras situações, outra
 *       thread pode enviar ao mesmo tempo. Dois envios simultâneos misturavam os bytes no socket.
 *     Pesquisar: ObjectOutputStream.reset() (a cache de objetos já enviados).
 *
 *   Passo 6 · Receber: a thread recetora
 *     Lógica sugerida: nos decisores e atuadores, arrancar uma thread que fica num ciclo a ler o que o
 *       servidor envia, e que entrega cada evento ao aparelho através de um callback ("quando chegar um
 *       evento, chama isto"), como o SensorControl do professor faz com o valor do slider.
 *     Porquê: o readObject() BLOQUEIA. A main não pode ficar presa nele, e a thread do Swing NUNCA pode
 *       bloquear (a janela congelava). Por isso é precisa uma thread própria.
 *     Pesquisar: interfaces funcionais (Consumer), lambdas, threads daemon.
 *
 *   Passo 7 · Sensores enviam; atuadores recebem (C10)
 *     Lógica sugerida: nos 4 sensores, o System.out.println(evento) do callback passa a send(evento).
 *       Nos 4 atuadores, as ordens simuladas do main SAEM, e o callback da thread recetora chama o
 *       MESMO aplicarOrdem que já existe (no AirConditioner, o aplicarOrdem certo para cada classe de
 *       ordem, com instanceof).
 *     Porquê: foi para isto que separámos o aplicarOrdem na fase 3. O ActionHistory e os ícones já usam
 *       invokeLater, por isso são seguros quando chamados pela thread recetora.
 *
 *   Passo 8 · Quando o servidor desaparece
 *     Lógica sugerida: se a ligação cair, a thread recetora termina e escreve uma mensagem clara.
 *     Porquê: o readObject() lança uma exceção; o aparelho não deve rebentar com um stack trace.
 *
 * CONCEITOS DA CADEIRA
 *   Sockets; streams de objetos e serialização; deadlock (ordem dos streams); thread recetora;
 *   race condition no stream e synchronized; callbacks; confinamento ao Swing (invokeLater).
 *
 * OPÇÕES
 *   Como entregar os eventos recebidos ao aparelho:
 *     A) Um callback passado à BrokerConnection. [sugestão] Igual à ideia do SensorControl.
 *     B) Cada aparelho cria a sua própria thread e chama um "receber()" bloqueante. Mais código
 *        repetido nos aparelhos.
 *
 * PRONTA QUANDO
 *   Fase 4: os 8 aparelhos registam-se com ids diferentes; sem servidor dá uma mensagem clara.
 *   Fase 5: o sensor envia (aparece na consola do servidor) e as lâmpadas aplicam as ordens do cliente
 *   de teste, sem ordens simuladas.
 */
public class BrokerConnection {
}

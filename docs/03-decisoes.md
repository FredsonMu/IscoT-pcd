# 03 · Decisões de desenho e trade-offs

Cada decisão segue o mesmo formato: **o problema**, **as opções**, **o que ganhamos e perdemos com cada uma**, **o que escolhemos e porquê**. Na oral, o professor quer ouvir sobretudo o "porquê" e o "o que perdemos".

> Trade-off = compromisso. Quase nunca há uma opção melhor em tudo: ganha-se numa coisa e paga-se noutra. Saber dizer o que se paga é o que mostra que se percebeu.

---

### D1 · Como criar threads: `extends Thread` ou `implements Runnable`
| | `extends Thread` | `implements Runnable` |
|---|---|---|
| Ganha | código mais curto; acesso direto a `isInterrupted()`, `getName()` | a classe pode herdar de outra; separa "a tarefa" de "quem a corre" |
| Perde | gasta a única herança da classe | um passo extra (`new Thread(r).start()`) |

**Escolha:** `extends Thread` em `ClientHandler` e `TopicDispatcher` (não precisam de herdar de mais nada); `Runnable` anónimo para threads pequenas nos clientes. Ambas são da aula 02.

### D2 · Exclusão mútua: `synchronized` ou `ReentrantLock`
| | `synchronized` | `ReentrantLock` |
|---|---|---|
| Ganha | impossível esquecer de destrancar (sai sozinho, até com exceção); funciona com `wait/notifyAll` | `tryLock` com prazo; várias condições por cadeado |
| Perde | um só "local de espera" por objeto; não dá para desistir de esperar pelo cadeado | é biblioteca de concorrência (proibida?); `unlock` em `finally` obrigatório |

**Escolha:** `synchronized`. O enunciado proíbe os mecanismos da biblioteca, a API do latch imposta já usa `synchronized`, e os 4 mandamentos da aula 04 assumem o cadeado intrínseco.

### D3 · Contador de ids: método `synchronized` ou `AtomicInteger`
| | `synchronized` | `AtomicInteger` |
|---|---|---|
| Ganha | só usa matéria básica, sem dúvidas sobre a proibição | sem cadeado, mais rápido |
| Perde | um cadeado por registo (irrelevante: registos são raros) | pode contar como "equivalente da biblioteca" |

**Escolha:** `synchronized` até o professor responder.

### D4 · Acordar threads: `notify` ou `notifyAll`
| | `notify` | `notifyAll` |
|---|---|---|
| Ganha | acorda só uma (menos trabalho) | nunca acorda "a errada" e deixa a certa a dormir |
| Perde | se houver threads à espera de condições diferentes no mesmo objeto, pode acordar uma que volta a dormir e ninguém avança | acorda threads que voltam logo a dormir (custo pequeno) |

**Escolha:** `notifyAll` (mandamento 4).

### D5 · Quem envia os eventos aos subscritores (a decisão mais importante)
| Opção | Ganha | Perde |
|---|---|---|
| **A** · o próprio `ClientHandler` do subscritor espera no tópico, envia e lê o ack | cumpre à letra "a thread dedicada gere envio e receção"; menos threads | a thread fica presa à espera do ack e não lê mais nada; o decisor (publica e subscreve) complica-se; a decisão "todos consumiram" fica espalhada |
| **B** · dispatcher por tópico + thread escritora por cliente | um cliente lento não atrasa a entrega aos outros; mais robusto | mais uma fila por cliente (feita por nós), o dobro das threads, mais código para explicar |
| **C** · dispatcher por tópico + `send()` sincronizado no handler | o "esperar por todos" está num só sítio, fácil de explicar e testar; poucas classes | se um cliente tiver a rede muito lenta, o `send` atrasa o dispatcher desse tópico |

**Escolha:** C. É a mais simples que cumpre o enunciado. B é a evolução natural se sobrar tempo. **Perguntar ao professor** se a C respeita "thread dedicada por cliente".

### D6 · Fila do tópico: uma fila partilhada ou uma por subscritor
| | uma por tópico | uma por subscritor (como MQTT/RabbitMQ) |
|---|---|---|
| Ganha | é o que o enunciado descreve ("só avança quando todos confirmarem") | cada subscritor anda ao seu ritmo; sem head-of-line blocking |
| Perde | um subscritor lento prende todos até ao timeout | não cumpre o enunciado |

**Escolha:** uma por tópico. Saber explicar o head-of-line blocking na oral.

### D7 · Estrutura da fila: `LinkedList` ou `ArrayDeque`
Ambas fazem inserir no fim e tirar do início em tempo constante. `ArrayDeque` é ligeiramente mais rápida e gasta menos memória; `LinkedList` é a que o professor usa nos slides. **Escolha:** `LinkedList` (familiar ao avaliador). Diferença irrelevante para o nosso volume.

### D8 · Formato das mensagens: objetos ou texto
| | objetos (`ObjectStream`) | texto (linhas, JSON) |
|---|---|---|
| Ganha | sem parsing; tipos verificados pelo compilador; é o que o enunciado pede | legível, depurável com qualquer ferramenta, independente da linguagem |
| Perde | só funciona entre programas Java com as mesmas classes; armadilhas (ordem dos streams, cache) | escrever e manter o parsing; erros só em execução |

**Escolha:** objetos (fase 4: "através de canais de objetos").

### D9 · Hierarquia de mensagens ou uma classe com "tipo"
| | hierarquia (`Message` + filhas) | uma classe com `enum Kind` |
|---|---|---|
| Ganha | cada mensagem só tem os seus campos; `instanceof` claro | menos ficheiros |
| Perde | mais classes | campos vazios; `switch` em todo o lado |

**Escolha:** hierarquia (o enunciado já impõe uma classe própria, `ReceptionConfirmationMessage`).

### D10 · Como enviar o tipo no registo: `String` ou enum
Se o cliente enviasse o enum, um tipo inválido nunca sairia do cliente e o servidor não teria nada para recusar. **Escolha:** `String`, validado no servidor (secção 2.1).

### D11 · Nomes dos tópicos
| Opção | Ganha | Perde |
|---|---|---|
| `area/grandeza/canal` (escolhida) | uma automação por divisão; inspirado em MQTT; fácil de ler | um decisor "da casa toda" precisaria de wildcards |
| `grandeza/canal` | menos tópicos | as luzes da casa toda acendiam ao mesmo tempo |
| wildcards (`+/luz/ordens`) | flexível como o MQTT | correspondência de padrões no `TopicManager`; mais difícil de raciocinar com o latch |

### D12 · Tópicos criados à primeira utilização ou todos no arranque
À primeira utilização evita 40 dispatchers a dormir, mas obriga a proteger o check-then-act no `getOrCreate`. **Escolha:** à primeira utilização, com `synchronized`.

### D13 · Timeout do latch
Sem timeout, um subscritor caído prendia o tópico para sempre. Timeout curto (1 s) pode avançar antes de clientes lentos responderem; longo (30 s) torna a demo lenta quando alguém cai. **Escolha inicial:** 5 s, numa constante, a afinar nos testes.

### D14 · Um projeto Eclipse ou três
| | um projeto com pacotes | três projetos (common/server/client) |
|---|---|---|
| Ganha | zero configuração; o professor corre tudo do mesmo sítio | separação real, como em produção |
| Perde | menos "realista" | Build Path entre projetos; fácil dar `ClassNotFoundException` |

**Escolha:** um projeto.

### D15 · Versão do Java
O projeto da semana 1 do professor está em Java 8. Usar 17/21 daria `record`, `switch` com setas e `instanceof` com padrão, mas arrisca o projeto não compilar na máquina do avaliador. **Escolha:** Java 8 até o professor dizer outra coisa.

### D16 · Testes: `main` de teste ou JUnit
`main` corre sem configurar nada; JUnit dá relatórios e asserções e já vem no Eclipse. **Escolha:** `main` por agora; passar a JUnit se o número de testes crescer. Decidir em conjunto.

### D17 · GUI e lógica separadas
As janelas (`gui`) não sabem nada de rede; os clientes (`client`) não criam componentes. Ganha: a fase 1 pode ser feita em paralelo com o servidor; trocar pelos componentes do professor ("[TBD]") é fácil. Perde: mais classes e uma "cola" entre as duas.

### D18 · Atualizar a GUI a partir de outras threads
Alterar componentes diretamente da thread recetora parece funcionar, mas é uma race condition. `SwingUtilities.invokeLater` entrega a alteração à EDT. **Escolha:** `invokeLater` em todas as atualizações que não venham de um listener.

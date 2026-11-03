# 01 · Fundamentos: threads, sincronização, coordenação e rede

Este documento explica, do zero, todos os termos que vamos usar no projeto. Cada conceito tem: o que é, um exemplo do dia a dia, como se escreve em Java (só a ideia, não o código completo) e onde aparece no IscoT.

Leitura recomendada por esta ordem. Os slides de referência estão indicados entre parênteses.

---

## Parte A · Processos e threads (aula 02)

### Programa, processo e thread
- **Programa**: o ficheiro com o código (o `.class`, o `.jar`).
- **Processo**: um programa em execução. Tem a sua própria memória; dois processos não partilham variáveis. No nosso projeto, o servidor e cada cliente são **processos diferentes** (cada "Run" no Eclipse é um processo).
- **Thread** (em português, *processo ligeiro*; de *thread of control*, linha de controlo): uma sequência de instruções a correr **dentro** de um processo. Um processo pode ter várias threads, e todas **partilham a mesma memória** (os mesmos objetos).

> Analogia: o processo é uma cozinha; as threads são os cozinheiros. Partilham o frigorífico e as panelas (a memória). Isto é ótimo para trabalhar em equipa e perigoso se dois pegarem na mesma panela ao mesmo tempo.

| | Processo | Thread |
|---|---|---|
| Memória | própria, isolada | partilhada com as outras threads do processo |
| Comunicar | por mensagens (sockets, ficheiros) | lendo e escrevendo os mesmos objetos |
| Risco principal | a comunicação é lenta e pode falhar | interferência (race conditions) |
| No IscoT | servidor, cada sensor, decisor, atuador | ClientHandler, TopicDispatcher, thread recetora, thread do Swing |

O IscoT junta **os dois mundos**: entre processos é *programação distribuída* (mensagens pela rede); dentro do servidor é *programação concorrente* (threads a partilhar tópicos).

### Concorrência e paralelismo
- **Concorrência**: várias tarefas *em curso* no mesmo período; podem alternar num só núcleo.
- **Paralelismo**: várias tarefas a correr *literalmente ao mesmo tempo*, em núcleos diferentes.
- A aula 02 mostra três cenários: **paralelo**, **intercalado** (um núcleo, as threads revezam-se) e **misto**. O programador **não controla** qual acontece. O código tem de estar correto em todos.

### Porquê usar threads
1. Fazer várias coisas ao mesmo tempo (atender 10 clientes).
2. **Não bloquear** enquanto se espera por algo lento: input do utilizador, rede, disco. No IscoT, `accept()` e `readObject()` bloqueiam; se estivessem na mesma thread, o servidor parava.
3. Aproveitar vários núcleos.

### Criar e lançar threads em Java
- Duas formas (aula 02):
  - `class X extends Thread` e redefinir `run()`;
  - `class X implements Runnable` com `run()`, e depois `new Thread(x).start()`.
- Usa-se `Runnable` quando a classe já herda de outra (o Java não tem herança múltipla).
- **`start()` vs `run()`**: `start()` cria a thread nova, que depois executa o `run()`. Chamar `run()` diretamente executa o método na thread atual, como um método qualquer, **sem concorrência nenhuma**. É dos erros mais perguntados.
- A thread `main` é criada automaticamente. O programa termina quando todas as threads (não-daemon) terminarem.

### Ciclo de vida de uma thread
`NEW` (criada) → `start()` → `RUNNABLE` (a correr ou pronta) → pode passar por `BLOCKED` (à espera de um cadeado), `WAITING` / `TIMED_WAITING` (em `wait`, `join`, `sleep`) → `TERMINATED` (o `run` acabou).

### sleep, interrupt, join
| Método | O que faz | Nota |
|---|---|---|
| `Thread.sleep(ms)` | pausa a thread atual | **não** liberta cadeados; lança `InterruptedException` |
| `t.interrupt()` | pede à thread `t` que pare o que está a fazer | é só um pedido; se `t` estiver em `sleep/wait/join`, recebe `InterruptedException`; o programador decide como reagir |
| `t.join()` | a thread atual espera que `t` termine | usado nos testes (esperar as threads acabarem antes de contar resultados) |

No IscoT: o sensor automático usa `sleep` entre leituras e `interrupt` para desligar; o `TopicDispatcher` termina quando é interrompido; o teste de carga usa `join`.

### Ação atómica
Uma ação que não pode ser interrompida a meio. **Uma thread só pode ser interrompida entre ações atómicas.** Atenção ao que *parece* simples mas não é atómico:
- `x++` = ler `x`, somar 1, escrever `x` (3 passos);
- chamar um método;
- `lista.add(e)` (muitos passos internos).

---

## Parte B · Interferência e sincronização (aula 03)

### Race condition (condição de corrida)
Quando o resultado depende da ordem imprevisível em que as threads executam. Exemplo da aula, a pilha:

| Thread A | Thread B | pos | vetor |
|---|---|---|---|
| | | -1 | {0,0} |
| `pos++` | | 0 | |
| | `pos++` | 1 | |
| | `vetor[pos]=7` | 1 | {0,7} |
| `vetor[pos]=5` | | 1 | {0,5} ← o 7 perdeu-se e a posição 0 ficou vazia |

No IscoT, sem cuidado: dois clientes registam-se ao mesmo tempo e recebem **o mesmo id**; dois sensores publicam ao mesmo tempo e **um evento perde-se**.

### Secção crítica
Sequência de ações que, se interrompida ou intercalada, pode criar **inconsistência**. Ex.: `pos++; vetor[pos]=i;`. No IscoT: o corpo de `publish`, de `newDeviceId`, de `getOrCreate`.

### Check-then-act
Padrão perigoso: verificar uma condição e agir com base nela, sem garantia de que a condição ainda é verdade quando se age. Ex.: "se o tópico não existe, crio-o" (`TopicManager`), "se `pos >= 0`, faço `pos--`" (pilha). Entre o *check* e o *act* outra thread pode mudar tudo.

### Exclusão mútua, mutex, cadeado, monitor
- **Mutex** (*mutual exclusion*) ou **cadeado**: só deixa uma thread de cada vez entrar numa secção de código. Dois estados (trancado/destrancado), duas operações (trancar/destrancar).
- Se uma thread tenta trancar um cadeado já trancado, **espera**. Quando é destrancado, entra **uma** das que esperavam, escolhida arbitrariamente.
- Em Java, **todos os objetos têm um cadeado intrínseco** (também chamado *monitor*).

### synchronized
- **Método sincronizado**: `public synchronized void push(...)` usa o cadeado do próprio objeto (`this`).
- **Bloco sincronizado**: `synchronized (obj) { ... }` usa o cadeado de `obj`.
- A sincronização é **por instância**, não por método nem por classe: dois métodos `synchronized` do mesmo objeto excluem-se um ao outro; o mesmo método em dois objetos diferentes não.
- `static synchronized` usa o cadeado da classe.
- **É responsabilidade do programador** identificar as secções críticas e protegê-las.

### Cadeados explícitos (Lock / ReentrantLock)
`lock()` / `unlock()` (sempre num `finally`) / `tryLock()`. Mais flexíveis (tentar com prazo), mas mais fáceis de esquecer de destrancar. **No IscoT usamos `synchronized`**, porque o enunciado proíbe os mecanismos da biblioteca e o `wait/notifyAll` precisa do cadeado intrínseco. Ver `03-decisoes.md`.

### Variáveis atómicas
`AtomicInteger`, `AtomicReference`... (`java.util.concurrent.atomic`): operações como `incrementAndGet()` que não podem ser interrompidas. Substituem um `synchronized` **só quando toda a secção crítica é uma única operação**. Ex.: o contador de ids. A perguntar ao professor se é permitido.

---

## Parte C · Coordenação (aula 04)

### Sincronização vs coordenação
- **Sincronização** evita que duas threads mexam ao mesmo tempo. Responde a "quem pode entrar agora?".
- **Coordenação** gere **condições e ordem**: "só tiro da mesa quando houver alguma coisa", "só avanço quando todos confirmarem". Responde a "quando é que posso continuar?".

### Espera ativa (*busy waiting*): o que NÃO fazer
`while (mesa vazia) { /* verificar outra vez */ }` → CPU a 100%, gasta energia e rouba tempo às outras threads.

### Produtor-consumidor
- **Produtores** colocam dados, **consumidores** retiram, através de um **recurso partilhado**.
- Colocar: acesso exclusivo; se não houver espaço, esperar.
- Retirar: acesso exclusivo; se estiver vazio, esperar.
- No IscoT: produtores = `ClientHandler` de sensores e decisores; recurso partilhado = `Topic`; consumidor = `TopicDispatcher`.

### wait, notify, notifyAll
| Método | O que faz |
|---|---|
| `wait()` | a thread **liberta o cadeado** e dorme até alguém fazer `notify/notifyAll` no **mesmo objeto**. Ao acordar, volta a disputar o cadeado antes de continuar. |
| `wait(ms)` | igual, mas acorda sozinha ao fim de `ms` (usado no latch). |
| `notify()` | acorda **uma** thread arbitrária à espera nesse objeto. |
| `notifyAll()` | acorda **todas**. |

Os três só podem ser chamados com o cadeado do objeto (dentro de `synchronized`), senão `IllegalMonitorStateException`.

### Spurious wakeup
Uma thread pode acordar de um `wait` **sem** ninguém ter feito `notify`, ou ser acordada por um `notifyAll` cuja condição já foi consumida por outra thread. Por isso a condição é sempre reverificada num `while`.

### Os 4 mandamentos da coordenação (aula 04, decorar)
1. A coordenação é feita **no recurso partilhado, nunca na thread**.
2. `wait/notify/notifyAll` **só dentro de `synchronized`**.
3. `wait` **sempre dentro de `while`**, nunca de `if`.
4. Usar **sempre `notifyAll`**, nunca `notify`.

### Latch (CountDownLatch)
Um "portão" com contador N: cada `countdown()` desconta 1, quem chama `await()` espera até chegar a 0. Não se reutiliza. No IscoT: um latch por evento, com N = número de subscritores. Ver `ModifiedCountdownLatch.java`.

### sleep vs wait (pergunta clássica)
| | `sleep` | `wait` |
|---|---|---|
| Liberta o cadeado? | não | sim |
| Precisa de `synchronized`? | não | sim |
| Acorda por | tempo (ou interrupt) | `notify/notifyAll`, tempo (se tiver), interrupt, spurious |
| Para quê | pausar | esperar por uma condição |

---

## Parte D · Bloqueios da concorrência (matéria que ainda vem)

- **Deadlock**: duas (ou mais) threads ficam paradas para sempre, cada uma à espera de um cadeado que a outra tem. Ex.: A tem o cadeado X e pede Y; B tem Y e pede X. Condições necessárias (Coffman): exclusão mútua, segurar-e-esperar, sem preempção, **espera circular**. Prevenção mais prática: **ordem fixa de aquisição dos cadeados** e **não segurar cadeados durante I/O ou esperas longas**.
- **Starvation (fome)**: uma thread nunca consegue o recurso porque outras passam sempre à frente.
- **Livelock**: as threads não estão bloqueadas, mas passam o tempo a reagir umas às outras sem progredir.
- No IscoT há um deadlock clássico **de rede**, não de cadeados: os dois lados a criar primeiro o `ObjectInputStream` (ver `02-arquitetura.md`, armadilhas).

---

## Parte E · Programação distribuída (sockets e serialização, por dar)

### Rede, IP e porto
- **Endereço IP**: identifica a máquina (`localhost` / `127.0.0.1` = a própria máquina).
- **Porto**: identifica o programa dentro da máquina (0–65535; usamos 8080). Só um programa pode estar à escuta num porto; um segundo dá `BindException`.
- **TCP**: protocolo que garante que os bytes chegam todos e pela ordem. Os sockets Java usam TCP.

### Socket e ServerSocket
- `ServerSocket(porto)`: o servidor fica à escuta. `accept()` **bloqueia** até um cliente ligar e devolve um `Socket` para falar com esse cliente.
- `new Socket(ip, porto)`: o cliente liga-se.
- Cada `Socket` dá dois *streams* de bytes: um de entrada e um de saída.
- Um socket é uma ligação **ponto a ponto**: o servidor tem um `Socket` por cliente, daí uma thread por cliente.

### Streams de objetos e serialização
- **Serializar**: transformar um objeto em bytes para enviar ou gravar. **Desserializar**: o inverso.
- `ObjectOutputStream.writeObject(obj)` / `ObjectInputStream.readObject()`.
- A classe tem de `implements Serializable` (interface sem métodos, só uma "marca").
- `serialVersionUID`: número de versão; se for diferente nos dois lados, a leitura falha.
- `transient`: campo que não é serializado.
- O stream guarda **cache** dos objetos já enviados; `reset()` limpa-a.
- **Ordem**: o construtor de `ObjectInputStream` bloqueia à espera de um cabeçalho que o outro lado só manda ao criar o seu `ObjectOutputStream`. Regra: **Output + `flush()` primeiro**, nos dois lados.

### Canais de texto (alternativa)
`PrintWriter` / `BufferedReader` sobre o socket, com linhas de texto. Legível e universal, mas obriga a escrever o parsing. O enunciado pede canais de objetos.

---

## Parte F · Swing e a thread da interface (aula 01b)

- Componentes: `JFrame` (janela), `JPanel`, `JLabel`, `JButton`, `JTextField`, `JTextArea`, `JCheckBox`, `JSlider`, `JList`.
- Layouts: `FlowLayout` (em fila), `GridLayout(l, c)` (grelha), `BorderLayout` (NORTH/SOUTH/EAST/WEST/CENTER).
- Eventos: um componente (a origem) gera um evento (`ActionEvent`) e chama os *listeners* registados (`addActionListener`), que executam `actionPerformed`.
- **Event Dispatch Thread (EDT)**: o Swing tem **uma thread própria** que desenha a interface e corre todos os listeners. Consequências:
  1. Nunca fazer nada demorado ou bloqueante num `actionPerformed` (`sleep`, `readObject`): a janela congela.
  2. O Swing **não é thread-safe**: outras threads não devem mexer nos componentes diretamente; devem pedir à EDT com `SwingUtilities.invokeLater(...)`.
- Molde do professor (`FrameTest`): construtor cria o `JFrame` e chama `addFrameContent()` e `pack()`; `open()` faz `setVisible(true)`; `main` cria e abre.

---

## Parte G · Termos do domínio (broker e domótica)

| Termo | Significado | No IscoT |
|---|---|---|
| Message broker | intermediário que recebe mensagens de produtores e entrega a consumidores, sem que se conheçam | o servidor |
| Publish / subscribe | quem publica não sabe quem recebe; quem subscreve recebe tudo do tópico | sensores e decisores publicam; decisores e atuadores subscrevem |
| Tópico | canal com nome que agrupa mensagens de um tema | um por área da casa: `LivingRoom`, `Garden`, … |
| Evento | uma mensagem num tópico | `SensorMessage` / `OrderMessage` (ex.: `LightSensorMessage`) |
| Ack (acknowledgement) | confirmação de receção | `AckMessage` |
| QoS (MQTT) | nível de garantia: 0 sem confirmação, 1 pelo menos uma vez (com ack), 2 exatamente uma vez | o nosso modelo parece QoS 1 |
| Retained message (MQTT) | o broker guarda a última mensagem e dá-a a quem subscrever depois | não temos; dúvida para o professor |
| Head-of-line blocking | o primeiro da fila atrasa todos os que vêm atrás | um atuador lento prende o tópico até ao timeout |
| Timeout | tempo máximo de espera | `await(waitPeriod)` do latch |
| Histerese | dois limiares diferentes para ligar e desligar, para não oscilar | regra do decisor de luz (acende abaixo de 500 lux, apaga acima de 600) |

---

## As 4 estratégias de segurança entre threads (MIT 6.031)

O curso *Software Construction* do MIT resume as formas de tornar seguro o acesso a dados partilhados em quatro estratégias. Usamo-las para justificar cada decisão:

1. **Confinamento**: os dados só são usados por uma thread. Ex.: o `ObjectInputStream` de um `ClientHandler` só é lido pela própria thread do handler.
2. **Imutabilidade**: dados que não mudam podem ser partilhados sem cadeado. Ex.: enums, campos `final` das mensagens.
3. **Tipos thread-safe**: estruturas que se protegem sozinhas, sem exigir coordenação a quem as usa. Ex.: o nosso `Topic` e o nosso latch (os da biblioteca estão proibidos).
4. **Sincronização**: cadeados à volta das secções críticas. Ex.: `synchronized`.

Fontes: MIT 6.031, leituras *Thread Safety* e *Locks and Synchronization*; Oracle Java Tutorials (*Concurrency*, *All About Sockets*, *Concurrency in Swing*); mosquitto `mqtt(7)`.

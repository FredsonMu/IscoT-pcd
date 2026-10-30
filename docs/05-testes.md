# 05 · Como testar

Erros de concorrência não aparecem quando se testa com uma thread nem, muitas vezes, numa execução curta. Por isso testamos em três níveis: **peças sozinhas**, **sistema a correr no Eclipse**, e **carga**.

## Nível 1 · Peças sozinhas (sem rede)

Correr as classes de `iscot.test` como programa (botão direito › Run As › Java Application). Cada uma descreve, no comentário, o que verifica.

| Teste | Fase | Verifica |
|---|---|---|
| `TopicStructureTest` | 2 | FIFO, ids sequenciais, tópicos separados, `getOrCreate` devolve o mesmo objeto, nomes de tópicos, parse de áreas e tipos |
| `LatchTest` | 5 | timeout, libertação, latch a zero, acks a mais, interrupção |
| `ConcurrencyStressTest` | 5 | 10 threads × 1000 publicações: nada se perde, nenhum id repete |

**Regra**: quem escreve o teste não é quem escreveu a classe. Assim testamos o que a classe deve fazer, não o que achamos que fizemos.

## Nível 2 · Sistema a correr no Eclipse

### Preparar
- A pasta `launch/` tem configurações prontas: aparecem em **Run › Run Configurations › Java Application** (e no botão Run ▾).
- Correr sempre **o servidor primeiro**.
- Cada programa tem a sua consola. No ícone da consola: **Display Selected Console** para alternar e **Pin Console** para fixar uma.
- Duas lâmpadas iguais: lançar a mesma configuração duas vezes.
- No fim: parar todas as consolas (quadrado vermelho) e **Remove All Terminated**. Se não, o próximo servidor dá `BindException`.

### Cenários por fase

**Fase 1–2 (entrega intercalar)**
- [ ] `9 Formulario de teste`: publicar 3 eventos em `sala/luz/leituras` e 1 em `jardim/humidade/leituras`; aparecem em filas separadas, por ordem.
- [ ] "Consumir cabeça" remove o mais antigo.
- [ ] As janelas de sensor, decisor e atuador abrem e reagem aos botões; o histórico do atuador mostra `Lâmpada ligada - HH:MM:SS`.

**Fase 3 · ligação**
- [ ] Servidor arranca e escreve que está à escuta.
- [ ] Três clientes recebem três ids diferentes.
- [ ] `8 Tipo invalido` é recusado com mensagem clara e o servidor continua a funcionar.
- [ ] Área inválida (`garagem`) também é recusada.
- [ ] Cliente sem servidor ligado mostra erro claro (não um stack trace).

**Fase 4 · mensagens**
- [ ] Sensor publica → o decisor da mesma área e grandeza recebe; o de outra área não.
- [ ] Decisor publica ordem → todas as lâmpadas da sala recebem; as do quarto não.
- [ ] Fechar a janela de um cliente não faz o servidor rebentar.

**Fase 5 · latch**
- [ ] 2 lâmpadas: o log do servidor diz que o evento foi consumido com 0 em falta.
- [ ] Um cliente que recebe mas não confirma (comentar o envio do ack temporariamente): o tópico avança ao fim do timeout com 1 em falta.
- [ ] Fechar uma lâmpada a meio: o evento seguinte já só espera pela outra.
- [ ] Publicar 20 leituras seguidas: chegam todas, por ordem.

**Fase 6 · extra**
- [ ] Atuador responde OK a ordens conhecidas e NOK a desconhecidas; o servidor regista o resultado.

**Automações (demo final)**
- [ ] Luz baixa → lâmpadas acendem.
- [ ] Terra seca → rega liga; humidade sobe → rega desliga.
- [ ] Começa a chover → janelas fecham.
- [ ] Temperatura alta → ar condicionado liga.

## Nível 3 · Carga e robustez

- `ConcurrencyStressTest`, corrido várias vezes seguidas.
- **Experiência didática**: tirar temporariamente o `synchronized` do `Topic.publish`, correr o teste de carga e ver os números a falhar. Voltar a pôr. É a race condition da aula 03 ao vivo; vale a pena mostrar na oral.
- Vários sensores em modo automático com intervalo curto (100 ms) durante 1 minuto: o servidor não pode encravar.

## Quando algo encrava: como investigar

1. **Correr em Debug** (ícone do inseto) em vez de Run.
2. Quando parar, na vista **Debug**, clicar no processo › **Suspend**. Aparecem todas as threads e a linha onde cada uma está. Os nomes das threads (`dispatcher-sala/luz/ordens`, ...) servem exatamente para isto.
3. Ver onde estão:
   - todas em `readObject` → provavelmente ninguém enviou nada (ou ordem dos streams errada);
   - uma em `wait` do `Topic` → fila vazia (normal) ou faltou um `notifyAll`;
   - em `latch.await` → falta algum ack;
   - várias `BLOCKED` à espera de um cadeado → alguém o segura demasiado tempo (I/O dentro de `synchronized`?) ou deadlock.
4. Alternativa fora do Eclipse: `jstack <pid>` (o `pid` vê-se com `jps`) imprime o mesmo e deteta deadlocks automaticamente.
5. Logs: cada `System.out` do servidor deve dizer quem (deviceId ou nome do tópico) e o quê.

## Antes de cada entrega

- [ ] `main` atualizada nos dois PCs, compila sem erros (Project › Clean).
- [ ] Todos os cenários da fase correspondente passaram.
- [ ] README atualizado (como correr, o que está feito, o que falta).
- [ ] Tag criada (`checkpoint-1`, `entrega-final`).

# 05 · Como testar as fases 1 a 3

**Regra:** quem escreve o teste de uma classe não é quem a escreveu. Assim testamos o que a classe deve fazer, não o que achamos que fizemos.

## Testes automáticos: `09 Testes`

`iscot.test.IscotTests` (as notas da classe têm a lista).

- [ ] Texto de cada um dos 9 eventos igual ao esperado, num Windows em português.
- [ ] 4 leituras com tipo `SENSOR`; 4 ordens com tipo `DECIDER`; `RegistrationEvent` com o tipo recebido.
- [ ] Leituras são `SensorEvent`, ordens são `HighLevelOrderEvent`, e tudo é `Event`.
- [ ] Todos os eventos são escritos e lidos num stream de objetos.
- [ ] Cada evento devolve a área com que foi criado.

## Verificação à mão, no Eclipse

### Fase 1 e 3 · Sensores

- [ ] `01 Sensor luz`: largar o slider imprime **uma** linha `LightSensorEvent [...]`.
- [ ] `02 Sensor temperatura`: idem, com `TemperatureSensorEvent`.
- [ ] `03 Sensor humidade ar`: idem, com `HumiditySensorEvent`.
- [ ] `04 Sensor humidade solo`: idem, com `GroundMoistureSensorEvent`.
- [ ] O valor impresso é igual ao da etiqueta por baixo do slider.

### Fase 1 e 3 · Atuadores

- [ ] `05 Lampada`, lançada duas vezes: cada uma acende e apaga sozinha; histórico com `Lâmpada ligada - HH:MM:SS`.
- [ ] `06 Ventilador`: liga e desliga; histórico com a hora certa.
- [ ] `07 Ar condicionado`: em modo `Frio`, a ordem de ventilação **não** muda nada; desligado, é aplicada.
- [ ] `08 Rega`: liga e desliga; histórico com a hora certa.
- [ ] A janela responde durante a sequência (dá para a arrastar).
- [ ] Muitas linhas no histórico: aparece a barra de deslocamento.

### Argumentos

- [ ] Menos de 3 argumentos: `Invalid arguments!`.
- [ ] Área inexistente (ex.: `sala`): erro com o nome da área. Saber explicá-lo.

## Se algo correr mal

| O que se vê | Onde procurar |
|---|---|
| A janela congela | uma pausa ou trabalho demorado na thread do Swing |
| Ícone não muda | o método certo daquele ícone (`setLigada`, `setLigado`, `setAtiva`, `setModo`) |
| Histórico sem nada | o painel foi mesmo adicionado à janela? `pack` depois de adicionar |
| Texto do evento diferente | comparar carácter a carácter com [02-arquitetura](02-arquitetura.md) |
| `Selection does not contain a main type` | a classe ainda é só o esqueleto |

Em Eclipse: correr em **Debug**, e usar **Suspend** para ver onde cada thread está.

## Antes da apresentação

- [ ] `main` atualizada nos dois PCs; compila sem erros (Project › Clean).
- [ ] Todas as caixas acima assinaladas, nos dois PCs.
- [ ] README atualizado com o que está feito.
- [ ] Consolas antigas fechadas (Remove All Terminated).
- [ ] Tag criada: `git tag entrega-intermedia` e `git push --tags`.

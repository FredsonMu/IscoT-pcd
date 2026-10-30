package iscot.gui;

/**
 * DecisorFrame — janela que mostra o que o decisor recebe e decide.
 *
 * OBJETIVO NO ENUNCIADO
 *   Secção 1.2: "As automações desenvolvidas com o consumo de eventos deverão ser
 *   demonstradas via GUI". O decisor é onde a automação "pensa", por isso tem de se ver.
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Título com id, área e grandeza.
 *   - Campo(s) para o limiar da regra (ex.: "ligar abaixo de 200 lux").
 *   - JLabel com a última leitura recebida.
 *   - Lista/área de texto com o registo: "12:01:03 leitura 150 lux -> LIGAR".
 *   - Métodos para o DecisorClient atualizar (chamados dentro de invokeLater).
 *
 * LIGAÇÕES
 *   Criada e controlada por: DecisorClient.
 *
 * DÚVIDA
 *   O limiar pode ser alterado com o sistema a correr? Se sim, é lido pela thread
 *   recetora e escrito pela thread do Swing: precisa de proteção (synchronized ou
 *   volatile, este último ainda não dado). Alternativa simples: fixo no arranque.
 *
 * Fase 1 · Responsável: Fredson · Revisor: Celso
 */
public class DecisorFrame {
}

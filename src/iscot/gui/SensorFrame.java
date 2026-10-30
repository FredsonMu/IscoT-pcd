package iscot.gui;

/**
 * SensorFrame — janela que simula um sensor físico.
 *
 * OBJETIVO NO ENUNCIADO
 *   Fase 1: "componentes que simulam os sensores (luz, temperatura, chuva, etc)".
 *   (O enunciado diz "[TBD]": o professor pode vir a dar componentes próprios.)
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - Título com o id e a área (ex.: "sala-sensor_luz-4").
 *   - JLabel com a grandeza e a unidade (lux, ºC, % de humidade, chuva sim/não).
 *   - JSlider com o valor atual (para chuva, um JCheckBox "a chover" chega).
 *   - JButton "Publicar leitura".
 *   - JCheckBox "Automático" (liga/desliga a publicação periódica).
 *   - Pequena área com as últimas leituras enviadas.
 *   - Não sabe nada de rede: expõe o valor atual e deixa o SensorClient registar
 *     listeners nos botões (separação GUI / lógica).
 *
 * LIGAÇÕES
 *   Criada e controlada por: SensorClient.
 *
 * PORQUÊ SEPARAR A JANELA DA LÓGICA
 *   Fase 1 pede "ambientar-se aos componentes gráficos" antes de haver rede. Se a janela
 *   não depender da ligação, pode ser desenhada e testada já, por um de nós, enquanto o
 *   outro faz o servidor. Também facilita trocar pelos componentes do professor.
 *
 * Fase 1 · Responsável: Fredson · Revisor: Celso
 */
public class SensorFrame {
}

package iscot.client;

/**
 * IrrigationDecider — programa do decisor da rega.
 *
 * OBJETIVO
 *   Enunciado 1.2: "se o sensor de humidade avisar que a terra está seca, o agente
 *   decisor da rega define uma ordem de irrigação, e os atuadores ligam a irrigação do
 *   jardim até o nível desejado de humidade ser atingido".
 *
 * O QUE VAMOS IMPLEMENTAR (mesmo padrão que LightingDecider)
 *   - main(args): endereço, porto, área (normalmente Garden).
 *   - register(Type.DECIDER, area); startReceiving:
 *       HumiditySensorMessage com humidity < 30 %  -> AreaIrrigationOrderMessage(true)
 *       HumiditySensorMessage com humidity >= 60 % -> AreaIrrigationOrderMessage(false)
 *       (dois limiares = "até o nível desejado"; só envia quando muda)
 *   - Sem janela: decisões na consola.
 *
 * LIGAÇÕES
 *   Recebe de: HumiditySensor (código base). Envia para: Irrigation.
 *
 * Prioridade 2 (automação de rega) · Responsável: Celso · Revisor: Fredson
 */
public class IrrigationDecider {
}

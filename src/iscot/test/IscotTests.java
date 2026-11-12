package iscot.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

import iscot.common.AreaIrrigationOrderEvent;
import iscot.common.AreaLightingOrderEvent;
import iscot.common.AreaTemperatureOrderEvent;
import iscot.common.AreaVentilationOrderEvent;
import iscot.common.Event;
import iscot.common.GroundMoistureSensorEvent;
import iscot.common.HighLevelOrderEvent;
import iscot.common.HumiditySensorEvent;
import iscot.common.LightSensorEvent;
import iscot.common.RegistrationEvent;
import iscot.common.SensorEvent;
import iscot.common.TemperatureSensorEvent;
import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * Testes da hierarquia de eventos: formato do texto, tipo, hierarquia, área e serialização.
 */
public class IscotTests {

    /** Quantas verificações passaram e quantas falharam (o programa só tem uma thread). */
    private static int passaram = 0;
    private static int falharam = 0;

    /**
     * Passo 1: compara o valor esperado com o obtido, escreve OK ou FALHOU e conta o resultado.
     * Em caso de falha, mostra os dois valores, para se ver logo a diferença.
     */
    private static void verificar(String descricao, Object esperado, Object obtido) {
        if (Objects.equals(esperado, obtido)) {
            passaram++;
            System.out.println("  OK      " + descricao);
        } else {
            falharam++;
            System.out.println("  FALHOU  " + descricao);
            System.out.println("          esperado: " + esperado);
            System.out.println("          obtido:   " + obtido);
        }
    }

    /**
     * Passo 5: escreve o objeto num stream de objetos, em memória, e volta a lê-lo.
     * É o que vai acontecer na fase 5, mas com um socket no lugar dos bytes em memória.
     */
    private static Object idaEVolta(Object objeto) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
            out.writeObject(objeto);                                  // serializar: objeto -> bytes
        }
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            return in.readObject();                                   // desserializar: bytes -> objeto novo
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Formato do texto (toString) ===");
        verificar("LightSensorEvent (formato dos slides)",
                "LightSensorEvent [light=320.0, area=LivingRoom, getType()=SENSOR]",
                new LightSensorEvent(Areas.LivingRoom, 320).toString());
        verificar("TemperatureSensorEvent",
                "TemperatureSensorEvent [temperature=27.0, area=LivingRoom, getType()=SENSOR]",
                new TemperatureSensorEvent(Areas.LivingRoom, 27).toString());
        verificar("HumiditySensorEvent",
                "HumiditySensorEvent [humidity=72.0, area=LivingRoom, getType()=SENSOR]",
                new HumiditySensorEvent(Areas.LivingRoom, 72).toString());
        verificar("GroundMoistureSensorEvent",
                "GroundMoistureSensorEvent [moisture=18.0, area=Garden, getType()=SENSOR]",
                new GroundMoistureSensorEvent(Areas.Garden, 18).toString());
        verificar("AreaLightingOrderEvent (formato dos slides)",
                "AreaLightingOrderEvent [state=true, area=LivingRoom, getType()=DECIDER]",
                new AreaLightingOrderEvent(Areas.LivingRoom, true).toString());
        verificar("AreaVentilationOrderEvent",
                "AreaVentilationOrderEvent [state=true, area=LivingRoom, getType()=DECIDER]",
                new AreaVentilationOrderEvent(Areas.LivingRoom, true).toString());
        verificar("AreaTemperatureOrderEvent",
                "AreaTemperatureOrderEvent [mode=Frio, area=LivingRoom, getType()=DECIDER]",
                new AreaTemperatureOrderEvent(Areas.LivingRoom, AirConditioningMode.Frio).toString());
        verificar("AreaIrrigationOrderEvent",
                "AreaIrrigationOrderEvent [state=true, area=Garden, getType()=DECIDER]",
                new AreaIrrigationOrderEvent(Areas.Garden, true).toString());
        verificar("RegistrationEvent",
                "RegistrationEvent [area=LivingRoom, getType()=ACTUATOR]",
                new RegistrationEvent(Areas.LivingRoom, Type.ACTUATOR).toString());

        // Os 9 eventos numa só lista do tipo Event: daqui para a frente, o código não sabe a classe
        // concreta de cada um (polimorfismo). As áreas variam de propósito, para testar o getArea().
        Event[] eventos = {
            new LightSensorEvent(Areas.Kitchen, 100),
            new TemperatureSensorEvent(Areas.WC, 21),
            new HumiditySensorEvent(Areas.LivingRoom, 50),
            new GroundMoistureSensorEvent(Areas.Garden, 30),
            new AreaLightingOrderEvent(Areas.BedRoom, false),
            new AreaVentilationOrderEvent(Areas.Kitchen, false),
            new AreaTemperatureOrderEvent(Areas.WC, AirConditioningMode.Aquecimento),
            new AreaIrrigationOrderEvent(Areas.Garden, false),
            new RegistrationEvent(Areas.LivingRoom, Type.SENSOR)
        };
        Areas[] areasEsperadas = {
            Areas.Kitchen, Areas.WC, Areas.LivingRoom, Areas.Garden,
            Areas.BedRoom, Areas.Kitchen, Areas.WC, Areas.Garden,
            Areas.LivingRoom
        };
        Type[] tiposEsperados = {
            Type.SENSOR, Type.SENSOR, Type.SENSOR, Type.SENSOR,
            Type.DECIDER, Type.DECIDER, Type.DECIDER, Type.DECIDER,
            Type.SENSOR      // o registo: o tipo do cliente que se regista
        };

        System.out.println();
        System.out.println("=== 2. Tipo (getType) ===");
        for (int i = 0; i < eventos.length; i++) {
            Event e = eventos[i];
            verificar(e.getClass().getSimpleName() + " é " + tiposEsperados[i], tiposEsperados[i], e.getType());
        }
        // O tipo do registo não é fixo: é o que recebeu no construtor.
        verificar("RegistrationEvent criado com DECIDER é DECIDER",
                Type.DECIDER, new RegistrationEvent(Areas.WC, Type.DECIDER).getType());

        System.out.println();
        System.out.println("=== 3. Hierarquia (instanceof) e área (getArea) ===");
        for (int i = 0; i < eventos.length; i++) {
            Event e = eventos[i];
            String nome = e.getClass().getSimpleName();
            boolean eLeitura = i < 4;              // os 4 primeiros são leituras
            boolean eOrdem = i >= 4 && i < 8;      // os 4 seguintes são ordens; o último é o registo
            verificar(nome + (eLeitura ? " é" : " não é") + " SensorEvent", eLeitura, e instanceof SensorEvent);
            verificar(nome + (eOrdem ? " é" : " não é") + " HighLevelOrderEvent", eOrdem, e instanceof HighLevelOrderEvent);
            verificar(nome + " tem a área " + areasEsperadas[i], areasEsperadas[i], e.getArea());
        }

        System.out.println();
        System.out.println("=== 4. Serialização (ida e volta num stream de objetos) ===");
        for (Event e : eventos) {
            String nome = e.getClass().getSimpleName();
            try {
                Object copia = idaEVolta(e);
                verificar(nome + " volta igual", e.toString(), copia.toString());
                verificar(nome + " volta como um objeto novo", true, copia != e);
            } catch (IOException | ClassNotFoundException erro) {
                verificar(nome + " pode ser serializado", "sem erro", erro.toString());
            }
        }

        System.out.println();
        System.out.println("Resultado: " + passaram + " passaram, " + falharam + " falharam.");
    }
}

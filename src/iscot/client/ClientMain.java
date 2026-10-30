package iscot.client;

/**
 * ClientMain — ponto de entrada de qualquer cliente (sensor, decisor ou atuador).
 *
 * OBJETIVO NO ENUNCIADO E NA APRESENTAÇÃO
 *   Enunciado 1.2: "No arranque do cliente, este liga-se automaticamente ao servidor,
 *   indicando o seu tipo e a área da casa onde opera. Estes parâmetros, bem como o
 *   endereço e porto do servidor, devem ser recebidos como argumentos de execução do main."
 *   Enunciado 2.1: java iscot { IP PORT AreaDispositivo TipoDispositivo }
 *   Apresentação: cada cliente é uma "aplicação independente".
 *
 * O QUE VAMOS IMPLEMENTAR
 *   - main(args): verificar que há 4 argumentos (senão mostrar como usar e sair):
 *       args[0] IP do servidor      ex.: localhost
 *       args[1] porto               ex.: 8080
 *       args[2] área                ex.: sala
 *       args[3] tipo                ex.: LAMPADA
 *   - Criar BrokerConnection e fazer register(área, tipo). Se for recusado, mostrar o
 *     motivo (JOptionPane ou consola) e terminar.
 *   - Ver o Role do tipo e arrancar a classe certa: SENSOR -> SensorClient,
 *     DECIDER -> DecisorClient, ACTUATOR -> ActuatorClient, passando a ligação, o id,
 *     a área e o tipo.
 *
 * PORQUÊ UM SÓ MAIN PARA OS TRÊS PAPÉIS
 *   O enunciado mostra um só comando para todos os clientes. Um main que decide pelo
 *   tipo evita 3 programas quase iguais e garante que todos se ligam da mesma forma.
 *   Alternativa: 3 mains (SensorMain, ...): mais explícito nas Run Configurations, mas
 *   repete o código de ligação e registo.
 *
 * O CENÁRIO DO PROFESSOR = 4 EXECUÇÕES DESTE MAIN
 *   localhost 8080 sala SENSOR_LUZ   |  localhost 8080 sala DECISOR_LUZ
 *   localhost 8080 sala LAMPADA      |  localhost 8080 sala LAMPADA   (candeeiro e teto)
 *
 * LIGAÇÕES
 *   Usa: BrokerConnection, Area, DeviceType (e o seu Role), SensorClient, DecisorClient,
 *   ActuatorClient.
 *
 * NO ECLIPSE
 *   Os argumentos vão em Run Configurations > Arguments > Program arguments.
 *   Já existem configurações prontas na pasta launch/ (aparecem no menu Run).
 *
 * DÚVIDAS
 *   - A classe principal tem de se chamar literalmente "iscot"? Em Java as classes
 *     começam por maiúscula; perguntar se iscot.client.ClientMain é aceitável.
 *
 * Fase 3 · Responsável: Fredson · Revisor: Celso
 */
public class ClientMain {
}

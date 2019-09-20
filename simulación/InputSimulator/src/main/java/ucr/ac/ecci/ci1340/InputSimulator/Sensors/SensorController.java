package ucr.ac.ecci.ci1340.InputSimulator.Sensors;

import ucr.ac.ecci.ci1340.InputSimulator.DBMS.MySQLConfiguration;
import ucr.ac.ecci.ci1340.InputSimulator.DBMS.MySQLExecutor;
import ucr.ac.ecci.ci1340.InputSimulator.FileManagement.CSVManager;

import java.io.IOException;
import java.time.LocalDateTime;

import org.javatuples.Triplet;

import java.util.*;

public class SensorController extends Thread {
    private MySQLConfiguration mySQLConfiguration;
    private Sensor sensor;
    private boolean delay;
    private boolean zero;
    private boolean stop;

    private SensorController(Sensor sensor, boolean delay, boolean zero, MySQLConfiguration mySQLConfiguration){
        this.mySQLConfiguration = mySQLConfiguration;
        this.sensor = sensor;
        this.delay = delay;
        this.zero = zero;
        this.stop = false;
    }

    private void setDelay(boolean delay) {
        this.delay = delay;
    }

    private void setZero(boolean zero) {
        this.zero = zero;
    }

    /**
     * Gets a data package from the sensors and saves it on the MySQL database
     */
    @Override
    public void run(){
        System.out.println("Sensor \033[33m\""+ sensor.getSensorId() +"\"\033[0m turned on.");
        try {
            MySQLExecutor mySQLExecutor = new MySQLExecutor(mySQLConfiguration);
            while(!stop) {
                Vector<Triplet<String, LocalDateTime, Double>> data = sensor.getData();
                for(Triplet<String, LocalDateTime, Double> t : data){
                    if(zero || t.getValue2() != 0){
                        mySQLExecutor.open();
                        mySQLExecutor.insertLog(t);
                        mySQLExecutor.close();
                    }
                    if(delay)sleep(1000);
                }
            }
        }
        catch (InterruptedException e){
            close();
        }
    }

    /**
     * Set stop flags to true
     */
    private void close(){
        this.stop = true;
        sensor.close();
        System.out.println("Sensor \033[33m\"" + sensor.getSensorId() + "\"\033[0m turned off");
    }

    // Antes de utilizar el simulador se debe de alterar la configuración de mySQL en el archivo "my.cnf" y colocar: default-time-zone='-06:00'
    // Como alternativa ingresar como root y ejecutar lo siguiente SET GLOBAL time_zone = '-6:00';
    /**
     * Reads the fluidSensors and the electricSensors CSV files and creates threads as sensor parameters are detected, two per each
     * @param args null
     * @throws IOException if the given files aren't found
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String, SensorController> sensorControllers = new HashMap<>();
        MySQLConfiguration mySQLConfiguration = new MySQLConfiguration("localhost","3307","usuario","contraseña","simulador");

        System.out.println("\033[34m====================================================================================================\033[0m");
        System.out.println("Sensor Simulator");
        System.out.println("\t 0) Load sensors (modify the csv files for that, old sensors can stay)");
        System.out.println("\t 1) Deactivate/Activate delay (save on the database every time a package arrives / every second)");
        System.out.println("\t 2) Ignore/Save measurements with zero as value");
        System.out.println("\t 3) Turn off sensor by id");
        System.out.println("\t 4) exit");
        System.out.println("\033[34m====================================================================================================\033[0m");

        boolean stop = false;
        boolean delay = true;
        boolean zero = false;

        while(!stop) {
            sleep(100);
            Scanner input1 = new Scanner(System.in);
            int answer;
            do {
                System.out.print("Option: ");
                answer = input1.nextInt();
                switch (answer) {
                    case 1:
                        if (delay) System.out.println("Delay deactivated");
                        else System.out.println("Delay activated");
                        delay = !delay;
                        for (Map.Entry<String, SensorController> sc : sensorControllers.entrySet()) {
                            sc.getValue().setDelay(delay);
                        }
                        break;
                    case 2:
                        if (zero) System.out.println("Ignoring measurements with zero as value");
                        else System.out.println("Saving measurements with zero as value");
                        zero = !zero;
                        for (Map.Entry<String, SensorController> sc : sensorControllers.entrySet()) {
                            sc.getValue().setZero(zero);
                        }
                        break;
                    case 3:
                        if (sensorControllers.size() == 0) {
                            System.out.println("There aren't sensors turned on");
                        } else {
                            Scanner input2 = new Scanner(System.in);
                            String id;
                            do {
                                System.out.print("Sensor id: ");
                                id = input2.nextLine();
                                if (!sensorControllers.containsKey(id)) {
                                    System.out.println("There is not sensor with that id, try again.");
                                }
                            } while (!sensorControllers.containsKey(id));
                            sensorControllers.get(id).close();
                            sensorControllers.remove(id);
                        }
                        break;
                }
            } while (answer != 0 && answer != 4);
            stop = answer == 4;
            if (!stop) {
                ArrayList<FluidSensor> fluidSensors = new ArrayList<>();
                CSVManager fluidCSVManager = new CSVManager("fluidSensors.csv");
                String[] fluidParams;
                int i = 0;
                while ((fluidParams = fluidCSVManager.readNext()) != null) {
                    if (fluidParams.length == 8 && !sensorControllers.containsKey(fluidParams[0])) {
                        fluidSensors.add(i, new FluidSensor(
                                fluidParams[0],                     // id
                                Double.parseDouble(fluidParams[1]), // maxSpeed
                                Double.parseDouble(fluidParams[2]), // acceleration
                                Double.parseDouble(fluidParams[3]), // pipe diameter
                                Double.parseDouble(fluidParams[4]), // openingValveProbability
                                Double.parseDouble(fluidParams[5]), // closingValveProbability
                                Integer.parseInt(fluidParams[6]),    // packageSize
                                Integer.parseInt(fluidParams[7])
                        ));
                        fluidSensors.get(i).start();
                        sensorControllers.put(fluidParams[0], new SensorController(fluidSensors.get(i), delay, zero, mySQLConfiguration));
                        sensorControllers.get(fluidParams[0]).start();
                        i++;
                    }
                }
                ArrayList<ElectricSensor> electricSensors = new ArrayList<>();
                CSVManager electricCSVManager = new CSVManager("electricSensors.csv");
                String[] electricParams;
                i = 0;
                while ((electricParams = electricCSVManager.readNext()) != null) {
                    if (electricParams.length == 6 && !sensorControllers.containsKey(electricParams[0])) {
                        electricSensors.add(i, new ElectricSensor(
                                electricParams[0],                     // id
                                Integer.parseInt(electricParams[1]),   // power
                                Double.parseDouble(electricParams[2]), // turnOnProbability
                                Double.parseDouble(electricParams[3]), // turnOffProbability
                                Integer.parseInt(electricParams[4]),    // packageSize
                                Integer.parseInt(electricParams[5])
                        ));
                        electricSensors.get(i).start();
                        sensorControllers.put(electricParams[0], new SensorController(electricSensors.get(i), delay, zero, mySQLConfiguration));
                        sensorControllers.get(electricParams[0]).start();
                        i++;
                    }
                }
            }
        }
        for(Map.Entry<String,SensorController> sc : sensorControllers.entrySet()){
            sc.getValue().close();
        }
    }
}

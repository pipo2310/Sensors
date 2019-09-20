package ucr.ac.ecci.ci1340.InputSimulator.Sensors;

import java.util.Random;

public class ElectricSensor extends Sensor {
    private double power;               // Watts
    private double turnOnProbability;   // 0-100
    private double turnOffProbability;  // 0-100
    private int bound;
    ElectricSensor(String id, double power, double turnOnProbability, double turnOffProbability, int packageSize,  int bound) {
        super(id, packageSize);
        this.power = power/3600;
        this.bound = bound;
        this.turnOnProbability = turnOnProbability * this.bound;
        this.turnOffProbability = turnOffProbability * this.bound;

    }

    /**
     * Simulates a device going on and off given the probabilities, and gathers kWs spent
     */
    @Override
    public void run(){
        double power = 0;
        try{
            while(!stop) {
                Random clic = new Random();
                if (power == 0){
                    power = (clic.nextInt(this.bound + 1) < turnOnProbability)? this.power : power;
                }else{
                    power = (clic.nextInt(this.bound + 1) < turnOffProbability)? 0 : power;
                }
                gatherData(power);
                sleep(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

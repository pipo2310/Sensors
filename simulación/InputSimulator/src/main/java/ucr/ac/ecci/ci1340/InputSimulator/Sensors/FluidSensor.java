package ucr.ac.ecci.ci1340.InputSimulator.Sensors;

import java.util.Random;

public class FluidSensor extends Sensor{
    private double maxSpeed; // m/s
    private double acceleration; // m^2/s
    private double diameter; // m
    private double openingValveProbability; // 0-100
    private double closingValveProbability; // 0-100
    private int bound;

    FluidSensor(String id, double maxSpeed, double acceleration, double diameter, double openingValveProbability, double closingValveProbability, int packageSize, int bound) {
        super(id,packageSize);
        this.maxSpeed = maxSpeed;
        this.acceleration = -1* acceleration;
        this.diameter = diameter;
        this.bound = bound;
        this.openingValveProbability = openingValveProbability * this.bound;
        this.closingValveProbability = closingValveProbability * this.bound;
    }

    /**
     * Simulates the opening and closing of a valve given the probabilities, and gathers the mL/s spent
     */
    @Override
    public void run(){
        double speed = 0;
        double area = (Math.PI * Math.pow(diameter, 2)) / 4;
        try{
            while(!stop) {
                Random turn = new Random();
                if (acceleration < 0) {
                    acceleration *= (turn.nextInt(this.bound + 1) < openingValveProbability)? -1 : 1;
                }else{
                    acceleration *= (turn.nextInt(this.bound + 1) < closingValveProbability)? -1 : 1;
                }
                speed += acceleration;
                speed = (speed < 0) ? 0 : Math.min(speed, maxSpeed);
                gatherData(speed * area/1000);
                sleep(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



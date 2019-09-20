package ucr.ac.ecci.ci1340.InputSimulator.Sensors;

import java.time.LocalDateTime;
import org.javatuples.Triplet;
import java.util.Vector;

class Sensor extends Thread{
    private String id;
    private int packageSize;
    private Vector<Triplet<String,LocalDateTime,Double>> data;
    boolean stop;

    Sensor(String id, int packageSize){
        this.id = id;
        this.packageSize = packageSize;
        data = new Vector<>();
        stop = false;
    }

    public String getSensorId() {
        return id;
    }

    synchronized void close(){
        packageSize = -1;
        stop = true;
        notify();
    }

    /**
     * adds an element to the triplet vector whit the sensor id, the local datetime and the measure
     * @param value the measured gathered from a sensor
     */
    synchronized void gatherData(Double value){
        data.addElement(new Triplet<>(id,LocalDateTime.now(),value));
        notify();
    }

    /**
     * Subtracts the first n triplets from the data vector and returns it
     * @return the first n triplets, n = packageSize
     * @throws InterruptedException if the data vector doesn't have enough elements and the program stops
     */
    synchronized Vector<Triplet<String, LocalDateTime, Double>> getData() throws InterruptedException {
        while (data.size() < packageSize) wait();
        Vector<Triplet<String, LocalDateTime, Double>> result = new Vector<>();
        for(int i = 0; i < packageSize; i++){
            Triplet<String, LocalDateTime, Double> first = data.firstElement();
            result.add(first);
            data.removeElement(first);
        }
        return result;
    }
}

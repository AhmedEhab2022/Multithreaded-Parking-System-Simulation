package parkingComponents;

import java.util.List;

public class Gate extends Thread {
    private int carsServed;
    private int gateNumber;
    private List<Car> cars;

    public Gate(int gateNumber, List<Car> cars) {
        carsServed = 0;
        this.gateNumber = gateNumber;
        this.cars = cars;
        setName("Gate " + gateNumber);
    }

    public int getNumber() {
        return gateNumber;
    }

    public int getCarsServed() {
        return carsServed;
    }

    @Override
    public void run() {
        for (Car car : cars) {
            car.start();
            carsServed++;
        }
    }
}

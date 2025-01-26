package parkingComponents;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Car extends Thread {
    private int gateNumber;
    private int carNumber;
    private int arrive;
    private int parks;
    private Spots spots;
    private CustomSemaphore spotsSemaphore;
    private static final String OUTPUT_FILE = "src/output.txt";

    public Car(int carNumber, int arrive, int parks, Spots spots) {
        this.carNumber = carNumber;
        this.arrive = arrive;
        this.parks = parks;
        this.spots = spots;
        this.spotsSemaphore = spots.getSpots();
        this.setName("Car " + this.carNumber);
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    @Override
    public void run() {
        arrive();
        park();
    }

    private void arrive() {
        try {
            sleep(arrive * 1000);
            String log = "%s from Gate %d arrived at time %d.".formatted(getName(), gateNumber, arrive);
            writeLog(log);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void park() {
        long startTime = System.currentTimeMillis();
        String s = "";
        try {
            if (spotsSemaphore.availablePermits() == 0) {
                String log1 = "%s from Gate %d waiting for a spot.".formatted(getName(), gateNumber);
                writeLog(log1);
                s = " after waiting for ";
            }
            spotsSemaphore.acquire();
            long endTime = System.currentTimeMillis();
            long waitingTime = (endTime - startTime + 999) / 1000;
            if (!s.isEmpty()) {
                s += waitingTime + " units of time";
            }
            String log2 = "%s from Gate %d parked%s. (Parking Status: %d spots occupied)".formatted(getName(), gateNumber, s, spots.occupiedSpots());
            writeLog(log2);
            sleep(parks * 1000);
            spotsSemaphore.release();
            String log = "%s from Gate %d left after %d units of time. (Parking Status: %d spots occupied)".formatted(getName(), gateNumber, parks, spots.occupiedSpots());
            writeLog(log);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void writeLog(String log) {
        System.out.println(log);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
            writer.write(log);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

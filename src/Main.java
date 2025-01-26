import parkingComponents.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws InterruptedException {
        List<String[]> lst = new ArrayList<>();
        Spots parkingSpots = new Spots();
        Gate gate1, gate2, gate3;
        List<Car> gateCars1 = new ArrayList<>();
        List<Car> gateCars2 = new ArrayList<>();
        List<Car> gateCars3 = new ArrayList<>();
        String filePath = "input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lst.add(line.split(", "));
            }

            for (String[] stringsArray : lst) {
                int gateNumber, carNumber, arrive, parks;

                gateNumber = Integer.parseInt(stringsArray[0].split(" ")[1]);
                carNumber = Integer.parseInt(stringsArray[1].split(" ")[1]);
                arrive = Integer.parseInt(stringsArray[2].split(" ")[1]);
                parks = Integer.parseInt(stringsArray[3].split(" ")[1]);

                Car car = new Car(carNumber, arrive, parks, parkingSpots);
                switch (gateNumber) {
                    case 1:
                        car.setGateNumber(gateNumber);
                        gateCars1.add(car);
                        break;
                    case 2:
                        car.setGateNumber(gateNumber);
                        gateCars2.add(car);
                        break;
                    case 3:
                        car.setGateNumber(gateNumber);
                        gateCars3.add(car);
                        break;
                    default:
                        String log = "Invalid gate Number";
                        System.out.println(log);
                        writer.write(log);
                        writer.newLine();
                        break;
                }
            }

            gate1 = new Gate(1, gateCars1);
            gate2 = new Gate(2, gateCars2);
            gate3 = new Gate(3, gateCars3);

            gate1.start();
            gate2.start();
            gate3.start();

            gate1.join();
            gate2.join();
            gate3.join();


            List<Car> allCars = new ArrayList<>();

            allCars.addAll(gateCars1);
            allCars.addAll(gateCars2);
            allCars.addAll(gateCars3);

            for (Car car : allCars) {
                car.join();
            }

            String totalCarsServed = "Total Cars Served: " + (gate1.getCarsServed() + gate2.getCarsServed() + gate3.getCarsServed());
            String currentCarsInParking = "Current Cars in Parking: " + parkingSpots.occupiedSpots();
            String details = "Details: ";
            String gate1Details = "- %s served %d cars".formatted(gate1.getName(), gate1.getCarsServed());
            String gate2Details = "- %s served %d cars".formatted(gate2.getName(), gate2.getCarsServed());
            String gate3Details = "- %s served %d cars".formatted(gate3.getName(), gate3.getCarsServed());

            System.out.println("...");
            writer.write("...");
            writer.newLine();
            
            System.out.println(totalCarsServed);
            writer.write(totalCarsServed);
            writer.newLine();

            System.out.println(currentCarsInParking);
            writer.write(currentCarsInParking);
            writer.newLine();

            System.out.println(details);
            writer.write(details);
            writer.newLine();

            System.out.println(gate1Details);
            writer.write(gate1Details);
            writer.newLine();

            System.out.println(gate2Details);
            writer.write(gate2Details);
            writer.newLine();

            System.out.println(gate3Details);
            writer.write(gate3Details);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

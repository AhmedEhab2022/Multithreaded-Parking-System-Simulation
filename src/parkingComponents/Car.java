package parkingComponents;

public class Car extends Thread {
    private static int carNumber = 0;
    private int arrive;
    private int parks;

    public Car(int arrive, int parks) {
        super();
        this.arrive = arrive;
        this.parks = parks;
    }

    public void park() {
        try {
            sleep(parks);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

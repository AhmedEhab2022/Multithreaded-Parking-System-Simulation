package parkingComponents;

public class Spots {
    private final int spotsNumber = 4;
    private final CustomSemaphore parkingSpots = new CustomSemaphore(4);

    public CustomSemaphore getSpots() {
        return parkingSpots;
    }

    public int occupiedSpots() {
        return spotsNumber - parkingSpots.availablePermits();
    }
}

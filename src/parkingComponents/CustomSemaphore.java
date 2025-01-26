package parkingComponents;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomSemaphore {
    private final int maxPermits;
    private int permits;
    private final Queue<Thread> waitingQueue;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public CustomSemaphore(int permits) {
        this.permits = permits;
        this.maxPermits = permits;
        this.waitingQueue = new LinkedList<>();
    }

    public void acquire() throws InterruptedException {
        lock.lock(); // Acquire the lock to enter critical section
        try {
            Thread currentThread = Thread.currentThread();
            while (permits <= 0) {
                waitingQueue.add(currentThread);
                condition.await(); // Wait until a permit is available
            }
            permits--; // Occupy a permit
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public void release() {
        lock.lock(); // Acquire the lock to enter critical section
        try {
            permits++; // Release a permit
            if (!waitingQueue.isEmpty()) {
                waitingQueue.poll(); // Remove the thread from the queue
                condition.signal(); // Signal the next waiting thread
            }
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public int availablePermits() {
        lock.lock(); // Acquire the lock to safely read the permits
        try {
            return permits; // Return the number of available permits
        } finally {
            lock.unlock(); // Release the lock
        }
    }
}

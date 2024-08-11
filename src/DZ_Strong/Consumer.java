import java.util.Random;

public class Consumer extends Thread {
    private final Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(new Random().nextInt(500));  // Simulate buying time
                store.consume();
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted.");
        }
    }
}

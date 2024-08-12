package DZ_Strong;

public class Store {
    private int products = 0;
    private static final int MAX_PRODUCTS = 3;

    public synchronized void produce(Product product) throws InterruptedException {
        while (products >= MAX_PRODUCTS) {
            System.out.println("Store is full. Producer is waiting.");
            wait();
        }
        products++;
        System.out.println("Producer added a product: " + product + ". Total products: " + products);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (products <= 0) {
            System.out.println("Store is empty. Consumer is waiting.");
            wait();
        }
        products--;
        System.out.println("Consumer bought a product. Total products: " + products);
        notify();
    }
}
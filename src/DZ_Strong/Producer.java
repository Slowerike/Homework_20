import java.util.Random;

class Producer extends Thread {
    private Store store;
    private static final Random random = new Random();

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(random.nextInt(500));  // Simulate production time
                Product product = createRandomProduct();
                store.produce(product);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Product createRandomProduct() {
        ModelOfCar model = ModelOfCar.values()[random.nextInt(ModelOfCar.values().length)];
        ColorOfCar color = ColorOfCar.values()[random.nextInt(ColorOfCar.values().length)];
        int year = 2000 + random.nextInt(25);  // Year between 2000 and 2024
        int price = 10000 + random.nextInt(50000);  // Price between 10000 and 60000
        return new Product(price, model, year, color);
    }
}

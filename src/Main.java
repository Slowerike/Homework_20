import DZ_Strong.Consumer;
import DZ_Strong.Producer;
import DZ_Strong.Store;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
         Задача 1:
         Пользователь вводит с клавиатуры значение в массив array0fInt. После чего запускаются два
         потока. Первый поток находит максимум в массиве, второй — минимум. Результаты
         вычислений возвращаются в метод main().
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите числа массива,через запятую:");
        String line = scanner.nextLine().trim();
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
        Thread t1 = new Thread(() -> {
            Optional<Integer> resultMax = arrayList.stream().max(Comparator.naturalOrder());
            if (resultMax.isPresent()) {
                System.out.println("Максимальное значение: " + resultMax.get());
            } else {
                System.out.println("Массив пуст.");
            }
        });
        t1.start();
        t1.join();
        scanner.close();
        Thread t2 = new Thread(() -> {
            Optional<Integer> resultMin = arrayList.stream().min(Comparator.naturalOrder());
            if (resultMin.isPresent()) {
                System.out.println("Минимальное значение: " + resultMin.get());
            } else {
                System.out.println("Массив пуст.");
            }
        });
        t2.start();
        t2.join();
        /*
         Задача 2:
         Сортировка массива цифр в нескольких потоках различными алгоритмами:
          сортировка вставками;
          сортировка выбором;
          сортировка пузырьком.
         Каждый вид сортировки должен запускаться в отдельном потоке. После вывести
         результат отсортированных массивов в консоль.
         */
        SortThread sortThread = new SortThread(arrayList);
        Thread insertionSort = new Thread(() -> System.out.println(Thread.currentThread() + " " + sortThread.insertionSort()));
        insertionSort.start();
        Thread selectionSort = new Thread(() -> System.out.println(Thread.currentThread() + " " + sortThread.selectionSort()));
        selectionSort.start();
        Thread bubbleSort = new Thread(() -> System.out.println(Thread.currentThread() + " " + sortThread.bubbleSorted()));
        bubbleSort.start();
        /*
         Задача *:
         Имеются сущности Магазин, Производитель, Покупатель. Цель задачи сделать так, чтобы
         производитель произвел 5 единиц продукта, а покупатель их купил. Пока производитель
         не произвел продукт, покупатель не может его купить. При этом одновременно в магазине
         может находиться не более 3 товаров
         */
        Store store = new Store();

        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.interrupt();  // Останавливаем потребителя после завершения производства
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Production finished.");
    }
}
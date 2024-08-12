import java.util.ArrayList;

public class SortThread  {
    private  volatile ArrayList<Integer> arrayList;

    public SortThread(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }
    public synchronized ArrayList<Integer> bubbleSorted(){
        ArrayList<Integer> arrayList1 = new ArrayList<>(arrayList);
        int n = arrayList1.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrayList1.get(j) > arrayList1.get(j + 1)) {
                    // Swap elements
                    int temp = arrayList1.get(j);
                    arrayList1.set(j, arrayList1.get(j + 1));
                    arrayList1.set(j + 1, temp);
                }
            }
        }
        return arrayList1;
    }
    public synchronized ArrayList<Integer> selectionSort(){
        ArrayList<Integer> list= new ArrayList<>(arrayList);
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            // Найти индекс минимального элемента в оставшейся части массива
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(minIndex)) {
                    minIndex = j;
                }
            }
            // Обмен элементов
            if (minIndex != i) {
                int temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
        return list;
    }
    public  synchronized ArrayList<Integer> insertionSort(){
        ArrayList<Integer> list= new ArrayList<>(arrayList);
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            // Перемещение элементов, которые больше ключа, на одну позицию вперед
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            // Вставка ключа в правильное место
            list.set(j + 1, key);
        }
        return list;
    }
}

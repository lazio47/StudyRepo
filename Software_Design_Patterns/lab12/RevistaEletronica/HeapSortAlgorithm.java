import java.util.Comparator;
import java.util.List;

public class HeapSortAlgorithm extends SortingAlgorithm {
    public HeapSortAlgorithm(SortingFilter sortingFilter, SortingOrder sortingOrder) {
        super(sortingFilter, sortingOrder);
    }

    private static <T> void heapSort(List<T> list, Comparator<? super T> comparator) {
        int n = list.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i, comparator);
        }

        for (int i = n - 1; i >= 0; i--) {
            T temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);
            heapify(list, i, 0, comparator);
        }
    }

    private static <T> void heapify(List<T> list, int n, int i, Comparator<? super T> comparator) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparator.compare(list.get(left), list.get(largest)) > 0) {
            largest = left;
        }

        if (right < n && comparator.compare(list.get(right), list.get(largest)) > 0) {
            largest = right;
        }

        if (largest != i) {
            T swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);
            heapify(list, n, largest, comparator);
        }
    }

    @Override
    public void sort(List<Smartphone> smartphones) {
        Comparator<Smartphone> comparator;
        switch (getSortingFilter()) {
            case RAM:
                comparator = Comparator.comparingInt(Smartphone::getMemory);
                break;
            case PRICE:
                comparator = Comparator.comparingDouble(Smartphone::getPrice);
                break;
            case DESCRIPTION:
                comparator = Comparator.comparing(Smartphone::getDescription);
                break;
            default:
                throw new IllegalArgumentException("Unknown sorting filter");
        }

        if (getSortingOrder() == SortingOrder.DESCENDING) {
            comparator = comparator.reversed();
        }

        long startTime = System.nanoTime(); // Início da contagem do tempo
        heapSort(smartphones, comparator);
        long endTime = System.nanoTime(); // Fim da contagem do tempo
        long elapsedTimeNanos = endTime - startTime; // Tempo decorrido em nanossegundos

        // Impressão do tempo decorrido em nanossegundos
        System.out.println("Tempo decorrido (HeapSort): " + elapsedTimeNanos + " nanossegundos");
    }
}

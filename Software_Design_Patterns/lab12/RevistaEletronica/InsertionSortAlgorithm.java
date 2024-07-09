import java.util.Comparator;
import java.util.List;

public class InsertionSortAlgorithm extends SortingAlgorithm {
    public InsertionSortAlgorithm(SortingFilter sortingFilter, SortingOrder sortingOrder) {
        super(sortingFilter, sortingOrder);
    }

    private static <T> void insertionSort(List<T> list, Comparator<? super T> comparator) {
        long startTime = System.nanoTime(); // Início da contagem do tempo

        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }

        long endTime = System.nanoTime(); // Fim da contagem do tempo
        long elapsedTimeNanos = endTime - startTime; // Tempo decorrido em nanossegundos

        // Impressão do tempo decorrido em nanossegundos
        System.out.println("Tempo decorrido (InsertionSort): " + elapsedTimeNanos + " nanossegundos");
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

        insertionSort(smartphones, comparator);
    }
}

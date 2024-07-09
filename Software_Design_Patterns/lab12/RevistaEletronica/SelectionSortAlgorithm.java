import java.util.Comparator;
import java.util.List;

public class SelectionSortAlgorithm extends SortingAlgorithm {
    public SelectionSortAlgorithm(SortingFilter sortingFilter, SortingOrder sortingOrder) {
        super(sortingFilter, sortingOrder);
    }

    private static <T> void selectionSort(List<T> list, Comparator<? super T> comparator) {
        long startTime = System.nanoTime(); // Início da contagem do tempo

        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }

        long endTime = System.nanoTime(); // Fim da contagem do tempo
        long elapsedTime = endTime - startTime; // Tempo decorrido em nanossegundos

        // Impressão do tempo decorrido
        System.out.println("Tempo decorrido: " + elapsedTime + " nanossegundos");
        // Em milissegundos o tempo era zero, então foi convertido para nanossegundos
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

        selectionSort(smartphones, comparator);
    }
}

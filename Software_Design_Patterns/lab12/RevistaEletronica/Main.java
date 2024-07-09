import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Smartphone> smartphones = new ArrayList<>();
        smartphones.add(new Smartphone(4, 500.0, "Smartphone A"));
        smartphones.add(new Smartphone(8, 700.0, "Smartphone B"));
        smartphones.add(new Smartphone(6, 600.0, "Smartphone C"));

        // Selection Sort
        SortingAlgorithm sortingAlgorithm1 = new SelectionSortAlgorithm(SortingFilter.RAM, SortingOrder.ASCENDING);
        sortingAlgorithm1.sort(smartphones);
        printSmartphones("Selection Sort - Ordenado por RAM (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm2 = new SelectionSortAlgorithm(SortingFilter.RAM, SortingOrder.DESCENDING);
        sortingAlgorithm2.sort(smartphones);
        printSmartphones("Selection Sort - Ordenado por RAM (Descendente):", smartphones);

        SortingAlgorithm sortingAlgorithm3 = new SelectionSortAlgorithm(SortingFilter.PRICE, SortingOrder.ASCENDING);
        sortingAlgorithm3.sort(smartphones);
        printSmartphones("Selection Sort - Ordenado por Preço (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm4 = new SelectionSortAlgorithm(SortingFilter.PRICE, SortingOrder.DESCENDING);
        sortingAlgorithm4.sort(smartphones);
        printSmartphones("Selection Sort - Ordenado por Preço (Descendente):", smartphones);

        SortingAlgorithm sortingAlgorithm5 = new SelectionSortAlgorithm(SortingFilter.DESCRIPTION, SortingOrder.ASCENDING);
        sortingAlgorithm5.sort(smartphones);
        printSmartphones("Selection Sort - Ordenado por Descrição (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm6 = new SelectionSortAlgorithm(SortingFilter.DESCRIPTION, SortingOrder.DESCENDING);
        sortingAlgorithm6.sort(smartphones);
        printSmartphones("Selection Sort - Ordenado por Descrição (Descendente):", smartphones);

        // Insertion Sort
        SortingAlgorithm sortingAlgorithm7 = new InsertionSortAlgorithm(SortingFilter.RAM, SortingOrder.ASCENDING);
        sortingAlgorithm7.sort(smartphones);
        printSmartphones("Insertion Sort - Ordenado por RAM (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm8 = new InsertionSortAlgorithm(SortingFilter.RAM, SortingOrder.DESCENDING);
        sortingAlgorithm8.sort(smartphones);
        printSmartphones("Insertion Sort - Ordenado por RAM (Descendente):", smartphones);

        SortingAlgorithm sortingAlgorithm9 = new InsertionSortAlgorithm(SortingFilter.PRICE, SortingOrder.ASCENDING);
        sortingAlgorithm9.sort(smartphones);
        printSmartphones("Insertion Sort - Ordenado por Preço (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm10 = new InsertionSortAlgorithm(SortingFilter.PRICE, SortingOrder.DESCENDING);
        sortingAlgorithm10.sort(smartphones);
        printSmartphones("Insertion Sort - Ordenado por Preço (Descendente):", smartphones);

        SortingAlgorithm sortingAlgorithm11 = new InsertionSortAlgorithm(SortingFilter.DESCRIPTION, SortingOrder.ASCENDING);
        sortingAlgorithm11.sort(smartphones);
        printSmartphones("Insertion Sort - Ordenado por Descrição (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm12 = new InsertionSortAlgorithm(SortingFilter.DESCRIPTION, SortingOrder.DESCENDING);
        sortingAlgorithm12.sort(smartphones);
        printSmartphones("Insertion Sort - Ordenado por Descrição (Descendente):", smartphones);

        // Heap Sort
        SortingAlgorithm sortingAlgorithm13 = new HeapSortAlgorithm(SortingFilter.RAM, SortingOrder.ASCENDING);
        sortingAlgorithm13.sort(smartphones);
        printSmartphones("Heap Sort - Ordenado por RAM (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm14 = new HeapSortAlgorithm(SortingFilter.RAM, SortingOrder.DESCENDING);
        sortingAlgorithm14.sort(smartphones);
        printSmartphones("Heap Sort - Ordenado por RAM (Descendente):", smartphones);

        SortingAlgorithm sortingAlgorithm15 = new HeapSortAlgorithm(SortingFilter.PRICE, SortingOrder.ASCENDING);
        sortingAlgorithm15.sort(smartphones);
        printSmartphones("Heap Sort - Ordenado por Preço (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm16 = new HeapSortAlgorithm(SortingFilter.PRICE, SortingOrder.DESCENDING);
        sortingAlgorithm16.sort(smartphones);
        printSmartphones("Heap Sort - Ordenado por Preço (Descendente):", smartphones);

        SortingAlgorithm sortingAlgorithm17 = new HeapSortAlgorithm(SortingFilter.DESCRIPTION, SortingOrder.ASCENDING);
        sortingAlgorithm17.sort(smartphones);
        printSmartphones("Heap Sort - Ordenado por Descrição (Ascendente):", smartphones);

        SortingAlgorithm sortingAlgorithm18 = new HeapSortAlgorithm(SortingFilter.DESCRIPTION, SortingOrder.DESCENDING);
        sortingAlgorithm18.sort(smartphones);
        printSmartphones("Heap Sort - Ordenado por Descrição (Descendente):", smartphones);
    }

    private static void printSmartphones(String message, List<Smartphone> smartphones) {
        System.out.println(message);
        for (Smartphone smartphone : smartphones) {
            System.out.println(smartphone);
        }
        System.out.println();
    }
}

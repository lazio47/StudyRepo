import java.util.List;

public abstract class SortingAlgorithm {
    private SortingFilter sortingFilter;
    private SortingOrder sortingOrder;

    public SortingAlgorithm(SortingFilter sortingFilter, SortingOrder sortingOrder) {
        this.sortingFilter = sortingFilter;
        this.sortingOrder = sortingOrder;
    }

    public abstract void sort(List<Smartphone> smartphones);

    public SortingFilter getSortingFilter() {
        return sortingFilter;
    }

    public SortingOrder getSortingOrder() {
        return sortingOrder;
    }
}

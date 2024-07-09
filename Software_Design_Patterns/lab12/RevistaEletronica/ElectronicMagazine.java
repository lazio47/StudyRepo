import java.util.ArrayList;

// Variables and classes names changed to english for convenience of the provided code

public class ElectronicMagazine {
    private SortingAlgorithm sortingAlgorithm;
    private SortingFilter sortingFilter;
    private ArrayList<Smartphone> smartphones;

    public ElectronicMagazine(SortingAlgorithm sortingAlgorithm, SortingFilter sortingFilter, ArrayList<Smartphone> smartphones) {
        this.sortingAlgorithm = sortingAlgorithm;
        this.sortingFilter = sortingFilter;
        this.smartphones = smartphones;
    }

    public ArrayList<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(ArrayList<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }

    public SortingAlgorithm getSortingAlgorithm() {
        return sortingAlgorithm;
    }
    
    public void setSortingAlgorithm(SortingAlgorithm sortingAlgorithm) {
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public SortingFilter getSortingFilter() {
        return sortingFilter;
    }
    
    public void setSortingFilter(SortingFilter sortingFilter) {
        this.sortingFilter = sortingFilter;
    }

    public void sort() {
        this.sortingAlgorithm.sort(this.smartphones);
    }

    public void print() {
        for (Smartphone smartphone : this.smartphones) {
            System.out.println(smartphone);
        }
    }
}
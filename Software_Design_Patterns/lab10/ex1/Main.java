import java.util.ListIterator;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        
        VectorGeneric<Integer> vector = new VectorGeneric<>();

        vector.addElem(10);
        vector.addElem(20);
        vector.addElem(30);

       
        System.out.println("Test Iterator:");
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        
        System.out.println("\nTest ListIterator (forward):");
        ListIterator<Integer> listIteratorForward = vector.listIterator();
        while (listIteratorForward.hasNext()) {
            System.out.println(listIteratorForward.next());
        }

        
        System.out.println("\nTest ListIterator (backward):");
        ListIterator<Integer> listIteratorBackward = vector.listIterator(vector.totalElem());
        while (listIteratorBackward.hasPrevious()) {
            System.out.println(listIteratorBackward.previous());
        }

        
        System.out.println("\nTest Multiple Iterators:");
        Iterator<Integer> iterator1 = vector.iterator();
        Iterator<Integer> iterator2 = vector.iterator();
        while (iterator1.hasNext()) {
            System.out.println("Iterator 1: " + iterator1.next());
            System.out.println("Iterator 2: " + iterator2.next());
        }
    }
}

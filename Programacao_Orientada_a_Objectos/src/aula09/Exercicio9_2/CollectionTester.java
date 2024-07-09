package aula09.Exercicio9_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class CollectionTester {
    public static void main(String[] args) { 
        int DIM = 5000; 
     
        Collection<Integer> col = new ArrayList<>();
        Collection<Integer> col1 = new LinkedList<>(); 
        Collection<Integer> col2 = new HashSet<>(); 
        Collection<Integer> col3 = new TreeSet<>(); 
        System.out.println("Desempenho do ArrayList: ");  
        checkPerformance(col, DIM);
        System.out.println("");
        System.out.println("Desempenho do LinkedList: ");  
        checkPerformance(col1, DIM);
        System.out.println(""); 
        System.out.println("Desempenho do HashSet: "); 
        checkPerformance(col2, DIM);
        System.out.println(""); 
        System.out.println("Desempenho do TreeSet: "); 
        checkPerformance(col3, DIM);
        System.out.println(""); 
        System.out.println("____________________________________________________________________________________________");
        System.out.printf("%-15s %10d %10d %10d %10d %10d %10d\n","Collection", 1000, 5000, 10000, 20000, 40000, 100000);
        printCheckPerformance(col);
        printCheckPerformance(col1);
        printCheckPerformance(col2);
        printCheckPerformance(col3);
        System.out.println("____________________________________________________________________________________________");

      } 
     
      private static void checkPerformance(Collection<Integer> col, int DIM) { 
        double start, stop, delta;     // Add 
        start = System.nanoTime(); // clock snapshot before 
        for(int i=0; i<DIM; i++ ) 
          col.add( i ); 
        stop = System.nanoTime();  // clock snapshot after 
        delta = (stop-start)/1e6; // convert to milliseconds 
        System.out.println(col.size()+ ": Add to " + 
          col.getClass().getSimpleName() +" took " + delta + "ms");     // Search 
        start = System.nanoTime(); // clock snapshot before 
        for(int i=0; i<DIM; i++ ) { 
          int n = (int) (Math.random()*DIM); 
          if (!col.contains(n))  
            System.out.println("Not found???"+n); 
        } 
        stop = System.nanoTime();  // clock snapshot after 
        delta = (stop-start)/1e6; // convert nanoseconds to milliseconds 
        System.out.println(col.size()+ ": Search to " +  
          col.getClass().getSimpleName() +" took " + delta + "ms");     // Remove 
        start = System.nanoTime(); // clock snapshot before 
        Iterator<Integer> iterator = col.iterator(); 
        while (iterator.hasNext()) { 
            iterator.next(); 
            iterator.remove(); 
        } 
        stop = System.nanoTime();  // clock snapshot after 
        delta = (stop-start)/1e6; // convert nanoseconds to milliseconds 
        System.out.println(col.size() + ": Remove from "+  
          col.getClass().getSimpleName() +" took " + delta + "ms"); 
      }

      private static void printCheckPerformance(Collection<Integer> col) { 
        System.out.println(col.getClass().getSimpleName());
        double start, stop, delta;     // Add 
        ArrayList<Double> deltaAdd = new ArrayList<>();
        ArrayList<Double> deltaSearch = new ArrayList<>();
        ArrayList<Double> deltaRemove = new ArrayList<>();

        int[] num = {1000, 5000, 10000, 20000, 40000, 100000};
        for(int j=0; j<6; j++){
            start = System.nanoTime(); // clock snapshot before 
            for(int i=0; i<num[j]; i++ ) 
                col.add( i ); 
            stop = System.nanoTime();  // clock snapshot after 
            delta = (stop-start)/1e6;// convert to milliseconds 
            deltaAdd.add(j, delta);

            start = System.nanoTime(); // clock snapshot before 
            for(int i=0; i<num[j]; i++ ) { 
            int n = (int) (Math.random()*num[j]); 
            if (!col.contains(n))  
                System.out.println("Not found???"+n); 
            } 
            stop = System.nanoTime();  // clock snapshot after
            delta = (stop-start)/1e6;// convert to milliseconds 
            deltaSearch.add(j, delta); // convert nanoseconds to milliseconds 

            start = System.nanoTime(); // clock snapshot before 
            Iterator<Integer> iterator = col.iterator(); 
            while (iterator.hasNext()) { 
                iterator.next(); 
                iterator.remove(); 
            } 
            stop = System.nanoTime();  // clock snapshot after 
            delta = (stop-start)/1e6;// convert to milliseconds
            deltaRemove.add(j, delta); // convert nanoseconds to milliseconds 
        }
        System.out.printf("%-15s %10.1f %10.1f %10.1f %10.1f %10.1f %10.1f\n","add", deltaAdd.get(0), deltaAdd.get(1), deltaAdd.get(2), deltaAdd.get(3), deltaAdd.get(4), deltaAdd.get(5));
        System.out.printf("%-15s %10.1f %10.1f %10.1f %10.1f %10.1f %10.1f\n","search", deltaSearch.get(0), deltaSearch.get(1), deltaSearch.get(2), deltaSearch.get(3), deltaSearch.get(4), deltaSearch.get(5));
        System.out.printf("%-15s %10.1f %10.1f %10.1f %10.1f %10.1f %10.1f\n","remove", deltaRemove.get(0), deltaRemove.get(1), deltaRemove.get(2), deltaRemove.get(3), deltaRemove.get(4), deltaRemove.get(5));

        
      }
}

package aula09.Exercicio9_1;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import aula05.DateYMD;
import aula09.Exercicio9_1.Exercício6_1.Pessoa;
import aula09.Exercicio9_1.Exercicio7_2.*;
 
public class ALDemo { 
 
  public static void main(String[] args) { 
    ArrayList<Integer> c1 = new ArrayList<>(); 
    for (int i = 10; i <= 100; i+=10)  
      c1.add(i); 
    System.out.println("Size: " + c1.size()); 
    for (int i = 0; i < c1.size(); i++)  
      System.out.println("Elemento: " + c1.get(i)); 
     
    ArrayList<String> c2 = new ArrayList<>(); 
    c2.add("Vento"); 
    c2.add("Calor"); 
    c2.add("Frio"); 
    c2.add("Chuva"); 
    System.out.println(c2); 
    Collections.sort(c2); 
    System.out.println(c2); 
    c2.remove("Frio");  
    c2.remove(0); 
    System.out.println(c2); 



    // a)


    c1.add(47);
    System.out.println("c2 contem Chuva? "+c2.contains("Chuva"));
    System.out.println("Índice de 47 em c1: "+c1.indexOf(47));
    c2.add("Chuva");
    System.out.println("Último índice de Chuva em c1 é: "+c2.lastIndexOf("Chuva"));
    System.out.println(c2);
    c2.set(1, "Neve");
    System.out.println(c2);
    System.out.println(c1.subList(0, 5));


    // b)

    Set<Pessoa> c3 = new HashSet<>();

    Pessoa p1 = new Pessoa("Jõao", 1111, new DateYMD(10, 12, 2020));
    Pessoa p2 = new Pessoa("Maria", 2222, new DateYMD(11, 12, 2020));
    Pessoa p3 = new Pessoa("Paulo", 3333, new DateYMD(12, 12, 2020));
    Pessoa p4 = new Pessoa("Virginia", 4444, new DateYMD(13, 12, 2020));
    Pessoa p5 = new Pessoa("Rafael", 5555, new DateYMD(14, 12, 2020));
    c3.add(p1);
    c3.add(p2);
    c3.add(p2);
    c3.add(p2);
    c3.add(p3);
    c3.add(p4);
    c3.add(p4);
    c3.add(p5);

    Iterator<Pessoa> itr = c3.iterator();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }

    // c)

    Set<aula09.Exercicio9_1.Exercicio7_2.Date> c4 = new TreeSet<>();
    aula09.Exercicio9_1.Exercicio7_2.Date d1 = new aula09.Exercicio9_1.Exercicio7_2.DateYMD(3, 1, 2000);
    aula09.Exercicio9_1.Exercicio7_2.Date d2 = new aula09.Exercicio9_1.Exercicio7_2.DateYMD(5, 1, 2000);
    aula09.Exercicio9_1.Exercicio7_2.Date d3 = new aula09.Exercicio9_1.Exercicio7_2.DateYMD(6, 1, 2000);
    aula09.Exercicio9_1.Exercicio7_2.Date d4 = new aula09.Exercicio9_1.Exercicio7_2.DateYMD(9, 1, 2000);
    aula09.Exercicio9_1.Exercicio7_2.Date d5 = new aula09.Exercicio9_1.Exercicio7_2.DateYMD(12, 1, 2000);

    c4.add(d2);
    c4.add(d5);
    c4.add(d1);
    c4.add(d3);
    c4.add(d4);

    System.out.println(c4);

  } 

 
}

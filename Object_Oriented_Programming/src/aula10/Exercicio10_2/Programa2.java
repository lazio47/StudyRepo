package aula10.Exercicio10_2;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

import aula10.Book;

public class Programa2 {
    public static void main(String[] args) {
        Map<String, ArrayList<Book>> livros = new TreeMap<String, ArrayList<Book>>();

        Book b1 = new Book("Thermodynamics: An Engineering Approach", "Yunus Cengel", 2006);
        Book b2 = new Book("Fluid Mechanics: Fundamentals and Applications", "Yunus Cengel", 2004);
        Book b3 = new Book("Fundamentals of Fluid Mechanics", "Yunus Cengel", 2001);
        Book b4 = new Book("Heat Transfer: A pratical Aproach", "Yunus Cengel", 1997);
        Book b5 = new Book("A Culpa É das Estrelas", "John Green", 2012);
        Book b6 = new Book("PS, Eu Te Amo", "Cecelia Ahern", 2004);
        Book b7 = new Book("Assim Falou Zaratustra", "Friedrich Nietzsche", 1885);
        Book b8 = new Book("Crítica da Razão Pura", "Immanuel Kant", 1787);
        Book b9 = new Book("Meu Sistema", "Aaron Nimzowitsch", 1925);
        Book b10 = new Book("My 60 Memorable Games", "Bobby Fischer", 1969);

        livros.put("Termoenergética",new ArrayList<Book>());
        livros.put("Mecanica de Fluidos",new ArrayList<Book>());
        livros.put("Romance",new ArrayList<Book>());
        livros.put("Filosofia",new ArrayList<Book>());
        livros.put("Xadrez",new ArrayList<Book>());
        
        livros.get("Termoenergética").add(b1);
        livros.get("Mecanica de Fluidos").add(b2);
        livros.get("Romance").add(b6);
        livros.get("Filosofia").add(b8);
        livros.get("Xadrez").add(b10);

        int t=4;
        String chave="";
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("1.Adicionar ");
            System.out.println("2.Alterar ");
            System.out.println("3.Remover ");
            System.out.println("4.Listar ");
            System.out.println("5.Print aleatorio ");
            System.out.println("0.Sair ");
            t = sc.nextInt();
            sc.nextLine();
            switch(t){
                case 1:
                    System.out.println("Género: ");
                    String genero = sc.nextLine();
                    System.out.println("--Book--");
                    System.out.println("Title:");
                    String title = sc.nextLine();
                    System.out.println("Author:");
                    String author = sc.nextLine();
                    System.out.println("Year:");
                    int year = sc.nextInt();
                    Book book = new Book(title, author, year);
                    livros.get(genero).add(book);;
                    break;
                case 2:
                    System.out.println("Género: ");
                    genero = sc.nextLine();
                    System.out.println("--Book--");
                    System.out.println("Title:");
                    title = sc.nextLine();
                    System.out.println("Author:");
                    author = sc.nextLine();
                    System.out.println("Year:");
                    year = sc.nextInt();
                    book = new Book(title, author, year);
                    livros.get(genero).add(book);;
                    break;
                case 3:
                    System.out.println("Género: ");
                    genero = sc.nextLine();
                    livros.remove(genero);
                case 4:
                System.out.println(livros.toString());
                for (Entry<String, ArrayList<Book>> set : livros.entrySet()) {
               System.out.println(set.getKey());
                }
                for (Entry<String, ArrayList<Book>> set : livros.entrySet()) {
                    System.out.println(set.getValue());
                }
                break;
                case 5:
                int i=0;
                int p = getRandomNumberUsingNextInt(0, livros.size());
                    for (Entry<String, ArrayList<Book>> set : livros.entrySet()) {
                        if(i==p){chave = set.getKey();}
                        i++;
                        }
                    System.out.println(livros.get(chave).get(getRandomNumberUsingNextInt(0, livros.get(chave).size())));
                break;
                case 0:
                    System.out.println("A sair...");
                default:
                    System.out.println("Opção inválida!");
            }
        } while(t!=0);
        sc.close();


    }
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}

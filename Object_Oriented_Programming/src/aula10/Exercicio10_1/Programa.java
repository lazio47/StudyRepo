package aula10.Exercicio10_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import aula10.Book;

public class Programa {
    public static void main(String[] args) {
        Map<String, Book> livros = new HashMap<String, Book>();
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
        
        livros.put("Termoenergética", b1);
        livros.put("Mecanica de Fluidos", b2);
        livros.put("Romance", b6);
        livros.put("Filosofia", b8);
        livros.put("Xadrez", b10);
        
        int t=4;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("1.Adicionar ");
            System.out.println("2.Alterar ");
            System.out.println("3.Remover ");
            System.out.println("4.Listar ");
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
                    livros.put(genero, book);
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
                    livros.replace(genero, book);
                    break;
                case 3:
                    System.out.println("Género: ");
                    genero = sc.nextLine();
                    livros.remove(genero);
                case 4:
                System.out.println(livros.toString());
                for (Entry<String, Book> set : livros.entrySet()) {
               System.out.println(set.getKey());
                }
                for (Entry<String, Book> set : livros.entrySet()) {
                    System.out.println(set.getValue());
                }
                break;
                case 0:
                    System.out.println("A sair...");
                default:
                    System.out.println("Opção inválida!");
            }
        } while(t!=0);
        sc.close();


    }
   
}

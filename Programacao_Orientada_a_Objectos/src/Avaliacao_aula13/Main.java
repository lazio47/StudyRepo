package Avaliacao_aula13;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        manager.load("contactos.txt");
        Scanner sc = new Scanner(System.in);
        int escolha;
        do{
            System.out.println("1.adicionar contacto ");
            System.out.println("2.modificar contacto ");
            System.out.println("3.apagar contacto ");
            System.out.println("4.procurar contacto por nome ");
            System.out.println("5.procurar contacto por telemovel ");
            System.out.println("6.procurar contacto por e-mail ");
            System.out.println("7.Listar contactos ");
            System.out.println("8.Listar contactos ordenados por nome ");
            System.out.println("9.Listar contactos ordenados por telemovel ");
            System.out.println("10.Listar contactos ordenados por e-mail ");
            System.out.println("11.Listar contactos ordenados por data de nascimento ");
            System.out.println("12.fechar");
            escolha = sc.nextInt();
            sc.nextLine();
            switch(escolha){
                case 1:
                    System.out.println("Nome: ");
                    String n = sc.nextLine();
                    System.out.println("Phone number: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Email: ");
                    String e = sc.nextLine();
                    System.out.println("Data de nascimento [yyyy-mm-dd]: ");
                    String d = sc.nextLine();
                    manager.addContact(new Contact(n, p, e, LocalDate.parse(d)));
                    break;
                case 2:
                    System.out.println("Id: ");
                    int i = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Nome: ");
                    String no = sc.nextLine();
                    System.out.println("Phone number: ");
                    int po = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Email: ");
                    String em = sc.nextLine();
                    System.out.println("Data de nascimento [yyyy-mm-dd]: ");
                    String da = sc.nextLine();
                    Contact cont = new Contact(no, po, em, LocalDate.parse(da));
                    cont.setId(i); 
                    manager.addContact(cont);
                    break;
                case 3:
                    System.out.println("Id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    manager.removeContact(manager.manager.get(id));
                    break;
                case 4:
                    System.out.println("Insira o nome ");
                    String nome = sc.nextLine();
                    System.out.println(manager.searchContactByname(nome).toString());
                    break;
                case 5:
                    System.out.println("Insira o telemovel ");
                    int tel = sc.nextInt();
                    sc.nextLine();
                    System.out.println(manager.searchContactByPhoneNumber(tel).toString());
                    break;
                case 6:
                    System.out.println("Insira o email ");
                    String mail = sc.nextLine();
                    System.out.println(manager.searchContactByEmail(mail).toString());
                    break;
                case 7:
                    manager.listAllContacts();
                    break;
                case 8: 
                    manager.listContactsByName();
                    break;
                case 9:
                    manager.listContactsByPhoneNumber();
                    break;
                case 10:
                    manager.listContactsByEmail();
                    break;
                case 11:
                    manager.listContactsByBirthDate();
                case 12:
                    manager.save("contactos.txt");
                    System.out.println("A encerrar!");

            }
        }while(escolha!=12);
        sc.close();
    }
}

package aula06.Exercício6_2;

import java.util.ArrayList;
import java.util.Scanner;

import aula05.DateYMD;
import aula06.Exercício6_1.Pessoa;

public class Test {
    public static void main(String[] args) {
        ArrayList<Contacto> c = new ArrayList<>();
        int option;
        int cont = 1;
        Scanner sc = new Scanner(System.in);
        do{
        System.out.println("1. Inserir contacto");
        System.out.println("2. Alterar contacto");
        System.out.println("3. Apagar contacto");
        System.out.println("4. Procurar contacto");
        System.out.println("5. Listar contacto");
        System.out.println("0. Sair");
        option = sc.nextInt();
        if(option==1){
            Contacto NCont = Dados(cont);
            if(existe(c, NCont.getPessoa())){
                System.out.println("Quer adicionar como novo contacto?[S/N] ");
                String resp = sc.nextLine();
                if(resp=="S"){
                    c.add(NCont);
                }
            }else{
            c.add(NCont);}
        }else if(option == 2||option==3||option==4){
            System.out.println("Pesquisa por Nome: ");
            //System.out.println("1 Número");
            //System.out.println("2 Nome ");
            //int option2 = sc.nextInt();
            String nom = sc.next();
                for(Contacto conta : c) {
                    if(conta.getPessoa().getName()==nom){
                        System.out.println(cont);
                    }
                }
            
                System.out.println("Indica o ID: ");
                int idfind = sc.nextInt();
                for(Contacto conta : c) {
                    if(conta.getID()==idfind){
                        if(option==2){
                            conta = Dados(idfind);
                        }else if(option==3){
                            c.remove(conta);
                        }else if(option==4){
                            System.out.println(conta);
                        }
                    }
                }
            
        }else if(option==5){
            for(Contacto conta : c){
                System.out.println(conta);
            }
        }
        cont++;
        }while (option!=0);
        sc.close();
    }
    public static boolean existe(ArrayList<Contacto> list, Pessoa p){
        for(Contacto cont : list) {
            if(cont.getPessoa()==p){
                return true;
            }
        }
        return false;
    }
    public static boolean existeNome(ArrayList<Contacto> list, String n){
        for(Contacto cont : list) {
            if(cont.getPessoa().getName()==n){
                return true;
            }
        }
        return false;
    }
    public static Contacto Dados(int id){
        Scanner sc = new Scanner(System.in);
        System.out.println("Pessoa ");
        System.out.println("Nome: ");
        String N = sc.next();
        System.out.println("CC: ");
        int cc = sc.nextInt();
        System.out.println("Informações de Nascimento: ");
        System.out.println("Dia: ");
        int dia = sc.nextInt();
        System.out.println("Mês: ");
        int mes = sc.nextInt();
        System.out.println("Ano: ");
        int ano = sc.nextInt();
        System.out.println("Telemovel: ");
        int t = sc.nextInt();
        System.out.println("Email: ");
        String e = sc.next();
        Pessoa p = new Pessoa(N, cc, new DateYMD(dia, mes, ano));
        return new Contacto(id, p, t, e);
    }

    
}

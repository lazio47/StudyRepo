public class Exercicio2{
    Import java.util.Scanner;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num=0;
        int soma=0;
        for(int i = 0; i<5; i++){
            System.out.println("Introduza um numero\n");
            num = sc.nextInt();
            soma += num;
        }
        System.out.println("A soma dos positivos inseridos é: "+soma);
    }
}
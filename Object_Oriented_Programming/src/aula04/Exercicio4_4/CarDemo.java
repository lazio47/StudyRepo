package aula04.Exercicio4_4;

import java.util.Scanner;

class Car {
    public String make;
    public String model;
    public int year;
    public int kms;

    public Car(String make, String model, int year, int kms) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.kms = kms;
    }

    public void drive(int distance) {
        //TODO: acumular distância percorrida
    }

}

public class CarDemo {

    static Scanner sc = new Scanner(System.in);

    static int registerCars(Car[] cars) {
        // TODO: pede dados dos carros ao utilizador e acrescenta ao vetor
        // registo de carros termina quando o utilizador inserir uma linha vazia 
        // devolve número de carros registados
        int i = 0;
        do{
           System.out.print("Insira dados do carro (marca modelo ano quilómetros): "); 
           String novo = sc.nextLine();
           boolean teste = novo.matches("\\w{1,}\\s\\w{1,}\\s\\d{4} \\d{1,}");
           if(teste){
            String[] array = novo.split(" ");
            cars[i]= new Car(array[0], array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]));
           }
           i+=1;
           if (novo == ""){
            return i;
           }
        }while(i<10);
        return i;
   }
    static void registerTrips(Car[] cars, int numCars) {
        // TODO: pede dados das viagens ao utilizador e atualiza informação do carro
        // registo de viagens termina quando o utilizador inserir uma linha vazia
        do{
            System.out.print("Registe uma viagem no formato \"carro:distância\": ");
            String trip = sc.nextLine();
            boolean teste = trip.matches("\\d:\\d{1,}");
            if(teste){
             String[] array = trip.split(":");
             if(cars[Integer.parseInt(array[0])]!=null){
                cars[Integer.parseInt(array[0])].kms+=Integer.parseInt(array[1]);
             }
             
            }else{
                if(trip == null){break;}
                System.out.println("Dados mal formatados. Tente novamente.");
            }
             
            if (trip == ""){
             break;
            }
         }while(true);
        

    }


    static void listCars(Car[] cars) {
        System.out.println("\nCarros registados: ");
        // TODO: lista todos os carros registados
        // Exemplo de resultado
        // Carros registados: 
        // Toyota Camry, 2010, kms: 234346
        // Renault Megane Sport Tourer, 2015, kms: 32536
        for(int i=0; i<cars.length-1;i++){
            if(cars[i]!=null){
            System.out.println(cars[i].make+" "+cars[i].model+", "+cars[i].year+", kms: "+cars[i].kms);
        }else{break;}
    }
        System.out.println("\n");
    }

    public static void main(String[] args) {

        Car[] cars = new Car[10];

        int numCars = registerCars(cars);

        if (numCars>0) {
            listCars(cars);
            registerTrips(cars, numCars);
            listCars(cars);
        }

        sc.close();

    }
}
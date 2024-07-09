package aula09.Exercicio9_3;

import java.util.Scanner;

public class PlaneTester {
    public static void main(String[] args) {
        PlaneManager planeManager = new PlaneManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPlane Fleet Menu:");
            System.out.println("1. Add a plane to the fleet");
            System.out.println("2. Remove a plane from the fleet");
            System.out.println("3. Search for a plane");
            System.out.println("4. Print summary of all planes in the fleet");
            System.out.println("5. Print list of all commercial planes in the fleet");
            System.out.println("6. Print list of all military planes in the fleet");
            System.out.println("7. Print the fastest plane in the fleet");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPlane(planeManager, scanner);
                    break;
                case 2:
                    removePlane(planeManager, scanner);
                    break;
                case 3:
                    searchPlane(planeManager, scanner);
                    break;
                case 4:
                    printAllPlanes(planeManager);
                    break;
                case 5:
                    printCommercialPlanes(planeManager);
                    break;
                case 6:
                    printMilitaryPlanes(planeManager);
                    break;
                case 7:
                    printFastestPlane(planeManager);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void addPlane(PlaneManager planeManager, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Qual é o id do avião? ");
        String id = scanner.nextLine();
        System.out.println("Fabricante: ");
        String manufacturer = scanner.nextLine();
        System.out.println("Modelo: ");
        String model = scanner.nextLine();
        System.out.println("Ano: ");
        int year = scanner.nextInt();
        System.out.println("Passageiros: ");
        int maxNumOfPassengers = scanner.nextInt();
        System.out.println("Velocidade máxima: ");
        double maxSpeed = scanner.nextDouble();
        System.out.println("Tipo de avião[1. Para Comercial/ 2. Para Militar]:");
        int tipo = scanner.nextInt();
        switch(tipo){
            case 1:
                System.out.println("Numero de Tripulantes: ");
                int numOfCrewMembers = scanner.nextInt();
                planeManager.addPlane(new CommercialPlane(id, manufacturer, model, year, maxNumOfPassengers, maxSpeed, numOfCrewMembers));
                break;
            case 2:
                System.out.println("Número de Mísseis: ");
                int numMissiles = scanner.nextInt();
                planeManager.addPlane(new MilitaryPlane(id, manufacturer, model, year, maxNumOfPassengers, maxSpeed, numMissiles));
                break;
        }

    }

    private static void removePlane(PlaneManager planeManager, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Id: ");
        String id = scanner.nextLine();
        planeManager.removePlane(id);
    }

    private static void searchPlane(PlaneManager planeManager, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Id: ");
        String id = scanner.nextLine();
        System.out.println(planeManager.searchPlane(id));
    }

    private static void printAllPlanes(PlaneManager planeManager) {
        planeManager.printAllPlanes();
    }

    private static void printCommercialPlanes(PlaneManager planeManager) {
        System.out.println(planeManager.getCommercialPlanes());
    }

    private static void printMilitaryPlanes(PlaneManager planeManager) {
        System.out.println(planeManager.getMilitaryPlanes());
    }

    private static void printFastestPlane(PlaneManager planeManager) {
        System.out.println(planeManager.getFastestPlane());
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Manager implements ManagerInterface {

    HashMap<String, Flight> flights = new HashMap<>(); 
    @Override
    public ArrayList<String> addReservation(String reservation) {
        String[] parts = reservation.split(" ");
        if (parts.length != 3){
            return null;
        }
        String flightCode = parts[0];
        char classID = parts[1].charAt(0);
        if (classID != 'E' && classID != 'T'){
            System.out.println("Classe invalida.");
            return null;
        }
        int numberOfSeats = Integer.parseInt(parts[2]);
        if (numberOfSeats < 1){
            System.out.println("Numero de lugares invalido.");
            return null;
        }

        Flight flight = flights.get(flightCode);
        return flight.addFlightReservation(classID, numberOfSeats);
    }

    @Override
    public void cancelReservation(String reservation) {
        try {
        String[] parts = reservation.split(":");
        if (parts.length != 2){
            System.out.println("Formato: C CODIGO_VOO:NUMERO_RESERVA");
            return;
        }
        String flightCode = parts[0];
        int numberOfReservation = Integer.parseInt(parts[1]);
        if (!flights.containsKey(flightCode)) {
            System.out.println("Código de voo "+flightCode+" nao existe.");
            return;
        }
        flights.get(flightCode).cancelFlightReservation(numberOfReservation);
        } catch (NumberFormatException e) {
            System.out.println("Formato: C CODIGO_VOO:NUMERO_RESERVA");
        }
    }

    @Override
    public void displayFlight(Flight flight) {
        flight.airplane.printAirplane();
    }

    @Override
    public void flightFromFile(String filename) {
        Flight flight = Flight.readFlightFromFlile(filename);
        if (flight != null){
            this.flights.put(flight.flightCode, flight);
        }
    }

    @Override
    public void flightFromInput(String flightString) {

        String[] parts = flightString.split(" ");
        String flightCode = parts[0];
        int rowsBusiness = 0;
        int seatsPerRowBusiness = 0;
        int rowsEconomy = 0;
        int seatsPerRowEconomy = 0;
        if (parts.length != 2 && parts.length != 3){
            System.out.println("Numero de argumentos invalido.");
            return;
        }

        if (parts.length == 2) {
            try {
            String[] numbers = parts[1].split("x");
            rowsEconomy = Integer.parseInt(numbers[0]);
            seatsPerRowEconomy = Integer.parseInt(numbers[1]);
            } catch (NumberFormatException e) {
                System.out.println("Formato Invalido.");
                return;
            }
        } else if (parts.length == 3) {
            try {
            String[] numbers = parts[1].split("x");
            rowsBusiness = Integer.parseInt(numbers[0]);
            seatsPerRowBusiness = Integer.parseInt(numbers[1]);
            numbers = parts[2].split("x");
            rowsEconomy = Integer.parseInt(numbers[0]);
            seatsPerRowEconomy = Integer.parseInt(numbers[1]);
            } catch (NumberFormatException e) {
                System.out.println("Formato Invalido.");
                return;
            }
        }

        Airplane airplane = new Airplane(rowsBusiness, seatsPerRowBusiness, rowsEconomy, seatsPerRowEconomy);
        Flight flight = new Flight(flightCode, airplane);
        this.flights.put(flightCode, flight);


    }

    @Override
    public String readMenuOption(Scanner scanner) {
        System.out.println("Escolha uma opção: (H para ajuda)");
        String option = scanner.nextLine();
        return option;
    }

    @Override
    public void showMenu() {
        System.out.println("#####################################################################");
        System.out.println("I filename (READ FLIGHT FROM FILE)");
        System.out.println("M flight_code (DISPLAY FLIGHT)");
        System.out.println("F flight_code num_seats_executive num_seats_tourist (CREATE FLIGHT)");
        System.out.println("R flight_code class number_seats (ADD RESERVATION)");
        System.out.println("C reservation_code (CANCEL RESERVATION)");
        System.out.println("Q Quit");
        System.out.println("#####################################################################");
    }

    public void processOption(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String instrunction = readMenuOption(scanner);
            char option = instrunction.charAt(0);
            if (option == 'Q'){
                System.out.println("Quiting...");
                break;
            }
            switch (option) {
                case 'H':
                    showMenu();
                    break;
                case 'I':
                    if (instrunction.length() < 3){
                        System.out.println("Nome de ficheiro invalido.");
                        break;
                    }
                    String filename = instrunction.substring(2);
                    flightFromFile(filename);
                    break;
                case 'M':
                    if (instrunction.length() < 3){
                        System.out.println("Código de voo invalido.");
                        break;
                    }
                    String flightCode = instrunction.substring(2);
                    if (!this.flights.containsKey(flightCode)){
                        System.out.println("Código de voo "+flightCode+" nao existe.");
                        break;
                    }
                    Flight flight = flights.get(flightCode);
                    displayFlight(flight);
                    break;
                case 'F':
                    if (instrunction.length() < 3){
                        System.out.println("Código de voo invalido.");
                        break;
                    }
                    String newFlight = instrunction.substring(2);
                    flightFromInput(newFlight);
                    break;
                case 'R':
                    if (instrunction.length() < 3){
                        System.out.println("Código de reserva invalido.");
                        break;
                    }
                    String newReservation = instrunction.substring(2);
                    String newFlightCode = newReservation.split(" ")[0];
                    if (!this.flights.containsKey(newFlightCode)){
                        System.out.println("Código de voo "+newFlightCode+" nao existe.");
                        break;
                    }
                    ArrayList<String> seats = addReservation(newReservation);
                    if (seats == null){
                        System.out.println("Nao foi possivel adicionar a reserva.");
                        break;
                    }
                    System.out.print(newFlightCode+":"+(flights.get(newFlightCode).airplane.reservationNumber-1)+" = ");
                    for(int i = 0; i < seats.size(); i++){
                        if (i < seats.size()-1){
                            System.out.print(seats.get(i)+" | ");
                        }else {
                            System.out.print(seats.get(i));
                        }
                    }
                    System.out.println();
                    break;
                case 'C':
                    if (instrunction.length() < 3){
                        System.out.println("Código de reserva invalido.");
                        break;
                    }
                    String cancelInstrunction = instrunction.substring(2);
                    cancelReservation(cancelInstrunction);
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
        scanner.close();
    }


    public void processCommand(String command) {
        if (command.isEmpty()) return;
    
        char option = command.charAt(0);
        String arguments = command.substring(1).trim();
    
        switch (option) {
            case 'H':
                System.out.println("Command: H");
                showMenu();
                break;
            case 'I':
                System.out.println("Command: I " + arguments);
                flightFromFile(arguments);
                break;
            case 'M':
                System.out.println("Command: M " + arguments);
                if (!this.flights.containsKey(arguments)){
                    System.out.println("Código de voo "+arguments+" nao existe.");
                    break;
                }
                Flight flight = flights.get(arguments);
                if (flight != null) displayFlight(flight);
                else System.out.println("Flight code does not exist.");
                break;
            case 'F':
                System.out.println("Command: F " + arguments);
                flightFromInput(arguments);
                break;
            case 'R':
                System.out.println("Command: R " + arguments);
                String newFlightCode = arguments.split(" ")[0];
                if (!flights.containsKey(arguments.split(" ")[0])){
                    System.out.println("Flight code does not exist.");
                    break;
                }
                ArrayList<String> seats = addReservation(arguments);
                if (seats == null || seats.isEmpty()){
                    System.out.println("Unable to add reservation.");
                    break;
                }
                System.out.print(newFlightCode+":"+(flights.get(newFlightCode).airplane.reservationNumber-1)+" = ");
                for(int i = 0; i < seats.size(); i++){
                    if (i < seats.size()-1){
                        System.out.print(seats.get(i)+" | ");
                    }else {
                        System.out.print(seats.get(i));
                    }
                }
                System.out.println();
                break;
            case 'C':
                System.out.println("Command: C " + arguments);
                cancelReservation(arguments);
                break;
            case 'Q':
                System.out.println("Command: Q");
                System.out.println("Quitting...");
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }
    

}

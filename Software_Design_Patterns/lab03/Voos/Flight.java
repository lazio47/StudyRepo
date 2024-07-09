import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Flight {
    String flightCode;
    Airplane airplane;

    public Flight(String flightCode, int rowsExecutive, int seatsPerRowExecutive, int rowsTourist, int seatsPerRowTourist){
        this.flightCode = flightCode;
        this.airplane = new Airplane(rowsExecutive, seatsPerRowExecutive, rowsTourist, seatsPerRowTourist);
    }

    public Flight(String flightCode, Airplane airplane){
        this.flightCode = flightCode;
        this.airplane = airplane;
    }

    public static Flight readFlightFromFlile(String flightFilename){
        String flightCode = "";
        int rowsBusiness = 0;
        int seatsPerRowBusiness = 0;
        int rowsEconomy = 0;
        int seatsPerRowEconomy = 0;
        char classID;
        int numberOfSeats;

        try (BufferedReader br = new BufferedReader(new FileReader(flightFilename))) {
            String line = br.readLine();
            if (line.charAt(0) != '>') {
                System.out.println("Error: first line must start with >");
                return null;
            }
            String[] parts = line.substring(1).split(" ");
            flightCode = parts[0].replace(">", "");
            if (parts.length == 2) {
                String[] numbers = parts[1].split("x");
                rowsEconomy = Integer.parseInt(numbers[0]);
                seatsPerRowEconomy = Integer.parseInt(numbers[1]);
            } else if (parts.length == 3) {
                String[] numbers = parts[1].split("x");
                rowsBusiness = Integer.parseInt(numbers[0]);
                seatsPerRowBusiness = Integer.parseInt(numbers[1]);
                numbers = parts[2].split("x");
                rowsEconomy = Integer.parseInt(numbers[0]);
                seatsPerRowEconomy = Integer.parseInt(numbers[1]);
            }

            Airplane airplane = new Airplane(rowsBusiness, seatsPerRowBusiness, rowsEconomy, seatsPerRowEconomy);
            int totalSeatsOnBusiness = rowsBusiness*seatsPerRowBusiness;
            int totalSeatsOnEconomy = rowsEconomy*seatsPerRowEconomy;
            printInfoOfReadedFile(flightCode, totalSeatsOnBusiness, totalSeatsOnEconomy);

            while ((line = br.readLine()) != null) {
                parts = line.split(" ");
                classID = parts[0].charAt(0);
                numberOfSeats = Integer.parseInt(parts[1]);
                airplane.addReservation(classID, numberOfSeats);
            }

            //airplane.printAirplane();
            Flight flight = new Flight(flightCode, airplane);

            return flight;
         }catch (IOException e) {
            System.out.println("Ficheiro nao encontrado.");
        }
        return null;
    }

    public static void printInfoOfReadedFile( String flightCode,int totalSeatsOnBusiness, int totalSeatsOnEconomy){
        if (totalSeatsOnEconomy == 0){
            System.out.printf("Código do voo: %s.", flightCode);
            System.out.printf(" Lugares disponíveis: %d lugares em classe\nExecutiva.", totalSeatsOnBusiness);
            System.out.println("Classe turistica não disponível neste voo.");
        }else
        if(totalSeatsOnBusiness == 0){
            System.out.printf("Código do voo: %s.", flightCode);
            System.out.printf(" Lugares disponíveis: %d lugares em classe\nTuristica.", totalSeatsOnEconomy);
            System.out.println("Classe executiva não disponível neste voo.");
        }else{
            System.out.printf("Código do voo: %s.", flightCode);
            System.out.printf(" Lugares disponíveis: %s lugares em classe\nExecutiva; %s lugares em turística.", totalSeatsOnBusiness, totalSeatsOnEconomy);
        }
        System.out.println();
        System.out.println();
    }
    
    public ArrayList<String> addFlightReservation(char classID, int numberOfSeats){
        return this.airplane.addReservation(classID, numberOfSeats);
    }

    public void cancelFlightReservation(int numberOfReservation){
        this.airplane.cancelReservation(numberOfReservation);
    }

    public void displayFlightInfomation(){
        this.airplane.printAirplane();
    }
}

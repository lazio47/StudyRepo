
import java.util.ArrayList;
import java.util.Arrays;

public class Airplane {
    int reservationNumber = 1;
    int firstBusinessEmptyRow = 0;
    int firstEconomyEmptyRow = 0;
    int availableSeatsBusiness = 0;
    int availableSeatsEconomy = 0;
    int rowsBusiness = 0;
    int seatsPerRowBusiness = 0;
    int rowsEconomy = 0;
    int seatsPerRowEconomy = 0;
    
    ArrayList<int[]> businessClass = new ArrayList<>();
    ArrayList<int[]> economyClass = new ArrayList<>();

    public Airplane(int rowsBusiness, int seatsPerRowBusiness, int rowsEconomy, int seatsPerRowEconomy) {
        this.rowsBusiness = rowsBusiness;
        this.seatsPerRowBusiness = seatsPerRowBusiness;
        this.rowsEconomy = rowsEconomy;
        this.seatsPerRowEconomy = seatsPerRowEconomy;
        this.availableSeatsEconomy = rowsEconomy * seatsPerRowEconomy;
        this.availableSeatsBusiness = rowsBusiness * seatsPerRowBusiness;
        initializeArrays();

    }

    public int getAvailableSeatsBusiness() {
        return availableSeatsBusiness;
    }

    public int getAvailableSeatsEconomy() {
        return availableSeatsEconomy;
    }

    public int getRowsBusiness(){
        return this.rowsBusiness;
    }

    public int getRowsEconomy(){
        return this.rowsEconomy;
    }

    private void initializeArrays(){
        for(int i = 0; i < getRowsBusiness(); i++){
            this.businessClass.add(new int[this.seatsPerRowBusiness]);
        }
        for(int i = 0; i < getRowsEconomy(); i++){
            this.economyClass.add(new int[this.seatsPerRowEconomy]);
        }
    }

    public ArrayList<String> addReservation(char className, int numberOfSeats){
        ArrayList<String> seats = new ArrayList<>();
        if(validNumberOfSeats(className, numberOfSeats)){
            seats = registerReservation(className, numberOfSeats);
        }else {
            System.out.printf("Não foi possível obter lugares para a reserva: %c %d", className, numberOfSeats);
            System.out.println();
        }
        return seats;
    }

    public boolean validNumberOfSeats(char className, int numberOfSeats){
        if (className == 'T' && numberOfSeats <= this.availableSeatsEconomy){
            return true;
        }else if (className == 'E' && numberOfSeats <= this.availableSeatsBusiness){
            return true;
        }
        return false;
    }

    public ArrayList<String> registerReservation(char className, int numberOfSeats){
        ArrayList<String> seats = new ArrayList<>();
        int counter = 0;
        if(className == 'E'){
            int i = 0;
            firstBusinessEmptyRow = firstFreeRowBusiness();
            if (firstBusinessEmptyRow != -1){
                i = firstBusinessEmptyRow;
            }
            for(; i < getRowsBusiness(); i++){
                for(int j = 0; j < this.seatsPerRowBusiness; j++){
                    if (businessClass.get(i)[j] == 0){
                        businessClass.get(i)[j] = reservationNumber;
                        char seatcolumn = (char)('A' + j);
                        seats.add((i+1)+""+seatcolumn);
                        availableSeatsBusiness--;
                        counter++;
                        if (counter == numberOfSeats){
                            break;
                        }
                    }
                }
                if (counter == numberOfSeats){
                    break;
                }
                if(i+1 == getRowsBusiness()){
                    i = -1;
                }
            }
        }else if (className == 'T'){
            int i = 0;
            firstEconomyEmptyRow = firstFreeRowEconomy();
            if (firstEconomyEmptyRow != -1){
                i = firstEconomyEmptyRow;
            }
            for(; i < getRowsEconomy(); i++){
                for(int j = 0; j < this.seatsPerRowEconomy; j++){
                    if (economyClass.get(i)[j] == 0){
                        economyClass.get(i)[j] = reservationNumber;
                        char seatcolumn = (char)('A' + j);
                        seats.add((i+1+getRowsBusiness())+""+seatcolumn);
                        availableSeatsEconomy--;
                        counter++;
                        if (counter == numberOfSeats){
                            break;
                        }
                    }
                }
                if (counter == numberOfSeats){
                    break;
                }
                if(i+1 == getRowsEconomy()){
                    i = -1;
                }
            }
        }

        this.reservationNumber++;
        return seats;
    }

    public int firstFreeRowBusiness(){
        for(int i = 0; i < getRowsBusiness(); i++){
            if(Arrays.equals(businessClass.get(i), new int[seatsPerRowBusiness])){
                return i;
            }
        }
        return -1;
    }
    public int firstFreeRowEconomy(){
        for(int i = 0; i < getRowsEconomy(); i++){
            if(Arrays.equals(economyClass.get(i), new int[seatsPerRowEconomy])){
                return i;
            }
        }
        return -1;
    }

    public void printAirplane(){
        int counter = 0;
        System.out.print("   ");
        for (int i = 0; i < getRowsBusiness()+getRowsEconomy(); i++){
            System.out.printf("%2d ", i+1);
        }
        System.out.println();
        char fila = 'A';
        for (int i = 0; i < getRowsBusiness()+getRowsEconomy(); i++){
            System.out.printf("%2c ", fila);
            if(counter < seatsPerRowBusiness){
                for (int j = 0; j < getRowsBusiness(); j++){
                    System.out.printf("%2d ", businessClass.get(j)[counter]);
                }
            }
            else {
                for (int j = 0; j < getRowsBusiness(); j++){
                    System.out.print("   ");
                }
            }
            if(counter < seatsPerRowEconomy){
                for (int j = 0; j < getRowsEconomy(); j++){
                    System.out.printf("%2d ", economyClass.get(j)[counter]);
                }
            }
            System.out.println();
            counter++;
            if (counter >= seatsPerRowBusiness && counter >= seatsPerRowEconomy){
                break;
            }
            fila++;
        }
    }

    public void cancelReservation(int reservation){
        boolean removed = false;
        for(int i = 0; i < getRowsBusiness(); i ++){
            for(int j = 0; j < seatsPerRowBusiness; j++){
                if (businessClass.get(i)[j] == reservation){
                    businessClass.get(i)[j] = 0;
                    availableSeatsBusiness++;
                    removed = true;
                }
            }
        }
        if (removed){
            return;
        }
        for(int i = 0; i < getRowsEconomy(); i ++){
            for(int j = 0; j < seatsPerRowEconomy; j++){
                if (economyClass.get(i)[j] == reservation){
                    economyClass.get(i)[j] = 0;
                    availableSeatsEconomy++;
                    removed = true;
                }
            }
        }
        if(!removed){
            System.out.println("A reserva "+reservation+" nao existe!");
        }
    }

}

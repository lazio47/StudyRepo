
public class Voos {
    
    public final String flightCode;
    public final Aviao plane;
    public final int executiveSeats;
    public final int turistSeats;
    public int reservation;

    public Voos(String flightCode, Aviao plane) {
        this.flightCode = flightCode;
        this.plane = plane;
        executiveSeats = plane.getExecutiveSeats();
        turistSeats = plane.getTuristSeats();
        reservation = 0;
    }

    public int getReservation() {
        return reservation;
    }

    public boolean setReservation(int numSeats, Type type) {
        int[][] seats;
        if (type == Type.T) {
            if (!hasEnoughSeats(plane.getTuristOccuppiedSeats(), numSeats, turistSeats)) {              //checks if there are enough seats in turist class
                System.out.println("Não há lugares suficientes na classe turística");
                return false;
            }
            seats = plane.getTurist();
        } else {
            if (!hasEnoughSeats(plane.getExecutiveOccuppiedSeats(), numSeats, executiveSeats)) {        //checks if there are enough seats in executive class
                System.out.println("Não há lugares suficientes na classe executiva");
                return false;
            }
            seats = plane.getExecutive();
        }
    
        if (!placeReservations(seats, numSeats)) {                                                         //try to place the reservations   
            System.out.println("Não foi possível fazer todas as reservas");
            return false;
        }
    
        //update the plane seats
        if (type == Type.T) {
            plane.setTurist(seats);
        } else {
            plane.setExecutive(seats);
        }
        reservation++;
        return true;
    }
    
    private boolean hasEnoughSeats(int occupiedSeats, int numSeats, int totalSeats) {
        return occupiedSeats + numSeats <= totalSeats;
    }
    
    private boolean placeReservations(int[][] seats, int numSeats) {
        int rows = seats.length;    //number of rows
        int cols = seats[0].length; //number of seats per row
        int count = 0;              //number of seats placed
    
        for (int i = 0; i < cols && count < numSeats; i++) {            //check each seat and place the reservation
            for (int j = 0; j < rows && count < numSeats; j++) {
                if (seats[j][i] == 0) {
                    seats[j][i] = reservation + 1;
                    count++;
                }
            }
        }
        return count == numSeats;                                    //return true if all seats were placed
    }

   
    // Remove the reservation
    public boolean removeReservation(int reservationID) {
        boolean found = false;
        if (executiveSeats != 0) {
            int[][] executive = plane.getExecutive();
            for (int j = 0; j < executive[0].length; j++) {
                for (int i = 0; i < executive.length; i++) {
                    if (executive[i][j] == reservationID) {
                        executive[i][j] = 0;
                        found = true;
                    }
                }
            }
            plane.setExecutive(executive);
        }
        if (!found) {
            int[][] turist = plane.getTurist();
            for (int j = 0; j < turist[0].length; j++) {
                for (int i = 0; i < turist.length; i++) {
                    if (turist[i][j] == reservationID) {
                        turist[i][j] = 0;
                        found = true;
                    }
                }
            }
            plane.setTurist(turist);
        }
        return found;
    }

    public void printMap() {
        int[][] executive = plane.getExecutive();
        int[][] turist = plane.getTurist();
        int rows = 0;
        int cols = 0;
        if (executiveSeats != 0) {
            if (turist.length >= executive.length) {
                rows = turist.length;
                cols = executive[0].length + turist[0].length;
            }
        } else {
            rows = turist.length;
            cols = turist[0].length;
        }
        // Print column numbers
        System.out.print("\t");
        for (int i = 1; i <= cols; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
    
        int letter = 65; // ASCII value of "A"
        for (int i = 0; i < rows; i++) {
            System.out.print((char) letter + "\t"); // Print row letters
            letter++;
    
            for (int j = 0; j < cols; j++) {
                if (executiveSeats != 0 && j < executive[0].length) {
                    if (i < executive.length) {
                        if (executive[i][j] != 0) {
                            System.out.print(executive[i][j] + "\t");
                        } else {
                            System.out.print("0\t");
                        }
                    } else {
                        System.out.print("\t");
                    }
                } else {
                    int x;
                    if (executiveSeats != 0) {
                        x = j - 1;
                    } else {
                        x = j;
                    }
                    if (turist[i][x - executive.length] != 0) {
                        System.out.print(turist[i][x - executive.length]);
                    } else {
                        System.out.print("0");
                    }
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
    
}


    

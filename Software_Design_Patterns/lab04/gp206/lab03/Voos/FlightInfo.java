

public class FlightInfo {
    private Voos flight;

    public FlightInfo(Voos flight) {
        this.flight = flight;
    }
  
    public void flightInfo() {
        System.out.println("Código de voo: " + flight.flightCode + ". Lugares disponíveis: " );
        if (flight.executiveSeats > 0) {
            System.out.println(flight.executiveSeats - flight.plane.getExecutiveOccuppiedSeats() + " lugares em classe Executiva");
        }
        if (flight.turistSeats > 0) {
            System.out.println(flight.turistSeats - flight.plane.getTuristOccuppiedSeats() + " lugares em classe Turística");
        }
        if (flight.executiveSeats == 0) {
            System.out.println("Classe Executiva não disponível");
        }
        if (flight.executiveSeats == 0 && flight.turistSeats == 0) {
            System.out.println("Não há lugares disponíveis");
        }
    }

    public void printMap() {
        int[][] executive = flight.plane.getExecutive();
        int[][] turist = flight.plane.getTurist();
        int rows = 0;
        int cols = 0;
        if (flight.executiveSeats != 0) {
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
                if (flight.executiveSeats != 0 && j < executive[0].length) {
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
                    if (flight.executiveSeats != 0) {
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

    // Print the seat number of the reservation
    public void SeatInfo(int reservationID, Type type, int numSeats) {
        int[][] seats;
        if (type == Type.T) {
            seats = flight.plane.getTurist();
        } else {
            seats = flight.plane.getExecutive();
        }
        if (seats == null) {
            System.out.println("Não há lugares disponíveis");
            return;
        }
        int x;

        if(flight.executiveSeats != 0) {
            x = flight.plane.getExecutive()[0].length;
        } else {
            x = 0;
        }
        for(int i = 0; i < seats[0].length; i++) {
            for(int j = 0; j < seats.length; j++) {
                if(seats[j][i] == reservationID) {
                    System.out.println(" " + (j+1+x) + (char)(i+65) + " |");
                }
            }
        }
    }
}


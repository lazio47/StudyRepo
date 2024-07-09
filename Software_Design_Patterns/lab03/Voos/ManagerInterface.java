import java.util.ArrayList;
import java.util.Scanner;

public interface ManagerInterface {
    public void flightFromFile(String filename);
    public void flightFromInput(String flightString);
    public String readMenuOption(Scanner scanner);
    public void displayFlight(Flight flight);
    public ArrayList<String> addReservation(String reservation);
    public void cancelReservation(String reservtion);
    public void showMenu();
}

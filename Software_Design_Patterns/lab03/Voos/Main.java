import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;



public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Manager manager = new Manager();

        if (args.length == 1) {
            File file = new File(args[0]);

            if (!file.exists()) {
                System.out.println("File not found");
                return;
            }
            
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                manager.processCommand(line); 
            }
            fileScanner.close();
        }

        manager.processOption(); 
    }
}


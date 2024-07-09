import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContactsStorageTXT implements ContactsStorageInterface{
    private String file;

    public ContactsStorageTXT(String file){
        this.file = file;
    }

    public List<Contact> loadContacts(){
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                contacts.add(new Contact(parts[0], parts[1]));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the binary file: " + e.toString());
            return null;
        }
        return contacts;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Contact contact : list) {
                writer.println(contact.getName() + "\t" + contact.getNumber());
            }
        } catch (IOException e) {
            System.out.println("Some error occurred saving the binary file: " + e.toString());
            return false;
        }
        return true;
    }
}

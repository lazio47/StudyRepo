import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactsStorageBinario implements ContactsStorageInterface{
    private String fpath;
    public ContactsStorageBinario(String fpath) {
        this.fpath = fpath;
    }

    public List<Contact> loadContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(fpath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            try {
                while (true) {
                    Contact contact = (Contact) objectIn.readObject();
                    contacts.add(contact);
                }
            } catch (EOFException e) {
                // End of file reached
            } finally {
                objectIn.close();
                fileIn.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the binary file: " + e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.toString());
            return null;
        }
        return contacts;
    }

    public boolean saveContacts(List<Contact> list) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fpath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            for (Contact contact : list) {
                objectOut.writeObject(contact);
            }

            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Some error occurred saving the binary file: " + e.toString());
            return false;
        }
        return true;
    
    }
}

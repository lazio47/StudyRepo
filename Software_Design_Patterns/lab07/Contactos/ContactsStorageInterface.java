import java.io.FileNotFoundException;
import java.util.List;

public interface ContactsStorageInterface {
    public List<Contact> loadContacts() throws FileNotFoundException;
    public boolean saveContacts(List<Contact> list);
}
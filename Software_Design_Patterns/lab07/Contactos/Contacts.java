import java.util.ArrayList;
import java.util.List;

public class Contacts implements ContactsInterface {

    ContactsStorageInterface store;
    List<Contact> contactsList = new ArrayList<>();

    public Contacts(ContactsStorageInterface store){
        this.store = store;
    }

    public Contacts(){

    }

    @override
    public void openAndLoad(ContactsStorageInterface store){
        try {
            contactsList = store.loadContacts();
        } catch (Exception e) {
            System.err.println("An error occurred.");
        }
    }

    @override
    public void saveAndClose(){
        this.store.saveContacts(contactsList);
    }

    @override
    public void saveAndClose(ContactsStorageInterface store){
        store.saveContacts(contactsList);
    }
    
    @override
    public boolean exist(Contact contact){
        for(Contact cont : contactsList){
            if(contact.equals(cont)){
                return true;
            }
        }
        return false;
    }

    @override
    public Contact getByName(String name){
        for(Contact cont : contactsList){
            if(cont.getName().equals(name)){
                return cont;
            }
        }
        return null;
    }

    @override
    public boolean add(Contact contact){
        if(contactsList.add(contact)){
            return true;
        }
        return false;
    }

    @override
    public boolean remove(Contact contact){
        if(contactsList.remove(contact)){
            return true;
        }
        return false;
    }
}

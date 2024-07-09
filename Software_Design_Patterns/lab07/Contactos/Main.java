import java.util.List;

public class Main {
    public static void main(String[] args) {

        Contact ana = new Contact("Ana Maria", "123456789");
        Contact paulo = new Contact("Paulo", "987654321");
        Contact joao = new Contact("Joao", "9871294321");
        Contact pedro = new Contact("Pedro", "987654321");

        ContactsStorageTXT textStorage = new ContactsStorageTXT("contacts.txt");
        Contacts contact = new Contacts(textStorage);
        contact.add(ana);
        contact.add(paulo);
        contact.add(pedro);
        contact.add(joao);
        contact.saveAndClose();


        ContactsStorageBinario binaryStorage = new ContactsStorageBinario("contacts.bin");
        Contacts contact1 = new Contacts(binaryStorage);
        contact1.add(ana);
        contact1.add(paulo);
        contact1.add(pedro);
        contact1.add(joao);
        contact1.saveAndClose();
        contact1.saveAndClose();

        ContactsStorageTXT textStorage2 = new ContactsStorageTXT("contacts.txt");
        List<Contact> contactsFromText = textStorage2.loadContacts();

        ContactsStorageBinario binaryStorage2 = new ContactsStorageBinario("contacts.bin");
        List<Contact> contactsFromBinary = binaryStorage2.loadContacts();


        System.out.println("Contacts from text format:");
        for (Contact contacto : contactsFromText) {
            System.out.println(contacto);
        }


        System.out.println("\nContacts from binary format:");
        for (Contact contacto : contactsFromBinary) {
            System.out.println(contacto);
        }
    }
}

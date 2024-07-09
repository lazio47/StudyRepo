package Avaliacao_aula13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class ContactManager implements ContactManagerInterface {
    TreeMap<Integer, Contact> manager = new TreeMap<Integer, Contact>();
    @Override
    public void load(String filePath) {
        try{
            Scanner input = new Scanner(new FileReader("contactos.txt"));
            input.nextLine();
            while(input.hasNextLine()){
                String[] cont = input.nextLine().split("\t");
                LocalDate today = LocalDate.parse(cont[3]);
                Contact contacto = new Contact(cont[0], Integer.parseInt(cont[1]), cont[2], today);
                if(!manager.containsValue(contacto)){
                    manager.put(contacto.getId(), contacto);
                }
            }
        }catch(IOException e){
            System.out.println("Ficheiro não encontrado!");
        }
    }

    @Override
    public void save(String filePath) {
        File file = new File(filePath);
        try{
            PrintWriter writer = new PrintWriter(file);
            writer.println("Nome\tPhone\tEmail\tData de nascimento");
            for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
                Contact c = set.getValue();
                writer.println(c.getName()+"\t"+c.getPhoneNumber()+"\t"+c.getEmail()+"\t"+c.getBirthDate());
            }
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("Ficheiro de escrita não encontrado! ");
        }
    }

    @Override
    public boolean validateEmail(String email) {
        final String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 9;
    }

    @Override
    public boolean addContact(Contact person) {
        manager.put(person.getId(), person);
        return manager.containsValue(person);
    }

    @Override
    public boolean removeContact(Contact person) {
        manager.remove(person.getId());
        return !manager.containsValue(person);
    }

    @Override
    public Contact searchContactByname(String name) {
        for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
            Contact c = set.getValue();
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    @Override
    public Contact searchContactByEmail(String email) {
        for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
            Contact c = set.getValue();
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }

    @Override
    public Contact searchContactByPhoneNumber(int phoneNumber) {
        for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
            Contact c = set.getValue();
            if(c.getPhoneNumber()==phoneNumber){
                return c;
            }
        }
        return null;
    }

    @Override
    public void listAllContacts() {
        System.out.println("=========Contactos===================");
        manager.forEach((k,v)->System.out.println(k+" "+v));
        System.out.println("=====================================");
    }

    @Override
    public void listContactsByName() {
        ArrayList<Contact> n = new ArrayList<Contact>();
        for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
            n.add(set.getValue());
        }
        Collections.sort(n, new CompareToName());
        for (Contact cont: n) {
            System.out.println(cont.getId()+" "+cont.toString());
        }
    }

    @Override
    public void listContactsByPhoneNumber() {
        ArrayList<Contact> n1 = new ArrayList<Contact>();
        for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
            n1.add(set.getValue());
        }
        Collections.sort(n1, new CompareToPhoneNumber());
        for (Contact cont: n1) {
            System.out.println(cont.getId()+" "+cont.toString());
        }
    }

    @Override
    public void listContactsByBirthDate() {
        ArrayList<Contact> n2 = new ArrayList<Contact>();
        for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
            n2.add(set.getValue());
        }
        Collections.sort(n2, new CompareToBirthDate());
        for (Contact cont: n2) {
            System.out.println(cont.getId()+" "+cont.toString());
        }
    }

    public void listContactsByEmail() {
        ArrayList<Contact> n3 = new ArrayList<Contact>();
        for (java.util.Map.Entry<Integer, Contact> set : manager.entrySet()) {
            n3.add(set.getValue());
        }
        Collections.sort(n3, new CompareToEmail());
        for (Contact cont: n3) {
            System.out.println(cont.getId()+" "+cont.toString());
        }
    }
    
}

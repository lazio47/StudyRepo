package Avaliacao_aula13;

import java.time.LocalDate;

public class Contact {
    private static int id = 0;
    private String name;
    private int phoneNumber;
    private String email;
    private LocalDate birthDate;
    public Contact(String name, int phoneNumber, String email, LocalDate birthDate) {
        Contact.id++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        Contact.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + phoneNumber;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phoneNumber != other.phoneNumber)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", birthDate="
                + birthDate + "]";
    }
}

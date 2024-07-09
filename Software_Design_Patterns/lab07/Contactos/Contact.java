import java.io.Serializable;

public class Contact implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String number;

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }
    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    @Override

    public String toString() {

        return name + " - " + getNumber();

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
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }
}

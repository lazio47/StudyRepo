package aula06.Exercício6_2;

import aula05.DateYMD;
import aula06.Exercício6_1.Pessoa;

public class Contacto{
    private int ID;
    private Pessoa Pessoa;    
    private int Telemovel;
    private String Email;

    public Contacto(int ID, aula06.Exercício6_1.Pessoa Pessoa, int Telemovel, String Email) {
            this.ID = ID;
            this.Pessoa = Pessoa;
            this.Telemovel = Telemovel;
            this.Email = Email;
    }

    public Contacto(int ID, aula06.Exercício6_1.Pessoa Pessoa, int Telemovel) {
            this.ID = ID;
            this.Pessoa = Pessoa;
            this.Telemovel = Telemovel;

    }

    public Contacto(int ID, aula06.Exercício6_1.Pessoa Pessoa, String Email) {
            this.ID = ID;
            this.Pessoa = Pessoa;
            this.Email = Email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Pessoa getPessoa() {
        return Pessoa;
    }

    public void setPessoa(Pessoa Pessoa) {
        this.Pessoa = Pessoa;
    }

    public int getTelemovel() {
        return Telemovel;
    }

    public void setTelemovel(int Telemovel) {
        if(validTelemovel()){
        this.Telemovel = Telemovel;
        }
    }

    public boolean validTelemovel(){
        if((int)Telemovel/100000000==9){
            return true;
            } return false;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        if(validEmail()){
            Email = email;
        }
    }
    public boolean validEmail(){
        if(Email!=null&&Email.contains("@")){
            String[] str_arr = Email.split("@");
            if(str_arr[1].contains(".pt")||str_arr[1].contains(".com")){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Contacto [ID=" + ID + ", Pessoa=" + Pessoa + ", Telemovel=" + Telemovel + ", Email=" + Email + "]";
    }


    
}

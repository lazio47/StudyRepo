package aula08.Exercicio8_1;

import java.util.ArrayList;

public class Empresa {
       
    private String nome;
    private int codigoPostal;
    private String email;
    private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
    
    public Empresa(String nome, int codigoPostal, String email) {
        assert email.contains("@");
        this.nome = nome;
        this.codigoPostal = codigoPostal;
        this.email = email;
        //this.veiculos = veiculos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void addVeiculos(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void removeVeiculos(Veiculo veiculo) {
        this.veiculos.remove(veiculo);
    }

    public void printEmpresa(){
        System.out.printf("%27s\n", "Dados da Empresa");
        System.out.printf("%-18s %s\n","Nome:",this.getNome());
        System.out.printf("%-18s %s\n","Código Postal:",this.getCodigoPostal());
        System.out.printf("%-18s %s\n","Email:",this.getEmail());
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-19s %11s %10s %10s\n", "Veículo", "Marca", "Modelo", "Matrícula");
        System.out.println("-----------------------------------------------------");
        for(Veiculo v:getVeiculos()){
            System.out.printf("%-19s %11s %10s %10s\n", v.getTipoVeiculo(), v.getMarca(), v.getModelo(), v.getMatricula());
        }
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public String toString() {
        printEmpresa();
        return "";
    }

    
    

}


import java.util.ArrayList;

public class Rental {
       
    private String nome;
    private String codigoPostal;
    private String email;
    private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
    
    public Rental(String nome, String codigoPostal, String email) {
        assert email.contains("@");
        this.nome = nome;
        this.codigoPostal = codigoPostal;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Veiculo> getStock() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void addVeiculo(Veiculo veiculo) {
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
        for(Veiculo v:getStock()){
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

package aula08.Exercicio8_1;

public abstract class Veiculo implements KmPercorridosInterface {

    protected String matricula;
    protected String marca;
    protected String modelo;
    protected double potencia;
    protected int ultimo;
    protected int distancia = 0;


    public Veiculo(String matricula, String marca, String modelo, double potencia) {
        assert validarMatricula(matricula);
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
    }

    public boolean validarMatricula(String m){
        if (m.contains("-")){
            String[] mat = matricula.split("-");
            if (mat.length==3&& mat[0] instanceof String&& isStringInt(mat[1]) &&mat[2] instanceof String){
                return true;
            }
        }
        return false;

    }

    public boolean isStringInt(String s)
{
    try
    {
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException ex)
    {
        return false;
    }
}


    @Override
    public void trajeto(int quilometros) {
        this.ultimo = quilometros;
        this.distancia += quilometros;
    }

    @Override
    public int ultimoTrajeto() {
        return this.ultimo;
    }

    @Override
    public int distanciaTotal() {
        return this.distancia;

    }


    public String getMatricula() {
        return matricula;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getModelo() {
        return modelo;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public double getPotencia() {
        return potencia;
    }


    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public String getTipoVeiculo(){
        if(this instanceof Taxi){
            return "Táxi";
        }else if(this instanceof AutomovelLigeiroEletrico){
            return "Ligeiro Elétrico";
        }else if(this instanceof AutomovelLigeiro ){
            return "Ligeiro";
        }else if(this instanceof PesadoPassageiroEletrico ){
            return "Passageiro Elétrico";
        }else if(this instanceof PesadoPassageiro ){
            return "Passageiro";
        }else if(this instanceof PesadoMercadorias ){
            return "Mercadorias";
        }
        return "Motociclo";
    }

    @Override
    public String toString() {
        return  marca +", matricula: "+matricula;
    }
    
}

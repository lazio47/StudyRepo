package aula06.Exercício6_1;

import aula05.DateYMD;

public class Professor extends Pessoa {

    private String categoria;
    private String departamento;

    public Professor(String Name, int cc, DateYMD dataNasc, String categoria, String departamento) {
        super(Name, cc, dataNasc);
        this.categoria = categoria;
        this.departamento= departamento;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Professor: "+getName()+" Categoria: " + categoria + "; Departamento: " + departamento+";";
    }
    
}

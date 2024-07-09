public class Emprestado extends Estado{
	private Livro livro;
	public Emprestado(Livro livro){
		this.livro = livro;
	}
	
	public void devolve(){
		livro.changeState(new Disponivel(livro));
	}
	
	public String toString(){
		return "Emprestado";
	}
}

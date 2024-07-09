public class Disponivel extends Estado{
	private Livro livro;
	public Disponivel(Livro livro){
		this.livro = livro;
	}
	
	public void reserva(){
		livro.changeState(new Reservado(livro));
	}
	
	public void requisita(){
		livro.changeState(new Emprestado(livro));
	}
	
	public String toString(){
		return "Disponível";
	}
}

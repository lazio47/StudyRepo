public class Reservado extends Estado{
	private Livro livro;
	public Reservado(Livro livro){
		this.livro = livro;
	}
	
	public void cancelaReserva(){
		livro.changeState(new Disponivel(livro));
	}
	
	public String toString(){
		return "Reservado";
	}
}

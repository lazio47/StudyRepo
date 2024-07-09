public class Inventario extends Estado{
	private Livro livro;
	public Inventario(Livro livro){
		this.livro = livro;
	}
	
	public void regista(){
		livro.changeState(new Disponivel(livro));
	}
	
	public String toString(){
		return "Inventário";
	}
}

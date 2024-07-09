public class Livro {
	private Estado estado;
	private String titulo;
	private String ISBN;
	private int ano;
	private String primeiroAutor;
	
	public Livro(String titulo, String ISBN, int ano, String primeiroAutor){
		this.titulo = titulo;
		this.ISBN = ISBN;
		this.ano = ano;
		this.primeiroAutor = primeiroAutor;
		this.estado = new Inventario(this);
	}
	
	public void changeState(Estado estado){
		this.estado = estado;
	}
	
	public void regista(){
		estado.regista();
	}
	
	public void requisita(){
		estado.requisita();
	}
	
	public void reserva(){
		estado.reserva();
	}
	
	public void cancelaReserva(){
		estado.cancelaReserva();
	}
	
	public void devolve(){
		estado.devolve();
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public String getPrimeiroAutor(){
		return primeiroAutor;
	}
	
	public String getEstado(){
		return estado.toString();
	}
}

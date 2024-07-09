import java.util.Scanner;
public class Main {
	public static void main(String[] args){
		Livro livro1 = new Livro("Java Anti-Stress", "123456789", 2012, "Omodionah");
		Livro livro2 = new Livro("A Guerra dos Padrões", "923456789", 2013, "Jorge Omel");
		Livro livro3 = new Livro("A Procura da Luz", "193456789", 2014, "Khumatkli");
		Scanner sc = new Scanner(System.in);
		String opcao;
		int livro, operacao;
		while(true){
			opcoes(livro1, livro2, livro3);
			System.out.print(">> ");
			opcao = sc.nextLine();
			String[] partes = opcao.split(",");
			livro = Integer.parseInt(partes[0].trim());
			operacao = Integer.parseInt(partes[1].trim());
			
			if(livro == 1){
				handleOperacao(livro1, operacao);
			}else if (livro == 2){
				handleOperacao(livro2, operacao);
			}else if(livro == 3){
				handleOperacao(livro3, operacao);
			}else {
				System.out.println("Livro inválido");
				break;
			}
		}
		sc.close();
	}
	
	public static void opcoes(Livro livro1, Livro livro2, Livro livro3){
		System.out.println("*** Biblioteca ***");
		System.out.printf("%-5d %-20s %-15s [%10s]\n", 1, livro1.getTitulo(), livro1.getPrimeiroAutor(), livro1.getEstado());
		System.out.printf("%-5d %-20s %-15s [%10s]\n", 2, livro2.getTitulo(), livro2.getPrimeiroAutor(), livro2.getEstado());
		System.out.printf("%-5d %-20s %-15s [%10s]\n", 3, livro3.getTitulo(), livro3.getPrimeiroAutor(), livro3.getEstado());
		System.out.println(">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela\n");
		
	}
	
	public static void handleOperacao(Livro livro, int operacao){
		switch(operacao){
			case 1:
				livro.regista();
				break;
			case 2:
				livro.requisita();
				break;
			case 3:
				livro.devolve();
				break;
			case 4:
				livro.reserva();
				break;
			case 5:
				livro.cancelaReserva();
				break;
			default:
				System.out.println("Operacao inválida");
		}
	}
}

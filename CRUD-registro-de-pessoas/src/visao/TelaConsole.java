package visao;

import java.util.Scanner;

import modelo.Cliente;
import repositorio.ClienteDAO;

public class TelaConsole {
	
	private Scanner entrada;
	private ClienteDAO clienteDAO;
	
	
	public TelaConsole() {	
		clienteDAO = new ClienteDAO();
		entrada = new Scanner(System.in);
		exibirMenu();
		entrada.close();
		clienteDAO.fecharConexao();
	}
	
	public void exibirMenu(){
		
		int opcaoEscolhida = 0;
		
		while(opcaoEscolhida != 5) {	
			System.out.println("(1)Inserir Cliente\n(2)Buscar Cliente");
			System.out.println("(3)Atualizar Cliente\n(4)Excluir Cliente\n(5)Sair");
			opcaoEscolhida = Integer.parseInt(entrada.nextLine());
			
			realizarOpcaoEscolhida(opcaoEscolhida);
		}	
	}
	
	private void realizarOpcaoEscolhida(int opcaoEscolhida) {
		
		switch (opcaoEscolhida) {
			
			case 1:
				System.out.print("Digite o nome: ");
				String nome = entrada.nextLine();
				
				System.out.print("Digite a idade: ");
				int idade = Integer.parseInt(entrada.nextLine());
				
				System.out.print("Digite o CPF: ");
				String cpf = entrada.nextLine();
				
				System.out.print("Digite a cidade: ");
				String cidade = entrada.nextLine();
				
				System.out.print("Digite o estado: ");
				String estado = entrada.nextLine();
				
				Cliente cliente = new Cliente(nome, idade, cpf, cidade, estado);
				//ClienteDao.inserirCliente()
				
				break;
				
			case 2:
				System.out.print("Digite o nome: ");
				nome = entrada.nextLine();
				
				System.out.print("Digite o cpf: ");
				cpf = entrada.nextLine();
				//ClienteDao.buscarCliente()
				
				break;
				
			case 3:
				System.out.print("Digite o nome: ");
				nome = entrada.nextLine();
				
				System.out.print("Digite o cpf: ");
				cpf = entrada.nextLine();
				
				//ClienteDao.atualizarCliente()
				break;
				
			case 4:
				System.out.print("Digite o nome: ");
				nome = entrada.nextLine();
				
				System.out.print("Digite o cpf: ");
				cpf = entrada.nextLine();
				//ClienteDao.excluirCliente
				break;
				
			case 5:
				System.out.println("Saindo...");
				break;
				
			default:
				System.out.println("Opcao Invalida!\nTente Novamente!");	
		}
		
	}
	
}

package visao;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Cliente;
import repositorio.ClienteDAO;

public class TelaConsole {
	
	private Scanner entrada;
	private ClienteDAO clienteDAO;
	
	
	public TelaConsole() {	
		clienteDAO = new ClienteDAO();
		clienteDAO.iniciarConexao();
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
			System.out.println();
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
				
				try {
					clienteDAO.inserirCliente(cliente);		
				}
				catch(SQLException e) {
					System.out.println("Erro na insercao de dados!");
				}
						
				break;
				
			case 2:		
				System.out.print("Digite o cpf: ");
				cpf = entrada.nextLine();
				
				try {
					Cliente clienteBusca = clienteDAO.buscarCliente(cpf);
					
					if(clienteBusca != null) {
						System.out.println(clienteBusca);
					}
					
					else {
						System.out.println("Cliente nao cadastrado!");
					}
					
				} 
				
				catch (SQLException e) {
					System.out.println("Erro na consulta de dados!");
				}
				
				break;
				
			case 3:	
				System.out.print("Digite o cpf: ");
				cpf = entrada.nextLine();
				
				System.out.print("Digite sua cidade atual: ");
				cidade = entrada.nextLine();
				
				System.out.print("Digite seu estado atual: ");
				estado = entrada.nextLine();
				
				
				try {
					clienteDAO.alterarCliente(cpf, cidade, estado);
				} 
			
				catch (SQLException e) {
					System.out.println("Erro de atualizacao de dados!");
					e.printStackTrace();
				}
				break;
				
			case 4:
				System.out.print("Digite o cpf: ");
				cpf = entrada.nextLine();
				
				try {
					clienteDAO.excluirCliente(cpf);	
				}
				catch(SQLException e) {
					System.out.println("Erro na exclusao de dados!");
					e.printStackTrace();
				}
				break;
				
			case 5:
				System.out.println("Saindo...");
				break;
				
			default:
				System.out.println("Opcao Invalida!\nTente Novamente!");	
		}
		
	}
	
}

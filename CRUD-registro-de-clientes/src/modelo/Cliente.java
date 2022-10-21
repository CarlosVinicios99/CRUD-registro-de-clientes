package modelo;

public class Cliente {
	
	private String nome;
	private int idade;
	private String cpf;
	private String cidade;
	private String estado;
	
	public Cliente(String nome, int idade, String cpf, String cidade, String estado) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	@Override
	public String toString() {
		return String.format("Nome: %s\nIdade: %d\nCPF: %s\nCidade: %s - %s",nome, idade, cpf, cidade, estado);
	}
	
}

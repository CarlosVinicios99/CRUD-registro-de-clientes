package repositorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import modelo.Cliente;

public class ClienteDAO {
	
	private Connection conexao;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	public void iniciarConexao() {
		conexao = getConexao();
	}
	
	public void fecharConexao() {
		try {
			if(!conexao.isClosed()) {
				conexao.close();
			}
		} 
		catch (SQLException e) {
			System.out.println("");
		}
	}
	private Connection getConexao() {
			
		try {
			Properties prop = getProperties();
			final String url = prop.getProperty("banco.url");
			final String usuario = prop.getProperty("banco.usuario");
			final String senha = prop.getProperty("banco.senha");
			return DriverManager.getConnection(url, usuario, senha);
		}
			
		catch(SQLException e) {
			System.out.println("Erro nas configuracoes do Banco de Dados!");
			return null;
		}
	}
		
	private Properties getProperties(){
		
		try {
			Properties prop = new Properties();
			String caminho = "/conexao.properties";
			prop.load(ClienteDAO.class.getResourceAsStream(caminho));
			return prop;
		}
		
		catch(IOException e) {
			System.out.println();
			return null;
		}
	}
	
	public boolean inserirCliente(Cliente cliente) throws SQLException {	
		String sql = 
			"""
				INSERT INTO clientes (nome, idade, cpf, cidade, estado) 
					VALUES (?, ?, ?, ?, ?); 
			""";
		
		pstmt = conexao.prepareStatement(sql);
		
		pstmt.setString(1, cliente.getNome());
		pstmt.setInt(2, cliente.getIdade());
		pstmt.setString(3, cliente.getCpf());
		pstmt.setString(4, cliente.getCidade());
		pstmt.setString(5, cliente.getEstado());
		
		return pstmt.execute();

	}
	
	public Cliente buscarCliente(String cpf_consulta) throws SQLException {
		String sql = 
			"""
				SELECT * FROM clientes WHERE cpf = ?
			""";
		
		pstmt = conexao.prepareStatement(sql);
		pstmt.setString(1, cpf_consulta);
		ResultSet resultadoConsulta = pstmt.executeQuery();
		
		if(resultadoConsulta != null) {
			
			while(resultadoConsulta.next()) {
				String nome = resultadoConsulta.getString("nome");
				int idade = resultadoConsulta.getInt("idade");
				String cpf = resultadoConsulta.getString("cpf");
				String cidade = resultadoConsulta.getString("cidade");
				String estado = resultadoConsulta.getString("estado");
				
				return new Cliente(nome, idade, cpf, cidade, estado);
			}
		}
		return null;
	}
	
	public boolean alterarCliente(String nome, String cpf, String cidade, String estado) {
		return false;
	}
	
	public boolean excluirCliente(String nome, String cpf) {
		return false;
	}
		
}

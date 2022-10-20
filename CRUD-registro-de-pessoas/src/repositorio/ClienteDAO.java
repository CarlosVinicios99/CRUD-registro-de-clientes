package repositorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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
	
	//criar metodos
	//Criar cliente()
	//Consultar cliente()
	//Alterar cliente()
	//Remover Cliente()
		
}

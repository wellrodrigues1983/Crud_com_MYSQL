package conexao.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Conexao {
	
	private static Connection conn = null;/*Objeto de conex�o do banco de dados do JDBC do import java.sql.Connection*/
	
	/**/
	public static Connection getConnection() {
		if (conn == null) {
			try {
			Properties props = loadProperties();
			String url = props .getProperty("dburl");/*Ler a String de conex�o do arquivo db.properties*/
			conn = DriverManager.getConnection(url, props);/*Fazendo a conex�o depois de ler os dados*/
		}
		catch (SQLException e) {
			throw new ConexaoException(e.getMessage());
		}
	}
		return conn;
}
	public static void closeConnection() {/*fechando a conex�o*/
		if (conn != null) {/*verificando se a conex�o esta nula*/
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ConexaoException(e.getMessage());
			}
		}
	}
	
	/*M�todo para carregar as propriedades que est�o no arquivo db.properties*/
	private static Properties loadProperties(){
		try (FileInputStream fs = new FileInputStream("db.properties")) {
				Properties props = new Properties();
				props.load(fs);
				return props;
		}
		catch (IOException e) {
			throw new ConexaoException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {/*Criando metodo para fechar o Statement com try e catch, para que n�o seja exigido toda vez o tratamento de exce��o*/
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new ConexaoException(e.getMessage());
				
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {/*Criando metodo para fechar o ResultSet com try e catch, para que n�o seja exigido toda vez o tratamento de exce��o*/
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new ConexaoException(e.getMessage());
				
			}
		}
	}
}

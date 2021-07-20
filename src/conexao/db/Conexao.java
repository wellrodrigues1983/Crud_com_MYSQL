package conexao.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
	
	private static Connection conn = null;/*Objeto de conexão do banco de dados do JDBC do import java.sql.Connection*/
	
	/**/
	public static Connection getConnection() {
		if (conn == null) {
			try {
			Properties props = loadProperties();
			String url = props .getProperty("dburl");/*Ler a String de conexão do arquivo db.properties*/
			conn = DriverManager.getConnection(url, props);/*Fazendo a conexão depois de ler os dados*/
		}
		catch (SQLException e) {
			throw new ConexaoException(e.getMessage());
		}
	}
		return conn;
}
	public static void closeConnection() {/*fechando a conexão*/
		if (conn != null) {/*verificando se a conexão esta nula*/
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ConexaoException(e.getMessage());
			}
		}
	}
	
	/*Método para carregar as propriedades que estão no arquivo db.properties*/
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
}

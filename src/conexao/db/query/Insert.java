package conexao.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import conexao.db.Conexao;
import conexao.db.ConexaoException;

public class Insert {
	
	public void InserirDados() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = Conexao.getConnection(); /*Criando a conexão*/
			
			/*Criando o insert*/
			st = conn.prepareStatement(
					"INSERT INTO pessoa "
					+ "(nome, email, dtnasc, endereco, sexo) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)");/*Place holder para ser passado como parametro depois*/
			
			st.setString(1, "teste");
			st.setString(2, "teste@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("01/01/1980").getTime()));/*Passar uma data pelo padrão sql.date para inserir no banco*/
			st.setString(4, "Rua teste, 4");
			st.setString(5, "Masculino");
			
			int rowsAffected = st.executeUpdate(); /*Pegar a quantidade de registros afetados*/
			
			System.out.println("Feito! Linhas Afetadas: " + rowsAffected);
		}
		catch (SQLException e) {
			throw new ConexaoException(e.getMessage());
		}
		catch (ParseException e) { /*Necessário tratamento para o sdf.parse*/
			e.printStackTrace();
		}
		finally {
			Conexao.closeStatement(st);
			Conexao.closeConnection();/*A conexão sempre é fechada por último*/
		}
	}
	
}



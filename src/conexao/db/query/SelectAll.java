package conexao.db.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.db.Conexao;

public class SelectAll {
	
	Connection conn = null;/*Conecta ao banco*/
	Statement st = null;/*Prepara uma consulta ao banco*/
	ResultSet rs = null;/*Resultado da consulta*/
	
	public void Select() {
		
		Connection conn = null;/*Conecta ao banco*/
		Statement st = null;/*Prepara uma consulta ao banco*/
		ResultSet rs = null;/*Resultado da consulta*/
			
			try {
				conn = Conexao.getConnection();/*Abrindo uma conexão*/
				
				st = conn.createStatement();/*Instancia de um objeto statment*/
				
				rs = st.executeQuery("select * from pessoa");/*fazendo uma consulta a uma tabela no banco*/
				
				while (rs.next()) {
					System.out.println(rs.getInt("id") + "," + rs.getString("nome") + "," + rs.getString("email") + "," + rs.getString("dtnasc") + "," + rs.getString("sexo"));
				}
						
			}
			catch (SQLException e ) {
				e.printStackTrace();
			}
			finally {
				Conexao.closeResultSet(rs);
				Conexao.closeStatement(st);
				Conexao.closeConnection();
			}
			
		}


}

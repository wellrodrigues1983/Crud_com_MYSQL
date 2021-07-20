package application;

import java.sql.Connection;

import conexao.db.Conexao;

public class Programa {

	public static void main(String[] args) {
		
		Connection conn = Conexao.getConnection();
		Conexao.closeConnection();

	}

}

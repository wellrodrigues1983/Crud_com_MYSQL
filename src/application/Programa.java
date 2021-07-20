package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import conexao.db.Conexao;
import conexao.db.ConexaoException;

public class Programa {

public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o Nome: ");
		String nome = sc.nextLine();
		
		System.out.println("Digite o e-mail: ");
		String email = sc.nextLine();
		
		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String dtnasc = sc.nextLine();
		
		System.out.println("Digite o endereço: ");
		String endereco = sc.nextLine();
		
		System.out.println("Digite o seu sexo: ");
		String sexo = sc.nextLine();
		
		
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
				
				st.setString(1, nome);
				st.setString(2, email);
				st.setDate(3, new java.sql.Date(sdf.parse(dtnasc).getTime()));/*Passar uma data pelo padrão sql.date para inserir no banco*/
				st.setString(4, endereco);
				st.setString(5, sexo);
				
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


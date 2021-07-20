package application.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import conexao.db.Conexao;
import conexao.db.ConexaoException;

public class Pessoa {
	
	private Integer id;	
	private String nome;
	private String email;
	private String dtnasc;
	private String endereco;
	private String sexo;
	
	public void CadastarPessoa() {
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Digite o Nome: ");
	nome = sc.nextLine();

	System.out.println("Digite o e-mail: ");
	email = sc.nextLine();
	
	System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
	dtnasc = sc.nextLine();
	
	System.out.println("Digite o endereço: ");
	endereco = sc.nextLine();
	
	System.out.println("Digite o seu sexo: ");
	sexo = sc.nextLine();

	}
	
	public Pessoa() {
		
	}
	
	public Pessoa(Integer id, String nome, String email, String dtnasc, String endereco, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dtnasc = dtnasc;
		this.endereco = endereco;
		this.sexo = sexo;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDtnasc() {
		return dtnasc;
	}

	public void setDtnasc(String dtnasc) {
		this.dtnasc = dtnasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
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
			
			st.setString(1, nome);
			st.setString(2, email);
			st.setDate(3, new java.sql.Date(sdf.parse(dtnasc).getTime()));/*Passar uma data pelo padrão sql.date para inserir no banco*/
			st.setString(4, endereco);
			st.setString(5, sexo);
			
			int rowsAffected = st.executeUpdate(); /*Pegar a quantidade de registros afetados*/
			
			System.out.println("Cadastrado! Linhas Afetadas: " + rowsAffected);
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
	
	public void SelectAll() {
		
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
	
	

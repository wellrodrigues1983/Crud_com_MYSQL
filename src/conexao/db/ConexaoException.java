package conexao.db;

public class ConexaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/*classe para carregar excec�es na conex�o com o banco*/
	public ConexaoException(String msg) {
		super(msg);
	}

}

package Exception;

public class VerificacaoDeSenhaException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VerificacaoDeSenhaException(String mensagem) {
		super(mensagem);
	}
	
	public VerificacaoDeSenhaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}

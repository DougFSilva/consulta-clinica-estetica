package br.com.clinicaEstetica.exception;

public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ObjetoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}

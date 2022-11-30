package br.com.clinicaEstetica.exception;

public class ErroNaMarcacaoDeConsultaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroNaMarcacaoDeConsultaException(String mensagem) {
		super(mensagem);
	}
	
	public ErroNaMarcacaoDeConsultaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
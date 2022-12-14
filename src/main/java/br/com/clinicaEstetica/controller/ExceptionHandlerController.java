package br.com.clinicaEstetica.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.clinicaEstetica.exception.ErroNaMarcacaoDeConsultaException;
import br.com.clinicaEstetica.exception.ExceptionPadrao;
import br.com.clinicaEstetica.exception.ObjetoNaoEncontradoException;
import br.com.clinicaEstetica.exception.ViolacaoDeIntegridadeDeDadosException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<ExceptionPadrao> objetoNaoEncontradoException(ObjetoNaoEncontradoException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionPadrao);
	}
	
	@ExceptionHandler(ViolacaoDeIntegridadeDeDadosException.class)
	public ResponseEntity<ExceptionPadrao> violacaoDeIntegridadeDeDadosException(ViolacaoDeIntegridadeDeDadosException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPadrao);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionPadrao> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<String> campos = exception.getFieldErrors().stream().map(campo -> campo.getField()).toList();
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), "Erro nos campos " + campos);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPadrao);
	}
	
	
	@ExceptionHandler(ErroNaMarcacaoDeConsultaException.class)
	public ResponseEntity<ExceptionPadrao> erroNaMarcacaoDeConsultaException(ErroNaMarcacaoDeConsultaException exception) {
		ExceptionPadrao exceptionPadrao = new ExceptionPadrao(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPadrao);
	}
	
	
}

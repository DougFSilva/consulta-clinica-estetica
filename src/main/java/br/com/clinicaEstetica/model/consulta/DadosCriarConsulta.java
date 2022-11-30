package br.com.clinicaEstetica.model.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarConsulta(
		
		@NotNull
		Long clienteId,
		
		@NotNull
		Long procedimentoId,
		
		@NotNull
		Long especialistaId,
		
		@NotBlank
		LocalDate data,
		
		@NotBlank
		LocalTime horario
		
		) {

	
}

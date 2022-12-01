package br.com.clinicaEstetica.model.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record DadosMarcarConsulta(
		
		@NotNull
		Long clienteId,
		
		@NotNull
		Long procedimentoId,
		
		@NotNull
		Long especialistaId,
		
		@NotNull
		LocalDate data,
		
		@NotNull
		LocalTime horario
		
		) {

	
}

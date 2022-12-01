package br.com.clinicaEstetica.model.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;


public record DadosRemarcarConsulta(
		
		@NotNull
		Long id,
		
		@NotNull
		LocalDate data,
		
		@NotNull
		LocalTime horario
		
		) {

}

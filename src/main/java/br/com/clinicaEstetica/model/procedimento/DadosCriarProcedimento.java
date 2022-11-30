package br.com.clinicaEstetica.model.procedimento;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarProcedimento(
		
		@NotBlank
		String tipo,
		
		@NotNull
		Integer duracao,
		
		@NotNull
		BigDecimal custo
		) {

}

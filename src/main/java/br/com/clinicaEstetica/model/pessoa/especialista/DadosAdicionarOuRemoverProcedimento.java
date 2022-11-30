package br.com.clinicaEstetica.model.pessoa.especialista;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record DadosAdicionarOuRemoverProcedimento(
		
		@NotNull
		Long id,
		
		List<String>tipos
		) {

}

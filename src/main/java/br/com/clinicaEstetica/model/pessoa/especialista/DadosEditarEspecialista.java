package br.com.clinicaEstetica.model.pessoa.especialista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEditarEspecialista(
		
		@NotNull
		Long id,
		
		@NotBlank
		String nome,
		
		@NotBlank
		@Pattern(regexp = "\\d{11}")
		String cpf,
		
		@NotBlank
		@Pattern(regexp = "\\d{10,11}")
		String telefone,
		
		@Email
		String email,
		
		@NotBlank
		String registro) {

}

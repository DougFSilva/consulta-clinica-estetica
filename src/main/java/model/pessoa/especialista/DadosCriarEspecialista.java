package model.pessoa.especialista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCriarEspecialista(
		
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
		String registro
		) {

}

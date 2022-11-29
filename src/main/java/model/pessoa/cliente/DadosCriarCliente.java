package model.pessoa.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import model.pessoa.Estado;

public record DadosCriarCliente(
		
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
		
		@NotNull
		Estado estado,
		
		@NotBlank
		String cidade,
		
		@NotBlank
		String bairro,
		
		@NotBlank
		String rua,
		
		@NotBlank
		String numero
		
		) {

}

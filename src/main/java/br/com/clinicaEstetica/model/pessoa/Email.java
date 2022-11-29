package br.com.clinicaEstetica.model.pessoa;

import br.com.clinicaEstetica.exception.ViolacaoDeIntegridadeDeDadosException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode(of = "endereco")
public class Email {

	@Column(name = "email")
	private String endereco;
	
	public Email(String endereco) {
		if (endereco == null || !endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {

			throw new ViolacaoDeIntegridadeDeDadosException("Email " + endereco + " não é valido!");
		}
		this.endereco = endereco;
	}
}

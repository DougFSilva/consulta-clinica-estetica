package model.pessoa;

import Exception.ViolacaoDeIntegridadeDeDadosException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
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

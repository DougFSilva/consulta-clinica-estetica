package br.com.clinicaEstetica.model.pessoa.cliente;


import br.com.clinicaEstetica.model.pessoa.Email;
import br.com.clinicaEstetica.model.pessoa.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "cpf", "email"})
@ToString
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String cpf;
	
	private String telefone;
	
	@Column(unique = true)
	@Embedded
	private Email email;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
}

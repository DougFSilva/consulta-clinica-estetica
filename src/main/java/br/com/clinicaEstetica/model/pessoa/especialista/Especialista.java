package br.com.clinicaEstetica.model.pessoa.especialista;

import java.util.ArrayList;
import java.util.List;

import br.com.clinicaEstetica.model.pessoa.Email;
import br.com.clinicaEstetica.model.procedimento.Procedimento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@EqualsAndHashCode(of = {"id", "cpf", "registro"})
@ToString
@Entity
@Table(name = "especialistas")
public class Especialista {

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
	
	@Column(unique = true)
	private String registro;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Procedimento> procedimentos = new ArrayList<>();
	
	public void adicionarProcedimento(Procedimento procedimento) {
		this.procedimentos.add(procedimento);
	}
	
	public void removerProcedimento(Procedimento procedimento) {
		this.procedimentos.remove(procedimento);
	}
	
}

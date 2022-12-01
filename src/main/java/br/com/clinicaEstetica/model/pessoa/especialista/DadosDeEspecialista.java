package br.com.clinicaEstetica.model.pessoa.especialista;

import java.util.ArrayList;
import java.util.List;

import br.com.clinicaEstetica.model.procedimento.Procedimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DadosDeEspecialista {

	private Long id;

	private String nome;

	private String cpf;

	private String telefone;

	private String email;

	private String registro;

	private List<Procedimento> procedimentos = new ArrayList<>();
	
	public DadosDeEspecialista(Especialista especialista) {
		this.id = especialista.getId();
		this.nome = especialista.getNome();
		this.cpf = especialista.getCpf();
		this.telefone = especialista.getTelefone();
		this.email = especialista.getEmail().getEndereco();
		this.registro = especialista.getRegistro();
		this.procedimentos = especialista.getProcedimentos();
	}
}

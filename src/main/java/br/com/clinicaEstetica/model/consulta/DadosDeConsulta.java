package br.com.clinicaEstetica.model.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DadosDeConsulta {

	private Long id;
	
	private String cpfDoCliente;
	
	private String nomeDoCliente;
	
	private String procedimento;
	
	private String registroDoEspecialista;
	
	private String nomeDoEspecialista;
	
	private LocalDate data;
	
	private LocalTime horarioInicial;
	
	private LocalTime horarioFinal;
	
	public DadosDeConsulta(Consulta consulta) {
		this.id = consulta.getId();
		this.cpfDoCliente = consulta.getCliente().getCpf();
		this.nomeDoCliente = consulta.getCliente().getNome();
		this.procedimento = consulta.getProcedimento().getTipo();
		this.registroDoEspecialista = consulta.getEspecialista().getRegistro();
		this.nomeDoEspecialista = consulta.getEspecialista().getNome();
		this.data = consulta.getData();
		this.horarioInicial = consulta.getHorarioInicial();
		this.horarioFinal = consulta.getHorarioFinal();
		
	}
}

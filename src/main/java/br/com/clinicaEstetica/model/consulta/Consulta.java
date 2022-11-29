package br.com.clinicaEstetica.model.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.clinicaEstetica.model.pessoa.cliente.Cliente;
import br.com.clinicaEstetica.model.pessoa.especialista.Especialista;
import br.com.clinicaEstetica.model.procedimento.Procedimento;
import jakarta.persistence.CascadeType;
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
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "consultas")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Procedimento procedimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Especialista especialista;
	
	private LocalDate data;
	
	private LocalTime horario;
	
	
}

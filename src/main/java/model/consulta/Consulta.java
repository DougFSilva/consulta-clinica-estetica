package model.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.pessoa.cliente.Cliente;
import model.pessoa.especialista.Especialista;
import model.procedimento.Procedimento;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Entity
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

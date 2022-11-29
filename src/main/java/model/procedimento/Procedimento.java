package model.procedimento;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
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
@EqualsAndHashCode(of = {"id", "tipo"})
@ToString
@Entity
public class Procedimento {

	private Long id;
	
	private String tipo;
	
	private Integer duracao;
	
	private BigDecimal custo;
}

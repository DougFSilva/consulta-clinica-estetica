package br.com.clinicaEstetica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clinicaEstetica.model.procedimento.Procedimento;

@Repository
public interface ProcedimentoReporitory extends JpaRepository<Procedimento, Long> {

	Optional<Procedimento> findByTipo(String tipo);

}

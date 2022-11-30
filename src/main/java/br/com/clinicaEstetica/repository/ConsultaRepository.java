package br.com.clinicaEstetica.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clinicaEstetica.model.consulta.Consulta;
import br.com.clinicaEstetica.model.pessoa.especialista.Especialista;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	List<Consulta> findByDataAndEspecialista(LocalDate data, Especialista especialista);

	Page<Consulta> findByEspecialista(Pageable paginacao, Especialista especialista);

	Page<Consulta> findByData(Pageable paginacao, LocalDate data);

}

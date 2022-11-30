package br.com.clinicaEstetica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clinicaEstetica.model.pessoa.Email;
import br.com.clinicaEstetica.model.pessoa.especialista.Especialista;
import br.com.clinicaEstetica.model.procedimento.Procedimento;

@Repository
public interface EspecialistaRepository extends JpaRepository<Especialista, Long>{

	Optional<Especialista> findByCpf(String cpf);

	Optional<Especialista> findByEmail(Email email);

	Optional<Especialista> findByRegistro(String registro);
	
	List<Especialista> findByCpfOrEmailOrRegistro(String cpf, Email email, String registro);

	Page<Especialista> findByProcedimentos(Pageable paginacao, Procedimento procedimento);
}

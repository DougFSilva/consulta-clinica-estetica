package br.com.clinicaEstetica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clinicaEstetica.model.pessoa.Email;
import br.com.clinicaEstetica.model.pessoa.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Optional<Cliente> findByEmail(Email email);

	Optional<Cliente> findByCpf(String cpf);

}

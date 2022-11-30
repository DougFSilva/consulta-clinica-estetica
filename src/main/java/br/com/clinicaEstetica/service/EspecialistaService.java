package br.com.clinicaEstetica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clinicaEstetica.exception.ObjetoNaoEncontradoException;
import br.com.clinicaEstetica.exception.ViolacaoDeIntegridadeDeDadosException;
import br.com.clinicaEstetica.model.pessoa.Email;
import br.com.clinicaEstetica.model.pessoa.especialista.DadosAdicionarOuRemoverProcedimento;
import br.com.clinicaEstetica.model.pessoa.especialista.DadosCriarEspecialista;
import br.com.clinicaEstetica.model.pessoa.especialista.DadosEditarEspecialista;
import br.com.clinicaEstetica.model.pessoa.especialista.Especialista;
import br.com.clinicaEstetica.model.procedimento.Procedimento;
import br.com.clinicaEstetica.repository.EspecialistaRepository;

@Service
public class EspecialistaService {

	@Autowired
	private EspecialistaRepository repository;
	
	@Autowired
	private ProcedimentoService procedimentoService;

	@Transactional
	public Especialista criar(DadosCriarEspecialista dados) {
		verificarIntegridadeDeDados(dados);
		Email email = new Email(dados.email());
		Especialista especialista = new Especialista(null, dados.nome(), dados.cpf(), dados.telefone(), email,
				dados.registro(), new ArrayList<>());
		return repository.save(especialista);
	}

	@Transactional
	public Especialista adicionarProcedimento(DadosAdicionarOuRemoverProcedimento dados) {
		Especialista especialista = buscar(dados.id());
		dados.tipos().forEach(tipo -> {
			Procedimento procedimento = procedimentoService.buscarPorTipo(tipo);
			if(especialista.getProcedimentos().contains(procedimento)) {
				throw new ViolacaoDeIntegridadeDeDadosException(especialista.getNome() + " já possui o procedimento " + procedimento.getTipo());
			}
			especialista.adicionarProcedimento(procedimentoService.buscarPorTipo(tipo));
		});
		return repository.save(especialista);
	}

	@Transactional
	public Especialista removerProcedimento(DadosAdicionarOuRemoverProcedimento dados) {
		Especialista especialista = buscar(dados.id());
		dados.tipos().forEach(tipo -> especialista.removerProcedimento(procedimentoService.buscarPorTipo(tipo)));
		return repository.save(especialista);
	}

	public void remover(Long id) {
		Especialista especialista = buscar(id);
		repository.delete(especialista);
	}

	@Transactional
	public Especialista editar(DadosEditarEspecialista dados) {
		verificarIntegridadeDeDados(dados);
		Especialista especialista = buscar(dados.id());
		especialista.setNome(dados.nome());
		especialista.setCpf(dados.cpf());
		especialista.setEmail(new Email(dados.email()));
		especialista.setTelefone(dados.telefone());
		especialista.setRegistro(dados.registro());
		return repository.save(especialista);
	}

	public Especialista buscar(Long id) {
		Optional<Especialista> especialista = repository.findById(id);
		return especialista
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Especialista com id " + id + " não encontrado!"));
	}

	public Especialista buscarPorCpf(String cpf) {
		Optional<Especialista> especialista = repository.findByCpf(cpf);
		return especialista.orElseThrow(
				() -> new ObjetoNaoEncontradoException("Especialista com cpf " + cpf + " não encontrado!"));
	}

	public Especialista buscarPorRegistro(String registro) {
		Optional<Especialista> especialista = repository.findByRegistro(registro);
		return especialista.orElseThrow(
				() -> new ObjetoNaoEncontradoException("Especialista com registro " + registro + " não encontrado!"));
	}

	public List<Especialista> buscarTodos() {
		return repository.findAll();
	}

	private void verificarIntegridadeDeDados(DadosCriarEspecialista dados) {
		Email email = new Email(dados.email());
		List<Especialista> especialistas = repository.findByCpfOrEmailOrRegistro(dados.cpf(), email,
				dados.registro());
		especialistas.forEach(especialista -> {
			if (especialista.getCpf().equals(dados.cpf())) {
				throw new ViolacaoDeIntegridadeDeDadosException("Especialista com cpf " + dados.cpf() + " já cadastrado!");
			}
			if (especialista.getEmail().equals(email)) {
				throw new ViolacaoDeIntegridadeDeDadosException("Especialista com email " + dados.email() + " já cadastrado!");
			}
			if (especialista.getRegistro().equals(dados.registro())) {
				throw new ViolacaoDeIntegridadeDeDadosException("Especialista com registro " + dados.registro() + " já cadastrado!");
			}
		});

	}

	private void verificarIntegridadeDeDados(DadosEditarEspecialista dados) {
		Email email = new Email(dados.email());
		List<Especialista> especialistas = repository.findByCpfOrEmailOrRegistro(dados.cpf(), email,
				dados.registro());
		especialistas.forEach(especialista -> {
			if(especialista.getId() != dados.id()) {
				if (especialista.getCpf().equals(dados.cpf())) {
					throw new ViolacaoDeIntegridadeDeDadosException("Especialista com cpf " + dados.cpf() + " já cadastrado!");
				}
				if (especialista.getEmail().equals(email)) {
					throw new ViolacaoDeIntegridadeDeDadosException("Especialista com email " + dados.email() + " já cadastrado!");
				}
				if (especialista.getRegistro().equals(dados.registro())) {
					throw new ViolacaoDeIntegridadeDeDadosException("Especialista com registro " + dados.registro() + " já cadastrado!");
				}
			}
		});

	}
}

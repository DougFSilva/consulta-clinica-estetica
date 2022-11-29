package br.com.clinicaEstetica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clinicaEstetica.exception.ObjetoNaoEncontradoException;
import br.com.clinicaEstetica.exception.ViolacaoDeIntegridadeDeDadosException;
import br.com.clinicaEstetica.model.pessoa.Email;
import br.com.clinicaEstetica.model.pessoa.Endereco;
import br.com.clinicaEstetica.model.pessoa.cliente.Cliente;
import br.com.clinicaEstetica.model.pessoa.cliente.DadosCriarCliente;
import br.com.clinicaEstetica.model.pessoa.cliente.DadosEditarCliente;
import br.com.clinicaEstetica.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired 
	private ClienteRepository repository;
	
	@Transactional
	public Cliente criar(DadosCriarCliente dados) {
		verificarIntegridadeDeDados(dados);
		Email email = new Email(dados.email());
		Endereco endereco = new Endereco(null, dados.estado(), dados.cidade(), dados.bairro(), dados.rua(), dados.numero());
		Cliente cliente = new Cliente(null, dados.nome(), dados.cpf(), dados.telefone(), email, endereco);
		return repository.save(cliente);
	}
	
	public void remover(Long id) {
		Cliente cliente = buscar(id);
		repository.delete(cliente);
	}
	
	@Transactional
	public Cliente editar(DadosEditarCliente dados) {
		Cliente cliente = buscar(dados.id());
		verificarIntegridadeDeDados(cliente, dados);
		cliente.setNome(dados.nome());
		cliente.setCpf(dados.cpf());
		cliente.setTelefone(dados.telefone());
		cliente.setEmail(new Email(dados.email()));
		cliente.getEndereco().setEstado(dados.estado());
		cliente.getEndereco().setCidade(dados.cidade());
		cliente.getEndereco().setBairro(dados.bairro());
		cliente.getEndereco().setRua(dados.rua());
		cliente.getEndereco().setNumero(dados.numero());
		return repository.save(cliente);
		
	}
	
	public Cliente buscar(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente com id " + id + " não encontrado!"));
	}
	
	public Cliente buscarPorCpf(String cpf) {
		Optional<Cliente> cliente = repository.findByCpf(cpf);
		return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente com cpf " + cpf + " não encontrado!"));
	}
	
	public List<Cliente> buscarTodos(){
		return repository.findAll();
	}
	
	private void verificarIntegridadeDeDados(DadosCriarCliente dados) {
		if(repository.findByEmail(new Email(dados.email())).isPresent()) {
			throw new ViolacaoDeIntegridadeDeDadosException("Usuário com email " + dados.email() + " já cadastrado!");
		}
		if(repository.findByCpf(dados.cpf()).isPresent()) {
			throw new ViolacaoDeIntegridadeDeDadosException("Usuário com cpf " + dados.cpf() + " já cadastrado!");
		}
	}
	
	private void verificarIntegridadeDeDados(Cliente cliente, DadosEditarCliente dados) {
		Optional<Cliente> clientePorCpf = repository.findByCpf(dados.cpf());
		Optional<Cliente> clientePorEmail = repository.findByEmail(new Email(dados.email()));
		if(clientePorCpf.isPresent() && !clientePorCpf.get().equals(cliente)) {
			throw new ViolacaoDeIntegridadeDeDadosException("Usuário com cpf " + dados.cpf() + " já cadastrado!");
		}
		if(clientePorEmail.isPresent() && !clientePorEmail.get().equals(cliente)) {
			throw new ViolacaoDeIntegridadeDeDadosException("Usuário com email " + dados.email() + " já cadastrado!");
		}
	}
	
}

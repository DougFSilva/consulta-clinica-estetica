package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.pessoa.cliente.Cliente;
import model.pessoa.cliente.DadosCriarCliente;
import repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired 
	private ClienteRepository repository;
	
	public Cliente criarCliente(DadosCriarCliente dados) {
		return null;
	}
}

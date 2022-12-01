package br.com.clinicaEstetica.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.clinicaEstetica.model.pessoa.cliente.Cliente;
import br.com.clinicaEstetica.model.pessoa.cliente.DadosCriarCliente;
import br.com.clinicaEstetica.model.pessoa.cliente.DadosDeCliente;
import br.com.clinicaEstetica.model.pessoa.cliente.DadosEditarCliente;
import br.com.clinicaEstetica.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@PostMapping
	public ResponseEntity<Cliente> criar(@RequestBody @Valid  DadosCriarCliente dados){
		Cliente cliente= service.criar(dados);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping()
	public ResponseEntity<Cliente> editar(@RequestBody @Valid  DadosEditarCliente dados){
		return ResponseEntity.ok().body(service.editar(dados));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DadosDeCliente> buscar(@PathVariable Long id){
		return ResponseEntity.ok().body(service.buscarDados(id));
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<DadosDeCliente> buscarPorCpf(@PathVariable String cpf){
		return ResponseEntity.ok().body(service.buscarPorCpf(cpf));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosDeCliente>> buscarTodos(Pageable paginacao){
		return ResponseEntity.ok().body(service.buscarTodos(paginacao));
	}
	
	
}

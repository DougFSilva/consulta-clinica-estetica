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

import br.com.clinicaEstetica.model.pessoa.especialista.DadosAdicionarOuRemoverProcedimento;
import br.com.clinicaEstetica.model.pessoa.especialista.DadosCriarEspecialista;
import br.com.clinicaEstetica.model.pessoa.especialista.DadosDeEspecialista;
import br.com.clinicaEstetica.model.pessoa.especialista.DadosEditarEspecialista;
import br.com.clinicaEstetica.model.pessoa.especialista.Especialista;
import br.com.clinicaEstetica.service.EspecialistaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/especialista")
public class EspecialistaController {

	
	@Autowired
	private EspecialistaService service;
	
	@PostMapping
	public ResponseEntity<Especialista> criar(@RequestBody @Valid DadosCriarEspecialista dados){
		Especialista especialista = service.criar(dados);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(especialista.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/adicionar-procedimento")
	public ResponseEntity<Especialista> adicionarProcedimento(@RequestBody @Valid DadosAdicionarOuRemoverProcedimento dados){
		return ResponseEntity.ok().body(service.adicionarProcedimento(dados));
	}
	
	@PostMapping(value = "/remover-procedimento")
	public ResponseEntity<Especialista> removerProcedimento(@RequestBody @Valid DadosAdicionarOuRemoverProcedimento dados){
		return ResponseEntity.ok().body(service.removerProcedimento(dados));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping()
	public ResponseEntity<Especialista> editar(@RequestBody DadosEditarEspecialista dados){
		return ResponseEntity.ok().body(service.editar(dados));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DadosDeEspecialista> buscar(@PathVariable Long id){
		return ResponseEntity.ok().body(service.buscarDados(id));
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<DadosDeEspecialista> buscarPorCpf(@PathVariable String cpf){
		return ResponseEntity.ok().body(service.buscarPorCpf(cpf));
	}
	
	@GetMapping(value = "/registro/{registro}")
	public ResponseEntity<DadosDeEspecialista> buscarPorRegistro(@PathVariable String registro){
		return ResponseEntity.ok().body(service.buscarPorRegistro(registro));
	}
	
	@GetMapping(value = "/procedimento/{tipo}")
	public ResponseEntity <Page<DadosDeEspecialista>> buscarPorTipoDeProcedimento(Pageable paginacao, @PathVariable String tipo){
		return ResponseEntity.ok().body(service.buscarPorTipoDeProcedimento(paginacao, tipo));
	}
	
	@GetMapping()
	public ResponseEntity<Page<DadosDeEspecialista>> buscarTodos(Pageable paginacao){
		return ResponseEntity.ok().body(service.buscarTodos(paginacao));
	}
}

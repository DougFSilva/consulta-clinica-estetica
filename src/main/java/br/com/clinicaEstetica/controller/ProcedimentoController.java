package br.com.clinicaEstetica.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.clinicaEstetica.model.procedimento.DadosCriarProcedimento;
import br.com.clinicaEstetica.model.procedimento.DadosEditarProcedimento;
import br.com.clinicaEstetica.model.procedimento.Procedimento;
import br.com.clinicaEstetica.service.ProcedimentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/procedimento")
public class ProcedimentoController {

	@Autowired
	private ProcedimentoService service;
	
	@PostMapping
	public ResponseEntity<Procedimento> criar(@RequestBody @Valid DadosCriarProcedimento dados){
		Procedimento procedimento = service.criar(dados);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(procedimento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	public ResponseEntity<Procedimento> editar(@RequestBody DadosEditarProcedimento dados){
		return ResponseEntity.ok().body(service.editar(dados));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Procedimento> buscar(@PathVariable Long id){
		return ResponseEntity.ok().body(service.buscar(id));
	}
	
	@GetMapping(value = "/tipo/{tipo}")
	public ResponseEntity<Procedimento> buscarPorTipo(@PathVariable String tipo){
		return ResponseEntity.ok().body(service.buscarPorTipo(tipo));
	}
	
	@GetMapping
	public ResponseEntity<List<Procedimento>> buscarTodos(){
		return ResponseEntity.ok().body(service.buscarTodos());
	}
	
}

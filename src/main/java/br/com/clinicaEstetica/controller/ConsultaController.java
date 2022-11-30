package br.com.clinicaEstetica.controller;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.clinicaEstetica.model.consulta.Consulta;
import br.com.clinicaEstetica.model.consulta.DadosCriarConsulta;
import br.com.clinicaEstetica.model.consulta.DadosDeConsulta;
import br.com.clinicaEstetica.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Consulta> marcar(@RequestBody DadosCriarConsulta dados){
		Consulta consulta = service.marcar(dados);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consulta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> desmarcar(@PathVariable Long id){
		service.desmarcar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Consulta> buscar(@PathVariable Long id){
		return ResponseEntity.ok().body(service.buscar(id));
	}
	
	@GetMapping(value = "/especialista/{id}")
	public ResponseEntity<Page<DadosDeConsulta>> buscarTodasPorEspecialista(Pageable paginacao, @PathVariable Long id){
		return ResponseEntity.ok().body(service.buscarTodasPorEspecialista(paginacao, id));
	}
	
	@GetMapping(value = "/data/{data}")
		public ResponseEntity<Page<DadosDeConsulta>> buscarTodasPorData(Pageable paginacao, @PathVariable LocalDate data){
			return ResponseEntity.ok().body(service.buscarTodasPorData(paginacao, data));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosDeConsulta>> buscarTodas(Pageable paginacao){
		return ResponseEntity.ok().body(service.buscarTodas(paginacao));
	}
}

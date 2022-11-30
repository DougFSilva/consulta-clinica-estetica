package br.com.clinicaEstetica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clinicaEstetica.exception.ObjetoNaoEncontradoException;
import br.com.clinicaEstetica.exception.ViolacaoDeIntegridadeDeDadosException;
import br.com.clinicaEstetica.model.procedimento.DadosCriarProcedimento;
import br.com.clinicaEstetica.model.procedimento.DadosEditarProcedimento;
import br.com.clinicaEstetica.model.procedimento.Procedimento;
import br.com.clinicaEstetica.repository.ProcedimentoReporitory;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoReporitory reporitory;
	
	@Transactional
	public Procedimento criar(DadosCriarProcedimento dados) {
		verificarIntegridadeDeDados(dados);
		Procedimento procedimento = new Procedimento(null, dados.tipo(), dados.duracao(), dados.custo());
		return reporitory.save(procedimento);	
	}
	
	public void remover(Long id) {
		Procedimento procedimento = buscar(id);
		reporitory.delete(procedimento);
	}
	
	@Transactional
	public Procedimento editar(DadosEditarProcedimento dados) {
		verificarIntegridadeDeDados(dados);
		Procedimento procedimento = buscar(dados.id());
		procedimento.setTipo(dados.tipo());
		procedimento.setDuracao(dados.duracao());
		procedimento.setCusto(dados.custo());
		return reporitory.save(procedimento);
	}
	
	public Procedimento buscar(Long id) {
		Optional<Procedimento> procedimento = reporitory.findById(id);
		return procedimento.orElseThrow(() -> new ObjetoNaoEncontradoException("Procedimento com id " + id + " não encontrado!"));
	}
	
	public Procedimento buscarPorTipo(String tipo) {
		Optional<Procedimento> procedimento = reporitory.findByTipo(tipo);
		return procedimento.orElseThrow(() -> new ObjetoNaoEncontradoException("Procedimento do tipo " + tipo + " não encontrado!"));
	}
	
	public List<Procedimento> buscarTodos(){
		return reporitory.findAll();
	}
	
	private void verificarIntegridadeDeDados(DadosCriarProcedimento dados) {
		if(reporitory.findByTipo(dados.tipo()).isPresent()) {
			throw new ViolacaoDeIntegridadeDeDadosException("Procedimento do tipo " + dados.tipo() + " já cadastrado!");
		}
	}
	
private void verificarIntegridadeDeDados(DadosEditarProcedimento dados) {
		Optional<Procedimento> procedimentoPorTipo = reporitory.findByTipo(dados.tipo());
		if(procedimentoPorTipo.isPresent() && (procedimentoPorTipo.get().getId() != dados.id())) {
			throw new ViolacaoDeIntegridadeDeDadosException("Procedimento do tipo " + dados.tipo() + " já cadastrado!");
		}
			
	}
}

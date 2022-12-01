package br.com.clinicaEstetica.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clinicaEstetica.exception.ErroNaMarcacaoDeConsultaException;
import br.com.clinicaEstetica.exception.ObjetoNaoEncontradoException;
import br.com.clinicaEstetica.model.consulta.Consulta;
import br.com.clinicaEstetica.model.consulta.DadosDeConsulta;
import br.com.clinicaEstetica.model.consulta.DadosMarcarConsulta;
import br.com.clinicaEstetica.model.consulta.DadosRemarcarConsulta;
import br.com.clinicaEstetica.model.pessoa.cliente.Cliente;
import br.com.clinicaEstetica.model.pessoa.especialista.Especialista;
import br.com.clinicaEstetica.model.procedimento.Procedimento;
import br.com.clinicaEstetica.repository.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository repository;

	@Autowired
	private EspecialistaService especialistaService;

	@Autowired
	private ProcedimentoService procedimentoService;

	@Autowired
	private ClienteService clienteService;

	@Transactional
	public Consulta marcar(DadosMarcarConsulta dados) {
		Especialista especialista = especialistaService.buscar(dados.especialistaId());
		Procedimento procedimento = procedimentoService.buscar(dados.procedimentoId());
		if (!especialista.getProcedimentos().contains(procedimento)) {
			throw new ErroNaMarcacaoDeConsultaException(
					"Especialista " + especialista.getNome() + " não executa o procedimento " + procedimento.getTipo());
		}
		verificarDisponibilidadeDeHorario(especialista, procedimento, dados.data(), dados.horario());
		Cliente cliente = clienteService.buscar(dados.clienteId());
		LocalTime horarioFinal = LocalTime.ofSecondOfDay(dados.horario().toSecondOfDay() + (procedimento.getDuracao()*60));
		Consulta consulta = new Consulta(null, cliente, procedimento, especialista, dados.data(), dados.horario(), horarioFinal);
		return repository.save(consulta);
	}

	public void desmarcar(Long id) {
		Consulta consulta = buscar(id);
		repository.delete(consulta);
	}
	
	@Transactional
	public Consulta remarcar(DadosRemarcarConsulta dados) {
		Consulta consulta = buscar(dados.id());
		verificarDisponibilidadeDeHorario(consulta.getEspecialista(), consulta.getProcedimento(), dados.data(), dados.horario());
		LocalTime horarioFinal = LocalTime.ofSecondOfDay(dados.horario().toSecondOfDay() + (consulta.getProcedimento().getDuracao()*60));
		consulta.setData(dados.data());
		consulta.setHorarioInicial(dados.horario());
		consulta.setHorarioFinal(horarioFinal);
		return repository.save(consulta);
	}

	public Consulta buscar(Long id) {
		Optional<Consulta> consulta = repository.findById(id);
		return consulta
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Consulta com id " + id + " não encontrada!"));
	}

	public Page<DadosDeConsulta> buscarTodasPorEspecialista(Pageable paginacao, Long especialistaId) {
		Especialista especialista = especialistaService.buscar(especialistaId);
		return repository.findByEspecialista(paginacao, especialista).map(DadosDeConsulta::new);
	}

	public Page<DadosDeConsulta> buscarTodasPorData(Pageable paginacao, LocalDate data) {
		return repository.findByData(paginacao, data).map(DadosDeConsulta::new);
	}

	public Page<DadosDeConsulta> buscarTodas(Pageable paginacao) {
		return repository.findAll(paginacao).map(DadosDeConsulta::new);
	}

	private void verificarDisponibilidadeDeHorario(Especialista especialista, Procedimento procedimento, LocalDate data,
			LocalTime horarioInicial) {
		LocalTime horarioFinal = LocalTime.ofSecondOfDay(horarioInicial.toSecondOfDay() + (procedimento.getDuracao()*60));
		List<Consulta> consultas = repository.findByDataAndEspecialista(data, especialista);
		consultas.forEach(consulta -> {
			if (horarioInicial.toSecondOfDay() >= consulta.getHorarioInicial().toSecondOfDay()
					&& horarioInicial.toSecondOfDay() < consulta.getHorarioFinal().toSecondOfDay()
					|| horarioFinal.toSecondOfDay() > consulta.getHorarioInicial().toSecondOfDay()
					&& horarioFinal.toSecondOfDay() < consulta.getHorarioFinal().toSecondOfDay()) {
				throw new ErroNaMarcacaoDeConsultaException("Horário " + horarioInicial + " não disponível para agendamento!");
			}
		});
	}

}

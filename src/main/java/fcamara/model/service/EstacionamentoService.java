package fcamara.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fcamara.controller.dto.EstacionamentoDto;
import fcamara.controller.form.EstacionamentoForm;
import fcamara.model.entity.Estacionamento;
import fcamara.model.repository.EstacionamentoRepository;

@Service
public class EstacionamentoService {
	
	private EstacionamentoRepository estacionamentoRepository;
	
	
	@Autowired
	public EstacionamentoService(EstacionamentoRepository estacionamentoRepository) {
		this.estacionamentoRepository = estacionamentoRepository;
	}


	public List<EstacionamentoDto> listar() {
		return EstacionamentoDto.converter(estacionamentoRepository.findAll());
	}
	
	
	public Estacionamento buscar(String cnpj) {
		return estacionamentoRepository.findByCnpj(cnpj);
	}
	
	public String cadastrar(Estacionamento estacionamento) {
		Estacionamento auxiliar = null;
		auxiliar = estacionamentoRepository.findByCnpj(estacionamento.getCnpj());
		if(auxiliar != null) 
			return "Estacionamento já cadastrado com esse Cnpj!";
		estacionamentoRepository.save(estacionamento);
		return "Cadastrado com Sucesso!";
		
	}
	
	public String deletar(String cnpj) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		if(estacionamento == null) 
			return "Estacionamento não encontrado!";
		estacionamentoRepository.deleteByCnpj(cnpj);
		return "Estacionamento deletado com Sucesso!";
	}
	
	public String atualizar(String cnpj,EstacionamentoForm form) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		if(estacionamento == null) 
			return "Estacionamento não encontrado!";
		form.atualizar(cnpj, estacionamentoRepository);
		return "Estacionamento atualizado com Sucesso!";
	}
}

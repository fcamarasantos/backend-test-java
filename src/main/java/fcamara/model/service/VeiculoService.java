package fcamara.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fcamara.controller.dto.VeiculoDto;
import fcamara.controller.form.VeiculoForm;
import fcamara.model.entity.Veiculo;
import fcamara.model.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	
	
	public List<VeiculoDto> listar() {
		return VeiculoDto.converter(veiculoRepository.findAll());
	}
	
	public Veiculo buscar(String placa) {
		return veiculoRepository.findByPlaca(placa);
	}
	
	public String cadastrar(Veiculo veiculo) {
		Veiculo auxiliar = null;
		auxiliar = veiculoRepository.findByPlaca(veiculo.getPlaca());
		if(auxiliar != null) 
			return "Veiculo já cadastrado com está placa!";
		veiculoRepository.save(veiculo);
		return "Cadastrado com Sucesso!";
	}
		
	public String deletar(String placa) {
		Veiculo veiculo = veiculoRepository.findByPlaca(placa);
		if(veiculo == null) 
			return "Veiculo não encontrado!";
		veiculoRepository.deleteByPlaca(placa);
		return "Veiculo deletado com Sucesso!";
	}
	
	public String atualizar(String placa, VeiculoForm form) {
		Veiculo veiculo = veiculoRepository.findByPlaca(placa);
		if(veiculo == null) 
			return "Veiculo não encontrado!";
		form.atualizar(placa, veiculoRepository);
		return "Veiculo atualizado com Sucesso!";
	}
}

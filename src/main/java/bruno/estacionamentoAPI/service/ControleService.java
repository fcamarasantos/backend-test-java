package bruno.estacionamentoAPI.service;

import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.model.Veiculo;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import bruno.estacionamentoAPI.repository.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControleService {

  @Autowired
  private EstacionamentoRepository estacionamentoRepository;

  @Autowired
  private VeiculosRepository veiculosRepository;

  public boolean estacionar(Veiculo veiculo, Estacionamento estacionamento) {
    if (!veiculo.isEstacionado()) {
      switch (veiculo.getTipo()) {
        case MOTO:
          if(estacionamento.getVagasOcupadasMotos() == estacionamento.getQuantidadeVagasMotos()) return false;
          estacionamento.setVagasOcupadasMotos(estacionamento.getVagasOcupadasMotos() + 1);
          veiculo.setEstacionado(true);
          estacionamentoRepository.save(estacionamento);
          veiculosRepository.save(veiculo);
          return true;
        case CARRO:
          if(estacionamento.getVagasOcupadasCarros() == estacionamento.getQuantidadeVagasCarros()) return false;
          estacionamento.setVagasOcupadasCarros(estacionamento.getVagasOcupadasCarros() + 1);
          veiculo.setEstacionado(true);
          estacionamentoRepository.save(estacionamento);
          veiculosRepository.save(veiculo);
          return true;
      }
    }
    return false;
    }


  public boolean retirar(Veiculo veiculo, Estacionamento estacionamento) {
    if(veiculo.isEstacionado()) {
      switch (veiculo.getTipo()) {
        case MOTO:
          estacionamento.setVagasOcupadasMotos(estacionamento.getVagasOcupadasMotos() - 1);
          veiculo.setEstacionado(false);
          estacionamentoRepository.save(estacionamento);
          veiculosRepository.save(veiculo);
          return true;
        case CARRO:
          estacionamento.setVagasOcupadasCarros(estacionamento.getVagasOcupadasCarros() - 1);
          veiculo.setEstacionado(false);
          estacionamentoRepository.save(estacionamento);
          veiculosRepository.save(veiculo);
          return true;
      }
    }
    return false;
  }
}

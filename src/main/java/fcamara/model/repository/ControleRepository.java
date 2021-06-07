package fcamara.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fcamara.model.entity.Controle;
import fcamara.model.entity.Veiculo;

public interface ControleRepository extends JpaRepository<Controle, Long>{

	@Query("select c from Controle c join c.veiculo v where v.placa=:placa and c.datahora_saida = null")
	Controle findByPlacaAndDatahoraSaidaNull(@Param("placa") String placa);

	@Query("select c from Controle c join c.estacionamento e where e.cnpj=:cnpj and c.datahora_saida = null")
	List<Controle> findByCnpjAndDatahoraSaidaNull(@Param("cnpj") String cnpj);

	@Query("select c from Controle c join c.veiculo v join c.estacionamento e where v.placa=:placa and e.cnpj=:cnpj and c.datahora_saida = null")
	Controle findByCnpjAndPlacaAndDatahoraSaidaNull(@Param("cnpj") String cnpj,@Param("placa") String placa);

	@Query("select c from Controle c join c.estacionamento e where e.cnpj=:cnpj and c.datahora_saida <> null")
	List<Controle> findByCnpjAndDatahoraSaidaNotNull(String cnpj);

}

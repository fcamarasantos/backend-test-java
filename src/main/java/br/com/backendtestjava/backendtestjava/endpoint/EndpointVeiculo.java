package br.com.backendtestjava.backendtestjava.endpoint;

import br.com.backendtestjava.backendtestjava.entity.Veiculo;
import br.com.backendtestjava.backendtestjava.respository.RepositoryVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class    EndpointVeiculo {

    @Autowired
    private RepositoryVeiculo repositoryVeiculo;

    @GetMapping
    public List<Veiculo> listAll() {
        return repositoryVeiculo.findAll();
    }

    @PostMapping
    public void addVeiculo(@RequestBody Veiculo veiculo) {
        repositoryVeiculo.save(veiculo);
    }

    @PutMapping(path = "/{id}")
    public void editVeiculo(@PathVariable("id") Long id,@RequestBody Veiculo veiculo) {
        if (repositoryVeiculo.findById(id).isPresent()) {
            veiculo.setId(id);
            repositoryVeiculo.save(veiculo);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteVeiculo(@PathVariable("id") Long id) {
        repositoryVeiculo.deleteById(id);
    }
}

package br.com.backendtestjava.backendtestjava.endpoint;

import br.com.backendtestjava.backendtestjava.entity.Empresa;
import br.com.backendtestjava.backendtestjava.respository.RepositoryEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EndpointEmpresa {
    @Autowired
    private RepositoryEmpresa repositoryEmpresa;

    @GetMapping
    public List<Empresa> listAll() {
        return repositoryEmpresa.findAll();
    }

    @GetMapping(path = "/{id}")
    public Empresa findById(@PathVariable("id") Long id) {
        Empresa empresa = repositoryEmpresa.findById(id).get();
        return empresa;
    }

    @PostMapping
    public void addEmpresa(@RequestBody Empresa empresa) {
        repositoryEmpresa.save(empresa);
    }

    @PutMapping(path = "/{id}")
    public void editEmpresa(@PathVariable("id") Long id, @RequestBody Empresa empresa) {
        if (repositoryEmpresa.findById(id).isPresent()) {
            empresa.setId(id);
            repositoryEmpresa.save(empresa);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmpresa(@PathVariable("id") Long id) {
        repositoryEmpresa.deleteById(id);
    }
}

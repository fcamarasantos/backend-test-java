package br.com.backendtestjava.backendtestjava.endpoint;

import br.com.backendtestjava.backendtestjava.entity.Relatorio;
import br.com.backendtestjava.backendtestjava.respository.RepositoryRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class EndpointRelatorio {

    @Autowired
    private RepositoryRelatorio repositoryRelatorio;

    @GetMapping
    public List<Relatorio> listAll() {
        return repositoryRelatorio.findAll();
    }
}

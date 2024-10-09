package com.fcamarasantos.testebackendjava.controller;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.dto.VagasDisponiveisDTO;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.RegistroEntradaSaidaService;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.EntradaVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.SaidaVeiculoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "entrada-saida/")
public class EntradaSaidaController {

    @Autowired
    private RegistroEntradaSaidaService registroEntradaSaidaService;

    @PostMapping("entrada-veiculo/")
    @Transactional
    public ResponseEntity<?> entradaVeiculo(@Valid @RequestBody EntradaVeiculoDTO entradaVeiculoDTO) {
        registroEntradaSaidaService.entradaVeiculo(entradaVeiculoDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("saida-veiculo/")
    @Transactional
    public ResponseEntity<?> saidaVeiculo(@Valid @RequestBody SaidaVeiculoDTO saidaVeiculoDTO) {
        registroEntradaSaidaService.saidaVeiculo(saidaVeiculoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("vagas-disponiveis/{idEstabelecimento}")
    public ResponseEntity<?> vagasDisponiveis(@Valid @PathVariable Long idEstabelecimento) {
        return ResponseEntity.ok(
                new VagasDisponiveisDTO(registroEntradaSaidaService.vagasDisponiveis(idEstabelecimento))
        );
    }

}

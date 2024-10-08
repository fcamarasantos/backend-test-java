package com.fcamarasantos.testebackendjava.controller;

import com.fcamarasantos.testebackendjava.domain.veiculo.Veiculo;
import com.fcamarasantos.testebackendjava.domain.veiculo.VeiculoRepository;
import com.fcamarasantos.testebackendjava.domain.veiculo.dto.CreateVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.veiculo.dto.UpdateVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.veiculo.dto.VeiculoDetailsDTO;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("veiculos/")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public ResponseEntity<Page<VeiculoDetailsDTO>> findAllVeiculos(Pageable pageable) {
        return ResponseEntity.ok(
                veiculoRepository
                        .findAll(pageable)
                        .map(VeiculoDetailsDTO::new)
        );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createVeiculo(
            @RequestBody @Valid CreateVeiculoDTO createVeiculoDTO,
            UriComponentsBuilder uriBuilder
    ) {
        var veiculo = new Veiculo(createVeiculoDTO);
        var criado = veiculoRepository.save(veiculo);

        var uri = uriBuilder.path("/veiculos/{id}")
                .buildAndExpand(criado.getId()).toUri();

        return ResponseEntity.
                created(uri)
                .body(new VeiculoDetailsDTO(criado));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateVeiculo(
            @PathVariable Long id,
            @Valid @RequestBody UpdateVeiculoDTO updateVeiculoDTO
    ) {
        var veiculo = veiculoRepository.getReferenceById(id);
        veiculo.updateVeiculo(updateVeiculoDTO);

        return ResponseEntity.ok(new VeiculoDetailsDTO(veiculo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVeiculo(@PathVariable Long id) {
        var veiculo = veiculoRepository.getReferenceById(id);
        return ResponseEntity.ok(new VeiculoDetailsDTO(veiculo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteVeiculo(@PathVariable Long id) {
        var veiculo = veiculoRepository.getReferenceById(id);
        veiculoRepository.delete(veiculo);

        return ResponseEntity.noContent().build();
    }
}

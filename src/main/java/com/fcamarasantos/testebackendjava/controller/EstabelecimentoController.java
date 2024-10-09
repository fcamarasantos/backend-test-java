package com.fcamarasantos.testebackendjava.controller;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.Estabelecimento;
import com.fcamarasantos.testebackendjava.domain.estabelecimento.EstabelecimentoRepository;
import com.fcamarasantos.testebackendjava.domain.estabelecimento.dto.CreateEstabelecimentoDTO;
import com.fcamarasantos.testebackendjava.domain.estabelecimento.dto.EstabelecimentoDetailsDto;
import com.fcamarasantos.testebackendjava.domain.estabelecimento.dto.UpdateEstabelecimentoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("estabelecimentos/")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping
    public ResponseEntity<Page<EstabelecimentoDetailsDto>> findAllEstabelecimentos(Pageable pageable) {
        return ResponseEntity.ok(
                estabelecimentoRepository
                        .findAll(pageable)
                        .map(EstabelecimentoDetailsDto::new)
        );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createEstabelecimento(
            @RequestBody @Valid CreateEstabelecimentoDTO createEstabelecimentoDTO,
            UriComponentsBuilder uriBuilder
    ) {
        var estabelecimento = new Estabelecimento(createEstabelecimentoDTO);
        var criado = estabelecimentoRepository.save(estabelecimento);

        var uri = uriBuilder.path("/estabelecimentos/{id}")
                .buildAndExpand(criado.getId()).toUri();

        return ResponseEntity.
                created(uri)
                .body(new EstabelecimentoDetailsDto(criado));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateEstabelecimento(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEstabelecimentoDTO updateEstabelecimentoDTO
    ) {
        var estabelecimento = estabelecimentoRepository.getReferenceById(id);
        estabelecimento.updateEstabelecimento(updateEstabelecimentoDTO);

        return ResponseEntity.ok(new EstabelecimentoDetailsDto(estabelecimento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEstabelecimento(@PathVariable Long id) {
        var estabelecimento = estabelecimentoRepository.getReferenceById(id);
        return ResponseEntity.ok(new EstabelecimentoDetailsDto(estabelecimento));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteEstabelecimento(@PathVariable Long id) {
        var estabelecimento = estabelecimentoRepository.getReferenceById(id);
        estabelecimentoRepository.delete(estabelecimento);

        return ResponseEntity.noContent().build();
    }
}

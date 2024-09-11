package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.service.EstablishmentService;
import com.harrisson.parking_api.to.EstablishmentData;
import com.harrisson.parking_api.to.EstablishmentDataDetails;
import com.harrisson.parking_api.to.EstablishmentList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Tag(name = "Establishment", description = "Establishment API")
@RestController
@RequestMapping("/establishments")
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @Operation(summary = "Criar novo Estabelecimento", description = "Enviando um objeto EstablishmentData, um novo estabelecimento será criado")
    @PostMapping
    @Transactional
    public ResponseEntity<EstablishmentDataDetails> createEstablishment(@RequestBody @Valid EstablishmentData establishmentData, UriComponentsBuilder uriBuilder) {
        var establishment = establishmentService.save(establishmentData);
        var uri = uriBuilder.path("/establishment/getById/{id}").buildAndExpand(establishment.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstablishmentDataDetails(establishment));
    }

    @Operation(summary = "Listar todos os estabelecimentos", description = "Listar todos os estabelecimentos ordenados por nome")
    @GetMapping("/{size}")
    public ResponseEntity<List<EstablishmentList>> getEstablishments(@PathVariable Integer size) {
        Pageable pageable = PageRequest.of(0, size);
        Page<EstablishmentList> establishmentsPage = establishmentService.getEstablishments(pageable);
        return ResponseEntity.ok(establishmentsPage.getContent());
    }

    @Operation(summary = "Obter um estabelecimento por ID", description = "Obter um estabelecimento por ID")
    @GetMapping("getById/{id}")
    public ResponseEntity<EstablishmentData> getEstablishmentById(@PathVariable Long id) {
        var establishment = establishmentService.getById(id);
        return ResponseEntity.ok(new EstablishmentData(establishment));
    }

    @Operation(summary = "Atualizar um estabelecimento", description = "Atualizar um estabelecimento")
    @PutMapping("{id}")
    public ResponseEntity<EstablishmentDataDetails> updateEstablishment(@PathVariable Long id, @RequestBody @Valid EstablishmentDataDetails updatedData) {
        var establishment = establishmentService.getById(id);
        establishment.updateEstablishment(updatedData.toEntity());
        var updatedEstablishment = establishmentService.update(new EstablishmentDataDetails(establishment));
        return ResponseEntity.ok(new EstablishmentDataDetails(updatedEstablishment));
    }

    @Operation(summary = "Deletar um estabelecimento", description = "Deletar um estabelecimento")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Long id) {
        establishmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

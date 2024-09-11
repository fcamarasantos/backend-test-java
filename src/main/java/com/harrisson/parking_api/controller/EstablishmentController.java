package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.Establishment;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Establishment", description = "Establishment API")
@RestController
@RequestMapping("/establishments")
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @Operation(summary = "Create a new establishment")
    @PostMapping
    @Transactional
    public ResponseEntity<EstablishmentDataDetails> createEstablishment(@RequestBody @Valid EstablishmentData establishmentData, UriComponentsBuilder uriBuilder) {
        var establishment = new Establishment(establishmentData);
        establishmentService.createEstablishment(establishment);
        var uri = uriBuilder.path("/establishment/getById/{id}").buildAndExpand(establishment.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstablishmentDataDetails(establishment));
    }

    @GetMapping("/{size}")
    public ResponseEntity<Page<EstablishmentList>> getEstablishments(@PageableDefault(size = 10) Pageable page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), size);
        return ResponseEntity.ok(establishmentService.getEstablishments(pageable));
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<EstablishmentData> getEstablishmentById(@PathVariable Long id) {
        var establishment = establishmentService.getById(id);
        return ResponseEntity.ok(new EstablishmentData(establishment));
    }

    @PutMapping
    public ResponseEntity<EstablishmentDataDetails> updateEstablishment(@RequestBody EstablishmentDataDetails establishmentData) {
        var establishment = establishmentService.getById(establishmentData.id());
        establishment.updateEstablishment(establishment);
        return ResponseEntity.ok(new EstablishmentDataDetails(establishment));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Long id) {
        establishmentService.deleteEstablishment(id);
        return ResponseEntity.noContent().build();
    }
}

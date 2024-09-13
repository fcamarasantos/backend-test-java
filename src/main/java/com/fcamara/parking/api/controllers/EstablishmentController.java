package com.fcamara.parking.api.controllers;

import com.fcamara.parking.api.domain.dto.EstablishmentPostDTO;
import com.fcamara.parking.api.domain.entities.Establishment;
import com.fcamara.parking.api.services.EstablishmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/establishment")
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    @Operation(summary = "Create a new establishment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Establishment created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstablishmentPostDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Establishment> create(@Valid @RequestBody EstablishmentPostDTO establishment) {
        return ResponseEntity.ok(establishmentService.create(establishment));
    }

    @Operation(summary = "Get all establishments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all establishments",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Establishment.class))}),
            @ApiResponse(responseCode = "404", description = "Establishments not found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Establishment>> getAll() {
        return ResponseEntity.ok(establishmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Establishment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(establishmentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Establishment> update(@PathVariable Long id, @Valid @RequestBody EstablishmentPostDTO establishment) {
        return ResponseEntity.ok(establishmentService.update(id, establishment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        establishmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

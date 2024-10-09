package com.fcamarasantos.testebackendjava.domain.estabelecimento.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateEstabelecimentoDTO(
        @NotBlank
        String nome,

        @NotBlank @Pattern(regexp = "^\\d{14}$")
        String cnpj,

        @NotBlank @Pattern(regexp = "^\\d{8,20}$")
        String telefone,

        @Valid
        EnderecoDTO endereco,

        @PositiveOrZero
        Integer numVagasMotos,

        @PositiveOrZero
        Integer numVagasCarros
) {
}

package com.fcamara.parking.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstablishmentPostDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatória")
    private String name;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotBlank(message = "O endereço é obrigatório")
    private String address;

    @NotBlank(message = "O telefone é obrigatório")
    private String phone;

    @NotBlank(message = "Quantidade de vagas para motos é obrigatória")
    @NotNull
    private Integer motorcycleSpots;

    @NotBlank(message = "Quantidade de vagas para carros é obrigatória")
    private Integer carSpots;
}
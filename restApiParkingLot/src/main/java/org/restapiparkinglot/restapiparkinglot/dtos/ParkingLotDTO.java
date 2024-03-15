package org.restapiparkinglot.restapiparkinglot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Builder
public class ParkingLotDTO {
    @NotBlank(message = "Must inform name")
    private String name;
    @NotBlank(message = "Must inform CNPJ")
    @CNPJ
    private String cnpj;
    @NotBlank(message = "Must inform address")
    private String address;
    @NotBlank(message = "Must inform phone number")
    @Pattern(regexp = "\\d{11}", message = "Phone number must contain exactly 11 digits")
    private String phoneNumber;
    @NotNull
    private int carSpaces;
    @NotNull
    private int motorcycleSpaces;
}

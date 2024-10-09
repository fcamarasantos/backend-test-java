package com.fcamarasantos.testebackendjava.domain.estabelecimento.dto;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(

        @NotBlank
        String numero,

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        String complemento,

        @Pattern(regexp = "^[A-Z]{2}$")
        String uf,

        @Pattern(regexp = "^\\d{8}$")
        String cep
) {

    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getNumero(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getComplemento(),
                endereco.getUf(),
                endereco.getCep()
        );
    }
}

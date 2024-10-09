package com.fcamarasantos.testebackendjava.domain.estabelecimento.dto;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.Endereco;
import com.fcamarasantos.testebackendjava.domain.estabelecimento.Estabelecimento;

public record EstabelecimentoDetailsDto(
        Long id,
        String nome,
        String cnpj,
        String telefone,
        EnderecoDTO endereco,
        Integer NumVagasMotos,
        Integer NumVagasCarros
) {
    public EstabelecimentoDetailsDto(Estabelecimento estabelecimento) {
        this(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getCnpj(),
                estabelecimento.getTelefone(),
                new EnderecoDTO(estabelecimento.getEndereco()),
                estabelecimento.getNumVagasMotos(),
                estabelecimento.getNumVagasCarros()
        );
    }
}

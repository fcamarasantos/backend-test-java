package com.fcamarasantos.testebackendjava.domain.estabelecimento;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.dto.EnderecoDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {

    public String numero;
    public String logradouro;
    public String bairro;
    public String cidade;
    public String complemento;
    public String uf;
    public String cep;

    public Endereco(EnderecoDTO endereco) {
        this.numero = endereco.numero();
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.complemento = endereco.complemento();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
    }
}

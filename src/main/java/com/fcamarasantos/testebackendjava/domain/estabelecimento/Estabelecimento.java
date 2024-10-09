package com.fcamarasantos.testebackendjava.domain.estabelecimento;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.dto.CreateEstabelecimentoDTO;
import com.fcamarasantos.testebackendjava.domain.estabelecimento.dto.UpdateEstabelecimentoDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "estabelecimentos")
@Entity(name = "Estabelecimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;

    public String telefone;

    public String cnpj;

    @Embedded
    public Endereco endereco;

    @Column(name = "num_vagas_motos")
    public Integer numVagasMotos;

    @Column(name = "num_vagas_carros")
    public Integer numVagasCarros;

    public Estabelecimento(CreateEstabelecimentoDTO createEstabelecimentoDTO) {
        this.nome = createEstabelecimentoDTO.nome();
        this.telefone = createEstabelecimentoDTO.telefone();
        this.cnpj = createEstabelecimentoDTO.cnpj();
        this.endereco = new Endereco(createEstabelecimentoDTO.endereco());
        this.numVagasMotos = createEstabelecimentoDTO.numVagasMotos();
        this.numVagasCarros = createEstabelecimentoDTO.numVagasCarros();

    }

    public void updateEstabelecimento(UpdateEstabelecimentoDTO updateEstabelecimentoDTO) {
        if (updateEstabelecimentoDTO.nome() != null) {
            this.nome = updateEstabelecimentoDTO.nome();
        }

        if (updateEstabelecimentoDTO.telefone() != null) {
            this.telefone = updateEstabelecimentoDTO.telefone();
        }

        if (updateEstabelecimentoDTO.cnpj() != null) {
            this.cnpj = updateEstabelecimentoDTO.cnpj();
        }

        if (updateEstabelecimentoDTO.endereco() != null) {
            this.endereco = new Endereco(updateEstabelecimentoDTO.endereco());
        }

        if (updateEstabelecimentoDTO.numVagasMotos() != null) {
            this.numVagasMotos = updateEstabelecimentoDTO.numVagasMotos();
        }

        if (updateEstabelecimentoDTO.numVagasCarros() != null) {
            this.numVagasCarros = updateEstabelecimentoDTO.numVagasCarros();
        }
    }
}

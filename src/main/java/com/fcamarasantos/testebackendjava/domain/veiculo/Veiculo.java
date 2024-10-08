package com.fcamarasantos.testebackendjava.domain.veiculo;

import com.fcamarasantos.testebackendjava.domain.veiculo.dto.CreateVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.veiculo.dto.UpdateVeiculoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "veiculos")
@Entity(name = "Veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String placa;
    private String cor;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;

    public Veiculo(CreateVeiculoDTO createVeiculoDTO) {
        this.modelo = createVeiculoDTO.modelo();
        this.marca = createVeiculoDTO.marca();
        this.placa = createVeiculoDTO.placa();
        this.tipo = createVeiculoDTO.tipo();
        this.cor = createVeiculoDTO.cor();
    }


    public void updateVeiculo(UpdateVeiculoDTO updateVeiculoDTO){
        this.marca = updateVeiculoDTO.marca();
        this.modelo = updateVeiculoDTO.modelo();
        this.placa = updateVeiculoDTO.placa();
        this.cor = updateVeiculoDTO.cor();
    }
}

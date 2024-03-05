package com.desafioFcamara.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "Veiculo")

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "cor", nullable = false)
    private String cor;

    @Column (name = "placa", nullable = false, length = 7)
    private String placa;

    @Column (name = "tipo", nullable = false)
    private String tipo;

    public Veiculo(int id, String marca, String modelo, String cor, String placa, String tipo){
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.tipo = tipo;

    }

    //getters

    public int getId(){
        return id;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public String getCor(){
        return cor;
    }

    public String getPlaca(){
        return placa;
    }

    public String getTipo(){
        return tipo;
    }

    //setters

    public void setId(int id){
        this.id = id;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public void setCor(String cor){
        this.cor = cor;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }

    public void setTipo(String tipo){
        this.tipo= tipo;
    }

    
}

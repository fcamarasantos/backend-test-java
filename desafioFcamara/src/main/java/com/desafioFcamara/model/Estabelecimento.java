package com.desafioFcamara.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Estabelecimento")

public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cnpj", nullable = false, length = 14, unique = true)
    private int cnpj;

    @Column(name = "endereco", nullable = false, length = 512)
    private String endereco;

    @Column (name = "telefone", nullable = false, length = 11)
    private int telefone;

    @Column (name = "vagasMotos", nullable = false)
    private int vagasMotos;

    @Column (name = "vagasCarros", nullable = false)
    private int vagasCarros;

    @Column (name = "qtdCarrosEstacionados", nullable = false)
    private int qtdCarrosEstacionados;

    @Column (name = "qtdMotosEstacionadas", nullable = false)
    private int qtdMotosEstacionadas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "veiculos",
            joinColumns = {@JoinColumn(name = "estabelecimento_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "veiculo_id", referencedColumnName = "id")})
    private List<Veiculo> veiculos;

    public Estabelecimento(int id, String nome, int cnpj, String endereco, int telefone, int vagasMotos, int vagasCarros, int qtdCarrosEstacionados, int qtdMotosEstacionadas, List <Veiculo> veiculos){
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.vagasMotos = vagasMotos;
        this.vagasCarros = vagasCarros;
        this.qtdCarrosEstacionados = qtdCarrosEstacionados;
        this.qtdMotosEstacionadas = qtdMotosEstacionadas;
        this.veiculos = veiculos;
    }

    //getters

    public int getQtdCarrosEstacionados(){
        return qtdCarrosEstacionados;
    }

    public int getQtdMotosEstacionadas(){
        return qtdMotosEstacionadas;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public int getCnpj(){
        return cnpj;
    }

    public String getEndereco(){
        return endereco;
    }

    public int getTelefone(){
        return telefone;
    }

    public int getVagasCarros(){
        return vagasCarros;
    }

    public int getVagasMotos(){
        return vagasMotos;
    }

    //setters

    public void setQtdCarrosEstacionados(int qtdCarrosEstacionados){
        this.qtdCarrosEstacionados = qtdCarrosEstacionados;
    }
    public void setQtdMotosEstacionadas(int qtdMotosEstacionadas){
        this.qtdMotosEstacionadas = qtdMotosEstacionadas;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCnpj(int cnpj){
        this.cnpj = cnpj;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void setTelefone(int telefone){
        this.telefone = telefone;
    }

    public void setVagasCarros(int vagasCarros){
        this.vagasCarros = vagasCarros;
    }

    public void setVagasMotos(int vagasMotos){
        this.vagasMotos = vagasMotos;
    }
}

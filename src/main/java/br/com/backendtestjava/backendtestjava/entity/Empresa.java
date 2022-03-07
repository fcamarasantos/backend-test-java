package br.com.backendtestjava.backendtestjava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private int vagas_moto;
    private int vagas_carro;


    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getVagas_moto() {
        return vagas_moto;
    }

    public void setVagas_moto(int vagas_moto) {
        this.vagas_moto = vagas_moto;
    }

    public int getVagas_carro() {
        return vagas_carro;
    }

    public void setVagas_carro(int vagas_carro) {
        this.vagas_carro = vagas_carro;
    }
}

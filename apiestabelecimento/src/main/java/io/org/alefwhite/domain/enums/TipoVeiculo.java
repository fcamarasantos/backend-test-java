package io.org.alefwhite.domain.enums;

public enum TipoVeiculo {
    MOTO('M'),
    CARRO('C');

    private char tipo;

    TipoVeiculo(char tipo){
        this.tipo = tipo;
    }

    public char getTipoVeiculo(){
        return this.tipo;
    }

}

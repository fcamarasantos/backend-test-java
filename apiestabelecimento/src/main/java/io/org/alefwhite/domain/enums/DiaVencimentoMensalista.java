package io.org.alefwhite.domain.enums;

public enum DiaVencimentoMensalista {
    DEZ(10),
    VINTE(20),
    TRINTA(30);

    private Integer dia;

    DiaVencimentoMensalista(Integer dia){
        this.dia = dia;
    }

    public Integer getDiaVencimentoMensalista(){
        return this.dia;
    }

}

package com.example.nsldizimo.model;

import java.io.Serializable;

public class Coletas implements Serializable {
    private Integer idColeta;
    private String valorColeta;
    private String dataColeta;
    private Integer dia;
    private Integer mes;
    private Integer ano;


    public Integer getDia() {return dia;}

    public void setDia(Integer dia) {this.dia = dia;}



    public Integer getMes() {return mes;}

    public void setMes(Integer mes) {this.mes = mes;}

    public Integer getAno() {return ano;}

    public void setAno(Integer ano) {this.ano = ano;}

    public Integer getIdColeta() {return idColeta;}

    public void setIdColeta(Integer idColeta) {this.idColeta = idColeta;}

    public String getValorColeta() {return valorColeta;}

    public void setValorColeta(String valorColeta) {this.valorColeta = valorColeta;}

    public String getDataColeta() {return dataColeta;}

    public void setDataColeta(String dataColeta) {this.dataColeta = dataColeta;}

    @Override
    public String toString() {

        return ""+ dataColeta +" - " +valorColeta ;
    }
}

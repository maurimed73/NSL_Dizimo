package com.example.nsldizimo.model;

import java.io.Serializable;

public class Coletas implements Serializable {
    private Integer idColeta;
    private String valorColeta;
    private String dataColeta;

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

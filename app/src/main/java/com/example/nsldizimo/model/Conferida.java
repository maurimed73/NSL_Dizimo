package com.example.nsldizimo.model;

import java.io.Serializable;

public class Conferida implements Serializable {

    public int codigo;
    public String nomeConferente;
    public double valorDuzentos;
    public double valorCem;
    public double valorCinquenta;
    public double valorVinte;
    public double valorDez;
    public double valorCinco;
    public double valorDois;
    public String valorMoedas;
    public String valorTotal;
    public String dataContagem;

    public Conferida() {
        this.codigo = codigo;
        this.nomeConferente = nomeConferente;
        this.valorDuzentos = valorDuzentos;
        this.valorCem = valorCem;
        this.valorCinquenta = valorCinquenta;
        this.valorVinte = valorVinte;
        this.valorDez = valorDez;
        this.valorCinco = valorCinco;
        this.valorDois = valorDois;
        this.valorMoedas = valorMoedas;
        this.valorTotal= valorTotal;
        this.dataContagem = dataContagem;
    }


    @Override
    public String toString() {
        return ""+ nomeConferente ;
    }

    public int getCodigo() {return codigo;}

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getNomeConferente() {
        return nomeConferente;
    }

    public void setNomeConferente(String nomeConferente) {
        this.nomeConferente = nomeConferente;
    }

    public double getValorDuzentos() {
        return valorDuzentos;
    }

    public void setValorDuzentos(double valorDuzentos) {
        this.valorDuzentos = valorDuzentos;
    }

    public double getValorCem() {
        return valorCem;
    }

    public void setValorCem(double valorCem) {
        this.valorCem = valorCem;
    }

    public double getValorCinquenta() {
        return valorCinquenta;
    }

    public void setValorCinquenta(double valorCinquenta) {
        this.valorCinquenta = valorCinquenta;
    }

    public double getValorVinte() {
        return valorVinte;
    }

    public void setValorVinte(double valorVinte) {
        this.valorVinte = valorVinte;
    }

    public double getValorDez() {
        return valorDez;
    }

    public void setValorDez(double valorDez) {
        this.valorDez = valorDez;
    }

    public double getValorCinco() {
        return valorCinco;
    }

    public void setValorCinco(double valorCinco) {
        this.valorCinco = valorCinco;
    }

    public double getValorDois() {
        return valorDois;
    }

    public void setValorDois(double valorDois) {
        this.valorDois = valorDois;
    }

    public String getValorMoedas() {return valorMoedas;}

    public void setValorMoedas(String valorMoedas) {
        this.valorMoedas = valorMoedas;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDataContagem() {
        return dataContagem;
    }

    public void setDataContagem(String dataContagem) {
        this.dataContagem = dataContagem;
    }

}

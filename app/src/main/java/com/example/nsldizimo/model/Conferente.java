package com.example.nsldizimo.model;

import android.content.Intent;

import java.io.Serializable;

public class Conferente implements Serializable {
    private Integer idConferente;
    private String nomeConferente;

    public Integer getIdConferente() {
        return idConferente;
    }

    public void setIdConferente(Integer idConferente) {
        this.idConferente = idConferente;
    }

    public String getNomeConferente() {
        return nomeConferente;
    }

    public void setNomeConferente(String nomeConferente) {
        this.nomeConferente = nomeConferente;
    }

    @Override
    public String toString() {
        return ""+ nomeConferente;
    }
}

package com.example.pethealth.Model;

import java.io.Serializable;

public class TipoVacinas implements Serializable {

    private int id;
    private String descricao;

    public TipoVacinas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

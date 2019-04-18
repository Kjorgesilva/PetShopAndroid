package com.example.pethealth.Model;

import java.io.Serializable;

public class EspecieAnimal implements Serializable {

    private int id;
    private String especie;
    private TipoVacinas idVacina;

    public EspecieAnimal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public TipoVacinas getIdVacina() {
        return idVacina;
    }

    public void setIdVacina(TipoVacinas idVacina) {
        this.idVacina = idVacina;
    }
}

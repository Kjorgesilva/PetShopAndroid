package com.example.pethealth.Model;

import java.io.Serializable;
import java.util.Date;

public class Vacinas implements Serializable {

    private int id;
    private String aviso;
    private String dataVacina;
    private String dataDaProxima;
    private String nomeAnimal;
    private int idAnimal;

    private String nomeVacina;
    private int idTipoVacina;

    public Vacinas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getDataVacina() {
        return dataVacina;
    }

    public void setDataVacina(String dataVacina) {
        this.dataVacina = dataVacina;
    }

    public String getDataDaProxima() {
        return dataDaProxima;
    }

    public void setDataDaProxima(String dataDaProxima) {
        this.dataDaProxima = dataDaProxima;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public int getIdTipoVacina() {
        return idTipoVacina;
    }

    public void setIdTipoVacina(int idTipoVacina) {
        this.idTipoVacina = idTipoVacina;
    }
}

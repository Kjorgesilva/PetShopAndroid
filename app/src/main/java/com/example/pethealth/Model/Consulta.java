package com.example.pethealth.Model;

import java.io.Serializable;

public class Consulta implements Serializable {
    private int id;
    private String nome;
    private String dataInicio;
    private String endereco;
    private String dataFim;
    private String medico;


    public Consulta(String nome, String endereco, String data, String spinner, String dataFim) {
        this.nome = nome;
        this.dataInicio = data;
        this.endereco = endereco;
        this.dataFim = dataFim;
        this.medico = spinner;

    }

    public Consulta() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}

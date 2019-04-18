package com.example.pethealth.Model;

import java.io.Serializable;

public class Consulta implements Serializable {
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private String data;
    private String medico;

    public Consulta(int id, String nome, String telefone, String endereco, String data, String medico) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.data = data;
        this.medico = medico;
    }

    public Consulta() {
    }

    public Consulta(String nome, String telefone, String endereco, String data, String medico) {

        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.data = data;
        this.medico = medico;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}

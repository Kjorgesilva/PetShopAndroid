package com.example.pethealth.Model;

import java.io.Serializable;

public class Animal implements Serializable {

    private int id;
    private String nome;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String sexo;
    private String paisOrigem;
    private String observacoes;
    private Cliente cliente;
    private EspecieAnimal idEspecieAnimal;


    public Animal() {
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EspecieAnimal getIdEspecieAnimal() {
        return idEspecieAnimal;
    }

    public void setIdEspecieAnimal(EspecieAnimal idEspecieAnimal) {
        this.idEspecieAnimal = idEspecieAnimal;
    }
}

package com.example.pethealth.Model;

import java.io.Serializable;

public class Agenda implements Serializable {
    private int id;
    private Animal animal;
    private Cliente cliente;
    private String dataInicio;
    private Endereco endereco;
    private String dataFim;
    private Medico medico;


    public Agenda() {

    }

    public Agenda(Animal idAnimal, Cliente idCliente, Endereco idEndereco, String data, Medico idMedico, String dataFim) {
        this.animal=idAnimal;
        this.cliente = idCliente;
        this.dataInicio = data;
        this.dataFim = dataFim;
        this.endereco = idEndereco;
        this.medico = idMedico;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}

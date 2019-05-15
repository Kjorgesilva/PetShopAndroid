package com.example.pethealth.Model;

import java.io.Serializable;

public class Agenda implements Serializable {
    private int id;
    private Animal animal;
    private String dataInicio;
    private Endereco endereco;
    private String dataFim;
    private Medico medico;
    private int idCliente;


    public Agenda() {

    }

    public Agenda(Animal id_animal, Endereco id_endereco, String data, Medico id_medico, String dataFim) {
        this.animal=id_animal;
        this.dataInicio = data;
        this.dataFim = dataFim;
        this.endereco = id_endereco;
        this.medico = id_medico;

    }

    public Agenda(Animal animal, int idCliente, Endereco endereco, String data, Medico medico, String dataFim) {
        this.animal=animal;
        this.idCliente = idCliente;
        this.dataInicio = data;
        this.dataFim = dataFim;
        this.endereco = endereco;
        this.medico = medico;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}

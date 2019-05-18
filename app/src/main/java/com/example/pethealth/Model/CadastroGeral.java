package com.example.pethealth.Model;

import java.io.Serializable;
import java.util.List;

public class CadastroGeral implements Serializable {

    private List<Animal> listaAnimal;
    private List<Endereco>listaEndereco;
    private List<Medico>listaMedico;


    public List<Animal> getListaAnimal() {
        return listaAnimal;
    }

    public void setListaAnimal(List<Animal> listaAnimal) {
        this.listaAnimal = listaAnimal;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public List<Medico> getListaMedico() {
        return listaMedico;
    }

    public void setListaMedico(List<Medico> listaMedico) {
        this.listaMedico = listaMedico;
    }
}

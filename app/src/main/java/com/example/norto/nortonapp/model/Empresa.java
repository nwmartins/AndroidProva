package com.example.norto.nortonapp.model;

import java.io.Serializable;

public class Empresa implements Serializable {
    private int id;
    private String descricao;

    public Empresa() {
    }

    public Empresa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return id + " - " + descricao;
    }
}

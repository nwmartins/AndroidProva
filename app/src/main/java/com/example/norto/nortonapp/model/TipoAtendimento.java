package com.example.norto.nortonapp.model;

import java.io.Serializable;

public class TipoAtendimento implements Serializable {
    private int id;
    private String descricao;

    public TipoAtendimento() {
    }

    public TipoAtendimento(int id, String descricao) {
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
        return descricao;
    }
}


package com.example.norto.nortonapp.model;

import java.util.Date;

public class Atendimento {
    private int id;
    private String assunto;
    private String contato;
    private String telefone;
    private String email;
    private TipoAtendimento tpAtendimento;
    private Date data;
    private Empresa empresa;

    public Atendimento() {
    }

    public Atendimento(int id, String assunto, String contato, String telefone, String email, TipoAtendimento tpAtendimento, Date data, Empresa empresa) {
        this.id = id;
        this.assunto = assunto;
        this.contato = contato;
        this.telefone = telefone;
        this.email = email;
        this.tpAtendimento = tpAtendimento;
        this.data = data;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoAtendimento getTpAtendimento() {
        return tpAtendimento;
    }

    public void setTpAtendimento(TipoAtendimento tpAtendimento) {
        this.tpAtendimento = tpAtendimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return getTpAtendimento().getDescricao() + " - " + assunto;
    }
}

package com.sovos;

import java.util.Date;

public class Cliente {
    int id_cliente;
    String nome;
    String cpf;
    Date dt_nasc;
    String telefone;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDate_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(java.sql.Date date_nasc) {
        this.dt_nasc = date_nasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", date_nasc=" + dt_nasc +
                ", telefone=" + telefone +
                '}';
    }
    public void nome(String nome) {
    }

    public void setDt_nasc(Date dt_nasc) {
    }
}

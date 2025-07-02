package br.com.fiap.model;

import br.com.fiap.bdexception.BDException;

import java.util.Date;

public class Acesso {

    private String nome;
    private String usuario;
    private String senha;
    private Date dataNasc;

    public Acesso() {}

    public Acesso(String nome, String usuario, String senha, Date dataNasc) throws BDException {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public java.sql.Date getDataNasc() {
        return new java.sql.Date(dataNasc.getTime());
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
}

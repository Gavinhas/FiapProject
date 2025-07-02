package br.com.fiap.model;

public class Extrato {

    private int id;
    private String tipo;
    private double valor;
    private String data;
    private String descricao;

    public Extrato() {}

    public Extrato(int id, String tipo, double valor, String data, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

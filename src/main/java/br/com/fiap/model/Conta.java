package br.com.fiap.model;

public class Conta {

    private double saldoConta;
    private double limiteCartao;
    private int idUsuario;
    private int numConta;
    private double limiteUtilizado;

    public Conta() {}

    public Conta(double saldoConta, double limiteCartao, int idUsuario, int numConta, double limiteUtilizado) {
        this.saldoConta = saldoConta;
        this.limiteCartao = limiteCartao;
        this.idUsuario = idUsuario;
        this.numConta = numConta;
        this.limiteUtilizado = limiteUtilizado;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public double getLimiteCartao() {
        return limiteCartao;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getLimiteUtilizado() {
        return limiteUtilizado;
    }

    public void setLimiteUtilizado(double limiteUtilizado) {
        this.limiteUtilizado = limiteUtilizado;
    }
}

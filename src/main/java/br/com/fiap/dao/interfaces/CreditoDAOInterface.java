package br.com.fiap.dao.interfaces;

import br.com.fiap.bdexception.BDException;

import java.sql.Connection;
import java.sql.SQLException;

public interface CreditoDAOInterface {

    public boolean pagarCredito(int numConta, double valor, String descricao, Connection connection) throws BDException;
    public boolean pagarFatura(int numConta, double valor, String descricao, Connection connection) throws BDException;

    double limiteDisponivel(int numConta, Connection conn) throws BDException;

    void registrarExtrato(int numConta, double valor, String tipo, String descricao, Connection conn) throws SQLException;
}

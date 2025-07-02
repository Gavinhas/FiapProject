package br.com.fiap.dao.interfaces;

import br.com.fiap.bdexception.BDException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ContaDAOInterface {

    void depositar(int numConta, double valor,String descricao, Connection connection ) throws BDException;
    boolean sacar(int numConta, double valor, String descricao, Connection connection ) throws BDException;
    void registrarExtrato(int numConta, double valor, String tipo, String descricao, Connection conn) throws SQLException;

    }

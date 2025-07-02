package br.com.fiap.dao.interfaces;

import br.com.fiap.bdexception.BDException;
import br.com.fiap.model.Acesso;
import br.com.fiap.model.Conta;

import java.sql.Connection;

public interface AcessoDAOInterface {

    void cadastrar(Acesso acesso, Conta novaConta) throws BDException;
    int conta(Conta novaConta, Connection connection) throws BDException;
    Conta login (String usuario, String senha, Connection connection) throws BDException;
}

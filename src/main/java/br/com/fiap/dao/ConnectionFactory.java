package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "RM559498";
    private static final String PASSWORD = "151205";

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver"); // 👈 só precisa UMA vez
            return DriverManager.getConnection(URL, USER, PASSWORD); // 👈 retorna a conexão aqui
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver Oracle não encontrado", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar o BD: " + e);
        }
    }
}

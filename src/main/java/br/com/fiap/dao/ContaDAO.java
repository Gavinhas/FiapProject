package br.com.fiap.dao;

import br.com.fiap.bdexception.BDException;
import br.com.fiap.dao.interfaces.ContaDAOInterface;
import oracle.jdbc.proxy.annotation.Pre;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContaDAO implements ContaDAOInterface {

    @Override
    public void depositar(int numConta, double valor,String descricao, Connection connection) throws BDException {

        String sql = "UPDATE t_aplicativo SET saldo_conta = saldo_conta + ? WHERE num_conta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDouble(1, valor);
            ps.setInt(2, numConta);
            ps.executeUpdate();

            registrarExtrato(numConta, valor, "entrada", descricao, connection);
        }catch (SQLException e){
            throw new BDException("Erro ao realizar deposito: " + e);
        }

    }

    @Override
    public boolean sacar(int numConta, double valor, String descricao, Connection connection) throws BDException {

        String verificaSaldoSql = "SELECT saldo_conta FROM t_aplicativo WHERE num_conta = ?";

        try (PreparedStatement ps = connection.prepareStatement(verificaSaldoSql)){

            ps.setInt(1, numConta);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Double saldo = rs.getDouble("saldo_conta");

                if(saldo >= valor){
                    String saqueSql = "UPDATE t_aplicativo SET saldo_conta = saldo_conta - ? WHERE num_conta = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(saqueSql)){
                        ps2.setDouble(1, valor);
                        ps2.setInt(2, numConta);
                        ps2.executeUpdate();

                        registrarExtrato(numConta, valor, "saida", descricao, connection);

                        return true;
                    }
                }else {
                    return false;
                }

            }else {
                return false;
            }

        }catch (SQLException e){
            throw new BDException("Erro ao realizar saque: " + e);
        }

    }


    @Override
    public void registrarExtrato(int numConta, double valor, String tipo, String descricao, Connection conn) throws SQLException {
        String sql = "INSERT INTO T_EXTRATO (NUM_CONTA, VALOR_MOVIMENTACAO, TIPO_MOVIMENTACAO, DESCRICAO, DATA_MOVIMENTACAO) " +
                "VALUES (?, ?, ?, ?, SYSDATE)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, numConta);
            ps.setDouble(2, valor);
            ps.setString(3, tipo);
            ps.setString(4, descricao);
            ps.executeUpdate();

        }
    }



    public double saldoConta(int numConta, Connection connection) throws BDException {

        String sql = "SELECT SALDO_CONTA FROM T_APLICATIVO WHERE NUM_CONTA = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, numConta);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                double resultado = rs.getDouble("SALDO_CONTA");
                System.out.println("DAO saldoConta retornando: " + resultado);

                return rs.getDouble("SALDO_CONTA");
            }

        }catch (SQLException e){
            throw new BDException("Erro ao ver saldo: " + e);
        }
        return 0;
    }

    public double fatura(int numConta, Connection connection) throws BDException {

        String sql = "SELECT LIMITE_UTILIZADO FROM T_APLICATIVO WHERE NUM_CONTA = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, numConta);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("LIMITE_UTILIZADO");
            }

        }catch (SQLException e){
            throw new BDException("Erro ao ver fatura: " + e);
        }
        return 0;
    }

    public Map<String, Double> buscarEntradasSaidas(int numConta, Connection conn) throws SQLException {
        String sql = "SELECT " +
                "SUM(CASE WHEN TIPO_MOVIMENTACAO = 'entrada' THEN VALOR_MOVIMENTACAO ELSE 0 END) AS ENTRADAS, " +
                "SUM(CASE WHEN TIPO_MOVIMENTACAO IN ('saida', 'saida_credito', 'pagamento_fatura') THEN VALOR_MOVIMENTACAO ELSE 0 END) AS SAIDAS " +
                "FROM T_EXTRATO WHERE NUM_CONTA = ?";

        Map<String, Double> valores = new HashMap<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numConta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                valores.put("entradas", rs.getDouble("ENTRADAS"));
                valores.put("saidas", rs.getDouble("SAIDAS"));
            }
        }

        return valores;
    }


}

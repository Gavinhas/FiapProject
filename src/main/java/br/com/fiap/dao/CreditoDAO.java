package br.com.fiap.dao;

import br.com.fiap.bdexception.BDException;
import br.com.fiap.dao.interfaces.CreditoDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditoDAO implements CreditoDAOInterface {

    @Override
    public boolean pagarCredito(int numConta, double valor, String descricao, Connection connection) throws BDException{

        String sql = "SELECT limite_cartao, limite_utilizado FROM t_aplicativo WHERE num_conta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, numConta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double limite = rs.getDouble("limite_cartao");
                double limite_utilizado = rs.getDouble("limite_utilizado");

                if ((limite - limite_utilizado) >= valor) {
                    String compraSql = "UPDATE t_aplicativo SET limite_utilizado = limite_utilizado + ? WHERE num_conta = ?";

                    try (PreparedStatement ps2 = connection.prepareStatement(compraSql)) {
                        ps2.setDouble(1, valor);
                        ps2.setInt(2, numConta);
                        ps2.executeUpdate();

                        registrarExtrato(numConta, valor, "saida_credito", descricao, connection);

                    }
                    return true;

                }else{return false;}

            }else{return false;}


        }catch (SQLException e){
            throw new BDException("Erro ao processar a compra no credito: " + e);
        }

    }

    @Override
    public boolean pagarFatura(int numConta, double valor, String descricao, Connection connection) throws BDException {

        String sql = "SELECT limite_cartao, limite_utilizado, saldo_conta FROM t_aplicativo WHERE num_conta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, numConta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double limiteUtilizado = rs.getDouble("limite_utilizado");
                double saldoConta = rs.getDouble("saldo_conta");

                if (limiteUtilizado >= valor && saldoConta >= valor) {

                    String updateFatura = "UPDATE t_aplicativo SET limite_utilizado = limite_utilizado - ?, saldo_conta = saldo_conta - ? WHERE num_conta = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(updateFatura)) {
                        ps2.setDouble(1, valor);
                        ps2.setDouble(2, valor);
                        ps2.setInt(3, numConta);
                        ps2.executeUpdate();

                        registrarExtrato(numConta, valor, "pagamento_fatura", descricao, connection);
                    }

                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new BDException("Erro ao processar pagamento da fatura: " + e);
        }
    }

    @Override
    public double limiteDisponivel(int numConta, Connection conn) throws BDException {
        String sql = "SELECT limite_cartao, limite_utilizado FROM t_aplicativo WHERE num_conta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numConta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double limite = rs.getDouble("limite_cartao");
                double utilizado = rs.getDouble("limite_utilizado");
                return limite - utilizado;
            }
        } catch (SQLException e) {
            throw new BDException("Erro ao calcular limite disponível: " + e);
        }
        return 0;
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



}

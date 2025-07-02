package br.com.fiap.dao;

import br.com.fiap.bdexception.BDException;
import br.com.fiap.dao.interfaces.AcessoDAOInterface;
import br.com.fiap.model.Acesso;
import br.com.fiap.model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessoDAO implements AcessoDAOInterface {

    @Override
    public Conta login(String usuario, String senha, Connection connection)throws BDException {

        String sql = "SELECT a.NUM_CONTA FROM T_APLICATIVO a " +
                "JOIN T_USUARIO u ON u.ID_USUARIO = a.ID_USUARIO " +
                "WHERE u.USUARIO = ? AND u.SENHA = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Conta conta = new Conta();
                conta.setNumConta(rs.getInt("NUM_CONTA"));
                return conta;
            }

        }catch (SQLException e){
            throw new BDException("Erro no login" + e);
        }
        return null;
    }


    @Override
    public void cadastrar(Acesso acesso, Conta novaConta) throws BDException {

        String sql = "INSERT INTO t_usuario (nome,usuario,senha,dt_nasc) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_usuario"})){

            connection.setAutoCommit(false);

            ps.setString(1, acesso.getNome());
            ps.setString(2, acesso.getUsuario());
            ps.setString(3, acesso.getSenha());
            ps.setDate(4, acesso.getDataNasc());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    novaConta.setIdUsuario(id);
                    int numConta = conta(novaConta, connection);
                }
            }

            ps.executeUpdate();
            connection.commit();

        }catch (SQLException e){
            throw new BDException("Erro na criacao do usuario:" + e );
        }

    }

    @Override
    public int conta(Conta novaConta, Connection connection) throws BDException {

        String sql = "INSERT INTO t_aplicativo (saldo_conta, limite_cartao, limite_utilizado, id_usuario) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, new String[]{"num_conta"})) {

            ps.setDouble(1, novaConta.getSaldoConta());
            ps.setDouble(2, novaConta.getLimiteCartao());
            ps.setDouble(3, novaConta.getLimiteUtilizado());
            ps.setInt(4, novaConta.getIdUsuario());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }else{throw new BDException("Erro ao gerar numero da conta");}
            }

        } catch (SQLException e) {
            throw new BDException("Erro:" + e);
        }

    }

}


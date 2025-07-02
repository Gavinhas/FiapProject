package br.com.fiap.dao;

import br.com.fiap.bdexception.BDException;
import br.com.fiap.model.Extrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtratoDAO {

    public List<Extrato> listarUltimosExtratos(int numConta, Connection connection) throws SQLException {
        List<Extrato> lista = new ArrayList<>();

        String sql = "SELECT * FROM ( " +
                    "SELECT * FROM T_EXTRATO WHERE NUM_CONTA = ? ORDER BY DATA_MOVIMENTACAO DESC" +
                    ") WHERE ROWNUM <= 20";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, numConta);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Extrato e = new Extrato();
                e.setId(rs.getInt("ID_EXTRATO"));
                e.setTipo(rs.getString("TIPO_MOVIMENTACAO"));
                e.setValor(rs.getDouble("VALOR_MOVIMENTACAO"));
                e.setData(rs.getString("DATA_MOVIMENTACAO"));
                e.setDescricao(rs.getString("DESCRICAO"));
                lista.add(e);

            }

        }catch (SQLException e) {
            BDException bde = new BDException("Erro ao listar extratos:" + e.getMessage(), e);
        }
        return lista;

    }



}

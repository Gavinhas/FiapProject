package br.com.fiap.controller;


import br.com.fiap.bdexception.BDException;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.CreditoDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/pagarcredito")
public class CreditoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        double valor = Double.parseDouble(req.getParameter("valor"));
        String descricao = req.getParameter("descricao");
        int numConta = Integer.parseInt(req.getSession().getAttribute("numConta").toString());

        try (Connection conn = ConnectionFactory.getConnection()) {

            CreditoDAO dao = new CreditoDAO();
            boolean sucesso = dao.pagarCredito(numConta, valor, descricao, conn);

            if (sucesso) {
                resp.sendRedirect("form-credito?msg=sucessoCredito");
            }else{
                resp.sendRedirect("form-credito?erro=pagamentoCredito");
            }

        }catch (BDException | SQLException e){
            e.printStackTrace();
        }

    }

}

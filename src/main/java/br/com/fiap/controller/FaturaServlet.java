package br.com.fiap.controller;


import br.com.fiap.bdexception.BDException;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.CreditoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/pagarfatura")
public class FaturaServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double valor = Double.parseDouble(req.getParameter("valor"));
        String descricao = req.getParameter("descricao");
        int numConta = Integer.parseInt(req.getSession().getAttribute("numConta").toString());

        try (Connection conn = ConnectionFactory.getConnection()) {

            CreditoDAO dao = new CreditoDAO();

            Boolean sucesso = dao.pagarFatura(numConta, valor, descricao, conn);
            if (sucesso) {
            resp.sendRedirect("form-credito?msg=sucessoPagamento");
            }else {
                resp.sendRedirect("form-credito?erro=falhaPagamento");
            }
        }catch (BDException | SQLException e){
            e.printStackTrace();
        }


    }

}

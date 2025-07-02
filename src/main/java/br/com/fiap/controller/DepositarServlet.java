package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.ContaDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet ("/depositar")
public class DepositarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        double valor = Double.parseDouble(req.getParameter("valor"));
        String descricao = req.getParameter("descricao");
        int numConta = Integer.parseInt(req.getSession().getAttribute("numConta").toString());

        try (Connection conn = ConnectionFactory.getConnection()) {
            ContaDAO dao = new ContaDAO();

            dao.depositar(numConta, valor, descricao, conn);
            resp.sendRedirect("form-deposito?msg=sucessoDeposito");
        }catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/debito.jsp?erro=depositar");
        }


    }

}

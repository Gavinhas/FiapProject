package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.ContaDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

@WebServlet("/form-depositosaque")
public class FormDepositarSacarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer numConta = Integer.parseInt(req.getSession().getAttribute("numConta").toString());

        try (Connection conn = ConnectionFactory.getConnection()) {

            ContaDAO dao = new ContaDAO();
            Map<String, Double> resultado = dao.buscarEntradasSaidas(numConta, conn);
            double saldo = dao.saldoConta(numConta, conn);

            req.setAttribute("entradas", resultado.get("entradas"));
            req.setAttribute("saidas", resultado.get("saidas"));
            req.setAttribute("saldo", saldo);

            req.getRequestDispatcher("debito.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("home.jsp?erro=carregamento");
        }
    }
}

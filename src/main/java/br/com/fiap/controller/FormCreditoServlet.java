package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.ContaDAO;
import br.com.fiap.dao.CreditoDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/form-credito")
public class FormCreditoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int numConta = Integer.parseInt(req.getSession().getAttribute("numConta").toString());

        try (Connection conn = ConnectionFactory.getConnection()) {
            ContaDAO dao = new ContaDAO();
            CreditoDAO cdao = new CreditoDAO();
            double saldo = dao.saldoConta(numConta, conn);
            double fatura = dao.fatura(numConta, conn);
            double limiteDisponivel = cdao.limiteDisponivel(numConta, conn);

            req.setAttribute("saldo", saldo);
            req.setAttribute("fatura", fatura);
            req.setAttribute("limiteDisponivel", limiteDisponivel);

            req.getRequestDispatcher("credito.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("home.jsp?erro=carregamento");
        }
    }
}

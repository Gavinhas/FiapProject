package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.ContaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

@WebServlet("/form-deposito")
    public class FormDepositoSaqueServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            int numConta = Integer.parseInt(req.getSession().getAttribute("numConta").toString());

            try (Connection conn = ConnectionFactory.getConnection()) {
                ContaDAO dao = new ContaDAO();

                double saldo = dao.saldoConta(numConta, conn);
                Map<String, Double> resultado = dao.buscarEntradasSaidas(numConta, conn);

                req.setAttribute("saldo", saldo);
                req.setAttribute("entradas", resultado.get("entradas"));
                req.setAttribute("saidas", resultado.get("saidas"));

                req.getRequestDispatcher("debito.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("home.jsp?erro=dados");
            }
        }
    }


package br.com.fiap.controller;


import br.com.fiap.dao.AcessoDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.model.Conta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        try (Connection conn = ConnectionFactory.getConnection()) {
            AcessoDAO dao = new AcessoDAO();
            Conta conta = dao.login(usuario, senha, conn);

            if (conta != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", usuario);
                session.setAttribute("numConta", conta.getNumConta());

                response.sendRedirect("home");
            } else {
                response.sendRedirect("index.jsp?erro=login");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?erro=exception");
        }
    }
}


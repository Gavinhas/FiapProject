package br.com.fiap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.fiap.dao.AcessoDAO;
import br.com.fiap.model.Acesso;
import br.com.fiap.model.Conta;

@WebServlet("/cadastrar")
public class CadastrarServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nome = req.getParameter("nome");
            String usuario = req.getParameter("usuario");
            String senha = req.getParameter("senha");
            String dataNascStr = req.getParameter("dataNasc");

            double saldo = Double.parseDouble(req.getParameter("saldoConta"));
            double limite = Double.parseDouble(req.getParameter("limiteCartao"));

            Date dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascStr);

            Acesso acesso = new Acesso(nome, usuario, senha, dataNasc);
            Conta novaConta = new Conta();
            novaConta.setSaldoConta(saldo);
            novaConta.setLimiteCartao(limite);
            novaConta.setLimiteUtilizado(0);

            AcessoDAO dao = new AcessoDAO();
            dao.cadastrar(acesso, novaConta);

            resp.sendRedirect("index.jsp");
        } catch (Exception e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
            e.printStackTrace();
            resp.sendRedirect("index.jsp?erro=cadastro");
        }
    }
}

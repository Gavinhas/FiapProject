package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.ContaDAO;
import br.com.fiap.dao.CreditoDAO;
import br.com.fiap.dao.ExtratoDAO;
import br.com.fiap.model.Extrato;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer numConta = Integer.parseInt(req.getSession().getAttribute("numConta").toString());

        try(Connection con = ConnectionFactory.getConnection()){
            ContaDAO dao = new ContaDAO();
            ExtratoDAO daoExtrato = new ExtratoDAO();
            CreditoDAO cdao = new CreditoDAO();
            double saldo = dao.saldoConta(numConta, con);
            double fatura = dao.fatura(numConta, con);
            double limiteDisponivel = cdao.limiteDisponivel(numConta, con);
            Map<String, Double> resultado = dao.buscarEntradasSaidas(numConta, con);


            req.setAttribute("entradas", resultado.get("entradas"));
            req.setAttribute("saidas", resultado.get("saidas"));
            req.setAttribute("saldo", saldo);
            req.setAttribute("fatura", fatura);
            req.setAttribute("limiteDisponivel", limiteDisponivel);

            List<Extrato> extratos = daoExtrato.listarUltimosExtratos(numConta, con);
            req.setAttribute("extratos", extratos);

            req.getRequestDispatcher("home.jsp").forward(req, resp);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

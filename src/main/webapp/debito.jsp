<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Debito - Deposito e Saque</title>

    <style>

        .titulo {
            font-family: 'Montserrat', sans-serif;
            font-weight: 700;
            text-transform: uppercase;
            font-size: 1.5rem;
        }

        .subtitulo {
            font-family: 'Montserrat', sans-serif;
            font-weight: 300;
            font-size: 1rem;
            color: #555;
        }

    </style>
</head>
<body>


<%@ include file="cabecalho.jsp"%>

<div class="container mt-3">
    <div class="row justify-content-center gx-3">
    <%@ include file="card/saldo.jsp" %>
    <%@ include file="card/entradas.jsp" %>
    <%@ include file="card/saidas.jsp" %>
    </div>
</div>

<h2 class="titulo">Realizar deposito</h2>
<form action="depositar" method="post" class="mb-3">
    <input type="number" step="0.01" name="valor" placeholder="Valor" required class="form-control mb-2">
    <input type="text" name="descricao" placeholder="Descrição" required class="form-control mb-2">
    <button type="submit" class="btn btn-success w-100">Depositar</button>
</form>


<%
    String sucessoDeposito = request.getParameter("msg");
    if (sucessoDeposito != null && sucessoDeposito.equals("sucessoDeposito")) {
%>
<div class="alert alert-success subtitulo" role="alert">
    Operação realizada com sucesso.
</div>
<%
    }
%>


<br>
<h2 class="titulo">Realizar saque</h2>
<form action="sacar" method="post" class="mb-3">
    <input type="number" step="0.01" name="valor" placeholder="Valor" required class="form-control mb-2">
    <input type="text" name="descricao" placeholder="Descrição" required class="form-control mb-2">
    <button type="submit" class="btn btn-success w-100">Sacar</button>
</form>

<%
    String erro = request.getParameter("erro");
    if (erro != null) {
        if (erro.equals("saldo")) {
%>
<div class="alert alert-danger subtitulo" role="alert">
    Saldo insuficiente.
</div>
<%
} else if (erro.equals("geral")) {
%>
<div class="alert alert-danger subtitulo" role="alert">
    Algo deu errado no processo.
</div>
<%
        }
    }

    String sucesso = request.getParameter("msg");
    if (sucesso != null && sucesso.equals("sucesso")) {
%>
<div class="alert alert-success subtitulo" role="alert">
    Operação realizada com sucesso.
</div>
<%
    }
%>


</body>
</html>

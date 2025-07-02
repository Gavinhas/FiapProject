<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Banco Java - Credito</title>

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
        <%@ include file="card/fatura.jsp" %>
        <%@ include file="card/limite-disponivel.jsp" %>
    </div>
</div>

<h2 class="titulo">Fazer compra no credito</h2>

<form action="pagarcredito" method="post" class="mb-3">
  <input type="number" step="0.01" name="valor" placeholder="Valor" required class="form-control mb-2">
  <input type="text" name="descricao" placeholder="Descrição" required class="form-control mb-2">
  <button type="submit" class="btn btn-success w-100">Pagar credito</button>
</form>

<%
    String erro = request.getParameter("erro");
    if (erro != null) {
        if (erro.equals("pagamentoCredito")) {
%>
<div class="alert alert-danger subtitulo" role="alert">
    Limite no credito insulficiente.
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
    if (sucesso != null && sucesso.equals("sucessoCredito")) {
%>
<div class="alert alert-success subtitulo" role="alert">
    Operação realizada com sucesso.
</div>
<%
    }
%>


<br>

<h2 class="titulo">Pagar fatura</h2>

<form action="pagarfatura" method="post" class="mb-3">
    <input type="number" step="0.01" name="valor" placeholder="Valor" required class="form-control mb-2">
    <input type="text" name="descricao" placeholder="Descrição" required class="form-control mb-2">
    <button type="submit" class="btn btn-success w-100">Pagar fatura</button>
</form>

<%
    String erroFatura = request.getParameter("erro");
    if (erroFatura != null) {
        if (erroFatura.equals("falhaPagamento")) {
%>
<div class="alert alert-danger subtitulo" role="alert">
    Valor invalido ou acima do permitido.
</div>
<%
} else if (erroFatura.equals("geral")) {
%>
<div class="alert alert-danger subtitulo" role="alert">
    Algo deu errado no processo.
</div>

<%
        }
    }

    String sucessoFatura = request.getParameter("msg");
    if (sucessoFatura != null && sucessoFatura.equals("sucessoPagamento")) {
%>
<div class="alert alert-success subtitulo" role="alert">
    Operação realizada com sucesso.
</div>
<%
    }
%>

</body>
</html>

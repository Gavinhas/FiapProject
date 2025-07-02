<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <html>
<head>
    <title>Banco Java</title>
</head>
<body>

<%@ include file="cabecalho.jsp"%>

<div class="container mt-3">
    <div class="row justify-content-center gx-3">
<%--        <div class="col-md-3">--%>
<%--            <div class="card text-white bg-secondary text-center mb-3">--%>
<%--                <div class="card-header">Saldo</div>--%>
<%--                <div class="card-body">--%>
<%--                    <h3 class="card-title">R$ <%= request.getAttribute("saldo") %></h3>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        <%@ include file="card/saldo.jsp" %>

    <%--        <div class="col-md-3">--%>
    <%--            <div class="card text-white bg-success text-center mb-3">--%>
    <%--                <div class="card-header">Entradas</div>--%>
    <%--                <div class="card-body">--%>
    <%--                    <h3 class="card-title">R$ <%= request.getAttribute("entradas") %></h3>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </div>--%>

    <%@ include file="card/entradas.jsp" %>

    <%--        <div class="col-md-3">--%>
    <%--            <div class="card text-white bg-danger text-center mb-3">--%>
    <%--                <div class="card-header">Saídas</div>--%>
    <%--                <div class="card-body">--%>
    <%--                    <h3 class="card-title">R$ <%= request.getAttribute("saidas") %></h3>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </div>--%>


    <%@ include file="card/saidas.jsp" %>

    <%--        <div class="col-md-3">--%>
    <%--            <div class="card text-white bg-primary text-center mb-3">--%>
    <%--                <div class="card-header">Fatura</div>--%>
    <%--                <div class="card-body">--%>
    <%--                    <h3 class="card-title">R$ <%= request.getAttribute("fatura") %></h3>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </div>--%>


    <%@ include file="card/limite-disponivel.jsp" %>
    <%@ include file="card/fatura.jsp" %>

    </div>
</div>


<h4 class="mt-5">Últimos Lançamentos</h4>
<table class="table table-striped table-hover mt-3">
    <thead>
    <tr>
        <th>Data</th>
        <th>Tipo</th>
        <th>Descrição</th>
        <th class="text-end">Valor</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="e" items="${extratos}">
        <tr>
            <td><${e.data}/></td>
            <td>${e.tipo}</td>
            <td>${e.descricao}</td>
            <td class="text-end">
                <fmt:formatNumber value="${e.valor}" type="currency"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

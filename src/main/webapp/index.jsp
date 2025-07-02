<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Banco Java - Login / Cadastro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@800&display=swap" rel="stylesheet">


    <style>

        .titulo {
            font-family: 'Montserrat', sans-serif;
            font-weight: 800;
            text-transform: uppercase;
            font-size: 2rem;
        }

        .subtitulo {
            font-family: 'Montserrat', sans-serif;
            font-weight: 300;
            font-size: 0.85rem;
            color: #555;
        }

    </style>

</head>
<body class="container mt-5 bg-primary-subtle">

<form action="login" method="post" class="mb-3 position-absolute top-50 start-50 translate-middle bg-light bg-light p-5 rounded-4 shadow titulo"
    style="width: 400px">
    <h1 class="mb-4 text-center pt-sans">
        BANCO JAVA
    </h1>
    <h5 class="mb-4 text-center subtitulo">Por favor faça login no seu sistema</h5>
    <div class="format-floating mb-3">
<%--        <label class="form-label">Usuário</label>--%>
        <input type="text" name="usuario" class="form-control" placeholder="Usuario" required>
    </div>
    <div class="format-floating mb-3">
<%--        <label class="form-label">Senha</label>--%>
        <input type="password" name="senha" class="form-control" placeholder="Senha" required>
    </div>

    <%
        String erro = request.getParameter("erro");
        if (erro != null && erro.equals("login")) {
    %>
    <div class="alert alert-danger subtitulo" role="alert">
        Senha ou Login incorretos.
    </div>
    <%
        }
    %>

    <div class="d-grid gap-2">
        <button type="submit" class="btn btn-primary btn-lg px-5">Entrar</button>
    </div>

    <p class="subtitulo">Não tem conta? <button class="btn btn-link subtitulo" data-bs-toggle="modal" data-bs-target="#cadastroModal">Cadastre-se aqui</button></p>
</form>




<div class="modal fade" id="cadastroModal" tabindex="-1" aria-labelledby="cadastroModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/cadastrar" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="cadastroModalLabel">Cadastro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Usuário</label>
                        <input type="text" name="usuario" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Senha</label>
                        <input type="password" name="senha" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Data de Nascimento</label>
                        <input type="date" name="dataNasc" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Saldo Inicial</label>
                        <input type="number" name="saldoConta" step="0.01" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Limite do Cartão</label>
                        <input type="number" name="limiteCartao" step="0.01" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Cadastrar</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logoff</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>

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

        .nav-link {
            transition: all 0.3s ease;
            font-weight: 400;
            color: #333 !important;
        }

        .nav-link:hover {
            color: #000 !important;
            transform: scale(1.05);
            text-shadow: 0 0 2px rgba(0,0,0,0.15);
        }


    </style>

</head>
<body>

<%
    String usuario = (String) session.getAttribute("usuarioLogado");
    if (usuario == null){
        response.sendRedirect("index.jsp?erro=acesso");
        return;
    }

%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand titulo d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/home">
            <img src="${pageContext.request.contextPath}/img/Java.png" alt="Logo" width="70" height="70" class="d-inline-block align-text-top rounded-4">
            Banco Java
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active subtitulo nav-link" aria-current="page" href="home">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link subtitulo nav-link" href="form-depositosaque">Debito</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link subtitulo nav-link" href="form-credito">Credito</a>
                </li>
            </ul>
            <span class="subtitulo" style="margin-right: 10px">Usuario: <%= session.getAttribute("usuarioLogado")%>  </span>
            <a href="logoff" class="btn btn-danger justify-content-end">Sair</a>
        </div>
    </div>
</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

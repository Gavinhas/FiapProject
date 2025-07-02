<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="col-md-3">
    <div class="card text-white bg-primary text-center mb-3">
        <div class="card-header">Fatura</div>
        <div class="card-body">
            <h3 class="card-title">R$ <%= request.getAttribute("fatura") %></h3>
        </div>
    </div>
</div>
</body>
</html>

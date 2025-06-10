<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Teste do doGet</title>
</head>
<body>
    <h1>Resultado do Teste do doGet</h1>

    <%
        // Recupera a mensagem que foi colocada no 'request' pelo Servlet
        String msg = (String) request.getAttribute("mensagemTeste");
    %>

    <p>A mensagem do Servlet é: <strong><%= msg %></strong></p>

    <br>
    <a href="<%= request.getContextPath() %>/index.html">Voltar para o Início</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erro!</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/view/erroStyle.css">
</head>
<body>
    <div id="conteinerPrincipal">
        <h1>Ocorreu um Erro!</h1>
        <p class="mensagem-erro">
            <% 
                // Recupera a mensagem de erro definida no Servlet
                String mensagemDeErro = (String) request.getAttribute("erro");
                if (mensagemDeErro != null) {
                    out.println(mensagemDeErro);
                } else {
                    out.println("Algo inesperado aconteceu. Por favor, tente novamente.");
                }
            %>
        </p>
        <br>
        <div class="area-botoes">
            <button type="button" class="botao botao-voltar" onclick="window.location.href='<%= request.getContextPath() %>/index.html'">Voltar para o Início</button>
        </div>
    </div>
</body>
</html>
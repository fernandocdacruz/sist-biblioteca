<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Livro" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acervo de Livros</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/view/listarLivrosStyle.css">
</head>
<body>
    <div id="container">
        <h1>Acervo de Livros</h1>

        <%
            List<Livro> livros = (List<Livro>) request.getAttribute("livros");

            if (livros == null || livros.isEmpty()) {
        %>
            <p class="info-message">Nenhum livro cadastrado ainda.</p>
        <%
            } else {
        %>
            <ul>
                <%
                    for (Livro livro : livros) {
                %>
                        <li>
                            <strong>Título:</strong> <%= livro.getTitulo() %><br>
                            <strong>Autor:</strong> <%= livro.getAutor() %><br>
                            <strong>Ano:</strong> <%= livro.getAno_de_publicacao() %><br>
                            <strong>ISBN:</strong> <%= livro.getIsbn() %>
                        </li>
                <%
                    }
                %>
            </ul>
        <%
            }
        %>

        <br>

        <div class="form-excluir">
            <h2>Excluir Livro por ISBN</h2>
            <form action="<%= request.getContextPath() %>/LivroServlet" method="POST">
                <input type="hidden" name="acao" value="excluirLivro">
                
                <div class="input-e-botao">
                    <label for="isbnExcluir">ISBN:</label>
                    <input type="text" id="isbnExcluir" name="isbnExcluir" placeholder="Digite o ISBN" required maxlength="13">
                    <button type="submit" class="botao botao-excluir">Excluir</button>
                </div>
                </form>
        </div>
        <br>
        <div class="botoes-listagem">
             <a href="<%= request.getContextPath() %>/index.html" class="botao botao-voltar">Voltar para o Início</a>
        </div>
       
    </div>
</body>
</html>
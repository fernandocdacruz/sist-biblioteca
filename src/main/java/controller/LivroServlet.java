package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator; // ESSENCIAL: Importar Iterator para remoção segura
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Livro;

/**
 * Servlet implementation class LivroServlet
 */
@WebServlet("/LivroServlet")
public class LivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static List<Livro> livros = new ArrayList<>();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Adiciona esta linha para garantir que caracteres especiais sejam tratados corretamente
		request.setCharacterEncoding("UTF-8"); 

		// Pega o parâmetro 'acao' para determinar se é uma requisição de exclusão
		String acao = request.getParameter("acao"); 
		String contextPath = request.getContextPath();
		
		// Lógica para EXCLUIR um livro (baseado no parâmetro 'acao' do formulário)
		if ("excluirLivro".equals(acao)) { // Verifica se a ação é "excluirLivro"
		    String isbnParaExcluir = request.getParameter("isbnExcluir"); // Pega o ISBN do formulário de exclusão
		    
		    // Validação básica do ISBN para exclusão
		    if (isbnParaExcluir == null || isbnParaExcluir.trim().isEmpty() || !isNumeric(isbnParaExcluir) || isbnParaExcluir.length() != 13) {
		        request.setAttribute("erro", "ISBN inválido para exclusão. Verifique se digitou 13 números válidos.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/erro.jsp");
		        dispatcher.forward(request, response);
		        return; // Interrompe a execução
		    }

		    boolean removido = false;
		    // Usa um Iterator para remover o livro da lista de forma segura
		    Iterator<Livro> iterator = livros.iterator();
		    while (iterator.hasNext()) {
		        Livro livro = iterator.next();
		        if (livro.getIsbn().equals(isbnParaExcluir)) {
		            iterator.remove(); // Remove o livro
		            removido = true;
		            break; // O ISBN é único, então pode parar após encontrar
		        }
		    }
		    
		    if (!removido) { // Se não foi removido, significa que o livro não foi encontrado
		        request.setAttribute("erro", "Livro com ISBN " + isbnParaExcluir + " não encontrado no acervo para exclusão.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/erro.jsp");
		        dispatcher.forward(request, response);
		        return; // Interrompe a execução
		    }
		    
		    // Redireciona de volta para a listagem para mostrar a lista atualizada
		    response.sendRedirect(contextPath + "/LivroServlet");
		    
		} else { // Lógica ORIGINAL para CADASTRAR um livro (quando 'acao' não é "excluirLivro")
		
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String anoPublicacaoStr = request.getParameter("anoPublicacao"); // Pega como String
			String isbn = request.getParameter("isbn");
			
			// Validação do ano de publicação (adicionado try-catch)
			int anoPublicacao = 0;
			try {
				anoPublicacao = Integer.parseInt(anoPublicacaoStr);
			} catch (NumberFormatException e) {
				request.setAttribute("erro", "Ano de Publicação inválido. Por favor, insira apenas números.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/erro.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// Validação do ISBN para cadastro (ordem corrigida)
			if (isbn == null || !isNumeric(isbn)) { // Primeiro verifica se é numérico
				request.setAttribute("erro", "ISBN inválido para cadastro. O ISBN deve conter apenas números.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/erro.jsp");
				dispatcher.forward(request, response);
				return; 
			}
			if (isbn.length() != 13) { // Depois verifica o tamanho
				request.setAttribute("erro", "ISBN inválido para cadastro. São necessários 13 dígitos. Tente novamente.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/erro.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			livros.add(new Livro(titulo, autor, anoPublicacao, isbn));
				
			contextPath = request.getContextPath();
			
			response.sendRedirect(contextPath + "/index.html");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Adiciona esta linha para garantir que caracteres especiais sejam tratados corretamente
		request.setCharacterEncoding("UTF-8"); 
		
		request.setAttribute("livros", livros);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listarLivros.jsp");
		dispatcher.forward(request, response);
	}
	
	private boolean isNumeric(String str) {
	    if (str == null || str.isEmpty()) {
	        return false;
	    }
	    for (char c : str.toCharArray()) {
	        if (!Character.isDigit(c)) {
	            return false; // Encontrou um caractere que não é um dígito
	        }
	    }
	    return true; // Todos os caracteres são dígitos
	}

}

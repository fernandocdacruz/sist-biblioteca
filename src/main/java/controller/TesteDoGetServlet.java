package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/teste") // Mapeamento para a URL /teste
public class TesteDoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Criar uma mensagem de teste
		String mensagem = "Olá do TesteDoGetServlet! Esta mensagem veio do doGet.";
		
		// 2. Colocar a mensagem no objeto 'request'
		request.setAttribute("mensagemTeste", mensagem);
		
		// 3. Encaminhar (forward) a requisição para o JSP
		// Certifique-se de que o caminho corresponde à localização real do seu JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/paginaTeste.jsp"); 
		dispatcher.forward(request, response);
	}
}

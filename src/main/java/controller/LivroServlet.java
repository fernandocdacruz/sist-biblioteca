package controller;

import java.io.IOException;
import java.util.ArrayList;
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
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String anoPublicacao = request.getParameter("anoPublicacao");
		String isbn = request.getParameter("isbn");
		
		livros.add(new Livro(titulo, autor, Integer.parseInt(anoPublicacao), isbn));
		
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/index.html");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("livros", livros);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listarLivros.jsp");
		dispatcher.forward(request, response);
	}

}

package model;

public class Livro {
	
	private String titulo;
	private String autor;
	private int ano_de_publicacao;
	private String isbn;
	
	public Livro() {
	}

	public Livro(String titulo, String autor, int ano_de_publicacao, String isbn) {
		this.titulo = titulo;
		this.autor = autor;
		this.ano_de_publicacao = ano_de_publicacao;
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAno_de_publicacao() {
		return ano_de_publicacao;
	}

	public void setAno_de_publicacao(int ano_de_publicacao) {
		this.ano_de_publicacao = ano_de_publicacao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Livro [titulo=" + titulo + ", autor=" + autor + ", ano_de_publicacao=" + ano_de_publicacao + ", isbn="
				+ isbn + "]";
	}
	
}

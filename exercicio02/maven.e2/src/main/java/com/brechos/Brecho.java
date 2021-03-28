package com.brechos;

public class Brecho {
	private int id;
	private String nome;
	private String instagram;
	private String cidade;
	private String estilo;
	
	public Brecho() {
		this.id = -1;
		this.nome = "";
		this.instagram = "";
		this.cidade = "";
		this.estilo = "";
	}
	
	public Brecho(int id, String nome, String instagram, String cidade, String estilo) {
		this.id = id;
		this.nome = nome;
		this.instagram = instagram;
		this.cidade = cidade;
		this.estilo = estilo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	

	@Override
	public String toString() {
		return "Brecho [id=" + id + ", nome=" + nome + ", instagram=" + instagram + ", cidade=" + cidade + ", estilo=" + estilo + "]";
	}
	
}

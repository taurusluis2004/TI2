package model;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe Produto
 * 
 * @author Luana Takeishi
 *
 */ 
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String DESCRICAO_PADRAO = "Novo Produto";
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String descricao;
	private float preco;
	private int quantidade;
	private LocalDateTime dataF;
	private LocalDate dataV;

	public Produto(int id, String descricao, float preco, int quantidade, LocalDateTime fabricacao, LocalDate validade) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.dataF = fabricacao;
		this.dataV = validade;
	}

	public Produto() {
		this.id = id;
		this.descricao = DESCRICAO_PADRAO;
		this.preco = 0.01F;
		this.quantidade = 1;
		this.dataF = LocalDateTime.now();
		this.dataV = LocalDate.now().plusMonths(6);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean emEstoque() {
		return (quantidade > 0);
	}

	public abstract boolean emValidade();
	
	public String getDescricao() {
		return descricao;
	}

	public float getPreco() {
		return preco;
	}

	public int getQuant() {
		return quantidade;
	}

	public LocalDateTime getDataFabricacao() {
		return dataF;
	}

	public LocalDate getDataValidade() {
		return dataV;
	}

	public void setDescricao(String descricao) {
		if (descricao.length() >= 3)
			this.descricao = descricao;
	}

	public void setPreco(float preco) {
		if (preco > 0)
			this.preco = preco;
	}

	public void setQuant(int quantidade) {
		if (quantidade > 0 && quantidade <= MAX_ESTOQUE)
			this.quantidade = quantidade;
	}

	public void setDataFabricacao(LocalDateTime dataFabricacao) {
		// Pega a Data Atual
		LocalDateTime agora = LocalDateTime.now();
		// Garante que a data de fabrica��o n�o pode ser futura
		if (agora.compareTo(dataFabricacao) >= 0)
			this.dataF = dataFabricacao;
	}

	public void setDataValidade(LocalDate dataValidade) {
		// Pega a Data Atual
		LocalDate agora = LocalDate.now();
		// Garante que a data de validade deve ser futura
		if (agora.compareTo(dataValidade) < 0)
			this.dataV = dataValidade;
	}



	/**
	 * M�todo sobreposto da classe Object. � executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Produto: " + descricao + "   Pre�o: R$" + preco + "   Quant.: " + quantidade + "   Fabrica��o: "
				+ dataF + "   Validade: " + dataV;
	}

	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Produto) obj).getId());
	}
}




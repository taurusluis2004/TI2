package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Produto;
import model.DateTime;

public class ProdutoDAO {
	
	private Connection conexao;
	
	public ProdutoDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "produtos";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "Opie123";
		boolean status = false;
		

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	private int maxId = 0;
	public int getMaxId() {
		try {  
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produtos WHERE id=(SELECT MAX(id) FROM produtos);");
			maxId = rs.getInt("id");
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return maxId;
	}
	
	public boolean inserir(Produto produto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produtos (id, descricao, preco, quantidade, data_fabricacao; data_validade) "
					       + "VALUES ("+produto.getId()+ ", '" + produto.getDescricao() + "', '"  
					       + produto.getPreco() + "', '" + produto.getQuant() + "', '" + produto.getDataFabricacao() + "', '" + produto.getDataValidade() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizar(Produto produto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE produtos SET descricao = '" + produto.getDescricao() + "', preco = '"  
				       + produto.getPreco() + "', quantidade = '" + produto.getQuant()
				       + "', data_fabricacao = '" + produto.getDataFabricacao() + "', data_validade = '" + produto.getDataValidade() + "'" 
					   + " WHERE id = " + produto.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluir(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produtos WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Produto get(int id) {
		Produto produto = null;
		boolean status = false;
		try {  
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");	
			produto = new Produto(rs.getInt("id"), rs.getString("descricao"), 
	                  rs.getFloat("preco"), rs.getInt("quantidade"), 
	                  ((DateTime) rs).getLocalDateTime("data_validade"), ((DateTime) rs).getLocalDate("data_fabricacao"));
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return produto;
	}
	
	public Produto[] getProdutos() {
		Produto[] produtos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");		
	         if(rs.next()){
	             rs.last();
	             produtos = new Produto[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 produtos[i] = new Produto(rs.getInt("id"), rs.getString("descricao"), 
	                		                  rs.getFloat("preco"), rs.getInt("quantidade"), 
	                		                  ((DateTime) rs).getLocalDateTime("data_validade"), ((DateTime) rs).getLocalDate("data_fabricacao"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}

	

}

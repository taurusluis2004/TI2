package com.brechos;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "brechos";
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
	
	public boolean inserirBrecho(Brecho brecho) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO brechos (id, nome, instagram, cidade, estilo) "
					       + "VALUES ("+brecho.getId()+ ", '" + brecho.getNome() + "', '"  
					       + brecho.getInstagram() + "', '" + brecho.getCidade() + "', '" + brecho.getEstilo() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarBrecho(Brecho brecho) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE brechos SET nome = '" + brecho.getNome() + "', instagram = '"  
				       + brecho.getInstagram() + "', cidade = '" + brecho.getCidade()
				       + "', estilo = '" + brecho.getEstilo() + "'"
					   + " WHERE id = " + brecho.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirBrecho(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM brechos WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Brecho[] getBrechos() {
		Brecho[] brechos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM brechos");		
	         if(rs.next()){
	             rs.last();
	             brechos = new Brecho[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 brechos[i] = new Brecho(rs.getInt("id"), rs.getString("nome"), 
	                		                  rs.getString("instagram"), rs.getString("cidade"), 
	                		                  rs.getString("estilo"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return brechos;
	}

}
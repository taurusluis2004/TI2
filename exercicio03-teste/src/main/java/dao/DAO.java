package dao;
import java.util.List;

/**
 * Interface DAO
 * 
 * @author Hugo de Paula
 *
 */public interface DAO<T, K> {
	public T get(K chave);
	public void add(T p);
	public void update(T p);
	public void delete(T p);
	public List<T> getAll();

}

 public class DAO {
		private Connection conexao;
		
		public DAO() {
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
		
	}
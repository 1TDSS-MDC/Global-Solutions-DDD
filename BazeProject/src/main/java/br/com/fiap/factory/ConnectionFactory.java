package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection conexao;
	
	/**
	 * A conenection factory serve para obter uma conexao com o banco de dados
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		if (conexao == null) {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		
			conexao = DriverManager.getConnection(
					"jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl","rm94642","280894");
		}
		return conexao;
	}
	
	@Override
	protected void finalize() throws Throwable {
		conexao.close();
	}
}

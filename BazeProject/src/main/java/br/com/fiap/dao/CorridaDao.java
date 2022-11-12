package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.to.CorridaTO;

public class CorridaDao {

private Connection conexao;
	
	/**
	 * Construtor que seve para receber a conexao
	 * @param conexao
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CorridaDao() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
	}
	
	
	/**
	 * Usando o resultSet para recuperar o id gerado pela sequence
	 */
	private CorridaTO parse(ResultSet result) throws SQLException {
		int id = result.getInt("id_corrida");
		String km = result.getString("nr_km");
		String tempo = result.getString("nr_tempo");
		int idUsuario = result.getInt("id_usuario");
		
		
		CorridaTO endereco = new CorridaTO(id, km, tempo, idUsuario);
		
		return endereco;
	}
	
	private List<CorridaTO> parseList(ResultSet result) throws SQLException {
		List<CorridaTO> lista = new ArrayList<CorridaTO>();
		while(result.next()) {
			CorridaTO correr = parse(result);
			lista.add(correr);
		}
		return lista;
	}
	
	public List<CorridaTO> listar() throws SQLException {
		PreparedStatement ps = conexao.prepareStatement("select * from t_baze_corrida");
		
		ResultSet resultSet = ps.executeQuery();
		
		return parseList(resultSet);
	}
	
	
	public void cadastrar(CorridaTO corridaTo) throws SQLException {
		
		/**
		 * Utilizar aqui o preparedStatement com o insert do sql
		 */
		PreparedStatement ps = conexao.prepareStatement("insert into t_baze_corrida (ID_USUARIO ,"
				+ " ID_CORRIDA ,"
				+ " NR_KM ,"
				+ " NR_TEMPO) "
				+ " values "
				+ "(?, SQ_ID_CORRIDA.NEXTVAL, ?, ?)", new String[] {"id_corrida"});
		
		
		ps.setInt(1, corridaTo.getIdUsuario());
		ps.setString(2, corridaTo.getKm());
		ps.setString(3, corridaTo.getTempo());
	
		/**
		 * Comando usado para executar uma query
		 */
		ps.executeUpdate();
		
		/**
		 * Usando o resultSet para recuperar o id gerado pela sequence
		 */
		ResultSet result = ps.getGeneratedKeys();
		if(result.next()) {
			int id = result.getInt(1);
			corridaTo.setId(id);
		}
	}
	
	public void atualizar(CorridaTO corrida) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement
				("update t_baze_corrida "
				+ "set nr_km = ?, "
				+ "nr_tempo = ?,"
				+ " where id_corrida= ?");
		
		ps.setString(1, corrida.getKm());
		ps.setString(1, corrida.getTempo());
			
		
		int quantidade = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Corrida nao encontrado, nao é possivel fazer atualizacao ");
		}
	}
	
	public void deletar(int id) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement("delete from t_baze_corrida where id_corrida = ?");
		
		ps.setInt(1, id);
		
		int quantidade  = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("corrida nao teve seu id encontrado, e nao podera ser removido ");
		}
	}
}
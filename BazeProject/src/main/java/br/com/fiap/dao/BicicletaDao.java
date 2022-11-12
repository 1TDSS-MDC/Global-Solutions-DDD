package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.to.BicicletaTO;

public class BicicletaDao {

private Connection conexao;
	
	/**
	 * Construtor que seve para receber a conexao
	 * @param conexao
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public BicicletaDao() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
	}
	
	
	/**
	 * Usando o resultSet para recuperar o id gerado pela sequence
	 */
	private BicicletaTO parse(ResultSet result) throws SQLException {
		int id = result.getInt("id_bike");
		String aro = result.getString("nr_aro");
		int quadro = result.getInt("nr_quadro");
		String peso = result.getString("nr_peso");
		String cor = result.getString("nm_cor");
		String tipo = result.getString("nm_tipo");
		int marcha = result.getInt("nr_marcha");
		int idUsuario = result.getInt("id_usuario");
		
		
		BicicletaTO bicicleta = new BicicletaTO(id, aro, quadro, peso, cor, tipo, marcha, idUsuario);
		
		return bicicleta;
	}
	
	private List<BicicletaTO> parseList(ResultSet result) throws SQLException {
		List<BicicletaTO> lista = new ArrayList<BicicletaTO>();
		while(result.next()) {
			BicicletaTO bike = parse(result);
			lista.add(bike);
		}
		return lista;
	}
	
	public List<BicicletaTO> listar() throws SQLException {
		PreparedStatement ps = conexao.prepareStatement("select * from t_baze_bike");
		
		ResultSet resultSet = ps.executeQuery();
		
		return parseList(resultSet);
	}
	
	
	public void cadastrar(BicicletaTO bikeTo) throws SQLException {
		
		/**
		 * Utilizar aqui o preparedStatement com o insert do sql
		 */
		PreparedStatement ps = conexao.prepareStatement("insert into t_baze_bike (ID_USUARIO ,"
				+ " ID_BIKE ,"
				+ " NR_ARO , "
				+ " NR_QUADRO ,"
				+ " NR_PESO ,"
				+ " NM_COR ,"
				+ " NM_TIPO , "
				+ " NR_MARCHA) values "
				+ "(?, sq_id_bike.nextval, ?, ?, ?, ?, ?, ?)", new String[] {"id_bike"});
		
		
		ps.setInt(1, bikeTo.getIdUsuario());
		ps.setString(2, bikeTo.getAro());
		ps.setInt(3, bikeTo.getQuadro());
		ps.setString(4, bikeTo.getPeso());
		ps.setString(5, bikeTo.getCor());
		ps.setString(6, bikeTo.getTipo());
		ps.setInt(7, bikeTo.getMarcha());
	
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
			bikeTo.setId(id);
		}
	}
	
	public void atualizar(BicicletaTO bicicleta) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement
				("update t_baze_bike "
				+ "set nr_aro= ?, "
				+ "nr_quadro = ?,"
				+ " nr_peso = ?,"
				+ " nm_cor = ?,"
				+ " nm_tipo = ?,"
				+ " nr_marcha = ?"
				+ " where id_bike = ?");
		
		ps.setString(1, bicicleta.getAro());
		ps.setInt(2, bicicleta.getQuadro());
		ps.setString(3, bicicleta.getPeso());
		ps.setString(4, bicicleta.getCor());
		ps.setString(5, bicicleta.getTipo());
		ps.setInt(6, bicicleta.getMarcha());
		ps.setInt(7, bicicleta.getId());
			
		
		int quantidade = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Bicicleta nao encontrado, nao é possivel fazer atualizacao ");
		}
	}
	
	public void deletar(int id) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement("delete from t_baze_usuario where id_ender_usuario = ?");
		
		ps.setInt(1, id);
		
		int quantidade  = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Bicicleta nao teve seu id encontrado, e nao podera ser removido ");
		}
	}
}
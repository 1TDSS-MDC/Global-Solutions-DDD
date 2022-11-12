package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.to.BicicletaEletricaTO;

public class BicicletaEletricaDao {

private Connection conexao;
	
	/**
	 * Construtor que seve para receber a conexao
	 * @param conexao
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public BicicletaEletricaDao() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
	}
	
	
	/**
	 * Usando o resultSet para recuperar o id gerado pela sequence
	 */
	private BicicletaEletricaTO parse(ResultSet result) throws SQLException {
		
		int id = result.getInt("id_bike_eletrica");
		int bateria = result.getInt("nr_amperes_bateria");
		String motor = result.getString("nm_motor");
		String aro = result.getString("nr_aro");
		int quadro = result.getInt("nr_quadro");
		String cor = result.getString("nm_cor");
		String peso = result.getString("nr_peso");
		int idUsuario = result.getInt("id_usuario");
		
		BicicletaEletricaTO bicicletaEletrica = new BicicletaEletricaTO(id, bateria, motor, aro, quadro, peso, cor, idUsuario);
		
		return bicicletaEletrica;
	}
	
	private List<BicicletaEletricaTO> parseList(ResultSet result) throws SQLException {
		List<BicicletaEletricaTO> lista = new ArrayList<BicicletaEletricaTO>();
		while(result.next()) {
			BicicletaEletricaTO bikeEletrica = parse(result);
			lista.add(bikeEletrica);
		}
		return lista;
	}
	
	public List<BicicletaEletricaTO> listar() throws SQLException {
		PreparedStatement ps = conexao.prepareStatement("select * from t_baze_bike_eletrica");
		
		ResultSet resultSet = ps.executeQuery();
		
		return parseList(resultSet);
	}
	
	
	public void cadastrar(BicicletaEletricaTO bikeEletricaTo) throws SQLException {
		
		/**
		 * Utilizar aqui o preparedStatement com o insert do sql
		 */
		PreparedStatement ps = conexao.prepareStatement("insert into t_baze_bike_eletrica (INSERT INTO T_BAZE_BIKE_ELETRICA (ID_USUARIO ,"
				+ " ID_BIKE_ELETRICA ,"
				+ " NR_AMPERES_BATERIA ,"
				+ " NM_MOTOR ,"
				+ " NR_ARO ,"
				+ " NR_QUADRO ,"
				+ " NR_PESO ,"
				+ " NM_COR)"
				+ " values "
				+ "(?, sq_id_bike_eletrica.nextval, ?, ?, ?, ?, ?, ?)", new String[] {"id_bike_eletrica"});
		
		ps.setInt(1, bikeEletricaTo.getIdUsuario());
		ps.setInt(2, bikeEletricaTo.getBateria());
		ps.setString(3, bikeEletricaTo.getMotor());
		ps.setString(4, bikeEletricaTo.getAro());
		ps.setInt(5, bikeEletricaTo.getQuadro());
		ps.setString(6, bikeEletricaTo.getPeso());
		ps.setString(7, bikeEletricaTo.getCor());
	
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
			bikeEletricaTo.setId(id);
		}
	}
	
	public void atualizar(BicicletaEletricaTO bicicletaEletrica) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement
				("update t_baze_bike_eletrica "
				+ "set nr_amperes_bateria = ?, "
				+ " nm_motor = ?,"
				+ " nr_aro = ?,"
				+ " nr_quadro = ?,"
				+ " nm_cor = ?,"
				+ " nr_peso = ?"
				+ " where id_bike = ?");
		
		ps.setInt(1, bicicletaEletrica.getBateria());
		ps.setString(2, bicicletaEletrica.getMotor());
		ps.setString(3, bicicletaEletrica.getAro());
		ps.setInt(4, bicicletaEletrica.getQuadro());
		ps.setString(5, bicicletaEletrica.getCor());
		ps.setString(6, bicicletaEletrica.getPeso());
		
			
		
		int quantidade = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Bicicleta eletrica nao encontrado, nao é possivel fazer atualizacao ");
		}
	}
	
	public void deletar(int id) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement("delete from t_baze_bike_eletrica where id_bike_eletrica = ?");
		
		ps.setInt(1, id);
		
		int quantidade  = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Bicicleta eletrica nao teve seu id encontrado, e nao podera ser removido ");
		}
	}
}
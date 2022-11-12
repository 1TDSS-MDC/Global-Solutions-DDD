package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.to.TelefoneTO;

public class TelefoneDao {
	
private Connection conexao;
	
	/**
	 * Construtor que seve para receber a conexao
	 * @param conexao
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelefoneDao() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
	}
	
	
	/**
	 * Usando o resultSet para recuperar o id gerado pela sequence
	 */
	private TelefoneTO parse(ResultSet result) throws SQLException {
		
		int id = result.getInt("id_telefone_usuario");
		String celular = result.getString("nr_telefone");
		int ddd = result.getInt("nr_ddd");
		int ddi = result.getInt("nr_ddi");
		int idUsuario = result.getInt("id_usuario");
		
		
		TelefoneTO telefone = new TelefoneTO(id, celular, ddd, ddi, idUsuario);
		
		return telefone;
	}
	
	private List<TelefoneTO> parseList(ResultSet result) throws SQLException {
		List<TelefoneTO> lista = new ArrayList<TelefoneTO>();
		while(result.next()) {
			TelefoneTO tel = parse(result);
			lista.add(tel);
		}
		return lista;
	}
	
	public List<TelefoneTO> listar() throws SQLException {
		PreparedStatement ps = conexao.prepareStatement("select * from t_baze_telefone_usuario");
		
		ResultSet resultSet = ps.executeQuery();
		
		return parseList(resultSet);
	}
	
	
	public void cadastrar(TelefoneTO telefoneTo) throws SQLException {
		
		/**
		 * Utilizar aqui o preparedStatement com o insert do sql
		 */
		PreparedStatement ps = conexao.prepareStatement("insert into t_baze_telefone_usuario (ID_USUARIO ,"
				+ " ID_TELEFONE_USUARIO ,"
				+ " NR_TELEFONE ,"
				+ " NR_DDD ,"
				+ " NR_DDI)  values "
				+ "(?, SQ_ID_TELEFONE_USUARIO.NEXTVAL, ?, ?, ?)", new String[] {"ID_TELEFONE_USUARIO"});
		
		
		ps.setInt(1, telefoneTo.getIdUsuario());
		ps.setString(2, telefoneTo.getCelular());
		ps.setInt(3, telefoneTo.getDdd());
		ps.setInt(4, telefoneTo.getDdi());
		
		
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
			telefoneTo.setId(id);
		}
	}
	
	public void atualizar(TelefoneTO telefone) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement
				("update t_baze_telefone_usuario "
				+ "set nr_telefone = ?,"
				+ "nr_ddd = ?,"
				+ "nr_ddi = ?,"
				+ "where id_telefone_usuario = ?");
		
		ps.setString(1, telefone.getCelular());
		ps.setInt(2, telefone.getDdd());
		ps.setInt(3, telefone.getDdi());
		ps.setInt(4, telefone.getId());
			
		
		int quantidade = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Telefone nao encontrado, nao é possivel fazer atualizacao ");
		}
	}
	
	public void deletar(int id) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement("delete from t_baze_telefone_usuario where id_telefone_usuario = ?");
		
		ps.setInt(1, id);
		
		int quantidade  = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Telefone nao teve seu id encontrado, e nao podera ser removido ");
		}
	}
}
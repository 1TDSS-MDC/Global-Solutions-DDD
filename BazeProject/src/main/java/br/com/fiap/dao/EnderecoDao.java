package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.to.EnderecoTO;
import br.com.fiap.to.UsuarioTO;

@XmlRootElement
public class EnderecoDao {

private Connection conexao;
	
	/**
	 * Construtor que seve para receber a conexao
	 * @param conexao
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public EnderecoDao() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
	}
	
	
	/**
	 * Usando o resultSet para recuperar o id gerado pela sequence
	 */
	private EnderecoTO parse(ResultSet result) throws SQLException {
		int id = result.getInt("id_ender_usuario");
		String logradouro = result.getString("nm_logradouro");
		int numero = result.getInt("nr_logradouro");
		String cidade = result.getString("nm_cidade");
		String bairro = result.getString("nm_bairro");
		String pais = result.getString("nm_pais");
		String cep = result.getString("nr_cep");
		String complemento = result.getString("ds_complemento");
		
		
		EnderecoTO endereco = new EnderecoTO(id, logradouro, numero, bairro, cep, cidade, pais, complemento);
		
		return endereco;
	}
	
	private List<EnderecoTO> parseList(ResultSet result) throws SQLException {
		List<EnderecoTO> lista = new ArrayList<EnderecoTO>();
		while(result.next()) {
			EnderecoTO ender = parse(result);
			lista.add(ender);
		}
		return lista;
	}
	
	public List<EnderecoTO> listar() throws SQLException {
		PreparedStatement ps = conexao.prepareStatement("select * from t_baze_ender_usuario");
		
		ResultSet resultSet = ps.executeQuery();
		
		return parseList(resultSet);
	}
	
	
	public void cadastrar(EnderecoTO enderecoTo) throws SQLException {
		
		/**
		 * Utilizar aqui o preparedStatement com o insert do sql
		 */
		PreparedStatement ps = conexao.prepareStatement("insert into t_baze_ender_usuario (ID_ENDER_USUARIO ,"
				+ " NM_LOGRADOURO ,"
				+ " NR_LOGRADOURO ,"
				+ " NM_CIDADE ,"
				+ " NM_BAIRRO ,"
				+ " NM_PAIS ,"
				+ " NR_CEP ,"
				+ " DS_COMPLEMENTO)"
				+ " values "
				+ "(SQ_ID_ENDER_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)", new String[] {"id_ender_usuario"});
		
		
		ps.setString(1, enderecoTo.getLogradouro());
		ps.setInt(2, enderecoTo.getNumero());
		ps.setString(3, enderecoTo.getBairro());
		ps.setString(4, enderecoTo.getCep());
		ps.setString(5, enderecoTo.getPais());
		ps.setString(6, enderecoTo.getCidade());
		ps.setString(7, enderecoTo.getComplemento());
	
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
			enderecoTo.setId(id);
		}
	}
	
	public void atualizar(EnderecoTO endereco) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement
				("update t_baze_usuario "
				+ "set nm_logradouro = ?, "
				+ "nr_cidade = ?,"
				+ " nm_bairro = ?,"
				+ " nm_pais = ?,"
				+ " nr_cep = ?,"
				+ " ds_complemento = ?,"
				+ " where id_ender_usuario = ?");
		
		ps.setString(1, endereco.getLogradouro());
		ps.setInt(2, endereco.getNumero());
		ps.setString(3, endereco.getCidade());
		ps.setString(4, endereco.getBairro());
		ps.setString(5, endereco.getPais());
		ps.setString(6, endereco.getCep());
		ps.setString(7, endereco.getComplemento());
			
		
		int quantidade = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Endereco nao encontrado, nao é possivel fazer atualizacao ");
		}
	}
	
	public void deletar(int id) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement("delete from t_baze_usuario where id_ender_usuario = ?");
		
		ps.setInt(1, id);
		
		int quantidade  = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("endereco nao teve seu id encontrado, e nao podera ser removido ");
		}
	}
}

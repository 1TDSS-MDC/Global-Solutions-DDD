package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.to.UsuarioTO;

public class UsuarioDao {

private Connection conexao;
	
	/**
	 * Construtor que seve para receber a conexao
	 * @param conexao
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UsuarioDao() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
	}
	
	
	/**
	 * Usando o resultSet para recuperar o id gerado pela sequence
	 */
	private UsuarioTO parse(ResultSet result) throws SQLException {
		
		int id = result.getInt("id_usuario");
		String nome = result.getString("nm_usuario");
		int idade = result.getInt("nr_idade");
		String cpf = result.getString("nr_cpf");
		String rg = result.getString("nr_rg");
		String genero = result.getString("tp_genero");
		String status = result.getString("st_cliente");
		String email = result.getString("nm_email");
		String login = result.getString("nm_login");
		String senha = result.getString("nm_senha");
		String peso = result.getString("nr_peso");
		String altura = result.getString("nr_altura");
		
		
		UsuarioTO usuario = new UsuarioTO(id, nome, idade, cpf, rg, genero, email,login,senha, peso, altura, status);
		
		return usuario;
	}
	
	private List<UsuarioTO> parseList(ResultSet result) throws SQLException {
		List<UsuarioTO> lista = new ArrayList<UsuarioTO>();
		while(result.next()) {
			UsuarioTO user = parse(result);
			lista.add(user);
		}
		return lista;
	}
	
	public List<UsuarioTO> listar() throws SQLException {
		PreparedStatement ps = conexao.prepareStatement("SELECT * FROM T_BAZE_USUARIO");
		
		ResultSet resultSet = ps.executeQuery();
		
		return parseList(resultSet);
	}
	
	
	public void cadastrar(UsuarioTO usuarioTo) throws SQLException {
		
		/**
		 * Utilizar aqui o preparedStatement com o insert do sql
		 */
		PreparedStatement ps = conexao.prepareStatement("insert into t_baze_usuario values "
				+ "(sq_t_baze_usuario.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", new String[] {"id_usuario"} );
		
		ps.setInt(1, usuarioTo.getId());
		ps.setString(2, usuarioTo.getNome());
		ps.setInt(3, usuarioTo.getIdade());
		ps.setString(4, usuarioTo.getCpf());
		ps.setString(5, usuarioTo.getRg());
		ps.setString(6, usuarioTo.getGenero());
		ps.setString(7, usuarioTo.getEmail());
		ps.setString(8, usuarioTo.getLogin());
		ps.setString(9, usuarioTo.getSenha());
		ps.setString(10, usuarioTo.getPeso());
		ps.setString(11, usuarioTo.getAltura());
		ps.setString(12, usuarioTo.getStatus());
		
		
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
			usuarioTo.setId(id);
		}
	}
	
	public void atualizar(UsuarioTO usuario) throws SQLException, IdNotFoundException {
		PreparedStatement ps = conexao.prepareStatement("update t_baze_usuario set nm_usuario = ?, "
				+ "nr_idade = ?, nr_cpf = ?, nr_rg = ?, tp_genero = ?, nm_email = ?, nr_peso = ?, nr_altura = ?, st_cliente = ?, where id_usuario = ?");
		
		ps.setString(1, usuario.getNome());
		ps.setInt(2, usuario.getIdade());
		ps.setString(3, usuario.getCpf());
		ps.setString(4, usuario.getRg());
		ps.setString(5, usuario.getGenero());
		ps.setString(6, usuario.getEmail());
		ps.setString(7, usuario.getPeso());
		ps.setString(8, usuario.getAltura());
		ps.setString(9, usuario.getStatus());
		ps.setInt(10, usuario.getId());
		
		int quantidade = ps.executeUpdate();
		
		if(quantidade == 0) {
			throw new IdNotFoundException("Usuario nao encontrado, nao é possivel fazer atualizacao ");
		}
	}
}

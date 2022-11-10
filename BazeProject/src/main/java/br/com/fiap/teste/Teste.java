package br.com.fiap.teste;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.to.UsuarioTO;

public class Teste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		UsuarioDao dao = new UsuarioDao();
		List<UsuarioTO> lista = dao.listar();
		
		for (UsuarioTO  user : lista) {
			System.out.println(user.getAltura());
		}

	}

}

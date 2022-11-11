package br.com.fiap.teste;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.TelefoneDao;
import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.to.TelefoneTO;
import br.com.fiap.to.UsuarioTO;

public class Teste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		TelefoneDao dao1 = new TelefoneDao();
		UsuarioDao dao = new UsuarioDao();
		List<TelefoneTO> lista = dao1.listar();
		
		for (TelefoneTO  user : lista) {
			System.out.println(user.getId());
		}

	}

}

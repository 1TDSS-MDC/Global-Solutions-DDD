package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.to.UsuarioTO;

@Path("/usuario")
public class UsuarioResource {

	private UsuarioDao dao;
	
	public UsuarioResource() {
		try {
			dao = new UsuarioDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioTO> get() throws SQLException {
		return dao.listar();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastra(UsuarioTO usuario, @Context UriInfo uriInfo) throws SQLException {
		
		dao.cadastrar(usuario);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		
		builder.path(Integer.toString(usuario.getId()));
		
		return Response.created(builder.build()).build();
	}
}

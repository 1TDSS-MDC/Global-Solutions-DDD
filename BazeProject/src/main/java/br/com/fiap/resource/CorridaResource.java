package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import br.com.fiap.dao.CorridaDao;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.to.CorridaTO;


@Path("/corrida")
public class CorridaResource {

private CorridaDao dao;
	
	public CorridaResource() {
		try {
			dao = new CorridaDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CorridaTO> get() throws SQLException {
		return dao.listar();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(CorridaTO corrida, @Context UriInfo uriInfo) throws SQLException {
		
		dao.cadastrar(corrida);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		
		builder.path(Integer.toString(corrida.getId()));
		
		return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(CorridaTO corrida, @PathParam("id") int id) throws SQLException, IdNotFoundException {
		corrida.setId(id);
		dao.atualizar(corrida);
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public void deletar(@PathParam("id") int id) throws SQLException, IdNotFoundException {
		dao.deletar(id);
	}
}

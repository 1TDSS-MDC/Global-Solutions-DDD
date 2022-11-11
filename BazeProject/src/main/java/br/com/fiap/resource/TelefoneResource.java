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

import br.com.fiap.dao.TelefoneDao;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.to.TelefoneTO;

@Path("/celular")
public class TelefoneResource {
	
		private TelefoneDao dao;
		
		public TelefoneResource() {
			try {
				dao = new TelefoneDao();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<TelefoneTO> get() throws SQLException {
			return dao.listar();
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response cadastrar(TelefoneTO telefone, @Context UriInfo uriInfo) throws SQLException {
			
			dao.cadastrar(telefone);
			
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			
			builder.path(Integer.toString(telefone.getId()));
			
			return Response.created(builder.build()).build();
		}
		
		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response atualizar(TelefoneTO telefone, @PathParam("id") int id) throws SQLException, IdNotFoundException {
			telefone.setId(id);
			dao.atualizar(telefone);
			
			return Response.ok().build();
		}
		
		@DELETE
		@Path("/{id}")
		public void deletar(@PathParam("id") int id) throws SQLException, IdNotFoundException {
			dao.deletar(id);
		}
}

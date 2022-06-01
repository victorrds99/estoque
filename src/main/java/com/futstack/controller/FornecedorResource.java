package com.futstack.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.futstack.entities.Fornecedor;

@Path("/fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

	@POST
	@Path("/cria")
	@Transactional
	public Response add(Fornecedor fornecedor) {
		
		fornecedor.persist();
		
		Response retorno = Response.status(Status.CREATED).build();
		return retorno;
	}
	
	@Path("/lista")
	@GET
	public List<Fornecedor> lista() {
		
		return Fornecedor.listAll();
	}
	
	
}

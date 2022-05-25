package com.futstack.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.futstack.entities.Produto;
import com.futstack.entities.Restaurante;

@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {
	
	@Path("/produto")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Produto produtos() {
		Produto prod = new Produto();
		prod.setId(1L);
		prod.setNome("MacBook 17");
		
		return prod;
	}
	
	@GET
	public List<Restaurante> buscar(){
		return Restaurante.listAll();
	}
	
	@Path("/id/{id}")
	@GET
	public Optional<Restaurante> buscarPorId(@PathParam("id") Long id){
		Optional<Restaurante> restaurantOp = Restaurante.findByIdOptional(id);
		if(restaurantOp.isEmpty()) {
			throw new NotFoundException("Não encontrei este ID....");
		}
		return restaurantOp;
	}

	@Path("/cria")
	@POST
	@Transactional
	public Response adicionar(Restaurante dto) {
		dto.persist();
		Response retorno = Response.status(Status.CREATED).build();
		return retorno;
		
	}
	
	@PUT
	@Path("/altera/{id}")
	@Transactional
	public String alterar(@PathParam("id") Long id, Restaurante dto) {
		Optional<Restaurante> restaurantOp = Restaurante.findByIdOptional(id);
		if (restaurantOp.isEmpty()) {
			throw new NotFoundException();
		}
		Restaurante restaurante = restaurantOp.get();
		
		restaurante.nome = dto.nome;
		restaurante.persist();
		
		return "Alteração feita com sucesso!";
	}
	
	@DELETE
	@Path("/exclui/{id}")
	@Transactional
	public String remover(@PathParam("id") Long id) {
		Optional<Restaurante> restaurantOp = Restaurante.findByIdOptional(id);
		restaurantOp.ifPresentOrElse(Restaurante::delete, () -> {throw new NotFoundException();});
		return "Removido com sucesso!";
		
		
	}
}

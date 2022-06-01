package com.futstack.controller;

import java.util.List;
import java.util.Optional;

import javax.json.bind.annotation.JsonbTransient;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.futstack.entities.Movimentacao;
import com.futstack.entities.Produto;


@Path("/movimentacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class MovimentacaoResource {
	
	@Path("/lista")
	@GET
	public List<Movimentacao> buscar(){
		
		return Movimentacao.listAll();
	}
	
	@Path("/buscaPorId/{id}")
	@GET
	public Optional<Movimentacao> buscarPorId(@PathParam("id") int id){
		
		Optional<Produto> produto = Produto.findByIdOptional(id);
		Produto p = produto.get();
		
		
		Optional<Movimentacao> movimentacao = Movimentacao.findByIdOptional(id);
		if(movimentacao.isEmpty()) {
			throw new NotFoundException("Não encontrei este ID....");
		}
		Movimentacao m = movimentacao.get();
		m.setProduto(p);
		return movimentacao;
	}

	@Path("/cria")
	@POST
	@Transactional
	@JsonbTransient
	public Response adicionar(Movimentacao movimentacao) {
		
		Response retorno = Response.status(Status.CREATED).build();
		return retorno;
		
	}
	
	
	
//	@PUT
//	@Path("/altera/{id}")
//	@Transactional
//	public String alterar(@PathParam("id") int id, Movimentacao dto) {
//		Optional<Movimentacao> movimentacaoOp = Movimentacao.findByIdOptional(id);
//		if (movimentacaoOp.isEmpty()) {
//			throw new NotFoundException();
//		}
//		Movimentacao movimentacao = movimentacaoOp.get();
//		
//		
//		movimentacao.persist();
//		
//		return "Alteração feita com sucesso!";
//	}
	
//	@DELETE
//	@Path("/exclui/{id}")
//	@Transactional
//	public String remover(@PathParam("id") int id) {
//		Optional<Movimentacao> movimentacaoOp = Movimentacao.findByIdOptional(id);
//		movimentacaoOp.ifPresentOrElse(Movimentacao::delete, () -> {throw new NotFoundException();});
//		return "Removido com sucesso!";
//		
//		
//	}
}

package com.futstack.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.futstack.entities.Movimentacao;


@Path("/movimentacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class MovimentacaoResource {
	
	@Path("/lista")
	@GET
	public List<Movimentacao> buscar(){
		try {

			return Movimentacao.listAll();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}
	
	@Path("/buscaPorId/{id}")
	@GET
	public Optional<Movimentacao> buscarPorId(@PathParam("id") int id) {
		try {

	
			Optional<Movimentacao> movimentacao = Movimentacao.findByIdOptional(id);
			if (movimentacao.isEmpty()) {
				throw new NotFoundException("Não encontrei este ID....");
			}
			
			//m.setProduto(p);
			return movimentacao;
		} catch (Exception e) {
			throw new NotFoundException();
		}
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

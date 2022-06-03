package com.futstack.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
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

import com.futstack.entities.Deposito;
import com.futstack.entities.Movimentacao;
import com.futstack.entities.Produto;

@Path("/deposito")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepositoResource {

	@Path("/lista")
	@GET
	public List<Deposito> lista() {
		try {
			return Deposito.listAll();
		} catch (Exception e) {
			throw new NotFoundException();
		}
		
	}
	
	@PUT
	@Path("/altera/{id}")
	@Transactional
	public String alterar(@PathParam("id") int id, Deposito dto) {
		try {
			Optional<Deposito> DepositoOp = Deposito.findByIdOptional(id);
			if (DepositoOp.isEmpty()) {
				throw new NotFoundException();
			}
			Deposito deposito = DepositoOp.get();
			
			deposito.setNome_deposito(dto.getNome_deposito());
			
			deposito.persist();
			
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setTipo_movimentacao("Alterando nome do Deposito: "+ deposito.id_deposito + ", para "+ deposito.nome_deposito + " !!");
			movimentacao.persist();
			
			return "Alteração feita com sucesso!";
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	@POST
	@Path("/cria")
	@Transactional
	public Response add(Deposito deposito) {

		try {
			deposito.persist();
			
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setTipo_movimentacao("Novo Deposito foi criado!! id: "+ deposito.id_deposito + ", nome: "+ deposito.nome_deposito + " !!");
			movimentacao.persist();
			
			Response retorno = Response.status(Status.CREATED).build();
			return retorno;
		} catch (Exception e) {
			throw new NullPointerException();
		}
	
	}

	@Path("/buscaPorId/{id}")
	@GET
	public Optional<Deposito> buscarPorId(@PathParam("id") int id_deposito) {
		try {
			Optional<Deposito> deposito = Deposito.findByIdOptional(id_deposito);
			if (deposito.isEmpty()) {
				throw new NotFoundException("Não encontrei este ID....");
			}

			return deposito;
		} catch (Exception e) {
			throw new NotFoundException();
		}
		
	}

	@PUT
	@Path("/addDeposito/{id_deposito}/produto/{id_produto}")
	@Transactional
	public String alterar(@PathParam("id_deposito") int id_deposito, @PathParam("id_produto") int id_produto) {
		try {
			Optional<Deposito> depositoOp = Deposito.findByIdOptional(id_deposito);
			if (depositoOp.isEmpty()) {
				throw new NotFoundException();
			}
			Deposito deposito = depositoOp.get();

			Optional<Produto> produtoOp = Produto.findByIdOptional(id_produto);
			if (produtoOp.isEmpty()) {
				throw new NotFoundException();
			}
			Produto produto = produtoOp.get();

			List<Produto> p = deposito.produto;

			p.add(produto);

			deposito.setProduto(p);

			produto.persist();

			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setTipo_movimentacao(
					"Tranferindo: " + produto.nome_produto + " ao deposito: " + deposito.nome_deposito);
			movimentacao.setProduto(produto);
			movimentacao.setQuantidade_movimentacao(produto.quantidade_produto);
			movimentacao.persist();

			return "Alteração feita com sucesso!";
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}
	
//	@PUT
//	@Path("/remProdDeposito/{id_produto}/{id_deposito}")
//	@Transactional
//	public String remProdDeposito(@PathParam("id_produto") int id_produto, @PathParam("id_deposito") int id_deposito) {
//		Optional<Deposito> depositoOp = Deposito.findByIdOptional(id_deposito);
//		if (depositoOp.isEmpty()) {
//			throw new NotFoundException();
//		}
//		Deposito deposito = depositoOp.get();
//
//		Optional<Produto> produtoOp = Produto.findByIdOptional(id_produto);
//		if (produtoOp.isEmpty()) {
//			throw new NotFoundException();
//		}
//		Produto produto = produtoOp.get();
//
//		List<Produto> p = deposito.produto;
//
//		p.remove(id_produto);
//
//		deposito.setProduto(p);
//
//		deposito.persist();
//
//		Movimentacao movimentacao = new Movimentacao();
//		movimentacao.setTipo_movimentacao(
//				"Removendo: " + produto.nome_produto + " do deposito: " + deposito.nome_deposito);
//		movimentacao.setProduto(produto);
//		movimentacao.setQuantidade_movimentacao(produto.quantidade_produto);
//		movimentacao.persist();
//
//		return "Alteração feita com sucesso!";
//	}

}

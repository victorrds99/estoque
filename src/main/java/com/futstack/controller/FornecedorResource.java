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
import com.futstack.entities.Fornecedor;
import com.futstack.entities.Movimentacao;
import com.futstack.entities.Produto;

@Path("/fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {
	
	@POST
	@Path("/cria")
	@Transactional
	public Response add(Fornecedor fornecedor) {
		try {

			fornecedor.persist();
			
			Response retorno = Response.status(Status.CREATED).build();
			return retorno;
		
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}
	
	@Path("/lista")
	@GET
	public List<Fornecedor> lista() {
		try {

			return Fornecedor.listAll();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}
	
	@PUT
	@Path("/{id_fornecedor}/addProduto/{id_produto}")
	@Transactional
	public Produto addProdutoFornecedor(@PathParam("id_fornecedor") int id_fornecedor, @PathParam("id_produto") int id_produto) {
		Optional<Fornecedor> fornecedorOp = Fornecedor.findByIdOptional(id_fornecedor);
		if(fornecedorOp.isEmpty()) {
			throw new NullPointerException();
		}
		Fornecedor fornecedor = fornecedorOp.get();
		
		Optional<Produto> produtoOp = Produto.findByIdOptional(id_produto);
		if (produtoOp.isEmpty()) {
			throw new NotFoundException();
		}
		Produto produto = produtoOp.get();

		List<Produto> p = fornecedor.getProduto();

		p.add(produto);

		fornecedor.setProduto(p);

		produto.persist();
		
		
//		try {
//			//List<Produto> produtoList = fornecedor.getProduto();
//			//produtoList.add(produto);
//			//fornecedor.setProduto(produtoList);
//			//fornecedor.persist();
//		} catch (Exception e) {
//			throw new NotFoundException();
//		}
//		//List<Produto> produtoList = fornecedor.getProduto();
//		//produtoList.add(produto);
//		//fornecedor.setProduto(produtoList);
//		//fornecedor.persist();
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipo_movimentacao("Fornecedor "+ fornecedor.getNome_fornecedor() +"agora tem o produto: "+  produto.nome_produto );
		movimentacao.setProduto(produto);
		movimentacao.persist();
		return produto;
		
	}
	
	@Path("/buscaPorId/{id}")
	@GET
	public Optional<Fornecedor> buscarPorId(@PathParam("id") int id_fornecedor) {
		try {
			Optional<Fornecedor> fornecedor = Fornecedor.findByIdOptional(id_fornecedor);
			if (fornecedor.isEmpty()) {
				throw new NotFoundException("Não encontrei este ID....");
			}

			return fornecedor;
		} catch (Exception e) {
			throw new NotFoundException();
		}
		
	}
}

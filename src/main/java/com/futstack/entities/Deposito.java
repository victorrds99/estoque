package com.futstack.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "Deposito")
public class Deposito extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_deposito;

	public String nome_deposito;

	public int pontoReposicao_deposito;
	
	@ManyToMany
	public List<Produto> produto;

	public int getId_deposito() {
		return id_deposito;
	}

	public void setId_deposito(int id_deposito) {
		this.id_deposito = id_deposito;
	}

	public String getNome_deposito() {
		return nome_deposito;
	}

	public void setNome_deposito(String nome_deposito) {
		this.nome_deposito = nome_deposito;
	}

	public int getPontoReposicao_deposito() {
		return pontoReposicao_deposito;
	}

	public void setPontoReposicao_deposito(int pontoReposicao_deposito) {
		this.pontoReposicao_deposito = pontoReposicao_deposito;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	public void addProdutoDeposito(Produto produto) {
		this.produto.add(produto); 
	}
	
	
}

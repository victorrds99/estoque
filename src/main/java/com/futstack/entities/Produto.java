package com.futstack.entities;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "Produto")
public class Produto extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_produto;

	public String nome_produto;

	public Double preco;

	public int quantidade_produto;

	public int pontoReposicao_produto;
	
	@ManyToOne
	@JsonbTransient
	public Fornecedor fornecedor;

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}

	public int getPontoReposicao_produto() {
		return pontoReposicao_produto;
	}

	public void setPontoReposicao_produto(int pontoReposicao_produto) {
		this.pontoReposicao_produto = pontoReposicao_produto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	

}

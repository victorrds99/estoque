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
@Table(name = "Fornecedor")
public class Fornecedor extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_fornecedor;
	private String nome_fornecedor;

	@ManyToMany
	private List<Produto> produtoDisponivel;

	public int getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(int id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public String getNome_fornecedor() {
		return nome_fornecedor;
	}

	public void setNome_fornecedor(String nome_fornecedor) {
		this.nome_fornecedor = nome_fornecedor;
	}

	public List<Produto> getProduto() {
		return produtoDisponivel;
	}

	public void setProduto(List<Produto> produto) {
		this.produtoDisponivel = produto;
	}

}

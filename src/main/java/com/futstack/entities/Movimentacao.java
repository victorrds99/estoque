package com.futstack.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "Movimentacao")
public class Movimentacao extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_movimentacao;

	@CreationTimestamp
	private Date data_movimentacao;

	public String tipo_movimentacao;

	public int quantidade_movimentacao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Produto produto;

	public int getId_movimentacao() {
		return id_movimentacao;
	}

	public void setId_movimentacao(int id_movimentacao) {
		this.id_movimentacao = id_movimentacao;
	}

	public Date getData_movimentacao() {
		return data_movimentacao;
	}

	public void setData_movimentacao(Date data_movimentacao) {
		this.data_movimentacao = data_movimentacao;
	}

	public String getTipo_movimentacao() {
		return tipo_movimentacao;
	}

	public void setTipo_movimentacao(String tipo_movimentacao) {
		this.tipo_movimentacao = tipo_movimentacao;
	}

	public int getQuantidade_movimentacao() {
		return quantidade_movimentacao;
	}

	public void setQuantidade_movimentacao(int quantidade_movimentacao) {
		this.quantidade_movimentacao = quantidade_movimentacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	

	

	
}

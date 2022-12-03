package com.vr.miniautorizador.model;

import com.vr.miniautorizador.dto.CartaoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartao")
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String numeroCartao;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Double saldo;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public CartaoDTO converterToDTO() {
		CartaoDTO cartaoDTO = new CartaoDTO();
		cartaoDTO.setNumeroCartao(this.getNumeroCartao());
		cartaoDTO.setSenha(getSenha());
		return cartaoDTO;
	}
	
	
}

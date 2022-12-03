package com.vr.miniautorizador.dto;

import com.vr.miniautorizador.model.Cartao;

public class CartaoDTO {
	
	
	private String numeroCartao;
	
	private String senha;
	
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
	

	public Cartao converterToModel() {
		Cartao cartao = new Cartao();
		cartao.setNumeroCartao(this.getNumeroCartao());
		cartao.setSenha(this.getSenha());
		return cartao;
	}
	
	
}

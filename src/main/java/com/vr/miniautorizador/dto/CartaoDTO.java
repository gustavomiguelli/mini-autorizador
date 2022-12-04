package com.vr.miniautorizador.dto;

import com.vr.miniautorizador.model.Cartao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartaoDTO {
	
	
	private String numeroCartao;
	
	private String senha;
	
	public String getNumeroCartao() {
		return numeroCartao;
	}


	public Cartao converterToModel() {
		Cartao cartao = new Cartao();
		cartao.setNumeroCartao(this.getNumeroCartao());
		cartao.setSenha(this.getSenha());
		return cartao;
	}
	
	
}

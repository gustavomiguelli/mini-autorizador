package com.vr.miniautorizador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vr.miniautorizador.dto.CartaoDTO;

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
	
	@Column(nullable = false, columnDefinition="Decimal(10,2)")
	private Double saldo;

	public CartaoDTO converterToDTO() {
		CartaoDTO cartaoDTO = new CartaoDTO();
		cartaoDTO.setNumeroCartao(this.getNumeroCartao());
		cartaoDTO.setSenha(getSenha());
		return cartaoDTO;
	}
	
	
}

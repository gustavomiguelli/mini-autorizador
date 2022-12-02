package com.vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;

@Service
public class CartaoServiceImpl implements CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	public CartaoDTO salvar(CartaoDTO cartaoDTO) {
		if(cartaoRepository.findByNumeroCartao(cartaoDTO.getNumeroCartao()) != null) {
			return null;
		}
		Cartao cartao = cartaoDTO.converterDTOtoModel(cartaoDTO);
		cartao.setSaldo(500.00);
		cartaoRepository.save(cartao);
		return cartaoDTO;
	}
	
	public Double recuperarSaldo(String numeroCartao) {
		return cartaoRepository.findByNumeroCartao(numeroCartao) != null ? cartaoRepository.findByNumeroCartao(numeroCartao).getSaldo() : null;
	}
	
}

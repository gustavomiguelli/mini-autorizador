package com.vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;

@Service
public class CartaoServiceImpl implements CartaoService {
	
	private static final double SALDO_INICIAL_CARTAO = 500.00;
	@Autowired
	private CartaoRepository cartaoRepository;
	
	public CartaoDTO criar(CartaoDTO cartaoDTO) throws DataIntegrityViolationException {
		Cartao cartao = cartaoDTO.converterToModel();
		cartao.setSaldo(SALDO_INICIAL_CARTAO);
		Cartao cartaoSalvo = cartaoRepository.save(cartao);
		return cartaoSalvo.converterToDTO();
	}
	
	public Double recuperarSaldo(String numeroCartao) throws NullPointerException {
		return cartaoRepository.findByNumeroCartao(numeroCartao).getSaldo();
	}
	
}

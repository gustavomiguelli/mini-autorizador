package com.vr.miniautorizador.service;

import org.springframework.dao.DataIntegrityViolationException;

import com.vr.miniautorizador.dto.CartaoDTO;


public interface CartaoService  {

	CartaoDTO criar(CartaoDTO cartao) throws DataIntegrityViolationException;

	Double recuperarSaldo(String numeroCartao) throws NullPointerException;
	
}

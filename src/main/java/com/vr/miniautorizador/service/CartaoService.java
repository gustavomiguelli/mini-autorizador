package com.vr.miniautorizador.service;

import javax.management.BadAttributeValueExpException;

import org.springframework.dao.DataIntegrityViolationException;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.dto.TransacaoDTO;


public interface CartaoService  {

	CartaoDTO criar(CartaoDTO cartao) throws DataIntegrityViolationException;

	Double recuperarSaldo(String numeroCartao) throws NullPointerException;
	
	void efetuarTransacao(TransacaoDTO transacao) throws BadAttributeValueExpException, UnsupportedOperationException, NullPointerException;

}

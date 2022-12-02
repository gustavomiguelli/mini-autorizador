package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.CartaoDTO;


public interface CartaoService  {

	CartaoDTO salvar(CartaoDTO cartao);

	Double recuperarSaldo(String numeroCartao);
	
}

package com.vr.miniautorizador.controller;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.enums.ResponseMessagesEnum;
import com.vr.miniautorizador.service.CartaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	
	@Autowired
	CartaoService cartaoService;
	
	
	@PostMapping
	public ResponseEntity<String> efetuarTransacao(@RequestBody TransacaoDTO transacaoDTO) {
		try {
			cartaoService.efetuarTransacao(transacaoDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessagesEnum.OK.toString());
		} catch (NullPointerException n) {
			return ResponseEntity.unprocessableEntity().body(ResponseMessagesEnum.CARTAO_INEXISTENTE.toString());
		} catch (BadAttributeValueExpException b) {
			return ResponseEntity.unprocessableEntity().body(ResponseMessagesEnum.SENHA_INVALIDA.toString());
		} catch (UnsupportedOperationException u) {
			return ResponseEntity.unprocessableEntity().body(ResponseMessagesEnum.SALDO_INSUFICIENTE.toString());
		}
		
	}
}

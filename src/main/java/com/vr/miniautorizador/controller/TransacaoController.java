package com.vr.miniautorizador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.miniautorizador.dto.TransacaoDTO;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	@PostMapping
	public ResponseEntity<String> efetuarTransacao(@RequestBody TransacaoDTO transacaoDTO) {
		return null;
	}
}

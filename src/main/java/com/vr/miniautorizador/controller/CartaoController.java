package com.vr.miniautorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService;
	
	@PostMapping
	public ResponseEntity<CartaoDTO> criar(@RequestBody CartaoDTO cartao) {
		try {
			CartaoDTO cartaoSalvo = this.cartaoService.criar(cartao);
			return ResponseEntity.status(HttpStatus.CREATED).body(cartaoSalvo); 
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(422).body(cartao);
		}
	}
	
	@GetMapping(value = "/{numeroCartao}")
	public ResponseEntity<Double> recuperarSaldo(@PathVariable String numeroCartao) {
		try {
			Double saldo = this.cartaoService.recuperarSaldo(numeroCartao);
			return ResponseEntity.ok(saldo);
		} catch (NullPointerException e) {
			return ResponseEntity.notFound().build();
		}

	}
}

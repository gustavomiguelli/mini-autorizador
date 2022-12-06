package com.vr.miniautorizador.service;

import java.util.Optional;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.dto.TransacaoDTO;
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
	
	public void efetuarTransacao(TransacaoDTO transacaoDTO) throws BadAttributeValueExpException, UnsupportedOperationException {
		Cartao cartao = validarCartao(transacaoDTO);
		Double novoSaldo = cartao.getSaldo() - transacaoDTO.getValor();
		cartao.setSaldo(novoSaldo);
		cartaoRepository.save(cartao);
	}

	private Cartao validarCartao(TransacaoDTO transacaoDTO) throws BadAttributeValueExpException, UnsupportedOperationException, NullPointerException {
		this.verificarCartaoExistente(transacaoDTO.getNumeroCartao());
		this.autenticarCartao(transacaoDTO.getNumeroCartao(), transacaoDTO.getSenhaCartao());
		return this.recuperarCartaoParaDebitoSeSaldoMaiorValor(transacaoDTO.getNumeroCartao(), transacaoDTO.getValor());
	}
	
	private void verificarCartaoExistente(String numeroCartao) {
		this.recuperarSaldo(numeroCartao);
		
	}

	public void autenticarCartao(String numeroCartao, String senha) throws BadAttributeValueExpException {
			 Optional
					.ofNullable(cartaoRepository.findByNumeroAndSenha(numeroCartao, senha))
					.orElseThrow(() -> new BadAttributeValueExpException(senha));
	}
	
	public Cartao recuperarCartaoParaDebitoSeSaldoMaiorValor(String numeroCartao, Double valor) throws UnsupportedOperationException {
			return Optional
					.ofNullable(cartaoRepository.findByNumeroCartaoIfSaldoMaiorValor(numeroCartao, valor))
					.orElseThrow(() -> new UnsupportedOperationException());
	}

}

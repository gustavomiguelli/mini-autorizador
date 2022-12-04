package com.vr.miniautorizador.service;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
	
	//Isolotion do tipo READ_COMMITTED impede leituras sujas,
	// o que viria a impedir que um débito fosse efetuado enquanto
	// uma transação ativa em outra instância ainda não tivesse sido concluída
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void efetuarTransacao(TransacaoDTO transacaoDTO) throws BadAttributeValueExpException, UnsupportedOperationException {
		this.verificarCartaoExistente(transacaoDTO.getNumeroCartao());
		this.autenticarCartao(transacaoDTO.getNumeroCartao(), transacaoDTO.getSenhaCartao());
		Cartao cartao = this.recuperarCartaoParaDebitoSeSaldoMaiorValor(transacaoDTO.getNumeroCartao(), transacaoDTO.getValor());
		Double novoSaldo = cartao.getSaldo() - transacaoDTO.getValor();
		cartao.setSaldo(novoSaldo);
		cartaoRepository.save(cartao);
	}
	
	private void verificarCartaoExistente(String numeroCartao) {
		this.recuperarSaldo(numeroCartao);
		
	}

	public Double autenticarCartao(String numeroCartao, String senha) throws BadAttributeValueExpException {
		try {
			return cartaoRepository.findByNumeroAndSenha(numeroCartao, senha).getSaldo();
		} catch (NullPointerException e) {
			throw new BadAttributeValueExpException(senha);
		}
	}
	
	public Cartao recuperarCartaoParaDebitoSeSaldoMaiorValor(String numeroCartao, Double valor) throws UnsupportedOperationException {
		try {
			cartaoRepository.findByNumeroCartaoIfSaldoMaiorValor(numeroCartao, valor).getSaldo();
			return cartaoRepository.findByNumeroCartaoIfSaldoMaiorValor(numeroCartao, valor);
		} catch (NullPointerException e) {
			throw new UnsupportedOperationException();
		}
		
	}

}

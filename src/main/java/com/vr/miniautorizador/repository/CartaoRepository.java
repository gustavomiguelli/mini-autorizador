package com.vr.miniautorizador.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vr.miniautorizador.model.Cartao;

@Repository
public interface CartaoRepository extends CrudRepository<Cartao, Long>{
	
	@Query("Select c from Cartao c where c.numeroCartao = :numeroCartao")
	Cartao findByNumeroCartao(@Param(value = "numeroCartao") String numeroCartao);
	
	@Query("Select c from Cartao c where c.numeroCartao = :numeroCartao and c.senha = :senha")
	Cartao findByNumeroAndSenha(@Param(value = "numeroCartao") String numeroCartao, @Param(value = "senha") String senha);
	
	@Query("Select c from Cartao c where c.numeroCartao = :numeroCartao and c.saldo > :valor")
	Cartao findByNumeroCartaoIfSaldoMaiorValor(@Param(value = "numeroCartao") String numeroCartao, @Param(value = "valor") Double valor);
	
	
	@Query("update Cartao set saldo = :novoSaldo where numeroCartao = :numeroCartao")
	void debitarSaldo(@Param(value = "numeroCartao") String numeroCartao, @Param(value = "novoSaldo") Double novoSaldo);
	
	

}

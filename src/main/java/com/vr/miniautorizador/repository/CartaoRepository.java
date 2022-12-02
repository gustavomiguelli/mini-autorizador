package com.vr.miniautorizador.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vr.miniautorizador.model.Cartao;

public interface CartaoRepository extends CrudRepository<Cartao, Long>{
	
	@Query("Select c from Cartao c where c.numeroCartao = :numeroCartao")
	Cartao findByNumeroCartao(@Param(value = "numeroCartao") String numeroCartao);
	
	

}

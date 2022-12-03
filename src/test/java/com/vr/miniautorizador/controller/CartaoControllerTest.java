package com.vr.miniautorizador.controller;

import java.net.URI;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CartaoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@Order(1)
	public void deveRetornarSucesso_QuandoSalvarCartao() throws Exception  {
		URI uri = new URI("/cartoes");
		String json = "{\"numeroCartao\": \"5549873025634505\", \"senha\": \"12345\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
						.status().isCreated())
			.andExpect(MockMvcResultMatchers
					.content().json(json));
		
		
	}
	
	@Test
	@Order(2)
	public void deveRetornarSucesso_QuandoBuscarSaldo() throws Exception {
		
		URI uri = new URI("/cartoes/5549873025634505");
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.get(uri))
			.andExpect(MockMvcResultMatchers
						.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers
						.content().json("500.0"));
	}
	
	
	@Test
	@Order(3)
	public void deveRetornar422_QuandoSalvarCartaoJaExistente() throws Exception  {
		URI uri = new URI("/cartoes");
		String json = "{\"numeroCartao\": \"5549873025634505\", \"senha\": \"12345\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
						.status().is(HttpStatus.SC_UNPROCESSABLE_ENTITY))
			.andExpect(MockMvcResultMatchers
					.content().json(json));
		
		
	}
	
	
	@Test
	@Order(4)
	public void deveRetornarNotFound_QuandoBuscarSaldoCartaoInexistente() throws Exception {
		
		URI uri = new URI("/cartoes/5549873025634506");
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.get(uri))
			.andExpect(MockMvcResultMatchers
						.status().isNotFound());
	}
}	

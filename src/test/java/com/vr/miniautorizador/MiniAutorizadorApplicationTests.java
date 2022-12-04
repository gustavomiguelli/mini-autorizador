package com.vr.miniautorizador;

import java.net.URI;

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

import com.vr.miniautorizador.enums.ResponseMessagesEnum;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class MiniAutorizadorApplicationTests {

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
						.status().isUnprocessableEntity())
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
	
	@Test
	@Order(5)
	public void deveRetornarCreatedAndOk_QuandoDebitarCartao() throws Exception  {
		URI uri = new URI("/transacoes");
		String json = "{\"numeroCartao\": \"5549873025634505\", \"senhaCartao\": \"12345\", \"valor\": \"10.00\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
						.status().isCreated())
			.andExpect(MockMvcResultMatchers
					.content().string(ResponseMessagesEnum.OK.toString()));
		
		
	}
	
	
	@Test
	@Order(6)
	public void deveRetornar422AndCartaoInexistente_QuandoDebitarCartao() throws Exception  {
		URI uri = new URI("/transacoes");
		String json = "{\"numeroCartao\": \"5549873025634506\", \"senhaCartao\": \"12345\", \"valor\": \"10.00\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
						.status().isUnprocessableEntity())
			.andExpect(MockMvcResultMatchers
					.content().string(ResponseMessagesEnum.CARTAO_INEXISTENTE.toString()));
		
		
	}
	
	@Test
	@Order(7)
	public void deveRetornar422AndSenhaInvalida_QuandoDebitarCartao() throws Exception  {
		URI uri = new URI("/transacoes");
		String json = "{\"numeroCartao\": \"5549873025634505\", \"senhaCartao\": \"1234\", \"valor\": \"10.00\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
						.status().isUnprocessableEntity())
			.andExpect(MockMvcResultMatchers
					.content().string(ResponseMessagesEnum.SENHA_INVALIDA.toString()));
		
		
	}
	
	@Test
	@Order(8)
	public void deveRetornar422AndSaldoInsuficiente_QuandoDebitarCartao() throws Exception  {
		URI uri = new URI("/transacoes");
		String json = "{\"numeroCartao\": \"5549873025634505\", \"senhaCartao\": \"12345\", \"valor\": \"550.00\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
						.status().isUnprocessableEntity())
			.andExpect(MockMvcResultMatchers
					.content().string(ResponseMessagesEnum.SALDO_INSUFICIENTE.toString()));
		
		
	}

}

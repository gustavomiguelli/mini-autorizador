package com.vr.miniautorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@EntityScan(basePackages = "com.vr.miniautorizador.model")
@ComponentScan(basePackages = "com.vr.miniautorizador.*")
@OpenAPIDefinition(
		info = @Info(title = "API-Mini Autorizador", version = "1.0.0"),
		servers = {
				@Server(url = "http://localhost:8080")
		})
public class MiniAutorizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniAutorizadorApplication.class, args);
	}

}

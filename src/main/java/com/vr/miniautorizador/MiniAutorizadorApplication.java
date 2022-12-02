package com.vr.miniautorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.vr.miniautorizador.model")
@ComponentScan(basePackages = "com.vr.miniautorizador.*")
public class MiniAutorizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniAutorizadorApplication.class, args);
	}

}

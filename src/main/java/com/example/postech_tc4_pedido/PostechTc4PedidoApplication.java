package com.example.postech_tc4_pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostechTc4PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostechTc4PedidoApplication.class, args);
	}

}

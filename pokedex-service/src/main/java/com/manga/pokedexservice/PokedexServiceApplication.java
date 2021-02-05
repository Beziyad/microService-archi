package com.manga.pokedexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PokedexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexServiceApplication.class, args);
	}

}

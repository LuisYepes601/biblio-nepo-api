package com.nexus.biblioNepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BiblioNepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioNepoApplication.class, args);
	}

}

package com.springboot.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsApplication implements CommandLineRunner {

	//private static final Logger LOG= LoggerFactory.getLogger(ProductsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//LOG.info("Hello World");
	}
}

package com.linktic.productos_service.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * ProductosServiceApplication.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@EnableJpaRepositories(basePackages = "com.linktic.productos_service.crosscutting.persistence.repository")
@EntityScan(basePackages = "com.linktic.productos_service.crosscutting.persistence.entity")
@SpringBootApplication(scanBasePackages = "com.linktic.productos_service")
public class ProductosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductosServiceApplication.class, args);
	}

}

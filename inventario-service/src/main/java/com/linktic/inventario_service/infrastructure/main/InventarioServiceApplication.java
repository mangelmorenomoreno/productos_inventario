package com.linktic.inventario_service.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.linktic.inventario_service.crosscutting.persistence.repository")
@EntityScan(basePackages = "com.linktic.inventario_service.crosscutting.persistence.entity")
@SpringBootApplication(scanBasePackages = "com.linktic.inventario_service")
@EnableFeignClients(basePackages = "com.linktic.inventario_service.crosscutting.utils")
public class InventarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioServiceApplication.class, args);
	}

}

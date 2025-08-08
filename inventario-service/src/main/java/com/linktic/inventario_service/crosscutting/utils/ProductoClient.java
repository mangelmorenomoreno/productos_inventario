package com.linktic.inventario_service.crosscutting.utils;

import com.linktic.inventario_service.crosscutting.domain.dto.ProductoDTO;

import com.linktic.inventario_service.crosscutting.domain.response.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productos-service", url = "${productos.service.url}")
public interface ProductoClient {

  @GetMapping(value = "/product/{id}", headers = "X-API-KEY=${productos.service.api-key}")
  ResponseEntity<RestResponse<ProductoDTO>> findById(@PathVariable("id") Long id);

}

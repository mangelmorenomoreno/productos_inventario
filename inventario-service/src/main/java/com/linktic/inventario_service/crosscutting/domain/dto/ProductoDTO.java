package com.linktic.inventario_service.crosscutting.domain.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ProductoDTO.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 2025-08-07
 */
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProductoDTO {

  private Long id;
  private String nombre;
  private BigDecimal precio;
  private String descripcion;

}

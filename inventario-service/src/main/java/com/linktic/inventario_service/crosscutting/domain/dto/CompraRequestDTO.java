package com.linktic.inventario_service.crosscutting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CompraDTO.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 2025-08-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraRequestDTO {
  private Long productoId;
  private Integer cantidad;
}

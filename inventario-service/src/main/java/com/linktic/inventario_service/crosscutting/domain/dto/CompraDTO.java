package com.linktic.inventario_service.crosscutting.domain.dto;

import java.math.BigDecimal;
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
public class CompraDTO {

  private Long productoId;
  private String nombre;
  private BigDecimal precioUnitario;
  private Integer cantidadComprada;
  private BigDecimal total;
  private String descripcion;

}

package com.linktic.productos_service.crosscutting.persistence.projection;

import java.math.BigDecimal;

/**
 * ProductoProjection.
 *
 * @author miguel.moreno
 * @version 1.1
 * @since 07-08-2025
 */
public interface ProductoProjection {

  Long getId();
  String getNombre();
  BigDecimal getPrecio();
  String getDescripcion();

}

package com.linktic.productos_service.crosscutting.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * MessageCodes.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since  07-08-2025
 */
@AllArgsConstructor
@ToString
@NoArgsConstructor
public enum MessageCodes {

  PRODUCT_CREATED_SUCCESS("Producto creado exitosamente"),
  PRODUCT_NOT_FOUND("Producto no encontrado"),
  PRODUCT_FIND_BY_ID_SUCCESS("Producto obtenido correctamente"),
  PRODUCT_FIND_ALL_SUCCESS("Listado de productos obtenido correctamente")
  ;


  private String message;

  public String getMessage() {
    return message;
  }

}

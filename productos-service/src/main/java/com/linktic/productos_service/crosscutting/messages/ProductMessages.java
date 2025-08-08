package com.linktic.productos_service.crosscutting.messages;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMessages {
  public static final String NOT_BLANK = "El nombre no puede estar vacío";
  public static final String PRECIO_MAXIMO = "El precio no puede superar los mil millones";
  public static final String PRECIO_DIGITOS = "El precio debe tener hasta 10 dígitos enteros y 2 decimales";
  public static final String DESCRIPCION_CARACTERES = "La descripción no debe superar los 255 caracteres";
  public static final String NOMBRE_CARACTERES = "El nombre no debe superar los 100 caracteres";

}

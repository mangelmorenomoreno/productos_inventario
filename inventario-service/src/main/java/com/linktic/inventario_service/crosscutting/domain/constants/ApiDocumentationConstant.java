package com.linktic.inventario_service.crosscutting.domain.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ControllerConstants.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiDocumentationConstant {

  public static final String FIND_ALL_SUMARY = "Obtener todos los productos";
  public static final String FIND_ALL_DESCRIPCION = "Retorna la lista de todos los productos registrados";
  public static final String FIND_ID_SUMARY = "Obtener la cantidad disponible de un producto producto por ID";
  public static final String FIND_ID_DESCRIPCION = "Consultar la cantidad disponible de un producto específico por ID (obteniendo\n" +
      "la información del producto desde el microservicio de productos).\n";
  public static final String COMPRA_SUMMARY = "Realizar compra de un producto";
  public static final String COMPRA_DESCRIPCION = "Permite realizar la compra de un producto verificando inventario y retornando los detalles";

  public static final String UPDATE_SUMARY = "Actualiza inventario de un nuevo producto";
  public static final String UPDATE_DESCRIPCION = "Actualiza la cantidad de productos del inventario";


}

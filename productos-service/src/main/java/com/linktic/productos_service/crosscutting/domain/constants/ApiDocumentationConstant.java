package com.linktic.productos_service.crosscutting.domain.constants;

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
  public static final String FIND_ID_SUMARY = "Obtener producto por ID";
  public static final String FIND_ID_DESCRIPCION = "Consulta un producto específico por su identificador";
  public static final String CREATE_SUMARY = "Crear un nuevo producto";
  public static final String CREATE_DESCRIPCION = "Registra un producto en el sistema";


}

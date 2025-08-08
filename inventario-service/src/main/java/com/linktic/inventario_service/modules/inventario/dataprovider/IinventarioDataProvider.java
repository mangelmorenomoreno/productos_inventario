package com.linktic.inventario_service.modules.inventario.dataprovider;

import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import java.util.Optional;

/**
 * IinventarioDataProvider.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
public interface IinventarioDataProvider {

  Optional<Inventario> findProductById(Long productoId);

  Inventario updateCantidad(Long productoId, int nuevaCantidad);

}

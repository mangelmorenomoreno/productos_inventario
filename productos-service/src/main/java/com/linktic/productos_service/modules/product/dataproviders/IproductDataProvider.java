package com.linktic.productos_service.modules.product.dataproviders;


import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import java.util.List;
import java.util.Optional;

/**
 * IproductDataProvider.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
public interface IproductDataProvider {

  List<ProductoProjection> findAll();
  Optional<ProductoProjection> findById(Long id);
  Producto save(Producto producto);

}

package com.linktic.productos_service.modules.product.usecase;

import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import com.linktic.productos_service.modules.product.dataproviders.IproductDataProvider;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductService.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

  private final IproductDataProvider iproductDataProvider;

  public List<ProductoProjection> obtenerTodos() {
    log.info("Obteniendo todos los productos");
    return iproductDataProvider.findAll();
  }

  public Optional<ProductoProjection> obtenerPorId(Long id) {
    log.info("Obteniendo producto por ID: {}", id);
    return iproductDataProvider.findById(id);
  }

  public Producto crearProducto(Producto producto) {
    log.info("Creando producto: {}", producto);
    return iproductDataProvider.save(producto);
  }

}

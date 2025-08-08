package com.linktic.productos_service.modules.product.dataproviders.jpa;

import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import com.linktic.productos_service.crosscutting.persistence.repository.ProductoRepository;
import com.linktic.productos_service.modules.product.dataproviders.IproductDataProvider;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * ProductDataProvider.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class ProductDataProvider  implements IproductDataProvider {


  private final ProductoRepository productoRepository;


  @Override
  public List<ProductoProjection> findAll() {
    log.info("Consultando todos los productos (proyección)");
    return productoRepository.findAllProjectedBy();
  }

  @Override
  public Optional<ProductoProjection> findById(Long id) {
    log.info("Consultando producto por ID: {}", id);
    return Optional.ofNullable(productoRepository.findProjectedById(id));
  }

  @Override
  public Producto save(Producto producto) {
    log.info("Guardando nuevo producto: {}", producto);
    return productoRepository.save(producto);
  }
}

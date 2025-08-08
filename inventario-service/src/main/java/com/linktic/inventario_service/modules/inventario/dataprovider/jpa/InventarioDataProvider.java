package com.linktic.inventario_service.modules.inventario.dataprovider.jpa;

import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import com.linktic.inventario_service.crosscutting.persistence.repository.InventarioRepository;
import com.linktic.inventario_service.modules.inventario.dataprovider.IinventarioDataProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * InventarioDataProvider.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class InventarioDataProvider implements IinventarioDataProvider {

  private final InventarioRepository inventarioRepository;

  @Override
  public Optional<Inventario> findProductById(Long productoId) {
    return inventarioRepository.findByIdProducto(productoId);
  }

  @Override
  public Inventario updateCantidad(Long productoId, int nuevaCantidad) {
    Inventario inventario = inventarioRepository.findByIdProducto(productoId)
        .orElse(new Inventario(null, productoId, 0));
    inventario.setCantidad(nuevaCantidad);
    return inventarioRepository.save(inventario);
  }
}

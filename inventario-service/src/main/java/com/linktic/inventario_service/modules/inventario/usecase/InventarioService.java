package com.linktic.inventario_service.modules.inventario.usecase;

import com.linktic.inventario_service.crosscutting.domain.dto.ActualizarInventarioDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.InventarioProductoDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.ProductoDTO;
import com.linktic.inventario_service.crosscutting.domain.enums.MessageCodes;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.crosscutting.domain.response.RestResponse;
import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import com.linktic.inventario_service.crosscutting.utils.ProductoClient;
import com.linktic.inventario_service.modules.inventario.dataprovider.IinventarioDataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * InventarioService.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class InventarioService {

  private final IinventarioDataProvider iinventarioDataProvider;

  private final ProductoClient productoClient;

  public InventarioProductoDTO findProductById(Long productoId) {

    ResponseEntity<RestResponse<ProductoDTO>> response = productoClient.findById(productoId);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().getData() != null) {
      ProductoDTO producto = response.getBody().getData();

      Inventario inventario = iinventarioDataProvider.findProductById(productoId).orElse(null);
      return InventarioProductoDTO.builder()
          .productoId(producto.getId())
          .nombre(producto.getNombre())
          .precio(producto.getPrecio())
          .descripcion(producto.getDescripcion())
          .cantidad(inventario != null ? inventario.getCantidad() : null)
          .build();
    } else {
      throw new CustomException(MessageCodes.PRODUCT_NOT_FOUND.getMessage());
    }
  }


  public Inventario updateInventario(ActualizarInventarioDTO dto) {
    return iinventarioDataProvider.findProductById(dto.getProductoId())
        .map(inventarioExistente -> {
          inventarioExistente.setCantidad(dto.getCantidad());
          return iinventarioDataProvider.update(inventarioExistente);
        })
        .orElseGet(() -> {
          Inventario nuevoInventario = new Inventario();
          nuevoInventario.setIdProducto(dto.getProductoId());
          nuevoInventario.setCantidad(dto.getCantidad());
          return iinventarioDataProvider.update(nuevoInventario);
        });
  }


}

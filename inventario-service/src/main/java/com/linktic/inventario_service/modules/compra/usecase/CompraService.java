package com.linktic.inventario_service.modules.compra.usecase;

import com.linktic.inventario_service.crosscutting.domain.dto.CompraDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.CompraRequestDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.ProductoDTO;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.crosscutting.domain.response.RestResponse;
import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import com.linktic.inventario_service.crosscutting.utils.ProductoClient;
import com.linktic.inventario_service.modules.inventario.dataprovider.IinventarioDataProvider;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * CompraService.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CompraService {

  private final IinventarioDataProvider iinventarioDataProvider;

  private final ProductoClient productoClient;

  public CompraDTO realizarCompra(CompraRequestDTO request) {
    ProductoDTO producto = obtenerProductoDesdeServicio(request.getProductoId());
    Inventario inventario = obtenerInventarioExistente(request.getProductoId());
    validarCantidadDisponible(inventario, request.getCantidad());

    actualizarCantidadInventario(inventario, request.getCantidad());

    return construirCompraDTO(producto, request.getCantidad());
  }

  private ProductoDTO obtenerProductoDesdeServicio(Long productoId) {
    ResponseEntity<RestResponse<ProductoDTO>> response = productoClient.findById(productoId);

    if (response.getStatusCode().is2xxSuccessful()
        && response.getBody() != null
        && response.getBody().getData() != null) {
      return response.getBody().getData();
    }

    throw new CustomException("Producto no encontrado");
  }

  private Inventario obtenerInventarioExistente(Long productoId) {
    return iinventarioDataProvider.findProductById(productoId)
        .orElseThrow(() -> new CustomException("Inventario no encontrado para el producto"));
  }

  private void validarCantidadDisponible(Inventario inventario, int cantidadSolicitada) {
    if (inventario.getCantidad() < cantidadSolicitada) {
      throw new CustomException("Inventario insuficiente para la compra solicitada");
    }
  }

  private void actualizarCantidadInventario(Inventario inventario, int cantidadComprada) {
    inventario.setCantidad(inventario.getCantidad() - cantidadComprada);
    iinventarioDataProvider.update(inventario);
  }

  private CompraDTO construirCompraDTO(ProductoDTO producto, int cantidadComprada) {
    return CompraDTO.builder()
        .productoId(producto.getId())
        .nombre(producto.getNombre())
        .precioUnitario(producto.getPrecio())
        .cantidadComprada(cantidadComprada)
        .total(producto.getPrecio().multiply(BigDecimal.valueOf(cantidadComprada)))
        .descripcion(producto.getDescripcion())
        .build();
  }



}

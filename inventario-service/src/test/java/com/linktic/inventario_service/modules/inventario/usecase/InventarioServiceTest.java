package com.linktic.inventario_service.modules.inventario.usecase;

import com.linktic.inventario_service.crosscutting.domain.dto.ActualizarInventarioDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.ProductoDTO;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.crosscutting.domain.response.RestResponse;
import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import com.linktic.inventario_service.crosscutting.utils.ProductoClient;
import com.linktic.inventario_service.modules.inventario.dataprovider.IinventarioDataProvider;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

/**
 * InventarioServiceTest.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@RunWith(MockitoJUnitRunner.class)
public class InventarioServiceTest {

  @Mock
  private IinventarioDataProvider iinventarioDataProvider;

  @Mock
  private ProductoClient productoClient;

  @InjectMocks
  private InventarioService inventarioService;

  private ProductoDTO producto;
  private ActualizarInventarioDTO dto;
  private Inventario inventario;

  @Before
  public void setUp() {
    producto = ProductoDTO.builder()
        .id(1L)
        .nombre("Producto 1")
        .precio(new BigDecimal("100.00"))
        .descripcion("Descripción")
        .build();

    dto = new ActualizarInventarioDTO();
    dto.setProductoId(1L);
    dto.setCantidad(10);

    inventario = new Inventario();
    inventario.setId(1L);
    inventario.setIdProducto(1L);
    inventario.setCantidad(5);
  }

  @Test
  public void testUpdateInventario_ProductoExisteYInventarioExiste() {
    RestResponse<ProductoDTO> restResponse = RestResponse.<ProductoDTO>builder().data(producto).build();
    when(productoClient.findById(1L)).thenReturn(ResponseEntity.ok(restResponse));
    when(iinventarioDataProvider.findProductById(1L)).thenReturn(Optional.of(inventario));
    when(iinventarioDataProvider.update(any())).thenReturn(inventario);

    Inventario resultado = inventarioService.updateInventario(dto);

    assertEquals(dto.getCantidad(), resultado.getCantidad());
    verify(iinventarioDataProvider, times(1)).update(any());
  }

  @Test
  public void testUpdateInventario_ProductoExisteYInventarioNoExiste() {
    RestResponse<ProductoDTO> restResponse = RestResponse.<ProductoDTO>builder().data(producto).build();
    when(productoClient.findById(1L)).thenReturn(ResponseEntity.ok(restResponse));
    when(iinventarioDataProvider.findProductById(1L)).thenReturn(Optional.empty());

    Inventario inventarioEsperado = new Inventario();
    inventarioEsperado.setIdProducto(1L);
    inventarioEsperado.setCantidad(10);

    when(iinventarioDataProvider.update(any())).thenReturn(inventarioEsperado);

    Inventario resultado = inventarioService.updateInventario(dto);

    assertEquals(dto.getCantidad(), resultado.getCantidad());
    verify(iinventarioDataProvider, times(1)).update(any());
  }

  @Test(expected = CustomException.class)
  public void testUpdateInventario_ProductoNoExiste() {
    // Simula respuesta sin datos
    RestResponse<ProductoDTO> restResponse = RestResponse.<ProductoDTO>builder().data(null).build();
    when(productoClient.findById(1L)).thenReturn(ResponseEntity.ok(restResponse));

    inventarioService.updateInventario(dto);
  }
}

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
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

/**
 * InventarioController.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@RunWith(MockitoJUnitRunner.class)
public class CompraServiceTest {

  @Mock
  private IinventarioDataProvider inventarioDataProvider;

  @Mock
  private ProductoClient productoClient;

  @InjectMocks
  private CompraService compraService;

  private CompraRequestDTO request;
  private ProductoDTO producto;
  private Inventario inventario;

  @Before
  public void setUp() {
    request = new CompraRequestDTO();
    request.setProductoId(1L);
    request.setCantidad(3);

    producto = ProductoDTO.builder()
        .id(1L)
        .nombre("Camiseta")
        .precio(new BigDecimal("100.00"))
        .descripcion("Camiseta deportiva")
        .build();

    inventario = new Inventario();
    inventario.setIdProducto(1L);
    inventario.setCantidad(5);
  }

  @Test
  public void testRealizarCompra_Success() {
    when(productoClient.findById(1L)).thenReturn(ResponseEntity.ok(
        RestResponse.<ProductoDTO>builder().data(producto).build()));
    when(inventarioDataProvider.findProductById(1L)).thenReturn(Optional.of(inventario));
    when(inventarioDataProvider.update(any())).thenReturn(inventario);

    CompraDTO compra = compraService.realizarCompra(request);

    assertNotNull(compra);
    assertEquals(producto.getId(), compra.getProductoId());
    assertEquals(producto.getNombre(), compra.getNombre());
    assertEquals(new BigDecimal("300.00"), compra.getTotal());
    verify(inventarioDataProvider, times(1)).update(any());
  }

  @Test(expected = CustomException.class)
  public void testRealizarCompra_ProductoNoExiste() {
    when(productoClient.findById(1L)).thenReturn(ResponseEntity.ok(
        RestResponse.<ProductoDTO>builder().data(null).build()));

    compraService.realizarCompra(request);
  }

  @Test(expected = CustomException.class)
  public void testRealizarCompra_InventarioNoExiste() {
    when(productoClient.findById(1L)).thenReturn(ResponseEntity.ok(
        RestResponse.<ProductoDTO>builder().data(producto).build()));
    when(inventarioDataProvider.findProductById(1L)).thenReturn(Optional.empty());

    compraService.realizarCompra(request);
  }

  @Test(expected = CustomException.class)
  public void testRealizarCompra_InventarioInsuficiente() {
    inventario.setCantidad(2); // Menor que la cantidad solicitada

    when(productoClient.findById(1L)).thenReturn(ResponseEntity.ok(
        RestResponse.<ProductoDTO>builder().data(producto).build()));
    when(inventarioDataProvider.findProductById(1L)).thenReturn(Optional.of(inventario));

    compraService.realizarCompra(request);
  }
}

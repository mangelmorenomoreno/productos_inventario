package com.linktic.inventario_service.modules.inventario.api;

import com.linktic.inventario_service.crosscutting.domain.dto.ActualizarInventarioDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.InventarioProductoDTO;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import com.linktic.inventario_service.modules.inventario.usecase.InventarioService;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

/**
 * InventarioControllerTest.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@RunWith(MockitoJUnitRunner.class)
public class InventarioControllerTest {

  @Mock
  private InventarioService inventarioService;

  @InjectMocks
  private InventarioController inventarioController;

  private ActualizarInventarioDTO actualizarDTO;
  private Inventario inventario;
  private InventarioProductoDTO productoDTO;

  @Before
  public void setUp() {
    actualizarDTO = new ActualizarInventarioDTO();
    actualizarDTO.setProductoId(1L);
    actualizarDTO.setCantidad(10);

    inventario = new Inventario();
    inventario.setIdProducto(1L);
    inventario.setCantidad(10);

    productoDTO = InventarioProductoDTO.builder()
        .productoId(1L)
        .nombre("Producto Test")
        .descripcion("Desc")
        .precio(new BigDecimal("150.00"))
        .cantidad(10)
        .build();
  }

  @Test
  public void testActualizarInventario_success() {
    when(inventarioService.updateInventario(actualizarDTO)).thenReturn(inventario);

    ResponseEntity<?> response = inventarioController.actualizarInventario(actualizarDTO);

    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());
  }

  @Test
  public void testActualizarInventario_error() {
    when(inventarioService.updateInventario(actualizarDTO))
        .thenThrow(new CustomException("Producto no encontrado"));

    ResponseEntity<?> response = inventarioController.actualizarInventario(actualizarDTO);

    assertEquals(400, response.getStatusCodeValue());
    assertNotNull(response.getBody());
      }

  @Test
  public void testFindProductById_success() {
    when(inventarioService.findProductById(1L)).thenReturn(productoDTO);

    ResponseEntity<?> response = inventarioController.findProductById(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());

  }

  @Test
  public void testFindProductById_error() {
    when(inventarioService.findProductById(2L))
        .thenThrow(new CustomException("Producto no encontrado"));

    ResponseEntity<?> response = inventarioController.findProductById(2L);

    assertEquals(400, response.getStatusCodeValue());
    assertNotNull(response.getBody());
  }
}

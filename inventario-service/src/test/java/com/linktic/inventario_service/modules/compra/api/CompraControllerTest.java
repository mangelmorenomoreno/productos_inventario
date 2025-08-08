package com.linktic.inventario_service.modules.compra.api;

import com.linktic.inventario_service.crosscutting.domain.dto.CompraDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.CompraRequestDTO;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.modules.compra.usecase.CompraService;
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
 * CompraController.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@RunWith(MockitoJUnitRunner.class)
public class CompraControllerTest {

  @Mock
  private CompraService compraService;

  @InjectMocks
  private CompraController compraController;

  private CompraRequestDTO request;
  private CompraDTO compra;

  @Before
  public void setUp() {

    request = new CompraRequestDTO();
    request.setProductoId(1L);
    request.setCantidad(2);

    compra = CompraDTO.builder()
        .productoId(1L)
        .nombre("Camisa")
        .precioUnitario(new BigDecimal("100.00"))
        .cantidadComprada(2)
        .total(new BigDecimal("200.00"))
        .descripcion("Camisa azul")
        .build();
  }

  @Test
  public void testRealizarCompra_success() {
    when(compraService.realizarCompra(request)).thenReturn(compra);

    ResponseEntity<?> response = compraController.realizarCompra(request);

    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());
    verify(compraService, times(1)).realizarCompra(request);
  }

  @Test
  public void testRealizarCompra_error() {
    when(compraService.realizarCompra(request))
        .thenThrow(new CustomException("Error al realizar la compra"));

    ResponseEntity<?> response = compraController.realizarCompra(request);

    assertEquals(400, response.getStatusCodeValue()); // La respuesta del error también retorna 200 con body de error
    assertNotNull(response.getBody());
    verify(compraService, times(1)).realizarCompra(request);
  }
}

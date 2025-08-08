package com.linktic.productos_service.modules.product.api;

import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import com.linktic.productos_service.modules.product.usecase.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

  @InjectMocks
  private ProductController productController;

  @Mock
  private ProductService productService;

  private Producto producto;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    producto = new Producto(1L, "Café", new BigDecimal("12000.00"), "Café premium");
  }

  @Test
  public void crearProductoTest() {
    lenient().when(productService.crearProducto(any(Producto.class))).thenReturn(producto);

    ResponseEntity<?> response = productController.create(producto);

    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());
  }

  @Test
  public void obtenerProductoPorIdTest() {
    ProductoProjection projection = mock(ProductoProjection.class);
    lenient().when(projection.getId()).thenReturn(1L);
    lenient().when(projection.getNombre()).thenReturn("Café");
    lenient().when(projection.getPrecio()).thenReturn(BigDecimal.valueOf(5000));
    lenient().when(projection.getDescripcion()).thenReturn("Bebida caliente");

    lenient().when(productService.obtenerPorId(1L)).thenReturn(Optional.of(projection));

    ResponseEntity<?> response = productController.findById(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());
  }

  @Test
  public void obtenerTodosLosProductosTest() {
    ProductoProjection projection = mock(ProductoProjection.class);
    lenient().when(projection.getId()).thenReturn(1L);
    lenient().when(projection.getNombre()).thenReturn("Café");
    lenient().when(projection.getPrecio()).thenReturn(BigDecimal.valueOf(4500));
    lenient().when(projection.getDescripcion()).thenReturn("Bebida caliente");

    lenient().when(productService.obtenerTodos()).thenReturn(List.of(projection));

    ResponseEntity<?> response = productController.findAll();

    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());
  }
}

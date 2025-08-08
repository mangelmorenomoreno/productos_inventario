package com.linktic.productos_service.modules.product.usecase;

import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import com.linktic.productos_service.modules.product.dataproviders.IproductDataProvider;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * ProductServiceTest.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 08-08-2025
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private IproductDataProvider iproductDataProvider;

  private Producto producto;
  private ProductoProjection projection;

  @Before
  public void setUp() {
    producto = new Producto(1L, "Café", new BigDecimal("12000.00"), "Café premium");
    projection = mock(ProductoProjection.class);
    lenient().when(projection.getId()).thenReturn(1L);
    lenient().when(projection.getNombre()).thenReturn("Café");
    lenient().when(projection.getPrecio()).thenReturn(new BigDecimal("12000.00"));
    lenient().when(projection.getDescripcion()).thenReturn("Café premium");
  }

  @Test
  public void testObtenerTodos() {
    when(iproductDataProvider.findAll()).thenReturn(List.of(projection));

    List<ProductoProjection> resultado = productService.obtenerTodos();

    assertNotNull(resultado);
    assertEquals(1, resultado.size());
    assertEquals("Café", resultado.get(0).getNombre());
  }

  @Test
  public void testObtenerPorId() {
    when(iproductDataProvider.findById(1L)).thenReturn(Optional.of(projection));

    Optional<ProductoProjection> resultado = productService.obtenerPorId(1L);

    assertTrue(resultado.isPresent());
    assertEquals("Café", resultado.get().getNombre());
  }

  @Test
  public void testCrearProducto() {
    when(iproductDataProvider.save(producto)).thenReturn(producto);

    Producto creado = productService.crearProducto(producto);

    assertNotNull(creado);
    assertEquals("Café", creado.getNombre());
    assertEquals(new BigDecimal("12000.00"), creado.getPrecio());
  }
}

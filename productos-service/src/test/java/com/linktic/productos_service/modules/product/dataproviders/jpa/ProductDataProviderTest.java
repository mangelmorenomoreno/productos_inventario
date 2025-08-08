package com.linktic.productos_service.modules.product.dataproviders.jpa;

import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import com.linktic.productos_service.crosscutting.persistence.repository.ProductoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * ProductDataProviderTest.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 08-08-2025
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductDataProviderTest {

  @InjectMocks
  private ProductDataProvider productDataProvider;

  @Mock
  private ProductoRepository productoRepository;

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
  public void testFindAll() {
    when(productoRepository.findAllProjectedBy()).thenReturn(List.of(projection));

    List<ProductoProjection> resultado = productDataProvider.findAll();

    assertNotNull(resultado);
    assertEquals(1, resultado.size());
    assertEquals("Café", resultado.get(0).getNombre());

    verify(productoRepository, times(1)).findAllProjectedBy();
  }

  @Test
  public void testFindById() {
    when(productoRepository.findProjectedById(1L)).thenReturn(projection);

    Optional<ProductoProjection> resultado = productDataProvider.findById(1L);

    assertTrue(resultado.isPresent());
    assertEquals("Café", resultado.get().getNombre());

    verify(productoRepository, times(1)).findProjectedById(1L);
  }

  @Test
  public void testFindByIdNotFound() {
    when(productoRepository.findProjectedById(2L)).thenReturn(null);

    Optional<ProductoProjection> resultado = productDataProvider.findById(2L);

    assertFalse(resultado.isPresent());

    verify(productoRepository, times(1)).findProjectedById(2L);
  }

  @Test
  public void testSave() {
    when(productoRepository.save(producto)).thenReturn(producto);

    Producto resultado = productDataProvider.save(producto);

    assertNotNull(resultado);
    assertEquals("Café", resultado.getNombre());

    verify(productoRepository, times(1)).save(producto);
  }
}

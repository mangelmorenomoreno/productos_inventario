package com.linktic.inventario_service.modules.inventario.dataprovider.jpa;

import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import com.linktic.inventario_service.crosscutting.persistence.repository.InventarioRepository;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * InventarioDataProviderTest.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@RunWith(MockitoJUnitRunner.class)
public class InventarioDataProviderTest {

  @Mock
  private InventarioRepository inventarioRepository;

  @InjectMocks
  private InventarioDataProvider inventarioDataProvider;

  private Inventario inventario;

  @Before
  public void setUp() {
    inventario = new Inventario();
    inventario.setId(1L);
    inventario.setIdProducto(100L);
    inventario.setCantidad(50);
  }

  @Test
  public void testFindProductById_ProductoExiste() {
    when(inventarioRepository.findByIdProducto(100L)).thenReturn(Optional.of(inventario));

    Optional<Inventario> resultado = inventarioDataProvider.findProductById(100L);

    assertTrue(resultado.isPresent());
    assertEquals(Long.valueOf(100L), resultado.get().getIdProducto());
    verify(inventarioRepository).findByIdProducto(100L);
  }

  @Test
  public void testFindProductById_ProductoNoExiste() {
    when(inventarioRepository.findByIdProducto(999L)).thenReturn(Optional.empty());

    Optional<Inventario> resultado = inventarioDataProvider.findProductById(999L);

    assertFalse(resultado.isPresent());
    verify(inventarioRepository).findByIdProducto(999L);
  }

  @Test
  public void testUpdateCantidad_ProductoExiste() {
    when(inventarioRepository.findByIdProducto(100L)).thenReturn(Optional.of(inventario));
    when(inventarioRepository.save(any(Inventario.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Inventario actualizado = inventarioDataProvider.updateCantidad(100L, 80);

    assertEquals(80, actualizado.getCantidad().intValue());
    assertEquals(Long.valueOf(100L), actualizado.getIdProducto());
    verify(inventarioRepository).save(any(Inventario.class));
  }

  @Test
  public void testUpdateCantidad_ProductoNoExiste() {
    when(inventarioRepository.findByIdProducto(200L)).thenReturn(Optional.empty());
    when(inventarioRepository.save(any(Inventario.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Inventario nuevo = inventarioDataProvider.updateCantidad(200L, 30);

    assertEquals(30, nuevo.getCantidad().intValue());
    assertEquals(Long.valueOf(200L), nuevo.getIdProducto());
    assertNull(nuevo.getId()); // porque se creó uno nuevo con id null
    verify(inventarioRepository).save(any(Inventario.class));
  }

  @Test
  public void testUpdate() {
    when(inventarioRepository.save(inventario)).thenReturn(inventario);

    Inventario resultado = inventarioDataProvider.update(inventario);

    assertEquals(inventario, resultado);
    verify(inventarioRepository).save(inventario);
  }
}

package com.linktic.inventario_service.crosscutting.persistence.repository;

import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * InventarioRepository.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

  Optional<Inventario> findByIdProducto(Long idProducto);

}

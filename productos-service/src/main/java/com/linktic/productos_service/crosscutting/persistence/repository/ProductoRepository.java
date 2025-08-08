package com.linktic.productos_service.crosscutting.persistence.repository;

import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<ProductoProjection> findAllProjectedBy();
  ProductoProjection findProjectedById(Long id);

}

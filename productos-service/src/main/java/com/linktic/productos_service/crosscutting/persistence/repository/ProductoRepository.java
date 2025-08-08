package com.linktic.productos_service.crosscutting.persistence.repository;

import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {


}

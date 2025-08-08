package com.linktic.productos_service.modules.product.dataproviders.jpa;

import com.linktic.productos_service.crosscutting.persistence.repository.ProductoRepository;
import com.linktic.productos_service.modules.product.dataproviders.IproductDataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * ProductDataProvider.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class ProductDataProvider  implements IproductDataProvider {


  private final ProductoRepository productoRepository;





}

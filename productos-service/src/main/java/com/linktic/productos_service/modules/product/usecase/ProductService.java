package com.linktic.productos_service.modules.product.usecase;

import com.linktic.productos_service.modules.product.dataproviders.IproductDataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductService.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

  private final IproductDataProvider iproductDataProvider;

}

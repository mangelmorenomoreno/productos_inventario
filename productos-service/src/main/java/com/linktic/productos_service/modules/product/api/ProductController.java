package com.linktic.productos_service.modules.product.api;


import com.linktic.productos_service.crosscutting.domain.constants.ControllerConstants;
import com.linktic.productos_service.modules.product.usecase.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping(ControllerConstants.PRODUCT)
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;


}

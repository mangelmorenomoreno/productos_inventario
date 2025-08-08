package com.linktic.productos_service.modules.product.api;


import com.linktic.productos_service.crosscutting.domain.constants.ApiDocumentationConstant;
import com.linktic.productos_service.crosscutting.domain.constants.ControllerConstants;
import com.linktic.productos_service.crosscutting.domain.constants.ResponseValueConstants;
import com.linktic.productos_service.crosscutting.domain.enums.MessageCodes;
import com.linktic.productos_service.crosscutting.patterns.IrestResponse;
import com.linktic.productos_service.crosscutting.persistence.entity.Producto;
import com.linktic.productos_service.crosscutting.persistence.projection.ProductoProjection;
import com.linktic.productos_service.crosscutting.utils.ResponseEntityUtil;
import com.linktic.productos_service.modules.product.usecase.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Operation(summary = ApiDocumentationConstant.FIND_ALL_SUMARY,
      description = ApiDocumentationConstant.FIND_ALL_DESCRIPCION)
  @GetMapping
  public ResponseEntity<IrestResponse<List<ProductoProjection>>> findAll() {
    List<ProductoProjection> productos = productService.obtenerTodos();
    return ResponseEntityUtil.createSuccessfulResponse(
        ResponseValueConstants.SUCCESS,
        productos,
        MessageCodes.PRODUCT_FIND_ALL_SUCCESS.getMessage(),
        MessageCodes.PRODUCT_FIND_ALL_SUCCESS.name()
    );
  }

  @Operation(summary = ApiDocumentationConstant.FIND_ID_SUMARY,
      description = ApiDocumentationConstant.FIND_ID_DESCRIPCION)
  @GetMapping("/{id}")
  public ResponseEntity<IrestResponse<ProductoProjection>> findById(
      @PathVariable Long id) {
    Optional<ProductoProjection> productoOpt = productService.obtenerPorId(id);

    return productoOpt
        .map(producto -> ResponseEntityUtil.createSuccessfulResponse(
            ResponseValueConstants.SUCCESS,
            producto,
            MessageCodes.PRODUCT_FIND_BY_ID_SUCCESS.getMessage(),
            MessageCodes.PRODUCT_FIND_BY_ID_SUCCESS.name()
        ))
        .orElseGet(() -> ResponseEntityUtil.createSuccessfulResponse(
            ResponseValueConstants.ERROR,
            null,
            MessageCodes.PRODUCT_NOT_FOUND.getMessage(),
            MessageCodes.PRODUCT_NOT_FOUND.name()
        ));
  }

  @Operation(summary = ApiDocumentationConstant.CREATE_SUMARY,
      description = ApiDocumentationConstant.CREATE_DESCRIPCION)
  @PostMapping
  public ResponseEntity<IrestResponse<Producto>> create(@RequestBody Producto producto) {
    Producto creado = productService.crearProducto(producto);
    return ResponseEntityUtil.createSuccessfulResponse(
        ResponseValueConstants.SUCCESS,
        creado,
        MessageCodes.PRODUCT_CREATED_SUCCESS.getMessage(),
        MessageCodes.PRODUCT_CREATED_SUCCESS.name()
    );
  }

}

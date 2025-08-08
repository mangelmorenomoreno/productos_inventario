package com.linktic.inventario_service.modules.compra.api;

import com.linktic.inventario_service.crosscutting.domain.constants.ApiDocumentationConstant;
import com.linktic.inventario_service.crosscutting.domain.constants.ControllerConstants;
import com.linktic.inventario_service.crosscutting.domain.constants.ResponseValueConstants;
import com.linktic.inventario_service.crosscutting.domain.dto.CompraDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.CompraRequestDTO;
import com.linktic.inventario_service.crosscutting.domain.enums.MessageCodes;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.crosscutting.patterns.IrestResponse;
import com.linktic.inventario_service.crosscutting.utils.ResponseEntityUtil;
import com.linktic.inventario_service.modules.compra.usecase.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CompraController.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping(ControllerConstants.COMPRA)
@SecurityRequirement(name = "apiKey")
@RequiredArgsConstructor
public class CompraController {

  private final CompraService compraService;

  @Operation(
      summary = ApiDocumentationConstant.COMPRA_SUMMARY,
      description = ApiDocumentationConstant.COMPRA_DESCRIPCION)
  @PostMapping()
  public ResponseEntity<IrestResponse<CompraDTO>> realizarCompra(
      @RequestBody CompraRequestDTO request) {
    try {
      CompraDTO compra = compraService.realizarCompra(request);
      return ResponseEntityUtil.createSuccessfulResponse(
          ResponseValueConstants.SUCCESS,
          compra,
          MessageCodes.COMPRA_SUCCESS.getMessage(),
          MessageCodes.COMPRA_SUCCESS.name());
    } catch (CustomException e) {
      return ResponseEntityUtil.createErrorResponse(e.getMessage());
    }
  }


}

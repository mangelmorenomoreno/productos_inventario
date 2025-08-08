package com.linktic.inventario_service.modules.inventario.api;

import com.linktic.inventario_service.crosscutting.domain.constants.ApiDocumentationConstant;
import com.linktic.inventario_service.crosscutting.domain.constants.ControllerConstants;
import com.linktic.inventario_service.crosscutting.domain.constants.ResponseValueConstants;
import com.linktic.inventario_service.crosscutting.domain.dto.ActualizarInventarioDTO;
import com.linktic.inventario_service.crosscutting.domain.dto.InventarioProductoDTO;
import com.linktic.inventario_service.crosscutting.domain.enums.MessageCodes;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.crosscutting.patterns.IrestResponse;
import com.linktic.inventario_service.crosscutting.persistence.entity.Inventario;
import com.linktic.inventario_service.crosscutting.utils.ResponseEntityUtil;
import com.linktic.inventario_service.modules.inventario.usecase.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InventarioController.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping(ControllerConstants.INVENTARIO)
@SecurityRequirement(name = "apiKey")
@RequiredArgsConstructor
public class InventarioController {

  private final InventarioService inventarioService;

  @Operation(summary = ApiDocumentationConstant.FIND_ID_SUMARY,
      description = ApiDocumentationConstant.FIND_ID_DESCRIPCION)
  @GetMapping("/{id}")
  public ResponseEntity<IrestResponse<InventarioProductoDTO>> findProductById(
      @PathVariable Long id) {
    try {
      InventarioProductoDTO productoOpt = inventarioService.findProductById(id);
      return ResponseEntityUtil.createSuccessfulResponse(
          ResponseValueConstants.SUCCESS,
          productoOpt,
          MessageCodes.PRODUCT_FIND_BY_ID_SUCCESS.getMessage(),
          MessageCodes.PRODUCT_FIND_BY_ID_SUCCESS.name()
      );
    } catch (CustomException e) {
      return ResponseEntityUtil.createErrorResponse(e.getMessage());
    }
  }

  @Operation(summary = ApiDocumentationConstant.UPDATE_SUMARY,
      description = ApiDocumentationConstant.UPDATE_DESCRIPCION)
  @PutMapping()
  public ResponseEntity<IrestResponse<Inventario>> actualizarInventario(
      @RequestBody ActualizarInventarioDTO dto) {
    try {
      Inventario inventario = inventarioService.updateInventario(dto);
      return ResponseEntityUtil.createSuccessfulResponse(
          ResponseValueConstants.SUCCESS,
          inventario,
          MessageCodes.UPDATE_SUCCES.getMessage(),
          MessageCodes.UPDATE_SUCCES.name());
    } catch (CustomException e) {
      return ResponseEntityUtil.createErrorResponse(e.getMessage());
    }
  }

}

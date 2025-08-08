package com.linktic.inventario_service.crosscutting.utils;

import com.linktic.inventario_service.crosscutting.domain.enums.ResponseStatusCode;
import com.linktic.inventario_service.crosscutting.domain.errors.CustomException;
import com.linktic.inventario_service.crosscutting.domain.response.ResponseStatus;
import com.linktic.inventario_service.crosscutting.domain.response.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<RestResponse<Object>> handleCustomException(CustomException ex) {
    return ResponseEntity.badRequest().body(RestResponse.<Object>builder()
        .responseStatus(getErrorResponseStatus(ex.getMessage()))
        .data(null)
        .build());
  }
  private static ResponseStatus getErrorResponseStatus(final String message) {
    return ResponseStatus.builder()
        .message(message)
        .statusCode(ResponseStatusCode.OK)
        .build();
  }
}
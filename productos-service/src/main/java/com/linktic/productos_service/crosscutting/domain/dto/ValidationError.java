package com.linktic.productos_service.crosscutting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ValidationError.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 2025-08-07
 */
@Data
@Builder
@AllArgsConstructor
public class ValidationError {

  private String translationCode;
  private String detail;
  private String lang;

}

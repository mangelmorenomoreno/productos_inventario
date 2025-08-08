package com.linktic.productos_service.crosscutting.domain.errors;

/**
 * CustomException.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 2025-08-07
 */
public class CustomException extends RuntimeException {
  private final String message;

  public CustomException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}


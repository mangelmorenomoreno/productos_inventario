package com.linktic.productos_service.crosscutting.utils;

import com.linktic.productos_service.crosscutting.domain.dto.ValidationError;
import com.linktic.productos_service.crosscutting.domain.enums.ResponseStatusCode;

import com.linktic.productos_service.crosscutting.patterns.IrestResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ResponseEntityUtilTest {



  @Test
  public void testCreateSuccessfulResponseWithoutData() {
    String message = "Sin datos";
    String detail = "Detalle";
    String translationCode = "CODE_OK";

    ResponseEntity<IrestResponse<Object>> response =
        ResponseEntityUtil.createSuccessfulResponse(message, detail, translationCode);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(detail, response.getBody().getDetail());
    assertEquals(translationCode, response.getBody().getTranslationCode());
    assertEquals(message, response.getBody().getResponseStatus().getMessage());
  }

  @Test
  public void testCreateSuccessfulResponseWithLang() {
    String message = "Éxito";
    String detail = "Detalles adicionales";
    String translationCode = "CODE_200";
    String lang = "es";
    String data = "Datos";

    ResponseEntity<IrestResponse<String>> response =
        ResponseEntityUtil.createSuccessfulResponse(message, data, detail, translationCode, lang);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    IrestResponse<String> body = response.getBody();
    assertNotNull(body);
    assertEquals(data, body.getData());
    assertEquals(detail, body.getDetail());
    assertEquals(lang, body.getLang());
  }

  @Test
  public void testCreateResponseValidationError() {
    ValidationError error = ValidationError.builder()
        .detail("Campo requerido")
        .translationCode("VALIDATION_ERR")
        .lang("es")
        .build();

    ResponseEntity<IrestResponse<Object>> response =
        ResponseEntityUtil.createResponseValidationError(error);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    IrestResponse<Object> body = response.getBody();
    assertNotNull(body);
    assertEquals("Campo requerido", body.getDetail());
    assertEquals("VALIDATION_ERR", body.getTranslationCode());
    assertEquals(ResponseStatusCode.OK, body.getResponseStatus().getStatusCode());
  }

  @Test
  public void testCreateSuccessfulResponseOnlyMessageAndData() {
    String message = "Mensaje simple";
    String data = "Contenido";

    ResponseEntity<IrestResponse<String>> response =
        ResponseEntityUtil.createSuccessfulResponse(message, data);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(message, response.getBody().getResponseStatus().getMessage());
    assertEquals(data, response.getBody().getData());
  }
}

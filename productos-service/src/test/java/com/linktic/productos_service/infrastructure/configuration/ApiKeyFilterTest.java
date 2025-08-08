package com.linktic.productos_service.infrastructure.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * ApiKeyFilterTest.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
public class ApiKeyFilterTest {

  private static final String VALID_API_KEY = "mi-clave-secreta";

  private ApiKeyFilter apiKeyFilter;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  @Before
  public void setUp() {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    filterChain = mock(FilterChain.class);
    apiKeyFilter = new ApiKeyFilter(VALID_API_KEY);
  }

  @Test
  public void testApiKeyValida_debePermitirContinuar() throws ServletException, IOException {
    when(request.getHeader("X-API-KEY")).thenReturn(VALID_API_KEY);

    apiKeyFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain, times(1)).doFilter(request, response);
    verify(response, never()).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    verify(response, never()).getWriter();
  }

  @Test
  public void testApiKeyInvalida_debeRetornar401() throws ServletException, IOException {
    when(request.getHeader("X-API-KEY")).thenReturn("clave-falsa");

    PrintWriter writer = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(writer);

    apiKeyFilter.doFilterInternal(request, response, filterChain);

    verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    verify(writer).write("API Key inválida o ausente.");
    verify(filterChain, never()).doFilter(request, response);
  }

  @Test
  public void testApiKeyAusente_debeRetornar401() throws ServletException, IOException {
    when(request.getHeader("X-API-KEY")).thenReturn(null);

    PrintWriter writer = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(writer);

    apiKeyFilter.doFilterInternal(request, response, filterChain);

    verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    verify(writer).write("API Key inválida o ausente.");
    verify(filterChain, never()).doFilter(request, response);
  }
}

package com.linktic.inventario_service.infrastructure.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * ApiKeyFilter.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
public class ApiKeyFilter extends OncePerRequestFilter {

  private final String apiKey;

  public ApiKeyFilter(String apiKey) {
    this.apiKey = apiKey;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader("X-API-KEY");

    if (apiKey.equals(header)) {
      filterChain.doFilter(request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("API Key inválida o ausente.");
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !request.getRequestURI().startsWith("/compra");
  }
}


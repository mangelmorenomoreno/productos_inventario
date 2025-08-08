package com.linktic.productos_service.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * SecurityConfig.
 *
 * @author miguel.moreno
 * @version 1.0
 * @since 07-08-2025
 */
@Configuration
public class SecurityConfig {

  @Value("${security.api-key}")
  private String apiKey;

  @Bean
  public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter() {
    FilterRegistrationBean<ApiKeyFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new ApiKeyFilter(apiKey));
    registration.addUrlPatterns("/*"); // Protege todos los endpoints
    registration.setOrder(1);
    return registration;
  }


}

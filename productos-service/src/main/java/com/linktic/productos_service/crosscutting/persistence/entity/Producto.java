package com.linktic.productos_service.crosscutting.persistence.entity;

import com.linktic.productos_service.crosscutting.messages.ProductMessages;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Producto.
 *
 * @author miguel.moreno
 * @version 1.1
 * @since 07-08-2025
 */
@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = ProductMessages.NOT_BLANK)
  @Size(max = 100, message = ProductMessages.NOMBRE_CARACTERES)
  private String nombre;

  @DecimalMax(value = "1000000000.00", message = ProductMessages.PRECIO_MAXIMO)
  @Digits(integer = 10, fraction = 2, message = ProductMessages.PRECIO_DIGITOS)
  private BigDecimal precio;

  @Size(max = 255, message = ProductMessages.DESCRIPCION_CARACTERES)
  private String descripcion;
}

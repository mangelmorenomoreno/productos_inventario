package com.linktic.inventario_service.modules.compra.api;

import com.linktic.inventario_service.crosscutting.domain.constants.ControllerConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
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




}

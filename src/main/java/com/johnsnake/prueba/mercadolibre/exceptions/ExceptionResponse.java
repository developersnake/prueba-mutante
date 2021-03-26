package com.johnsnake.prueba.mercadolibre.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
	  private LocalDateTime timestamp;
	  private String mensaje;
	  private String detalles;
	  private String httpCodeMessage;
	}
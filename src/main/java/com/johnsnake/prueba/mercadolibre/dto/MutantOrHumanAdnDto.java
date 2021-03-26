package com.johnsnake.prueba.mercadolibre.dto;

import com.johnsnake.prueba.mercadolibre.entities.MutantOrHumanAdnEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MutantOrHumanAdnDto {
	private Integer id;
	private String adn;
	private Boolean isMutant;
}

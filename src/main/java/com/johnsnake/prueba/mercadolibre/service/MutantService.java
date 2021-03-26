package com.johnsnake.prueba.mercadolibre.service;

import com.johnsnake.prueba.mercadolibre.dto.StatsDto;
import com.johnsnake.prueba.mercadolibre.exceptions.MutantException;

public interface MutantService {

	public boolean isMutant(String[] dna) throws MutantException;

	public StatsDto obtenerestadistica();
}

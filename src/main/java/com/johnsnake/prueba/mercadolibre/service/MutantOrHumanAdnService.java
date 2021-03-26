package com.johnsnake.prueba.mercadolibre.service;

import com.johnsnake.prueba.mercadolibre.dto.MutantOrHumanAdnDto;
import com.johnsnake.prueba.mercadolibre.dto.StatsDto;

public interface MutantOrHumanAdnService {
	
	public void guardar(MutantOrHumanAdnDto dto);

	public boolean exiateAdn(String adn);
	
	public StatsDto obtenerestadistica();
}

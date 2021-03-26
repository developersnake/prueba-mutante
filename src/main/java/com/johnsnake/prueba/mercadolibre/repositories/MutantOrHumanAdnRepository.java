package com.johnsnake.prueba.mercadolibre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.johnsnake.prueba.mercadolibre.dto.StatsDto;
import com.johnsnake.prueba.mercadolibre.entities.MutantOrHumanAdnEntity;

public interface MutantOrHumanAdnRepository extends JpaRepository<MutantOrHumanAdnEntity, Integer> {
	
	public boolean existsByAdn(String adn);
	
	@Query(value = "select \r\n" + 
			"(select count(*) from mutant_or_human_adn where is_mutant = true) as count_mutant_dna,\r\n" + 
			"(select count(*) from mutant_or_human_adn where is_mutant = false) as count_human_dna", nativeQuery = true)
	public StatsDto estadistica();

}

package com.johnsnake.prueba.mercadolibre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnsnake.prueba.mercadolibre.dto.MutantOrHumanAdnDto;
import com.johnsnake.prueba.mercadolibre.dto.StatsDto;
import com.johnsnake.prueba.mercadolibre.entities.MutantOrHumanAdnEntity;
import com.johnsnake.prueba.mercadolibre.repositories.MutantOrHumanAdnRepository;
import com.johnsnake.prueba.mercadolibre.service.MutantOrHumanAdnService;

@Service
public class MutantOrHumanAdnServiceImpl implements MutantOrHumanAdnService {

	@Autowired
	MutantOrHumanAdnRepository repo;

	@Override
	public void guardar(MutantOrHumanAdnDto dto) {
		this.repo.save(this.dtoToEntity(dto));

	}

	@Override
	public boolean exiateAdn(String adn) {
		return this.repo.existsByAdn(adn);
	}

	private MutantOrHumanAdnEntity dtoToEntity(MutantOrHumanAdnDto dto) {
		return MutantOrHumanAdnEntity.builder().id(dto.getId()).adn(dto.getAdn()).isMutant(dto.getIsMutant()).build();
	}

	@Override
	public StatsDto obtenerestadistica() {
		
		return this.repo.estadistica();
	}

}

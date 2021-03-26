package com.johnsnake.prueba.mercadolibre.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="mutant_or_human_adn")
@Data
@AllArgsConstructor
@Builder
public class MutantOrHumanAdnEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "adn", updatable=false, unique= true, nullable = false, length = 4000)
	private String adn;
	
	@Column(name = "is_mutant", updatable=false, nullable = false)
	private Boolean isMutant;
}

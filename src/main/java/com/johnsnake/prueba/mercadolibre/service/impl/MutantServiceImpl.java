package com.johnsnake.prueba.mercadolibre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnsnake.prueba.mercadolibre.dto.MutantOrHumanAdnDto;
import com.johnsnake.prueba.mercadolibre.dto.StatsDto;
import com.johnsnake.prueba.mercadolibre.exceptions.MutantException;
import com.johnsnake.prueba.mercadolibre.service.MutantOrHumanAdnService;
import com.johnsnake.prueba.mercadolibre.service.MutantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MutantServiceImpl implements MutantService {

	@Autowired
	MutantOrHumanAdnService service;

	private final String PATRON_A = "AAAA";
	private final String PATRON_C = "CCCC";
	private final String PATRON_G = "GGGG";
	private final String PATRON_T = "TTTT";
	private final String PATRON = "ATCG";
	private Integer catidadPatronEncontrado;

	@Override
	public boolean isMutant(String[] dna) throws MutantException {
		this.catidadPatronEncontrado = 0;
		this.validarMatriz(dna);
		this.buscarPatron(dna);
		this.guardarAdn(dna);
		return this.catidadPatronEncontrado > 1;
	}

	@Override
	public StatsDto obtenerestadistica() {		
		return this.service.obtenerestadistica();
	}

	private void buscarPatron(String[] dna) {
		this.buscarPatronHorizontal(dna);

		if (this.catidadPatronEncontrado > 1) {
			return;
		}
		this.buscarPatronVertical(dna);

		if (this.catidadPatronEncontrado > 1) {
			return;
		}
		this.buscarPatronOblicuo(dna);
	}

	private void buscarPatronHorizontal(String[] dna) {
		//Busco los patrones mutantes de forma horizontal en cada fila de la matriz. 
		for (String dna1 : dna) {

			if (this.catidadPatronEncontrado > 1) {
				return;
			}

			if (dna1.contains(this.PATRON_A)) {
				this.catidadPatronEncontrado++;
			}

			if (dna1.contains(this.PATRON_C)) {
				this.catidadPatronEncontrado++;
			}

			if (dna1.contains(this.PATRON_G)) {
				this.catidadPatronEncontrado++;
			}

			if (dna1.contains(this.PATRON_T)) {
				this.catidadPatronEncontrado++;
			}
		}
	}

	private void buscarPatronVertical(String[] dna) {
		String[] dnaTemp = new String[dna[0].length()];
		//Paso de columnas a filas
		for (int i = 0; i < dna.length; i++) {
			String dna1 = dna[i];
			for (int j = 0; j < dna1.length(); j++) {
				if (i == 0) {
					dnaTemp[j] = String.valueOf(dna1.charAt(j));
				} else {
					dnaTemp[j] += String.valueOf(dna1.charAt(j));
				}
			}
		}
		//Como ya tengo lac colunas como filas, busco el patrón horizontalmente.
		this.buscarPatronHorizontal(dnaTemp);
	}

	private void buscarPatronOblicuo(String[] dna) {
		int maxIndex = dna.length - 4;
		String[] dnaTemp = new String[(maxIndex * 2) + 1];
		//Paso la diagonal mayour como una fila
		for (int i = 0; i < dna.length; i++) {
			if (i == 0) {
				dnaTemp[0] = String.valueOf(dna[i].charAt(i));
			} else {
				dnaTemp[0] += String.valueOf(dna[i].charAt(i));
			}
		}
		//Paso las diaginales que tengan 4 o mas caracteres como fila.
		for (int i = 1; i <= maxIndex; i++) {
			for (int j = 0; j < dnaTemp[i - 1].length() - 1; j++) {
				if (j == 0) {
					dnaTemp[i] = String.valueOf(dna[j].charAt(j + i));
					dnaTemp[maxIndex + i] = String.valueOf(dna[j + i].charAt(j));
				} else {
					dnaTemp[i] += String.valueOf(dna[j].charAt(j + i));
					dnaTemp[maxIndex + i] += String.valueOf(dna[j + i].charAt(j));
				}
			}
		}
		//Como ya tengo las diagonales como fila las busco con el patron horizontal.
		this.buscarPatronHorizontal(dnaTemp);
	}

	private void validarMatriz(String[] dna) throws MutantException {

		// Valido que la cadena tenga minimo 4 caracteres-
		if (dna[0].length() < 4) {
			log.error("El tamaño minimo de cada fila es de 4 caracteres.");
			throw new MutantException("El tamaño minimo de cada fila es de 4 caracteres.");
		}
		// Valido que el número de filas de la matriz se igual al número de columnas
		// (NxN).
		if (dna[0].length() != dna.length) {
			log.error("El número de filas no corresponde con la cantidad de carateres por fila (NxN).");
			throw new MutantException("El número de filas no corresponde con la cantidad de carateres por fila (NxN).");
		}
		// Valido que contenga solamente el Patron de caracteres (A,T,C,G);
		for (String dna1 : dna) {
			for (int j = 0; j < dna1.length(); j++) {
				char dnaChar = dna1.charAt(j);
				if (!this.PATRON.contains(String.valueOf(dnaChar).toUpperCase())) {
					log.error("Los caracteres pemitidos son unicamente '" + this.PATRON + "'.");
					throw new MutantException("Los caracteres pemitidos son unicamente '" + this.PATRON + "'.");
				}
			}
		}
	}

	private void guardarAdn(String[] dna) {
		StringBuilder sb = new StringBuilder();
		for (String adn : dna) {
			sb.append(adn).append(",");
		}
		//Si no existe el adn lo creo en la base de datos.
		if (!this.service.exiateAdn(sb.toString())) {
			MutantOrHumanAdnDto dto = MutantOrHumanAdnDto.builder().adn(sb.toString())
					.isMutant(this.catidadPatronEncontrado > 1).build();
			this.service.guardar(dto);
		}
	}
}

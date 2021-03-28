package com.johnsnake.prueba.mercadolibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnsnake.prueba.mercadolibre.dto.MutantDto;
import com.johnsnake.prueba.mercadolibre.dto.StatsDto;
import com.johnsnake.prueba.mercadolibre.service.MutantService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/mutant")
public class MutantController {
	
	@Autowired
	MutantService mutantService;
	
	@PostMapping("/mutant")
	public ResponseEntity<Boolean> isMutant(@RequestBody MutantDto dto) {
		Boolean isMutant = this.mutantService.isMutant(dto.getDna());
		return ResponseEntity.status(isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(isMutant);
	}
	
	@GetMapping("/stats")
	public ResponseEntity<StatsDto> stats() {
		return ResponseEntity.status(HttpStatus.OK).body(this.mutantService.obtenerestadistica());
	}
}

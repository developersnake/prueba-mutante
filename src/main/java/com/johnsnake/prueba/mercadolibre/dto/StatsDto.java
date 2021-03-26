package com.johnsnake.prueba.mercadolibre.dto;

public interface StatsDto {
	
	public Integer getCount_mutant_dna();

	public Integer getCount_human_dna();	

	public default Double getRatio() {
		int div = this.getCount_human_dna() == 0 ? 1 : this.getCount_human_dna();
		System.out.println("Div = " + div);
		return Double.valueOf(this.getCount_mutant_dna())/div;
	}
	
}

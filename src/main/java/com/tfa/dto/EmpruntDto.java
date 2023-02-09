package com.tfa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmpruntDto {

	private Long id;
	private String numeroEmprunt;
	private String numeroDetenteur;
	private String typeEmprunt; 
	private double totalEmprunt;
	private double montantRegle;
}

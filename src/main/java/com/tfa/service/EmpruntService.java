package com.tfa.service;

import java.util.List;

import com.tfa.dto.EmpruntDto;

public interface EmpruntService {

	EmpruntDto creeEmprunt(EmpruntDto dto, String numeroDetenteur);
	EmpruntDto modifieEmprunt(EmpruntDto dto, String numeroEmprunt);
	EmpruntDto obtenirEmprunt(String numeroEmprunt);
	List<EmpruntDto> obtenirEmpruntParDetenteur(String numeroDetenteur);
	List<EmpruntDto> obtenirEmprunts();
}

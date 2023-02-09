package com.tfa.service.Impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tfa.dto.EmpruntDto;
import com.tfa.entite.Detenteur;
import com.tfa.entite.Emprunt;
import com.tfa.repository.DetenteurRepository;
import com.tfa.repository.EmpruntRepository;
import com.tfa.service.EmpruntService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpruntServiceImpl implements EmpruntService {

	private final DetenteurRepository drepo;
	private final EmpruntRepository erepo;
	private final ModelMapper mapper;
	
	@Override
	public EmpruntDto creeEmprunt(EmpruntDto dto, String numeroDetenteur) {

		Detenteur detenteur = drepo.findByNumeroUnique(numeroDetenteur);
		if(detenteur == null) {
			log.info("Detenteur n'existe pas!");
			return null;
		}
		Emprunt e = mapper.map(detenteur, Emprunt.class);
		e.setNumeroEmprunt(numeroUnique(10));
		e.setNumeroDetenteur(numeroDetenteur);
		Emprunt saved = erepo.save(e);
		
		return mapper.map(saved, EmpruntDto.class);
	}

	private String numeroUnique(int taille) {
		while (true) {
			String numeroUnique = RandomStringUtils.randomNumeric(taille);
			Emprunt emprunt = erepo.findByNumeroEmprunt(numeroUnique);
			if (emprunt == null) {
				return numeroUnique;
			}
		}
	}
	@Override
	public EmpruntDto modifieEmprunt(EmpruntDto dto, String numeroEmprunt) {

		Emprunt emprunt = erepo.findByNumeroEmprunt(numeroEmprunt);
		if(emprunt == null) {
			log.warn("Emprunt n'existe pas!");
			return null;
		}
		
		emprunt.setMontantRegle(dto.getMontantRegle());
		emprunt.setTotalEmprunt(dto.getTotalEmprunt());
		emprunt.setTypeEmprunt(dto.getTypeEmprunt());
		
		Emprunt saved = erepo.save(emprunt);
		
		return mapper.map(saved, EmpruntDto.class);
	}

	@Override
	public EmpruntDto obtenirEmprunt(String numeroEmprunt) {
		Emprunt emprunt = erepo.findByNumeroEmprunt(numeroEmprunt);
		if(emprunt == null) {
			log.warn("Emprunt n'existe pas!");
			return null;
		}
		
		return mapper.map(emprunt, EmpruntDto.class);
	}

	@Override
	public List<EmpruntDto> obtenirEmpruntParDetenteur(String numeroDetenteur) {

		List<Emprunt> emprunts = erepo.findByNumeroDetenteur(numeroDetenteur);
		if(CollectionUtils.isEmpty(emprunts)) {
			log.info("Pas d'emprunt pour ce detenteur!");
			return Collections.emptyList();
		}
		return emprunts.stream().map(e -> mapper.map(e, EmpruntDto.class)).toList();
	}

	@Override
	public List<EmpruntDto> obtenirEmprunts() {
		List<Emprunt> emprunts = erepo.findAll();
		if(CollectionUtils.isEmpty(emprunts)) {
			log.info("Pas d'emprunts!");
			return Collections.emptyList();
		}
		return emprunts.stream().map(e -> mapper.map(e, EmpruntDto.class)).toList();
	}

}

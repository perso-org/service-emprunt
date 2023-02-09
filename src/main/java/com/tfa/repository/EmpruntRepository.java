package com.tfa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	Emprunt findByNumeroEmprunt(String numeroEmprunt);
	List<Emprunt> findByNumeroDetenteur(String numeroDetenteur);
}

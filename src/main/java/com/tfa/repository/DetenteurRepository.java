package com.tfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Detenteur;

public interface DetenteurRepository extends JpaRepository<Detenteur, Long> {

	Detenteur findByNumeroUnique(String numeroUnique);
}

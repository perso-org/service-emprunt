package com.tfa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tfa.dto.EmpruntDto;
import com.tfa.service.EmpruntService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/emprunts")
public class EmpruntController {

	private final EmpruntService service;

	@PostMapping("creation/{numeroDetenteur}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<EmpruntDto> creeEmprunt(@RequestBody EmpruntDto dto, @PathVariable String numeroDetenteur) {

		EmpruntDto emp = service.creeEmprunt(dto, numeroDetenteur);

		return ResponseEntity.ok(emp);
	}

	@PutMapping("up/{numeroEmprunt}")
	public ResponseEntity<EmpruntDto> modifieEmprunt(@RequestBody EmpruntDto dto, @PathVariable String numeroEmprunt) {

		EmpruntDto emp = service.modifieEmprunt(dto, numeroEmprunt);

		return ResponseEntity.ok(emp);
	}

	@GetMapping("{numeroEmprunt}")
	public ResponseEntity<EmpruntDto> obtenirEmprunt(@PathVariable String numeroEmprunt) {

		EmpruntDto emp = service.obtenirEmprunt(numeroEmprunt);

		return ResponseEntity.ok(emp);
	}

	@GetMapping("detenteur/{numeroDetenteur}")
	public ResponseEntity<List<EmpruntDto>> obtenirEmpruntParDetenteur(@PathVariable String numeroDetenteur) {

		List<EmpruntDto> empruntDtos = service.obtenirEmpruntParDetenteur(numeroDetenteur);

		return ResponseEntity.ok(empruntDtos);
	}

	@GetMapping
	public ResponseEntity<List<EmpruntDto>> obtenirEmprunts() {

		List<EmpruntDto> empruntDtos = service.obtenirEmprunts();
		return ResponseEntity.ok(empruntDtos);
	}

}

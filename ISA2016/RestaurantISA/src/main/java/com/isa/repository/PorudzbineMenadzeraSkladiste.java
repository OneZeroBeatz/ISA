package com.isa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.korisnici.MenadzerRestorana;

public interface PorudzbineMenadzeraSkladiste extends JpaRepository<PorudzbinaMenadzer, Long> {

	Page<PorudzbinaMenadzer> findByMenadzerrestorana(MenadzerRestorana menadzerRestorana, Pageable pageable);
	
	PorudzbinaMenadzer save(PorudzbinaMenadzer porudzbinaMenadzer);
	
}
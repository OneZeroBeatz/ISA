package com.isa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Ponuda;
import com.isa.model.korisnici.Ponudjac;

public interface PonudaSkladiste extends JpaRepository<Ponuda, Long> {

	Page<Ponuda> findByPonudjac(Ponudjac ponudjac, Pageable pageable);
	
	Ponuda save(Ponuda ponuda);
	
}
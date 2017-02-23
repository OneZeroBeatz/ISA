package com.isa.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;

public interface PorudzbinaSkladiste extends JpaRepository<Porudzbina, Serializable>{

	Porudzbina findById(Long id);
	
	Porudzbina save(Porudzbina namirnica);

	Page<Porudzbina> findByRestoran(Restoran restoran, Pageable pageable);

	Page<Porudzbina> findByKonobar(Konobar konobar, Pageable pageable);



	
	
}

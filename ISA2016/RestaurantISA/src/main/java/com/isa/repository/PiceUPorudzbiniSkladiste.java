package com.isa.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;

public interface PiceUPorudzbiniSkladiste extends JpaRepository<PiceUPorudzbini, Serializable> {
	
	PiceUPorudzbini save(PiceUPorudzbini piceUPorudzbini);

	Page<PiceUPorudzbini> findByPorudzbina(Porudzbina porudzbina,Pageable pageable);
	
	
	
}

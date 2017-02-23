package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.StavkaPorudzbineMenadzera;

public interface StavkaPorudzbineMenSkladiste extends JpaRepository<StavkaPorudzbineMenadzera, Long>{
	
	StavkaPorudzbineMenadzera save(StavkaPorudzbineMenadzera stavkaPorudzbineMenadzera);
	
}

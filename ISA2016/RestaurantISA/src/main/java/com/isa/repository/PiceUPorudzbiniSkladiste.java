package com.isa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.PiceUPorudzbini;

public interface PiceUPorudzbiniSkladiste extends JpaRepository<PiceUPorudzbini, Serializable> {
	
	PiceUPorudzbini save(PiceUPorudzbini piceUPorudzbini);
	
	
	
}

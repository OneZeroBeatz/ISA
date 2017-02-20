package com.isa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.JeloUPorudzbini;

public interface JeloUPorudzbiniSkladiste extends JpaRepository<JeloUPorudzbini, Serializable> {
	
	JeloUPorudzbini save(JeloUPorudzbini jeloUPorudzbini);
	
}

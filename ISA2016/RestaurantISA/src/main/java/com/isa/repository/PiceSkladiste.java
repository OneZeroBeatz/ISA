package com.isa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Pice;
import com.isa.model.PorudzbinaMenadzer;

public interface PiceSkladiste extends JpaRepository<Pice, Long>{

	Page<Pice> findByPorudzbinaMenadzer(PorudzbinaMenadzer porudzbinaMenadzer, Pageable pageable);
	
	Pice save(Pice pice);
	
}
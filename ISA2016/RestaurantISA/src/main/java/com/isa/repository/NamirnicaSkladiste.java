package com.isa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Namirnica;
import com.isa.model.PorudzbinaMenadzer;

public interface NamirnicaSkladiste extends JpaRepository<Namirnica, Long>{

	Page<Namirnica> findByPorudzbinaMenadzer(PorudzbinaMenadzer porudzbinaMenadzer, Pageable pageable);
	
	Namirnica save(Namirnica namirnica);
	
}

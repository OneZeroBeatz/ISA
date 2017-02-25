package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Namirnica;

public interface NamirnicaSkladiste extends JpaRepository<Namirnica, Long>{

	//Page<Namirnica> findByPorudzbinamenadzer(PorudzbinaMenadzer porudzbinaMenadzer, Pageable pageable);
	
	Namirnica save(Namirnica porudzbina);
	
}

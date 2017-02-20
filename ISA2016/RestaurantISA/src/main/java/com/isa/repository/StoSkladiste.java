package com.isa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Restoran;
import com.isa.model.Sto;

public interface StoSkladiste extends JpaRepository<Sto, Long>{

	Page<Sto> findByRestoran(Restoran restoran, Pageable pageable);
	
	Sto save(Sto jelo);
	
}

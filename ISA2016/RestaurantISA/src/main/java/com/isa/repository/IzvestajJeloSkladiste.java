package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.IzvestajJelo;

public interface IzvestajJeloSkladiste extends JpaRepository<IzvestajJelo, Long> {
	
	IzvestajJelo save(IzvestajJelo izvestajJelo);
	
}

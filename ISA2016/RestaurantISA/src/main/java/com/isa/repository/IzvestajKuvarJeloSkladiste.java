package com.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.IzvestajKuvarJelo;

public interface IzvestajKuvarJeloSkladiste extends JpaRepository<IzvestajKuvarJelo, Long> {

	IzvestajKuvarJelo save(IzvestajKuvarJelo izvestajKuvarJelo);
	
}

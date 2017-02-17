package com.isa.services;

import java.util.List;

import com.isa.model.korisnici.Ponudjac;

public interface PonudjacServis {

	List<Ponudjac> findAll();
	
	Ponudjac findOne(Long id);
	
	Ponudjac save(Ponudjac ponudjac);
	
	void delete(Long id);
}

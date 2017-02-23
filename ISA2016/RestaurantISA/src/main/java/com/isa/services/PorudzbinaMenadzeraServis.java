package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.Namirnica;
import com.isa.model.PorudzbinaMenadzer;

public interface PorudzbinaMenadzeraServis {

	List<PorudzbinaMenadzer> findAll();
	
	PorudzbinaMenadzer findOne(Long id);
	
	PorudzbinaMenadzer save(PorudzbinaMenadzer ponudjac);
	
	void delete(Long id);
	
	Page<Namirnica> izlistajNamirnice(PorudzbinaMenadzer ponudjac, Pageable pageable);
	
	//Page<Pice> izlistajPica(PorudzbinaMenadzer ponudjac, Pageable pageable);
	
}

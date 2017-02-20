package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.Jelo;
import com.isa.model.Pice;
import com.isa.model.Restoran;

public interface RestoranServis {

	List<Restoran> findAll();
	
	Restoran findOne(Long id);
	
	Restoran save(Restoran restoran);
	
	void delete(Long id);

	Page<Jelo> izlistajJelovnik(Restoran restoran, Pageable pageable);

	Page<Pice> izlistajKartuPica(Restoran restoran, Pageable pageable);

	void save(Jelo jelo);

	void save(Pice pice);

	void izbrisiJelo(Long id);

	void izbrisiPice(Long id);


	
}

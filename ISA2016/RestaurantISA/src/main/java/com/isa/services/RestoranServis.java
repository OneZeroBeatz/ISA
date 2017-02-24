package com.isa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.isa.model.Jelo;
import com.isa.model.Pice;
import com.isa.model.Restoran;
import com.isa.model.Sto;

public interface RestoranServis {

	List<Restoran> findAll();
	
	Restoran findOne(Long id);
	
	Restoran save(Restoran restoran);
	
	void delete(Long id);

	Page<Jelo> izlistajJelovnik(Restoran restoran, Pageable pageable);

	Page<Pice> izlistajKartuPica(Restoran restoran, Pageable pageable);

	Page<Sto> izlistajStolove (Restoran restoran, Pageable pageable);

	void save(Jelo jelo);

	void save(Pice pice);

	void izbrisiJelo(Long id);

	void izbrisiPice(Long id);
	
	void izbrisiPiceUPorudzbini(Long id);
	
	void izbrisiJeloUPorudzbini(Long id);
	
	Page<Sto> kreirajStolove(Restoran restoran, ArrayList<Integer> oznake, Pageable pageable);

	Sto izlistajSto(Sto sto);

	
	
}

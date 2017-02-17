package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.korisnici.MenadzerRestorana;

public interface MenadzerRestoranaServis {

	List<MenadzerRestorana> findAll();
	
	MenadzerRestorana findOne(Long id);
	
	MenadzerRestorana save(MenadzerRestorana ponudjac);
	
	void delete(Long id);
	
	Page<PorudzbinaMenadzer> izlistajPorudzbineMenadzera(MenadzerRestorana menadzerRestorana, Pageable pageable);
	
}

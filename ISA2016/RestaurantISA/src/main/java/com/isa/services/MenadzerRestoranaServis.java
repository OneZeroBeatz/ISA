package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.Namirnica;
import com.isa.model.Pice;
import com.isa.model.Porudzbina;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.StavkaPorudzbineMenadzera;
import com.isa.model.korisnici.MenadzerRestorana;

public interface MenadzerRestoranaServis {

	List<MenadzerRestorana> findAll();
	
	MenadzerRestorana findOne(Long id);
	
	MenadzerRestorana save(MenadzerRestorana ponudjac);
	
	void delete(Long id);
	
	Page<PorudzbinaMenadzer> izlistajPorudzbineMenadzera(MenadzerRestorana menadzerRestorana, Pageable pageable);
	
	Restoran izlistajRestoran(MenadzerRestorana menadzerRestorana);

	List<Namirnica> izlistajSveNamirnice();

	List<Pice> izlistajSvaPica();

	void dodajPorudzbinu(PorudzbinaMenadzer porudzbina, List<StavkaPorudzbineMenadzera> stavke);
}

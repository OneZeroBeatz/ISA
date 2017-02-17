package com.isa.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;

public interface MenadzerSistemaServis {

	public Restoran saveRestoran(Restoran restoran);
	public Page<Restoran> izlistajRestorane(Pageable pageable);
	public MenadzerRestorana saveMenadzerRestorana(MenadzerRestorana menadzerRestorana);
	

	
}

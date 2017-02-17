package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.repository.MenadzerRestoranaSkladiste;
import com.isa.repository.RestoranSkladiste;

@Service
public class MenadzerSistemaServisImpl implements MenadzerSistemaServis {

	@Autowired
	private RestoranSkladiste restoranSkladiste;

	@Autowired
	private MenadzerRestoranaSkladiste menadzerRestoranaSkladiste;
	
	@Override
	public Restoran saveRestoran(Restoran restoran) {
		return restoranSkladiste.save(restoran);
	}
	
	
	@Override
	public Page<Restoran> izlistajRestorane(Pageable pageable) {
		return restoranSkladiste.findAll(pageable);
	}

	@Override
	public MenadzerRestorana saveMenadzerRestorana(MenadzerRestorana menadzerRestorana) {
		System.out.println("registrovao je menadzera restorana " + menadzerRestorana.getIme());
		return menadzerRestoranaSkladiste.save(menadzerRestorana);
	}

	
}

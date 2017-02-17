package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.repository.MenadzerRestoranaSkladiste;
import com.isa.repository.PorudzbineMenadzeraSkladiste;

@Service
public class MenadzerRestoranaServisImpl implements MenadzerRestoranaServis{

	@Autowired
	private MenadzerRestoranaSkladiste menadzerRestoranaSkladiste;
	
	@Autowired
	private PorudzbineMenadzeraSkladiste porudzbinaMenadzeraSkladiste;
	
	@Override
	public List<MenadzerRestorana> findAll() {
		return menadzerRestoranaSkladiste.findAll();
	}

	@Override
	public MenadzerRestorana findOne(Long id) {
		return menadzerRestoranaSkladiste.findOne(id);
	}

	@Override
	public MenadzerRestorana save(MenadzerRestorana menadzerRestorana) {
		return menadzerRestoranaSkladiste.save(menadzerRestorana);
	}

	@Override
	public void delete(Long id) {
		menadzerRestoranaSkladiste.delete(id);	
	}

	@Override
	public Page<PorudzbinaMenadzer> izlistajPorudzbineMenadzera(MenadzerRestorana menadzerRestorana,
			Pageable pageable) {
		
		return porudzbinaMenadzeraSkladiste.findByMenadzerrestorana(menadzerRestorana, pageable);
	}

}

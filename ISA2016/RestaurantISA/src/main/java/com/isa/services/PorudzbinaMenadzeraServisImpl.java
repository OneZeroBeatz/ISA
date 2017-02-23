package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Namirnica;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.repository.NamirnicaSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PorudzbineMenadzeraSkladiste;

@Service
public class PorudzbinaMenadzeraServisImpl implements PorudzbinaMenadzeraServis{

	@Autowired
	PorudzbineMenadzeraSkladiste porMenSkladiste;

	@Autowired
	NamirnicaSkladiste namirnicaSkladiste;
	
	@Autowired
	PiceSkladiste piceSkladiste;
	
	@Override
	public List<PorudzbinaMenadzer> findAll() {
		return porMenSkladiste.findAll();
	}

	@Override
	public PorudzbinaMenadzer findOne(Long id) {
		return porMenSkladiste.findOne(id);
	}

	@Override
	public PorudzbinaMenadzer save(PorudzbinaMenadzer ponudjac) {
		return porMenSkladiste.save(ponudjac);
	}

	@Override
	public void delete(Long id) {
		porMenSkladiste.delete(id);
	}

	@Override
	public Page<Namirnica> izlistajNamirnice(PorudzbinaMenadzer ponudjac, Pageable pageable) {
		return namirnicaSkladiste.findByPorudzbinaMenadzer(ponudjac, pageable);
	}
/*
	@Override
	public Page<Pice> izlistajPica(PorudzbinaMenadzer ponudjac, Pageable pageable) {
		return piceSkladiste.findByPorudzbinaMenadzer(ponudjac, pageable);
	}
*/
}

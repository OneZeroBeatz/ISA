package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Jelo;
import com.isa.model.Pice;
import com.isa.model.Restoran;
import com.isa.repository.JeloSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.RestoranSkladiste;

@Service
public class RestoranServisImpl implements RestoranServis{

	@Autowired
	RestoranSkladiste restoranSkladiste;

	@Autowired
	JeloSkladiste jeloSkladiste;
	
	@Autowired
	PiceSkladiste piceSkladiste;
	
	@Override
	public List<Restoran> findAll() {
		return restoranSkladiste.findAll();
	}

	@Override
	public Restoran findOne(Long id) {
		return restoranSkladiste.findOne(id);
	}

	@Override
	public Restoran save(Restoran restoran) {
		return restoranSkladiste.save(restoran);
	}

	@Override
	public void delete(Long id) {
		restoranSkladiste.delete(id);
	}

	@Override
	public Page<Jelo> izlistajJelovnik(Restoran restoran, Pageable pageable) {
		return jeloSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public Page<Pice> izlistajKartuPica(Restoran restoran, Pageable pageable) {
		return piceSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public void save(Jelo jelo) {
		jeloSkladiste.save(jelo);
	}

	@Override
	public void save(Pice pice) {
		piceSkladiste.save(pice);
	}

	@Override
	public void izbrisiJelo(Long id) {
		jeloSkladiste.delete(id);
	}

	@Override
	public void izbrisiPice(Long id) {
		piceSkladiste.delete(id);
	}

}

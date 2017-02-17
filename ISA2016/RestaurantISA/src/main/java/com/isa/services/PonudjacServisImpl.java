package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Ponuda;
import com.isa.model.korisnici.Ponudjac;
import com.isa.repository.PonudaSkladiste;
import com.isa.repository.PonudjacSkladiste;

@Service
public class PonudjacServisImpl implements PonudjacServis{

	@Autowired
	private PonudjacSkladiste ponudjacSkladiste;

	@Autowired
	private PonudaSkladiste ponudaSkladiste;
	
	@Override
	public List<Ponudjac> findAll() {
		return ponudjacSkladiste.findAll(); 
	}

	@Override
	public Ponudjac findOne(Long id) {
		return ponudjacSkladiste.findOne(id);
	}

	@Override
	public Ponudjac save(Ponudjac ponudjac) {
		return ponudjacSkladiste.save(ponudjac);
	}

	@Override
	public void delete(Long id) {
		ponudjacSkladiste.delete(id);
	}

	@Override
	public Page<Ponuda> izlistajPonude(Ponudjac ponudjac, Pageable pageable) {
		return ponudaSkladiste.findByPonudjac(ponudjac, pageable);
	}
}

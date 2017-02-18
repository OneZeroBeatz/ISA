package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Sanker;
import com.isa.repository.SankerSkladiste;

public class SankerServisImpl implements SankerServis {
	

	@Autowired
	private SankerSkladiste sankerSkladiste;
	
	@Override
	public List<Korisnik> findAll() {
		return sankerSkladiste.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		return sankerSkladiste.findOne(id);
	}

	@Override
	public Sanker save(Sanker kuvar) {
		return sankerSkladiste.save(kuvar);
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik gost = sankerSkladiste.findOne(id);
		if(gost == null){
			return null;
		}else{
			sankerSkladiste.delete(gost);
			return gost;
		}
	}

	@Override
	public Korisnik findByEmail(String email) {
		try{
			return sankerSkladiste.findByEmail(email).get(0);
		}catch(Exception e){
			return null;
		}
	}


}

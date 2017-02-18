package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;
import com.isa.repository.KonobarSkladiste;

@Service
public class KonobarServisImpl implements KonobarServis {

	@Autowired
	private KonobarSkladiste konobarSkladiste;
	
	@Override
	public List<Korisnik> findAll() {
		return konobarSkladiste.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		return konobarSkladiste.findOne(id);
	}

	@Override
	public Konobar save(Konobar kuvar) {
		return konobarSkladiste.save(kuvar);
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik gost = konobarSkladiste.findOne(id);
		if(gost == null){
			return null;
		}else{
			konobarSkladiste.delete(gost);
			return gost;
		}
	}

	@Override
	public Korisnik findByEmail(String email) {
		try{
			return konobarSkladiste.findByEmail(email).get(0);
		}catch(Exception e){
			return null;
		}
	}

	
}

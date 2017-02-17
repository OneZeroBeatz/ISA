package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.repository.GostSkladiste;

@Service
public class GostServisImp implements GostServis {

	@Autowired
	private GostSkladiste gostSkladiste;
	
	@Override
	public List<Korisnik> findAll() {
		return gostSkladiste.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		return gostSkladiste.findOne(id);
	}

	@Override
	public Gost save(Gost gost) {
		return gostSkladiste.save(gost);
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik gost = gostSkladiste.findOne(id);
		if(gost == null){
			return null;
		}else{
			gostSkladiste.delete(gost);
			return gost;
		}
	}

	@Override
	public Korisnik findByEmail(String email) {
		try{
			return gostSkladiste.findByEmail(email).get(0);
		}catch(Exception e){
			return null;
		}
	}

}

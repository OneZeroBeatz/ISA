package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;
import com.isa.pomocni.GostPrijatelj;
import com.isa.repository.GostSkladiste;
import com.isa.repository.PrijateljSkladiste;

@Service
public class GostServisImp implements GostServis {

	@Autowired
	private GostSkladiste gostSkladiste;
	
	@Autowired
	private PrijateljSkladiste prijSkladiste;
	
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
	public void delete(GostPrijatelj gostPrij) {

		if(gostPrij != null){
			prijSkladiste.deleteGostPrij(gostPrij.getGost().getEmail(), gostPrij.getPrijatelj().getEmail());
			prijSkladiste.deletePrijGost(gostPrij.getPrijatelj().getEmail(), gostPrij.getGost().getEmail());
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

	@Override
	public Page<Prijatelj> izlistajPrijatelje(Gost gost, Pageable pageable) {
		try{
			return prijSkladiste.findByEmailGosta(gost.getEmail(), pageable);
		}catch(Exception ex){
			return null;			
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}

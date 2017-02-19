package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Kuvar;
import com.isa.repository.KuvarSkladiste;

@Service
public class KuvarServisImpl implements KuvarServis{

	@Autowired
	private KuvarSkladiste kuvarSkladiste;
	
	@Override
	public List<Korisnik> findAll() {
		return kuvarSkladiste.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		System.out.println("prvo pronasao kuvara po ID-u");
		return kuvarSkladiste.findOne(id);
	}

	@Override
	public Kuvar save(Kuvar kuvar) {
		System.out.println("sacuvao kuvara po imenu " + kuvar.getIme());
		return kuvarSkladiste.save(kuvar);
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik gost = kuvarSkladiste.findOne(id);
		if(gost == null){
			return null;
		}else{
			kuvarSkladiste.delete(gost);
			return gost;
		}
	}

	@Override
	public Korisnik findByEmail(String email) {
		try{
			return kuvarSkladiste.findByEmail(email).get(0);
		}catch(Exception e){
			return null;
		}
	}

}

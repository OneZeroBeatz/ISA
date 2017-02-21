package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.JeloUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Kuvar;
import com.isa.repository.JeloUPorudzbiniSkladiste;
import com.isa.repository.KuvarSkladiste;

@Service
public class KuvarServisImpl implements KuvarServis{

	@Autowired
	private KuvarSkladiste kuvarSkladiste;
	
	@Autowired
	private JeloUPorudzbiniSkladiste jeloUPorudzbiniSkladiste;
	
	
	
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

	@Override
	public Page<JeloUPorudzbini> izlistajJelaPorudzbine(Porudzbina porudzbina,
			Pageable pageable) {
		return jeloUPorudzbiniSkladiste.findByPorudzbina(porudzbina, pageable);
	}

	@Override
	public void sacuvajJeloUPorudzbini(JeloUPorudzbini jeloUPorudzbini) {
		jeloUPorudzbiniSkladiste.save(jeloUPorudzbini);
	}

}

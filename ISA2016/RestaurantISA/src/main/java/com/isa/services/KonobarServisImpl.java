package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Pice;
import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;
import com.isa.repository.JeloSkladiste;
import com.isa.repository.JeloUPorudzbiniSkladiste;
import com.isa.repository.KonobarSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PiceUPorudzbiniSkladiste;
import com.isa.repository.PorudzbinaSkladiste;
import com.isa.repository.RestoranSkladiste;

@Service
public class KonobarServisImpl implements KonobarServis {

	@Autowired
	private KonobarSkladiste konobarSkladiste;
	@Autowired
	private RestoranSkladiste restoranSkladiste;
	@Autowired
	private PorudzbinaSkladiste porudzbinaSkladiste;
	@Autowired
	private JeloSkladiste jeloSkladiste;
	@Autowired
	private PiceSkladiste piceSkladiste;	
	@Autowired
	private PiceUPorudzbiniSkladiste piceUPorudzbiniSkladiste;
	@Autowired
	private JeloUPorudzbiniSkladiste jeloUPorudzbiniSkladiste;
	
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

	@Override
	public Restoran izlistajRestoran(Konobar konobar) {
		return restoranSkladiste.findByKonobar(konobar);
	}

	@Override
	public void savePorudzbina(Porudzbina porudzbina) {
		porudzbinaSkladiste.save(porudzbina);
	}

	@Override
	public Jelo pronadjiJelo(Long i) {
		return jeloSkladiste.findById(i);
	}

	@Override
	public Pice pronadjiPice(Long i) {
		return piceSkladiste.findById(i);
	}

	@Override
	public void saveJeloUPorudzbini(JeloUPorudzbini jeloUPorudzbini) {
		jeloUPorudzbiniSkladiste.save(jeloUPorudzbini);
	}

	@Override
	public void savePiceUPorudzbini(PiceUPorudzbini piceUPorudzbini) {
		piceUPorudzbiniSkladiste.save(piceUPorudzbini);
	}

	@Override
	public Porudzbina pronadjiPorudzbinu(Long id) {
		return porudzbinaSkladiste.findById(id);
	}

	@Override
	public Page<Porudzbina> izlistajPorudzbine(Konobar konobar,
			Pageable pageable) {
		return porudzbinaSkladiste.findByKonobar(konobar, pageable);
	}

	
}

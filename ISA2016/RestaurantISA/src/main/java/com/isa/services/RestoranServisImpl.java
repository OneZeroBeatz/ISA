package com.isa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Jelo;
import com.isa.model.Pice;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Sanker;
import com.isa.repository.JeloSkladiste;
import com.isa.repository.JeloUPorudzbiniSkladiste;
import com.isa.repository.KonobarSkladiste;
import com.isa.repository.KuvarSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PiceUPorudzbiniSkladiste;
import com.isa.repository.PonudjacSkladiste;
import com.isa.repository.RestoranSkladiste;
import com.isa.repository.SankerSkladiste;
import com.isa.repository.StoSkladiste;

@Service
public class RestoranServisImpl implements RestoranServis{

	@Autowired
	RestoranSkladiste restoranSkladiste;

	@Autowired
	JeloSkladiste jeloSkladiste;
	
	@Autowired
	PiceSkladiste piceSkladiste;

	@Autowired
	KuvarSkladiste kuvarSkladiste;
	
	@Autowired
	StoSkladiste stoSkladiste;

	@Autowired
	KonobarSkladiste konobarSkladiste;
	
	@Autowired
	JeloUPorudzbiniSkladiste jeloUPorudzbiniSkladiste;
	
	@Autowired
	PiceUPorudzbiniSkladiste piceUPorudzbiniSkladiste;
	
	@Autowired
	PonudjacSkladiste ponudjacSkladiste;
	
	@Autowired
	SankerSkladiste sankerSkladiste;
	
	
	
	
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

	@Override
	public Page<Sto> kreirajStolove(Restoran restoran, ArrayList<Integer> oznake, Pageable pageable) {
		for(Integer i : oznake){
			Sto s = new Sto();
			s.setOznaka(i);
			s.setBrojmesta(0);
			s.setSegment("nijesto");
			s.setZauzet(false);
			s.setRestoran(restoran);
			stoSkladiste.save(s);
		}
		
		return stoSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public Sto izlistajSto(Sto sto) {
		return stoSkladiste.findByRestoranAndOznaka(sto.getRestoran(), sto.getOznaka());
	}

	@Override
	public Page<Sto> izlistajStolove(Restoran restoran, Pageable pageable) {
		return stoSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public void dodajRedoveIKolone(Restoran rest) {
		restoranSkladiste.save(rest);
		
	}

	@Override
	public List<Ponudjac> izlistajPonudjaceVanRestorana(Restoran restoran) {
		List<Ponudjac> sviPonudjaci = ponudjacSkladiste.findAll();
		List<Ponudjac> restoranoviPonudjaci = ponudjacSkladiste.findByRestoran(restoran);
		for(Ponudjac pon : restoranoviPonudjaci){
			if(sviPonudjaci.contains(pon)){
				sviPonudjaci.remove(pon);
			}
		}
		
		return sviPonudjaci;
	}

	@Override
	public void dodajPonudjacaURestoran(Restoran restoran, Ponudjac ponudjac) {
		/*List<Ponudjac> ponudjaci = ponudjacSkladiste.findByRestoran(restoran);
		if(ponudjaci == null)
			ponudjaci = new ArrayList<>();
		
		ponudjaci.add(ponudjac);
		*/
		restoran.getPonudjac().add(ponudjac);
		restoranSkladiste.save(restoran);
	}

	@Override
	public Ponudjac save(Ponudjac ponudjac) {
		return ponudjacSkladiste.save(ponudjac);
	}
	
	@Override
	public void izbrisiPiceUPorudzbini(Long id) {
		piceUPorudzbiniSkladiste.deleteById(id);
	}

	@Override
	public void izbrisiJeloUPorudzbini(Long id) {
		jeloUPorudzbiniSkladiste.deleteById(id);
	}

	@Override
	public List<Konobar> izlistajKonobare(Restoran restoran) {
		return konobarSkladiste.findByRestoran(restoran);
	}

	@Override
	public List<Kuvar> izlistajKuvare(Restoran restoran) {
		return kuvarSkladiste.findByRestoran(restoran);
	}

	@Override
	public List<Sanker> izlistajSankere(Restoran restoran) {
		return sankerSkladiste.findByRestoran(restoran);
	}

}

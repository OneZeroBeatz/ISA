package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.Namirnica;
import com.isa.model.Pice;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.StavkaPorudzbineMenadzera;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.repository.MenadzerRestoranaSkladiste;
import com.isa.repository.NamirnicaSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PorudzbineMenadzeraSkladiste;
import com.isa.repository.RestoranSkladiste;
import com.isa.repository.StavkaPorudzbineMenSkladiste;

@Service
public class MenadzerRestoranaServisImpl implements MenadzerRestoranaServis{

	@Autowired
	private MenadzerRestoranaSkladiste menadzerRestoranaSkladiste;
	
	@Autowired
	private PorudzbineMenadzeraSkladiste porudzbinaMenadzeraSkladiste;
	
	@Autowired
	private RestoranSkladiste restoranSkladiste;
	
	@Autowired
	private NamirnicaSkladiste namirnicaSkladiste;
	
	@Autowired
	private PiceSkladiste piceSkladiste;
	
	@Autowired
	private StavkaPorudzbineMenSkladiste stavkaPorudzbineMenSkladiste;
	
	@Override
	public List<MenadzerRestorana> findAll() {
		return menadzerRestoranaSkladiste.findAll();
	}

	@Override
	public MenadzerRestorana findOne(Long id) {
		return menadzerRestoranaSkladiste.findOne(id);
	}

	@Override
	public MenadzerRestorana save(MenadzerRestorana menadzerRestorana) {
		return menadzerRestoranaSkladiste.save(menadzerRestorana);
	}

	@Override
	public void delete(Long id) {
		menadzerRestoranaSkladiste.delete(id);	
	}

	@Override
	public Page<PorudzbinaMenadzer> izlistajPorudzbineMenadzera(MenadzerRestorana menadzerRestorana,
			Pageable pageable) {
		
		return porudzbinaMenadzeraSkladiste.findByMenadzerrestorana(menadzerRestorana, pageable);
	}

	@Override
	public Restoran izlistajRestoran(MenadzerRestorana menadzerRestorana) {
		return restoranSkladiste.findByMenadzerrestorana(menadzerRestorana);
	}

	@Override
	public List<Namirnica> izlistajSveNamirnice() {
		return namirnicaSkladiste.findAll();
	}

	@Override
	public List<Pice> izlistajSvaPica() {
		return piceSkladiste.findAll();
	}

	@Override
	public void dodajPorudzbinu(PorudzbinaMenadzer porudzbinaMenadzer, List<StavkaPorudzbineMenadzera> stavke) {
		PorudzbinaMenadzer pm = porudzbinaMenadzeraSkladiste.save(porudzbinaMenadzer);
		
		for(StavkaPorudzbineMenadzera s : stavke){
			s.setPorudzbinamenadzer(pm);
			stavkaPorudzbineMenSkladiste.save(s);
			/*
			if(s.getNamirnica() != null){
				
			}else if(s.getPice() != null){
				s.setPorudzbinamenadzer(pm);
				stavkaPorudzbineMenSkladiste.save(s);
			}
			*/
		}
	}

}

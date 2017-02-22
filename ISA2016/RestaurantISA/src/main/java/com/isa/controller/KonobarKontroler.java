package com.isa.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Pice;
import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.pomocni.JelaPica;
import com.isa.services.KonobarServis;
import com.isa.services.RestoranServis;

@Controller
@RequestMapping("/konobarKontroler")


public class KonobarKontroler {

	@Autowired
	public KonobarServis konobarServis;
	@Autowired
	public RestoranServis restoranServis;	
	
	@RequestMapping(value = "/ucitajJelaKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Jelo>> ucitajJelaKonobara(@RequestBody Konobar konobar) {

		Restoran restoran = konobarServis.izlistajRestoran(konobar);
		Page<Jelo> jela = restoranServis.izlistajJelovnik(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Jelo>>(jela.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPicaKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pice>> ucitajPicaKonobara(@RequestBody Konobar konobar) {

		Restoran restoran = konobarServis.izlistajRestoran(konobar);
		Page<Pice> pica = restoranServis.izlistajKartuPica(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Pice>>(pica.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPicaKonobara(@RequestBody JelaPica jelaPica)  {
		
		// Dodata porudzbina
		Porudzbina porudzbina = new Porudzbina();
		Restoran restoran = jelaPica.getKonobar().getRestoran();
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		porudzbina.setVremePrimanja(currentTime);
		porudzbina.setRestoran(restoran);
		porudzbina.setSanker(null);
		porudzbina.setKonobar((Konobar)konobarServis.findOne(jelaPica.getKonobar().getId()));
		porudzbina.setSto(jelaPica.getSto());

		
		konobarServis.savePorudzbina(porudzbina);
		
		ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
		ArrayList<Pice> picaL = new ArrayList<Pice>();
		
		for (int i = 0; i< jelaPica.getSvaJela().length; i++){
			jelaL.add(konobarServis.pronadjiJelo(jelaPica.getSvaJela()[i].getJel()));
		}

		for (int i = 0; i< jelaPica.getSvaPica().length; i++){
			picaL.add(konobarServis.pronadjiPice(jelaPica.getSvaPica()[i].getPic()));
		}
		
		if (jelaL.isEmpty()){
			porudzbina.setSpremnaJela(true);			
		} else {
			porudzbina.setSpremnaJela(false);
		}

		if(picaL.isEmpty()){
			porudzbina.setSpremnaPica(true);		
		} else {
			porudzbina.setSpremnaPica(false);		
		}
		

		// KREIRANJE SVIH JELA U PORUDZBINI
		Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
		for (Jelo temp : uniqueSetJela) {
			JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
			jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
			jeloUPorudzbini.setJelo(temp);
			jeloUPorudzbini.setPorudzbina(porudzbina);
			
			konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
			
		}
		
		// KREIRANJE SVIH JELA U PORUDZBINI
		Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
		for (Pice temp : uniqueSetPica) {
			PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
			piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
			piceUPorudzbini.setPice(temp);
			piceUPorudzbini.setPorudzbina(porudzbina);
			konobarServis.savePiceUPorudzbini(piceUPorudzbini);
		}
		Page<Porudzbina> porudzbine = konobarServis.izlistajPorudzbine((Konobar)konobarServis.findOne(jelaPica.getKonobar().getId()), new PageRequest(0, 10));

		
		return new ResponseEntity<List<Porudzbina>>(porudzbine.getContent(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/izlistajStolove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sto>> ucitajStoloveKonobara(@RequestBody Konobar konobar) {

		// TODO: Da vrati samo stolove za koje je zaduzen ovaj konobar, a ne sve 
		Restoran restoran = konobarServis.izlistajRestoran(konobar);
		Page<Sto> stolovi = restoranServis.izlistajStolove(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Sto>>(stolovi.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPorudzbine(@RequestBody Konobar konobar){		
		Page<Porudzbina> porudzbine = konobarServis.izlistajPorudzbine(konobar, new PageRequest(0, 10));
		System.out.println("Ucitao porudzbina " + porudzbine.getContent().size());
		
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajJelaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JeloUPorudzbini>> ucitajJelaPorudzbine(@RequestBody Porudzbina porudzbina){
		Page<JeloUPorudzbini> jelaUPorudzbini = konobarServis.izlistajJelaPorudzbine(porudzbina, new PageRequest(0, 10));
		return new ResponseEntity<List<JeloUPorudzbini>> (jelaUPorudzbini.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPicaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PiceUPorudzbini>> ucitajPicaPorudzbine(@RequestBody Porudzbina porudzbina){
		Page<PiceUPorudzbini> picaUPorudzbini = konobarServis.izlistajPicaPorudzbine(porudzbina, new PageRequest(0, 10));
		return new ResponseEntity<List<PiceUPorudzbini>> (picaUPorudzbini.getContent(), HttpStatus.OK);
	}
	
}



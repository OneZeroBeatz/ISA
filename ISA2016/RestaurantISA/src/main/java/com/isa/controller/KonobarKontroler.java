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
	public ResponseEntity<Porudzbina> ucitajPicaKonobara(@RequestBody JelaPica jelaPica)  {
		
		// Dodata porudzbina
		Porudzbina porudzbina = new Porudzbina();
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		porudzbina.setVremePrimanja(currentTime);
		konobarServis.savePorudzbina(porudzbina);
		
		
		ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
		ArrayList<Pice> picaL = new ArrayList<Pice>();
		
		

		System.out.println( " ID OVI JELA ");
		for (int i = 0; i< jelaPica.getSvaJela().length; i++){
			jelaL.add(konobarServis.pronadjiJelo(jelaPica.getSvaJela()[i].getJel()));
		}

		for (int i = 0; i< jelaPica.getSvaPica().length; i++){
			picaL.add(konobarServis.pronadjiPice(jelaPica.getSvaPica()[i].getPic()));
		}
		

		
		// KREIRANJE SVIH JELA U PORUDZBINI
		Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
		System.out.println("**** SPISAK JELA ******");
		for (Jelo temp : uniqueSetJela) {
			JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
			jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
			jeloUPorudzbini.setJelo(temp);
			jeloUPorudzbini.setPorudzbina(porudzbina);
			System.out.println(temp.getNaziv() + " " + Collections.frequency(jelaL, temp) + " komada");
			
			konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
			
		}
		
		// KREIRANJE SVIH JELA U PORUDZBINI
		Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
		System.out.println("DODATO JE "+ uniqueSetPica.size() + " razlicitih pica");
		System.out.println("DODATO JE "+ uniqueSetPica.size() + " razlicitih pica");
		for (Pice temp : uniqueSetPica) {
			System.out.println(temp + " ovo pice ispisi kakvo je");
			PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
			piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
			piceUPorudzbini.setPice(temp);
			piceUPorudzbini.setPorudzbina(porudzbina);
			konobarServis.savePiceUPorudzbini(piceUPorudzbini);
		}

		return new ResponseEntity<Porudzbina>(HttpStatus.OK);
	}
	
	

	
	
	
}



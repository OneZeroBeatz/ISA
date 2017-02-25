package com.isa.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.isa.model.ZahtevZaPrijateljstvo;
import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;
import com.isa.pomocni.GostPrijatelj;
import com.isa.services.GostServis;

@Controller
@RequestMapping("/gostKontroler")
public class GostKontroler {
	
	@Autowired
	public GostServis gostServis;
	
	@RequestMapping(value = "/izmeniGosta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gost> izmeniPonudjaca(@RequestBody Gost gost) {
		Gost originalGost = (Gost) gostServis.findOne(gost.getId());
		
		originalGost.setIme(gost.getIme());
		originalGost.setPrezime(gost.getPrezime());
		originalGost.setEmail(gost.getEmail());
		originalGost.setSifra(gost.getSifra());
		
		originalGost = gostServis.save(originalGost);
		
		return new ResponseEntity<Gost>(originalGost, HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajPrijateljeNeprijatelje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izlistajPrijateljeNeprijatelje(@RequestBody Gost gost) {

		Gost originalGost = (Gost) gostServis.findOne(gost.getId());
		
		Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));
		
		List<Korisnik> korisnici = new ArrayList<>();
		
		Iterator<Prijatelj> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajZahteveZaPrijateljstvo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izlistajZahteveZaPrijateljstvo(@RequestBody Gost gost) {

		Gost originalGost = (Gost) gostServis.findOne(gost.getId());
		
		Page<ZahtevZaPrijateljstvo> prijatelji = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 10));
		
		List<Korisnik> korisnici = new ArrayList<>();
		
		Iterator<ZahtevZaPrijateljstvo> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ukloniPrijatelja", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void izbrisiPrijatelja(@RequestBody GostPrijatelj gostPrij) {
		
		gostServis.delete(gostPrij);
	}
	
	@RequestMapping(value = "/prihvatiZahtev", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void prihvatiZahtev(@RequestBody GostPrijatelj gostPrij) {
		
		gostServis.addPrijateljstvo(gostPrij);
		gostServis.removeZahtevZaPrijateljstvo(gostPrij);	
	}
	

}

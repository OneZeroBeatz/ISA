package com.isa.controller;

import java.util.ArrayList;
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

import com.isa.model.Jelo;
import com.isa.model.Namirnica;
import com.isa.model.Pice;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.pomocni.ListaStavki;
import com.isa.services.MenadzerRestoranaServis;
import com.isa.services.RestoranServis;

@Controller
@RequestMapping("/menadzerRestoranaKontroler")
public class MenadzerRestoranaKontroler {

	@Autowired
	public MenadzerRestoranaServis menadzerRestoranaServis;
	
	@Autowired
	public RestoranServis restoranServirs;
	
	@RequestMapping(value = "/izlistajPonude", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorudzbinaMenadzer>> izlistajPonude(@RequestBody MenadzerRestorana menadzerRestorana) {

		Page<PorudzbinaMenadzer> porudzbinaMenadzer = menadzerRestoranaServis.izlistajPorudzbineMenadzera(menadzerRestorana, new PageRequest(0, 10));
		
		return new ResponseEntity<List<PorudzbinaMenadzer>>(porudzbinaMenadzer.getContent(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajRestoran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restoran> izlistajRestoran(@RequestBody MenadzerRestorana menadzerRestorana) {

		MenadzerRestorana tempMenadzerRestorana = menadzerRestoranaServis.findOne(menadzerRestorana.getId());
		
		Restoran restoran = menadzerRestoranaServis.izlistajRestoran(tempMenadzerRestorana);
		
		return new ResponseEntity<Restoran>(restoran, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/izlistajJela", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Jelo>> izlistajJela(@RequestBody Restoran restoran) {
		
		Page<Jelo> jela = restoranServirs.izlistajJelovnik(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Jelo>>(jela.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPica", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pice>> izlistajPica(@RequestBody Restoran restoran) {
		
		Page<Pice> pice = restoranServirs.izlistajKartuPica(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Pice>>(pice.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajJelo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Jelo> dodajJelo(@RequestBody Jelo jelo) {
		
		restoranServirs.save(jelo);
		
		return new ResponseEntity<Jelo>(jelo, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/dodajPice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pice> dodajPice(@RequestBody Pice pice) {
		
		restoranServirs.save(pice);
		
		return new ResponseEntity<Pice>(pice, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/izmeniNazivRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restoran> izmeniNazivRestorana(@RequestBody Restoran restoran) {
		Restoran originalRestoran = restoranServirs.findOne(restoran.getId());
		
		originalRestoran.setNaziv(restoran.getNaziv());
		
		restoranServirs.save(originalRestoran);
		
		return new ResponseEntity<Restoran>(originalRestoran, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniOpisRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restoran> izmeniOpisRestorana(@RequestBody Restoran restoran) {
		Restoran originalRestoran = restoranServirs.findOne(restoran.getId());
		
		originalRestoran.setOpis(restoran.getOpis());
		
		restoranServirs.save(originalRestoran);
		
		return new ResponseEntity<Restoran>(originalRestoran, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izbrisiJelo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void izbrisiJelo(@RequestBody Long id) {
		
		restoranServirs.izbrisiJelo(id);
		
		//return new ResponseEntity<Restoran>(originalRestoran, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izbrisiPice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void izbrisiPice(@RequestBody Long id) {	
		restoranServirs.izbrisiPice(id);

	}
	
	@RequestMapping(value = "/napraviStolove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sto>> napraviStolove(@RequestBody int[] oznakeStolova) {	
		System.out.println(oznakeStolova[0]);
		Restoran restoran = restoranServirs.findOne((long)oznakeStolova[0]);
		System.out.println(restoran);
		ArrayList<Integer> oznake = new ArrayList<>();
		for(int i=1; i<oznakeStolova.length; i++){
			oznake.add(oznakeStolova[i]);
		}
		Page<Sto> stolovi = restoranServirs.kreirajStolove(restoran, oznake, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Sto>>(stolovi.getContent(), HttpStatus.OK);
	}
	/*
	@RequestMapping(value = "/izlistajStolove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sto>> izlistajStolove(@RequestBody Restoran restoran) {
		
		Page<Sto> stolovi = restoranServirs.izlistajStolove(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Sto>>(stolovi.getContent(), HttpStatus.OK);
	}
	*/
	@RequestMapping(value = "/izlistajSto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sto> izlistajSto(@RequestBody Sto sto) {	
		Sto s = restoranServirs.izlistajSto(sto);	
		return new ResponseEntity<Sto>(s, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajSveNamirnice", method = RequestMethod.POST)
	public ResponseEntity<List<Namirnica>> izlistajSveNamirnice() {	
		
		List<Namirnica> namirnice = menadzerRestoranaServis.izlistajSveNamirnice();
		
		return new ResponseEntity<List<Namirnica>>(namirnice, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajSvaPica", method = RequestMethod.POST)
	public ResponseEntity<List<Pice>> izlistajSvaPica() {	
	
		List<Pice> pice = menadzerRestoranaServis.izlistajSvaPica();
		
		return new ResponseEntity<List<Pice>>(pice, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajStavke", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void dodajStavke(@RequestBody ListaStavki listaStavki) {	
	
		System.out.println(listaStavki);
		menadzerRestoranaServis.dodajPorudzbinu(listaStavki.getPorudzbinaMenadzer(), listaStavki.getStavke());

	}
}

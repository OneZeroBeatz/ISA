package com.isa.controller;

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

import com.isa.model.Ponuda;
import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.StavkaPorudzbineMenadzera;
import com.isa.model.korisnici.Ponudjac;
import com.isa.services.PonudjacServis;
import com.isa.services.PorudzbinaMenadzeraServis;

@Controller
@RequestMapping("/ponudjacKontroler")
public class PonudjacKontroler {
	
	@Autowired
	public PonudjacServis ponudjacServis;
	
	@Autowired
	public PorudzbinaMenadzeraServis porudzbinaMenadzeraServis;
	
	@RequestMapping(value = "/izmeniPonudjaca", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ponudjac> izmeniPonudjaca(@RequestBody Ponudjac ponudjac) {
		Ponudjac originalPonudjac = ponudjacServis.findOne(ponudjac.getId());
		
		originalPonudjac.setIme(ponudjac.getIme());
		originalPonudjac.setPrezime(ponudjac.getPrezime());
		originalPonudjac.setEmail(ponudjac.getEmail());
		originalPonudjac.setSifra(ponudjac.getSifra());
		
		originalPonudjac = ponudjacServis.save(originalPonudjac);
		
		return new ResponseEntity<Ponudjac>(originalPonudjac, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPonude", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ponuda>> izlistajPonude(@RequestBody Ponudjac ponudjac) {
		Page<Ponuda> ponude = ponudjacServis.izlistajPonude(ponudjac, new PageRequest(0, 10));

		return new ResponseEntity<List<Ponuda>>(ponude.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPorudzbineBezPonude", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorudzbinaMenadzer>> izlistajPorudzbineBezPonude(@RequestBody Ponudjac ponudjac) {
		
		List<PorudzbinaMenadzer> ponude = ponudjacServis.izlistajPorudzbineBezPonude(ponudjac);

		return new ResponseEntity<List<PorudzbinaMenadzer>>(ponude, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajStavkePorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StavkaPorudzbineMenadzera>> izlistajStavkePorudzbine(@RequestBody PorudzbinaMenadzer porudzbinaMenadzer) {
		
		List<StavkaPorudzbineMenadzera> stavke = porudzbinaMenadzeraServis.izlistajStavke(porudzbinaMenadzer);

		return new ResponseEntity<List<StavkaPorudzbineMenadzera>>(stavke, HttpStatus.OK);
	}
}

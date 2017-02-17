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
import com.isa.model.korisnici.Ponudjac;
import com.isa.services.PonudjacServis;

@Controller
@RequestMapping("/ponudjacKontroler")
public class PonudjacKontroler {
	
	@Autowired
	public PonudjacServis ponudjacServis;
	
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
}

package com.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Ponudjac;
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
	
	@RequestMapping(value = "/sviPrijatelji", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gost> sviPrijatelji(@RequestBody Gost gost) {
		System.out.println("USAO IZMENI");
		Gost originalGost = (Gost) gostServis.findOne(gost.getId());
		
		
		
		return new ResponseEntity<Gost>(originalGost, HttpStatus.OK);
	}

}

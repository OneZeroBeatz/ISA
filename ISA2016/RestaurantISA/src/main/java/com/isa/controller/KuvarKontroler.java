package com.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isa.model.korisnici.Kuvar;
import com.isa.services.KuvarServis;

@Controller
@RequestMapping("/kuvarKontroler")
public class KuvarKontroler {
	
	@Autowired
	public KuvarServis kuvarServis;
	
	@RequestMapping(value = "/izmeniKuvara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Kuvar> izmeniPonudjaca(@RequestBody Kuvar gost) {
		Kuvar originalKuvar = (Kuvar) kuvarServis.findOne(gost.getId());
		
		originalKuvar.setIme(gost.getIme());
		originalKuvar.setPrezime(gost.getPrezime());
		originalKuvar.setEmail(gost.getEmail());
		originalKuvar.setSifra(gost.getSifra());
		
		originalKuvar = kuvarServis.save(originalKuvar);
		
		return new ResponseEntity<Kuvar>(originalKuvar, HttpStatus.OK);
	}

}

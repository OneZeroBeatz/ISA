package com.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.services.MenadzerSistemaServis;

@Controller
@RequestMapping("/menSistemaKontroler")
public class MenadzerSistemaKontroler {
	/*
	@Autowired
	public MenadzerSistemaServis menSistemaServis;
	
	@RequestMapping(value = "/registrujRestoran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Korisnik> getNew(Model model, @RequestBody Restoran restoran) {
		
		
	restoran.setTipKorisnika(TipKorisnika.GOST);
		menaSistemaServis.save(newGuest);
		return new ResponseEntity<Korisnik>(newGuest, HttpStatus.CREATED);
	
	
	
	}
	*/
}

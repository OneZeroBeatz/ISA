package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isa.model.Restoran;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.MenadzerSistemaServis;

@Controller
@RequestMapping("/menSistemaKontroler")
public class MenadzerSistemaKontroler {
	
	@Autowired
	public MenadzerSistemaServis menSistemaServis;
	
	@RequestMapping(value = "/registrujRestoran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restoran> registrujRestoran(Model model, @RequestBody Restoran restoran) {
		System.out.println("USAO u  reg restoran");
		menSistemaServis.saveRestoran(restoran);
		return new ResponseEntity<Restoran>(restoran, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/izlistajRestorane", method = RequestMethod.POST)
	public ResponseEntity<List<Restoran>> izlistajRestorane() {
		//TODO STA JE BRE OVO NEW PAGE REQUEST
		Page<Restoran> restorani = menSistemaServis.izlistajRestorane(new PageRequest(0, 10));
		return new ResponseEntity<List<Restoran>>(restorani.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registrujMenadzeraRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenadzerRestorana> registrujMenadzeraRestorana(Model model, @RequestBody MenadzerRestorana menadzerRestorana) {
		System.out.println("USAO u  reg menadzera");
		menadzerRestorana.setTipKorisnika(TipKorisnika.MENADZER_RESTRORANA);
		menSistemaServis.saveMenadzerRestorana(menadzerRestorana);
		return new ResponseEntity<MenadzerRestorana>(menadzerRestorana, HttpStatus.CREATED);
	}
	
	
}

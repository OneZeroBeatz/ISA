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

import com.isa.model.PorudzbinaMenadzer;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.services.MenadzerRestoranaServis;

@Controller
@RequestMapping("/menadzerRestoranaKontroler")
public class MenadzerRestoranaKontroler {

	@Autowired
	public MenadzerRestoranaServis menadzerRestoranaServis;
	
	@RequestMapping(value = "/izlistajPonude", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorudzbinaMenadzer>> izlistajPonude(@RequestBody MenadzerRestorana menadzerRestorana) {

		Page<PorudzbinaMenadzer> porudzbinaMenadzer = menadzerRestoranaServis.izlistajPorudzbineMenadzera(menadzerRestorana, new PageRequest(0, 10));
		
		return new ResponseEntity<List<PorudzbinaMenadzer>>(porudzbinaMenadzer.getContent(), HttpStatus.OK);
	}

}

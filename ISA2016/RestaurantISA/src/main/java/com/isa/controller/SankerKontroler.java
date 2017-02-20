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

import com.isa.model.Pice;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Sanker;
import com.isa.services.SankerServis;

@Controller
@RequestMapping("/sankerKontroler")
public class SankerKontroler {

	@Autowired
	public SankerServis sankerServis;
	
	
	
	
	@RequestMapping(value = "/ucitajPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPorudzbine(@RequestBody Sanker sanker){
		Restoran restoran = sanker.getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
}
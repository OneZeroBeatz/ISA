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

import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Sanker;
import com.isa.pomocni.PorudzbinaSanker;
import com.isa.services.KonobarServis;
import com.isa.services.SankerServis;

@Controller
@RequestMapping("/sankerKontroler")
public class SankerKontroler {

	@Autowired
	public SankerServis sankerServis;
	
	@Autowired
	public KonobarServis konobarServis;
	
	
	
	
	
	
	@RequestMapping(value = "/ucitajPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPorudzbine(@RequestBody Sanker sanker){
		Restoran restoran = sanker.getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPicaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PiceUPorudzbini>> ucitajPicaPorudzbine(@RequestBody Porudzbina porudzbina){
		Page<PiceUPorudzbini> picaUPorudzbini = sankerServis.izlistajPicaPorudzbine(porudzbina, new PageRequest(0, 10));
		
		
		return new ResponseEntity<List<PiceUPorudzbini>> (picaUPorudzbini.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prihvatiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> prihvatiPorudzbinu(@RequestBody PorudzbinaSanker poSa){

		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(poSa.getPorudzbina().getId());
		if(porudzbina.getSanker()!= null){
			System.out.println("vec setovan");
			return new ResponseEntity<List<Porudzbina>> (HttpStatus.NOT_MODIFIED);
		}
		porudzbina.setSanker(poSa.getSanker());
		konobarServis.savePorudzbina(porudzbina);
		
		
		Restoran restoran = poSa.getSanker().getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zavrsiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> zavrsiPorudzbinu(@RequestBody Porudzbina porudzbina1){

		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(porudzbina1.getId());
		porudzbina.setSpremna(true);
		konobarServis.savePorudzbina(porudzbina);
		
		
		Restoran restoran = porudzbina.getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	
}
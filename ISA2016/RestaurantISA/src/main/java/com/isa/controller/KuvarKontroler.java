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

import com.isa.model.JeloUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Kuvar;
import com.isa.pomocni.PorudzbinaKuvar;
import com.isa.services.KonobarServis;
import com.isa.services.KuvarServis;
import com.isa.services.SankerServis;

@Controller
@RequestMapping("/kuvarKontroler")
public class KuvarKontroler {

	@Autowired
	public KuvarServis kuvarServis;
	@Autowired
	public SankerServis sankerServis;
	@Autowired
	public KonobarServis konobarServis;
	
	

	
	@RequestMapping(value = "/ucitajPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPorudzbine(@RequestBody Kuvar kuvar){
		Restoran restoran = kuvar.getRestoran();
		// Uzeo sam ovde sankera jer je vec implementirano
		
		System.out.println("usao u ucitaj");
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajJelaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JeloUPorudzbini>> ucitajJelaPorudzbine(@RequestBody PorudzbinaKuvar porudzbinaKuvar){
		Page<JeloUPorudzbini> jelaUPorudzbini = kuvarServis.izlistajJelaPorudzbine(porudzbinaKuvar.getPorudzbina(), new PageRequest(0, 10));
		
		List<JeloUPorudzbini> retVal = new ArrayList<JeloUPorudzbini>();
		
		List<JeloUPorudzbini> picaLista = jelaUPorudzbini.getContent();
		for (int i = 0; i < picaLista.size(); i++){
			if(picaLista.get(i).getJelo().getTipKuvara().equals(porudzbinaKuvar.getKuvar().getTipKuvara())){
				System.out.println("usao, i tip je " + porudzbinaKuvar.getKuvar().getTipKuvara());
				retVal.add(picaLista.get(i));
			}
		}
		
		
		return new ResponseEntity<List<JeloUPorudzbini>> (retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prihvatiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> prihvatiPorudzbinu(@RequestBody PorudzbinaKuvar poSa){

		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(poSa.getPorudzbina().getId());
		Page<JeloUPorudzbini> jelaUPorudzbini = kuvarServis.izlistajJelaPorudzbine(poSa.getPorudzbina(), new PageRequest(0, 10));
		
		List<JeloUPorudzbini> listaNjegovih = new ArrayList<JeloUPorudzbini>();
		
		List<JeloUPorudzbini> picaLista = jelaUPorudzbini.getContent();
		for (int i = 0; i < picaLista.size(); i++){
			if(picaLista.get(i).getJelo().getTipKuvara().equals(poSa.getKuvar().getTipKuvara())){
				listaNjegovih.add(picaLista.get(i));
			}
		}

		for (int i = 0; i<listaNjegovih.size(); i++){
			if (listaNjegovih.get(i).getKuvar() != null){
				return new ResponseEntity<List<Porudzbina>> (HttpStatus.NOT_MODIFIED);
			} else {
				listaNjegovih.get(i).setKuvar(poSa.getKuvar());
				kuvarServis.sacuvajJeloUPorudzbini(listaNjegovih.get(i));
			}
			
		}
		Restoran restoran = poSa.getKuvar().getRestoran();
		
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		
		
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zavrsiPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> zavrsiPorudzbinu(@RequestBody PorudzbinaKuvar poKu){

		Porudzbina porudzbina = konobarServis.pronadjiPorudzbinu(poKu.getPorudzbina().getId());
		Page<JeloUPorudzbini> jelaUPorudzbini = kuvarServis.izlistajJelaPorudzbine(poKu.getPorudzbina(), new PageRequest(0, 10));
		List<JeloUPorudzbini> listaNjegovih = new ArrayList<JeloUPorudzbini>();
		
		List<JeloUPorudzbini> jelaLista = jelaUPorudzbini.getContent();
		for (int i = 0; i < jelaLista.size(); i++){
			if(jelaLista.get(i).getJelo().getTipKuvara().equals(poKu.getKuvar().getTipKuvara())){
				listaNjegovih.add(jelaLista.get(i));
			}
		}
		for (int i = 0; i<listaNjegovih.size(); i++){
			if (listaNjegovih.get(i).getKuvar().getId() == poKu.getKuvar().getId()){
				listaNjegovih.get(i).setSpremno(true);
				kuvarServis.sacuvajJeloUPorudzbini(listaNjegovih.get(i));			
			}
		}
		
		Restoran restoran = poKu.getKuvar().getRestoran();
		Page<Porudzbina> porudzbine = sankerServis.izlistajPorudzbine(restoran, new PageRequest(0, 10));
		return new ResponseEntity<List<Porudzbina>> (porudzbine.getContent(), HttpStatus.OK);
	}
	
	
}

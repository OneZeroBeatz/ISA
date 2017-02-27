package com.isa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.isa.model.DanUNedelji;
import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Pice;
import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.PosetaRestoranu;
import com.isa.model.RacunKonobar;
import com.isa.model.Restoran;
import com.isa.model.Smena;
import com.isa.model.SmenaUDanu;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.pomocni.IzmeniPorudzbinuIzmeni;
import com.isa.pomocni.IzmeniPorudzbinuPrikaz;
import com.isa.pomocni.JelaPica;
import com.isa.pomocni.PorudzbinaKonobar;
import com.isa.services.KonobarServis;
import com.isa.services.RestoranServis;

@Controller
@RequestMapping("/konobarKontroler")


public class KonobarKontroler {

	@Autowired
	public KonobarServis konobarServis;
	@Autowired
	public RestoranServis restoranServis;	
	
	@RequestMapping(value = "/ucitajJelaKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Jelo>> ucitajJelaKonobara(@RequestBody Konobar konobar) {

		Restoran restoran = konobarServis.izlistajRestoran(konobar);
		Page<Jelo> jela = restoranServis.izlistajJelovnik(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Jelo>>(jela.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPicaKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pice>> ucitajPicaKonobara(@RequestBody Konobar konobar) {

		Restoran restoran = konobarServis.izlistajRestoran(konobar);
		Page<Pice> pica = restoranServis.izlistajKartuPica(restoran, new PageRequest(0, 10));
		
		return new ResponseEntity<List<Pice>>(pica.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dodajPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPicaKonobara(@RequestBody JelaPica jelaPica)  {
		
		// Dodata porudzbina
		Porudzbina porudzbina = new Porudzbina();
		Restoran restoran = jelaPica.getKonobar().getRestoran();
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		porudzbina.setVremePrimanja(currentTime);
		porudzbina.setRestoran(restoranServis.findOne(restoran.getId()));
		porudzbina.setSanker(null);
		porudzbina.setKonobar((Konobar)konobarServis.findOne(jelaPica.getKonobar().getId()));

		porudzbina.setSto(restoranServis.izlistajSto(jelaPica.getSto()));
		
		konobarServis.savePorudzbina(porudzbina);
		ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
		ArrayList<Pice> picaL = new ArrayList<Pice>();
		
		for (int i = 0; i< jelaPica.getSvaJela().length; i++){
			jelaL.add(konobarServis.pronadjiJelo(jelaPica.getSvaJela()[i].getJel()));
		}

		for (int i = 0; i< jelaPica.getSvaPica().length; i++){
			picaL.add(konobarServis.pronadjiPice(jelaPica.getSvaPica()[i].getPic()));
		}
		
		if (jelaL.isEmpty()){
			porudzbina.setSpremnaJela(true);			
		} else {
			porudzbina.setSpremnaJela(false);
		}

		if(picaL.isEmpty()){
			porudzbina.setSpremnaPica(true);		
		} else {
			porudzbina.setSpremnaPica(false);		
		}
		

		// KREIRANJE SVIH JELA U PORUDZBINI
		Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
		for (Jelo temp : uniqueSetJela) {
			JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
			jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
			jeloUPorudzbini.setJelo(temp);
			jeloUPorudzbini.setPorudzbina(porudzbina);
			
			konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
			
		}
		
		// KREIRANJE SVIH JELA U PORUDZBINI
		Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
		for (Pice temp : uniqueSetPica) {
			PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
			piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
			piceUPorudzbini.setPice(temp);
			piceUPorudzbini.setPorudzbina(porudzbina);
			konobarServis.savePiceUPorudzbini(piceUPorudzbini);
		}


		return new ResponseEntity<List<Porudzbina>>(vratiPorudzbineKonobara((Konobar)konobarServis.findOne(jelaPica.getKonobar().getId())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/potvrdiIzmene", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> potvrdiIzmene(@RequestBody IzmeniPorudzbinuIzmeni parametar)  {
		System.out.println("sme da brise jela = " + parametar.isSmeDaBriseJela());
		System.out.println("sme da brise pica = " + parametar.isSmeDaBrisePica());
		System.out.println("sme da doda jela = " + parametar.isSmeDaDodaJela());
		System.out.println("sme da doda pica = " + parametar.isSmeDaDodaPica());
		// Slucaj kad sve sme
		if(parametar.isSmeDaBriseJela() && parametar.isSmeDaBrisePica() &&
				parametar.isSmeDaDodaJela() && parametar.isSmeDaDodaPica()){
			
			ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
			ArrayList<Pice> picaL = new ArrayList<Pice>();
			
			for (int i = 0; i< parametar.getSvaJela().length; i++){
				jelaL.add(konobarServis.pronadjiJelo(parametar.getSvaJela()[i].getJel()));
			}

			for (int i = 0; i< parametar.getSvaPica().length; i++){
				picaL.add(konobarServis.pronadjiPice(parametar.getSvaPica()[i].getPic()));
			}
			
			if (jelaL.isEmpty()){
				parametar.getPorudzbina().setSpremnaJela(true);			
			} else {
				parametar.getPorudzbina().setSpremnaJela(false);
			}

			if(picaL.isEmpty()){
				parametar.getPorudzbina().setSpremnaPica(true);		
			} else {
				parametar.getPorudzbina().setSpremnaPica(false);		
			}
			
			// BRISEM STARA
			List<JeloUPorudzbini> jela = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0, 10)).getContent();
			List<PiceUPorudzbini> pica = konobarServis.izlistajPicaPorudzbine(parametar.getPorudzbina(), new PageRequest(0, 10)).getContent();		
			
			for (int i = 0; i< jela.size(); i++){
				System.out.println("usao da obrise jelo jednom");
				restoranServis.izbrisiJeloUPorudzbini(jela.get(i).getId());
			}
			for (int i = 0; i< pica.size(); i++){
				System.out.println("usao da obrise pice jednom");
				restoranServis.izbrisiPiceUPorudzbini(pica.get(i).getId());
			}
			
			// KREIRANJE SVIH JELA
			Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
			for (Jelo temp : uniqueSetJela) {
				JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
				jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
				jeloUPorudzbini.setJelo(temp);
				jeloUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				List<JeloUPorudzbini> svaJelaUP = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0,10)).getContent();

				for (int i = 0; i < svaJelaUP.size(); i++){
					if(svaJelaUP.get(i).getJelo().getTipKuvara().equals(jeloUPorudzbini.getJelo().getTipKuvara())){
						jeloUPorudzbini.setKuvar(svaJelaUP.get(i).getKuvar());
					}
				}

				parametar.getPorudzbina().setSpremnaJela(false);
				System.out.println("sacuvao jelo puta " + jeloUPorudzbini.getKolicina());
				konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
				
			}
			
			// KREIRANJE SVIH PICA U PORUDZBINI
			Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
			for (Pice temp : uniqueSetPica) {
				PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
				piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
				piceUPorudzbini.setPice(temp);
				piceUPorudzbini.setPorudzbina(parametar.getPorudzbina());

				System.out.println("sacuvao pice puta " + piceUPorudzbini.getKolicina());
				konobarServis.savePiceUPorudzbini(piceUPorudzbini);
				parametar.getPorudzbina().setSpremnaPica(false);
				
			}
			// SLUCAJ KAD SME SAMO DA DODA JELA I BRISE PICa
		} else if(!parametar.isSmeDaBriseJela() && parametar.isSmeDaBrisePica() &&
				parametar.isSmeDaDodaJela() && parametar.isSmeDaDodaPica()){
			
			ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
			ArrayList<Pice> picaL = new ArrayList<Pice>();
			
			for (int i = 0; i< parametar.getSvaJela().length; i++){
				jelaL.add(konobarServis.pronadjiJelo(parametar.getSvaJela()[i].getJel()));
			}

			for (int i = 0; i< parametar.getSvaPica().length; i++){
				picaL.add(konobarServis.pronadjiPice(parametar.getSvaPica()[i].getPic()));
			}
			
			if(picaL.isEmpty()){
				parametar.getPorudzbina().setSpremnaPica(true);		
			} else {
				parametar.getPorudzbina().setSpremnaPica(false);		
			}
			
			// BRISEM STARA
			List<PiceUPorudzbini> pica = konobarServis.izlistajPicaPorudzbine(parametar.getPorudzbina(), new PageRequest(0, 10)).getContent();
					
			for (int i = 0; i< pica.size(); i++){
				System.out.println("usao da obrise pice jednom");
				restoranServis.izbrisiPiceUPorudzbini(pica.get(i).getId());
			}
			
			// KREIRANJE SVIH JELA
			Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
			for (Jelo temp : uniqueSetJela) {
				JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
				jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
				jeloUPorudzbini.setJelo(temp);
				jeloUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				List<JeloUPorudzbini> svaJelaUP = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0,10)).getContent();

				for (int i = 0; i < svaJelaUP.size(); i++){
					if(svaJelaUP.get(i).getJelo().getTipKuvara().equals(jeloUPorudzbini.getJelo().getTipKuvara())){
						jeloUPorudzbini.setKuvar(svaJelaUP.get(i).getKuvar());
					}
				}

				List<JeloUPorudzbini> jelaUP = konobarServis.izlistajJelaPorudzbineIJela(parametar.getPorudzbina(),jeloUPorudzbini.getJelo(), new PageRequest(0,10)).getContent();
				if(!jelaUP.isEmpty()){
					jelaUP.get(0).setKolicina(jelaUP.get(0).getKolicina() + jeloUPorudzbini.getKolicina());
					konobarServis.saveJeloUPorudzbini(jelaUP.get(0));	
				} else {
					konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
				}
				parametar.getPorudzbina().setSpremnaJela(false);
				
			}
			
			// KREIRANJE SVIH PICA U PORUDZBINI
			Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
			for (Pice temp : uniqueSetPica) {
				PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
				piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
				piceUPorudzbini.setPice(temp);
				piceUPorudzbini.setPorudzbina(parametar.getPorudzbina());

				System.out.println("sacuvao pice puta " + piceUPorudzbini.getKolicina());
				konobarServis.savePiceUPorudzbini(piceUPorudzbini);
				parametar.getPorudzbina().setSpremnaPica(false);
				
			}	
			// SLUCAJ KAD SME SAMO DA DODA PICA I BRISE JELA
		} else if(parametar.isSmeDaBriseJela() && !parametar.isSmeDaBrisePica() &&
				parametar.isSmeDaDodaJela() && parametar.isSmeDaDodaPica()){
			
			ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
			ArrayList<Pice> picaL = new ArrayList<Pice>();
			
			for (int i = 0; i< parametar.getSvaJela().length; i++){
				jelaL.add(konobarServis.pronadjiJelo(parametar.getSvaJela()[i].getJel()));
			}

			for (int i = 0; i< parametar.getSvaPica().length; i++){
				picaL.add(konobarServis.pronadjiPice(parametar.getSvaPica()[i].getPic()));
			}
			
			if (jelaL.isEmpty()){
				parametar.getPorudzbina().setSpremnaJela(true);			
			} else {
				parametar.getPorudzbina().setSpremnaJela(false);
			}
			
			// BRISEM STARA
			List<JeloUPorudzbini> jela = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0, 10)).getContent();

			for (int i = 0; i< jela.size(); i++){
				System.out.println("usao da obrise jelo jednom");
				restoranServis.izbrisiJeloUPorudzbini(jela.get(i).getId());
			}
			
			// KREIRANJE SVIH JELA
			Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
			for (Jelo temp : uniqueSetJela) {
				JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
				jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
				jeloUPorudzbini.setJelo(temp);
				jeloUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				List<JeloUPorudzbini> svaJelaUP = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0,10)).getContent();

				for (int i = 0; i < svaJelaUP.size(); i++){
					if(svaJelaUP.get(i).getJelo().getTipKuvara().equals(jeloUPorudzbini.getJelo().getTipKuvara())){
						jeloUPorudzbini.setKuvar(svaJelaUP.get(i).getKuvar());
					}
				}
				
				System.out.println("sacuvao jelo puta " + jeloUPorudzbini.getKolicina());
				konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
				parametar.getPorudzbina().setSpremnaJela(false);
				
			}
			
			// KREIRANJE SVIH PICA U PORUDZBINI
			Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
			for (Pice temp : uniqueSetPica) {
				PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
				piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
				piceUPorudzbini.setPice(temp);
				piceUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				// TODO: DA SIGNALIZIRAM SANKERU DA JE DOSLO DO PROMENE, AKO MOGU DA INKREMENTUJEM AKO VEC IMA
				
				List<PiceUPorudzbini> picaUP = konobarServis.izlistajPicaPorudzbineIPica(parametar.getPorudzbina(),piceUPorudzbini.getPice(), new PageRequest(0,10)).getContent();
				if(!picaUP.isEmpty()){
					picaUP.get(0).setKolicina(picaUP.get(0).getKolicina() + piceUPorudzbini.getKolicina());
					konobarServis.savePiceUPorudzbini(picaUP.get(0));	
				} else {
					konobarServis.savePiceUPorudzbini(piceUPorudzbini);	
				}
				parametar.getPorudzbina().setSpremnaPica(false);
			}
			// SLUCAJ DA NE SME SAMO DA DODA JELA I PICA
		} else if(!parametar.isSmeDaBriseJela() && !parametar.isSmeDaBrisePica() &&
				parametar.isSmeDaDodaJela() && parametar.isSmeDaDodaPica()){

			ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
			ArrayList<Pice> picaL = new ArrayList<Pice>();
			
			for (int i = 0; i< parametar.getSvaJela().length; i++){
				jelaL.add(konobarServis.pronadjiJelo(parametar.getSvaJela()[i].getJel()));
			}

			for (int i = 0; i< parametar.getSvaPica().length; i++){
				picaL.add(konobarServis.pronadjiPice(parametar.getSvaPica()[i].getPic()));
			}
			
			// KREIRANJE SVIH JELA
			Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
			for (Jelo temp : uniqueSetJela) {
				JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
				jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
				jeloUPorudzbini.setJelo(temp);
				jeloUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				List<JeloUPorudzbini> svaJelaUP = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0,10)).getContent();

				for (int i = 0; i < svaJelaUP.size(); i++){
					if(svaJelaUP.get(i).getJelo().getTipKuvara().equals(jeloUPorudzbini.getJelo().getTipKuvara())){
						jeloUPorudzbini.setKuvar(svaJelaUP.get(i).getKuvar());
					}
				}

				List<JeloUPorudzbini> jelaUP = konobarServis.izlistajJelaPorudzbineIJela(parametar.getPorudzbina(),jeloUPorudzbini.getJelo(), new PageRequest(0,10)).getContent();
				if(!jelaUP.isEmpty()){
					jelaUP.get(0).setKolicina(jelaUP.get(0).getKolicina() + jeloUPorudzbini.getKolicina());
					konobarServis.saveJeloUPorudzbini(jelaUP.get(0));	
				} else {
					konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
				}	
				parametar.getPorudzbina().setSpremnaJela(false);
				
				
			}
			
			// KREIRANJE SVIH PICA U PORUDZBINI
			Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
			for (Pice temp : uniqueSetPica) {
				PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
				piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
				piceUPorudzbini.setPice(temp);
				piceUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				// TODO: DA SIGNALIZIRAM SANKERU DA JE DOSLO DO PROMENE, AKO MOGU DA INKREMENTUJEM AKO VEC IMA
				
				List<PiceUPorudzbini> picaUP = konobarServis.izlistajPicaPorudzbineIPica(parametar.getPorudzbina(),piceUPorudzbini.getPice(), new PageRequest(0,10)).getContent();
				if(!picaUP.isEmpty()){
					picaUP.get(0).setKolicina(picaUP.get(0).getKolicina() + piceUPorudzbini.getKolicina());
					konobarServis.savePiceUPorudzbini(picaUP.get(0));	
				} else {
					konobarServis.savePiceUPorudzbini(piceUPorudzbini);	
				}
				parametar.getPorudzbina().setSpremnaPica(false);
			}
			// SMEM SAMO DA DODAM PICA
		}else if(!parametar.isSmeDaBriseJela() && !parametar.isSmeDaBrisePica() &&
				!parametar.isSmeDaDodaJela() && parametar.isSmeDaDodaPica()){

			ArrayList<Pice> picaL = new ArrayList<Pice>();


			for (int i = 0; i< parametar.getSvaPica().length; i++){
				picaL.add(konobarServis.pronadjiPice(parametar.getSvaPica()[i].getPic()));
			}
			
			// KREIRANJE SVIH PICA U PORUDZBINI
			Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
			for (Pice temp : uniqueSetPica) {
				PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
				piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
				piceUPorudzbini.setPice(temp);
				piceUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				// TODO: DA SIGNALIZIRAM SANKERU DA JE DOSLO DO PROMENE, AKO MOGU DA INKREMENTUJEM AKO VEC IMA
				
				List<PiceUPorudzbini> picaUP = konobarServis.izlistajPicaPorudzbineIPica(parametar.getPorudzbina(),piceUPorudzbini.getPice(), new PageRequest(0,10)).getContent();
				if(!picaUP.isEmpty()){
					picaUP.get(0).setKolicina(picaUP.get(0).getKolicina() + piceUPorudzbini.getKolicina());
					konobarServis.savePiceUPorudzbini(picaUP.get(0));	
				} else {
					konobarServis.savePiceUPorudzbini(piceUPorudzbini);	
				}
				
				parametar.getPorudzbina().setSpremnaPica(false);
			}
			// SMEM SAMO DA DODAM JELA
		} else if(!parametar.isSmeDaBriseJela() && !parametar.isSmeDaBrisePica() &&
				parametar.isSmeDaDodaJela() && !parametar.isSmeDaDodaPica()){

			ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
			
			for (int i = 0; i< parametar.getSvaJela().length; i++){
				jelaL.add(konobarServis.pronadjiJelo(parametar.getSvaJela()[i].getJel()));
			}
			
			// KREIRANJE SVIH JELA
			Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
			for (Jelo temp : uniqueSetJela) {
				JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
				jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
				jeloUPorudzbini.setJelo(temp);
				jeloUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				List<JeloUPorudzbini> svaJelaUP = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0,10)).getContent();

				for (int i = 0; i < svaJelaUP.size(); i++){
					if(svaJelaUP.get(i).getJelo().getTipKuvara().equals(jeloUPorudzbini.getJelo().getTipKuvara())){
						jeloUPorudzbini.setKuvar(svaJelaUP.get(i).getKuvar());
					}
				}

				List<JeloUPorudzbini> jelaUP = konobarServis.izlistajJelaPorudzbineIJela(parametar.getPorudzbina(),jeloUPorudzbini.getJelo(), new PageRequest(0,10)).getContent();
				if(!jelaUP.isEmpty()){
					jelaUP.get(0).setKolicina(jelaUP.get(0).getKolicina() + jeloUPorudzbini.getKolicina());
					konobarServis.saveJeloUPorudzbini(jelaUP.get(0));	
				} else {
					konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
				}
				parametar.getPorudzbina().setSpremnaJela(false);
				
				
			}	
			// SMEM SAMO DA BRISEM I DODAJEM PICA
		} else if(!parametar.isSmeDaBriseJela() && parametar.isSmeDaBrisePica() &&
				!parametar.isSmeDaDodaJela() && parametar.isSmeDaDodaPica()){
			
			ArrayList<Pice> picaL = new ArrayList<Pice>();

			for (int i = 0; i< parametar.getSvaPica().length; i++){
				picaL.add(konobarServis.pronadjiPice(parametar.getSvaPica()[i].getPic()));
			}

			if(picaL.isEmpty()){
				parametar.getPorudzbina().setSpremnaPica(true);		
			} else {
				parametar.getPorudzbina().setSpremnaPica(false);		
			}
			
			// BRISEM STARA
			List<PiceUPorudzbini> pica = konobarServis.izlistajPicaPorudzbine(parametar.getPorudzbina(), new PageRequest(0, 10)).getContent();
				
			for (int i = 0; i< pica.size(); i++){
				System.out.println("usao da obrise pice jednom");
				restoranServis.izbrisiPiceUPorudzbini(pica.get(i).getId());
			}
			
			// KREIRANJE SVIH PICA U PORUDZBINI
			Set<Pice> uniqueSetPica = new HashSet<Pice>(picaL);
			for (Pice temp : uniqueSetPica) {
				PiceUPorudzbini piceUPorudzbini = new PiceUPorudzbini();
				piceUPorudzbini.setKolicina(Collections.frequency(picaL, temp));
				piceUPorudzbini.setPice(temp);
				piceUPorudzbini.setPorudzbina(parametar.getPorudzbina());

				System.out.println("sacuvao pice puta " + piceUPorudzbini.getKolicina());
				konobarServis.savePiceUPorudzbini(piceUPorudzbini);
				parametar.getPorudzbina().setSpremnaPica(false);
			}
			// SLUCAJ KAD SME SAMO DA BRISE I DODAJE JELA
		} else if(parametar.isSmeDaBriseJela() && !parametar.isSmeDaBrisePica() &&
				parametar.isSmeDaDodaJela() && !parametar.isSmeDaDodaPica()){
			
			ArrayList<Jelo> jelaL = new ArrayList<Jelo>();
			
			for (int i = 0; i< parametar.getSvaJela().length; i++){
				jelaL.add(konobarServis.pronadjiJelo(parametar.getSvaJela()[i].getJel()));
			}

			if (jelaL.isEmpty()){
				parametar.getPorudzbina().setSpremnaJela(true);			
			} else {
				parametar.getPorudzbina().setSpremnaJela(false);
			}
			
			// BRISEM STARA
			List<JeloUPorudzbini> jela = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0, 10)).getContent();
					
			for (int i = 0; i< jela.size(); i++){
				System.out.println("usao da obrise jelo jednom");
				restoranServis.izbrisiJeloUPorudzbini(jela.get(i).getId());
			}
			// KREIRANJE SVIH JELA
			Set<Jelo> uniqueSetJela = new HashSet<Jelo>(jelaL);
			for (Jelo temp : uniqueSetJela) {
				JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
				jeloUPorudzbini.setKolicina(Collections.frequency(jelaL, temp));
				jeloUPorudzbini.setJelo(temp);
				jeloUPorudzbini.setPorudzbina(parametar.getPorudzbina());
				
				List<JeloUPorudzbini> svaJelaUP = konobarServis.izlistajJelaPorudzbine(parametar.getPorudzbina(), new PageRequest(0,10)).getContent();

				parametar.getPorudzbina().setSpremnaJela(false);
				
				for (int i = 0; i < svaJelaUP.size(); i++){
					if(svaJelaUP.get(i).getJelo().getTipKuvara().equals(jeloUPorudzbini.getJelo().getTipKuvara())){
						jeloUPorudzbini.setKuvar(svaJelaUP.get(i).getKuvar());
					}
				}

				System.out.println("sacuvao jelo puta " + jeloUPorudzbini.getKolicina());
				konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
				parametar.getPorudzbina().setSpremnaJela(false);
				
			}
			// SLUCAJ ELSE - NE SME NISTA
		} else {
			return new ResponseEntity<List<Porudzbina>> (HttpStatus.NOT_MODIFIED);	
		}
		Porudzbina porudz = konobarServis.pronadjiPorudzbinu(parametar.getPorudzbina().getId());
		porudz.setSpremnaJela(parametar.getPorudzbina().isSpremnaJela());
		porudz.setSpremnaPica(parametar.getPorudzbina().isSpremnaPica());
		konobarServis.savePorudzbina(porudz);	
		
		return new ResponseEntity<List<Porudzbina>>(vratiPorudzbineKonobara((Konobar)konobarServis.findOne(porudz.getId())), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/izlistajStolove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sto>> ucitajStoloveKonobara(@RequestBody Konobar konobar) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int trenutniDanUNedelji = calendar.get(Calendar.DAY_OF_WEEK);
		DanUNedelji dan = null;
		
		
		System.out.println("ponedeljak = " + calendar.get(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.HOUR_OF_DAY, 24); // adds one hour
		System.out.println("utorak = " + calendar.get(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.HOUR_OF_DAY, 24); // adds one hour
		System.out.println("sreda = " + calendar.get(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.HOUR_OF_DAY, 24); // adds one hour
		System.out.println("cetvrtak = " + calendar.get(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.HOUR_OF_DAY, 24); // adds one hour
		System.out.println("petak = " + calendar.get(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.HOUR_OF_DAY, 24); // adds one hour
		System.out.println("subota = " + calendar.get(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.HOUR_OF_DAY, 24); // adds one hour
		System.out.println("nedelja = " + calendar.get(Calendar.DAY_OF_WEEK));
		calendar.add(Calendar.HOUR_OF_DAY, 24); // adds one hour
		
		
		
		if(trenutniDanUNedelji == 2){
			dan = DanUNedelji.PONEDELJAK;
		} else if (trenutniDanUNedelji == 3){
			dan = DanUNedelji.UTORAK;
		} else if (trenutniDanUNedelji == 4){
			dan = DanUNedelji.SREDA;
		} else if (trenutniDanUNedelji == 5){
			dan = DanUNedelji.CETVRTAK;
		} else if (trenutniDanUNedelji == 6){
			dan = DanUNedelji.PETAK;
		} else if (trenutniDanUNedelji == 7){
			dan = DanUNedelji.SUBOTA;
		} else if (trenutniDanUNedelji == 1){
			dan = DanUNedelji.NEDELJA;
		} else {
			dan = DanUNedelji.NEDELJA;
		}
		

		SmenaUDanu smenaKonobara = konobarServis.izlistajSmenuUDanu(konobar,dan);
		System.out.println(smenaKonobara.getId() + " ovo je id smene konobara");
		List<Sto> stoloviKonobara = restoranServis.izlistajStoloveSmene(smenaKonobara);
		
		
		return new ResponseEntity<List<Sto>>(stoloviKonobara, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> ucitajPorudzbine(@RequestBody Konobar konobar){		
		Page<Porudzbina> porudzbine = konobarServis.izlistajPorudzbine(konobar, new PageRequest(0, 10));		
		return new ResponseEntity<List<Porudzbina>>(vratiPorudzbineKonobara((Konobar)konobarServis.findOne(konobar.getId())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajJelaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JeloUPorudzbini>> ucitajJelaPorudzbine(@RequestBody Porudzbina porudzbina){
		Page<JeloUPorudzbini> jelaUPorudzbini = konobarServis.izlistajJelaPorudzbine(porudzbina, new PageRequest(0, 10));
		return new ResponseEntity<List<JeloUPorudzbini>> (jelaUPorudzbini.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajPicaPorudzbine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PiceUPorudzbini>> ucitajPicaPorudzbine(@RequestBody Porudzbina porudzbina){
		Page<PiceUPorudzbini> picaUPorudzbini = konobarServis.izlistajPicaPorudzbine(porudzbina, new PageRequest(0, 10));
		return new ResponseEntity<List<PiceUPorudzbini>> (picaUPorudzbini.getContent(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniPorudzbinu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IzmeniPorudzbinuPrikaz> izmeniPorudzbinu(@RequestBody Porudzbina porudzbina){
		IzmeniPorudzbinuPrikaz retVal = new IzmeniPorudzbinuPrikaz();
		
		List<JeloUPorudzbini> listaNjegovih = new ArrayList<JeloUPorudzbini>();
		
		List<PiceUPorudzbini> svaPica = konobarServis.izlistajPicaPorudzbine(porudzbina, new PageRequest(0, 10)).getContent();
		List<JeloUPorudzbini> svaJela = konobarServis.izlistajJelaPorudzbine(porudzbina, new PageRequest(0, 10)).getContent();
		
		ArrayList<PiceUPorudzbini> svaPicaBack = new ArrayList<PiceUPorudzbini>();
		ArrayList<JeloUPorudzbini> svaJelaBack = new ArrayList<JeloUPorudzbini>();
		
		boolean smePicaDaBrise = true;
		boolean smeJelaDaBrise = true;

		boolean smePicaDaDoda = true;
		boolean smeJelaDaDoda = true;
		
		if(svaPica.isEmpty() && porudzbina.isSpremnoJednoJelo()){
			smePicaDaBrise = false;
			smePicaDaDoda = false;
		}

		if(svaJela.isEmpty() && porudzbina.isSpremnaPica()){
			smeJelaDaBrise = false;
			smeJelaDaDoda = false;
		}
		
		if(konobarServis.pronadjiPorudzbinu(porudzbina.getId()).getSanker() != null){
			smePicaDaBrise = false;
			if(konobarServis.pronadjiPorudzbinu(porudzbina.getId()).isSpremnaPica()){
				smePicaDaDoda = false;
			}
		} 
		

		for (int i = 0; i<svaJela.size(); i++){
			if (svaJela.get(i).getKuvar() != null){
				smeJelaDaBrise = false;
				if(svaJela.get(i).isSpremno() == true){
					smeJelaDaDoda = false;
				}
			}
		}
		
		

		for (int i = 0; i < svaJela.size(); i++){
			svaJelaBack.add(svaJela.get(i));
		}
		
		for (int i = 0; i < svaPica.size(); i++){
			svaPicaBack.add(svaPica.get(i));
		}

		retVal.setDodataJela(svaJelaBack);
		retVal.setDodataPica(svaPicaBack);
		
		retVal.setSmeDaBriseJela(smeJelaDaBrise);
		retVal.setSmeDaBrisePica(smePicaDaBrise);

		retVal.setSmeDaDodaJela(smeJelaDaDoda);
		retVal.setSmeDaDodaPica(smePicaDaDoda);
		
		return new ResponseEntity<IzmeniPorudzbinuPrikaz> (retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/kreirajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RacunKonobar> kreirajRacun(@RequestBody PorudzbinaKonobar porKon){
		Page<PiceUPorudzbini> picaUPorudzbini = konobarServis.izlistajPicaPorudzbine(porKon.getPorudzbina(), new PageRequest(0, 10));
		Page<JeloUPorudzbini> jelaUPorudzbini = konobarServis.izlistajJelaPorudzbine(porKon.getPorudzbina(), new PageRequest(0, 10));
			
		float ukupno = 0;
		
		for (int i = 0; i < picaUPorudzbini.getContent().size(); i++){
			ukupno += picaUPorudzbini.getContent().get(i).getKolicina() * picaUPorudzbini.getContent().get(i).getPice().getCena();
		}
		
		for (int i = 0; i < jelaUPorudzbini.getContent().size(); i++){
			ukupno += jelaUPorudzbini.getContent().get(i).getKolicina() * jelaUPorudzbini.getContent().get(i).getJelo().getCena();
		}		
		
		RacunKonobar racun = new RacunKonobar();
		racun.setUkupno(ukupno);
		
		return new ResponseEntity<RacunKonobar> (racun, HttpStatus.OK);
	}

	@RequestMapping(value = "/kreiraj", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Porudzbina>> kreirajKrajnji(@RequestBody PorudzbinaKonobar porKon){
		Page<PiceUPorudzbini> picaUPorudzbini = konobarServis.izlistajPicaPorudzbine(porKon.getPorudzbina(), new PageRequest(0, 10));
		Page<JeloUPorudzbini> jelaUPorudzbini = konobarServis.izlistajJelaPorudzbine(porKon.getPorudzbina(), new PageRequest(0, 10));
			
		float ukupno = 0;
		
		for (int i = 0; i < picaUPorudzbini.getContent().size(); i++){
			ukupno += picaUPorudzbini.getContent().get(i).getKolicina() * picaUPorudzbini.getContent().get(i).getPice().getCena();
		}
		
		for (int i = 0; i < jelaUPorudzbini.getContent().size(); i++){
			ukupno += jelaUPorudzbini.getContent().get(i).getKolicina() * jelaUPorudzbini.getContent().get(i).getJelo().getCena();
		}		
		
		RacunKonobar racun = new RacunKonobar();
		racun.setUkupno(ukupno);
		
		//TODO skontati koji je konobar pa uraditi na osnovu onog kriterijuma sto su dali
		
		java.util.Date vremeNaplate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(vremeNaplate);
		Porudzbina porudz = konobarServis.pronadjiPorudzbinu(porKon.getPorudzbina().getId());
		porudz.setVremeNaplate(currentTime);
		porudz.setKonobar1(porKon.getKonobar());		
		
		racun.setPorudzbina(porudz);
		porudz.setRacun(racun);
		
		if(porudz.getKonobar().getId().equals(porudz.getKonobar1())){
			racun.setKonobar((Konobar)konobarServis.findOne(porudz.getKonobar().getId()));
		} else{
				
			/*
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance(); 
			
			
			
			
			String primljenaStr = porudz.getVremePrimanja();
			Date vremePrimanja = null;
			try {
				vremePrimanja = format.parse(primljenaStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			List<PosetaRestoranu> posete = konobarServis.izlistajPosetePoStolu(porudz.getSto());
			PosetaRestoranu trazenaPoseta = null;
			for (int i =0 ; i < posete.size();i++){
				String terminStr = posete.get(i).getTermin();
				Date terminDat = null;
				try {
					terminDat = format.parse(terminStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				calendar.setTime(terminDat); // sets calendar time/date
				calendar.add(Calendar.HOUR_OF_DAY, posete.get(i).getBrSati()); // adds one hour
				Date terminDatKraj = calendar.getTime();
				
				
				
				trazenaPoseta = posete.get(i);
				
			}
			
			String terminStr = trazenaPoseta.getTermin();
			Date terminDat = null;
			try {
				terminDat = format.parse(terminStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar c = Calendar.getInstance();
			c.setTime(terminDat);
			int danUNedeljiPosete = c.get(Calendar.DAY_OF_WEEK);
			
			List<SmenaUDanu> smenePoDanimaKonobara = restoranServis.izlistajSmenePoDanimaKonobara((Konobar) konobarServis.findOne(porudz.getKonobar().getId()));
			List<SmenaUDanu> smenePoDanimaKonobara1 = restoranServis.izlistajSmenePoDanimaKonobara((Konobar) konobarServis.findOne(porudz.getKonobar1().getId()));
			
			Smena smenaKonobara = null;
			Smena smenaKonobara1 = null;
			
			for (int i = 0; i < smenePoDanimaKonobara.size(); i++){
				if (smenePoDanimaKonobara.get(i).getDanUNedelji() == danUNedeljiPosete))){
					smenaKonobara = smenePoDanimaKonobara.get(i).getSmena();
				}
			}
			
			*/
		
		}
		
		konobarServis.saveRacun(racun);
		konobarServis.savePorudzbina(porudz);
		
		return new ResponseEntity<List<Porudzbina>>(vratiPorudzbineKonobara((Konobar)konobarServis.findOne(porKon.getKonobar().getId())), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/ucitajKonobareRestorana", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Konobar>> ucitajKonobareRestorana(@RequestBody Konobar konobar){
		Restoran restoran = konobarServis.izlistajRestoran(konobar);
		List<Konobar> retVal = restoranServis.izlistajKonobare(restoran);
		return new ResponseEntity<List<Konobar>>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajKalendarKonobara", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmenaUDanu>> ucitajKalendarKonobara(@RequestBody Konobar parametar){
		Konobar konobar = (Konobar) konobarServis.findOne(parametar.getId());
		List<SmenaUDanu> retVal = restoranServis.izlistajSmenePoDanimaKonobara(konobar);
		return new ResponseEntity<List<SmenaUDanu>>(retVal, HttpStatus.OK);
	}
	
	private List<Porudzbina> vratiPorudzbineKonobara(Konobar konobar){
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // sets calendar time/date
		int trenutniDanUNedelji = calendar.get(Calendar.DAY_OF_WEEK);
		DanUNedelji dan = null;
		if(trenutniDanUNedelji == 2){
			dan = DanUNedelji.PONEDELJAK;
		} else if (trenutniDanUNedelji == 3){
			dan = DanUNedelji.UTORAK;
		} else if (trenutniDanUNedelji == 4){
			dan = DanUNedelji.SREDA;
		} else if (trenutniDanUNedelji == 5){
			dan = DanUNedelji.CETVRTAK;
		} else if (trenutniDanUNedelji == 6){
			dan = DanUNedelji.PETAK;
		} else if (trenutniDanUNedelji == 7){
			dan = DanUNedelji.SUBOTA;
		} else if (trenutniDanUNedelji == 1){
			dan = DanUNedelji.NEDELJA;
		} else {
			dan = DanUNedelji.NEDELJA;
		}
		
		SmenaUDanu smenaKonobara = konobarServis.izlistajSmenuUDanu(konobar,dan);
		
		List<Sto> stoloviKonobara = restoranServis.izlistajStoloveSmene(smenaKonobara);
		
		for (int i = 0 ; i< stoloviKonobara.size(); i++){
			List<Porudzbina> tempPorudzbine = konobarServis.izlistajPorudzbineStola(stoloviKonobara.get(i));
			for (int j = 0; j < tempPorudzbine.size(); j++){
				porudzbine.add(tempPorudzbine.get(j));
			}
			
		}
		
		List<Porudzbina> retVal = new ArrayList<Porudzbina>();
		
		for (int i = 0; i<porudzbine.size(); i++){
			if(porudzbine.get(i).getRacun() == null){
				retVal.add(porudzbine.get(i));
			}
		}
		return retVal;
	}
	

}



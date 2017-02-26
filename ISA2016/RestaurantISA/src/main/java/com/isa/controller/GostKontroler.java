package com.isa.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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

import com.isa.model.ZahtevZaPrijateljstvo;
import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.pomocni.GostPrijatelj;
import com.isa.pomocni.Poruka;
import com.isa.pomocni.PretragaPrijatelja;
import com.isa.services.GostServis;

@Controller
@RequestMapping("/gostKontroler")
public class GostKontroler {
	
	@Autowired
	public GostServis gostServis;
	
	//Ako zatreba sortiranje u code-behind
	/*public static final Comparator<Korisnik> IME_ASC_PREZIME_ASC = new Comparator<Korisnik>() {
     public int compare(Korisnik p1, Korisnik p2) {
        int nameOrder = p1.getIme().compareTo(p2.getIme());
        if(nameOrder != 0) {
          return nameOrder;
        }
        return p1.getPrezime().compareTo(p2.getPrezime());
     }
    };*/
	
	//Collections.sort(korisnici, IME_ASC_PREZIME_ASC);
	
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

	@RequestMapping(value = "/izlistajPrijateljeNeprijatelje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izlistajPrijateljeNeprijatelje(@RequestBody Gost gost) {
		Gost originalGost = (Gost) gostServis.findOne(gost.getId());	
		Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));	
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<Prijatelj> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pretraziPravePrijatelje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> pretraziPravePrijatelje(@RequestBody PretragaPrijatelja pretrPrij) {
		
		String ime = "";
		String prezime = "";
		
		if(pretrPrij.getParamPretrageIme() != null)
			ime = pretrPrij.getParamPretrageIme().trim();
			ime = ime.toLowerCase();
		
		if(pretrPrij.getParamPretragePrz() != null)
			prezime = pretrPrij.getParamPretragePrz().trim();
			prezime = prezime.toLowerCase();
		
		if(!ime.equals("") && prezime.equals("")){		
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));		
			List<Korisnik> korisnici = new ArrayList<>();		
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmailPrijatelj());
				if(kor.getIme().toLowerCase().contains(ime))			
					korisnici.add(kor); 
			}
			
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
			
		}else if(ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));		
			List<Korisnik> korisnici = new ArrayList<>();		
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmailPrijatelj());
				if(kor.getPrezime().toLowerCase().contains(prezime))			
					korisnici.add(kor); 
			}
			
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
			
		}else if(!ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));		
			List<Korisnik> korisnici = new ArrayList<>();		
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmailPrijatelj());
				if(kor.getIme().toLowerCase().contains(ime) && kor.getPrezime().toLowerCase().contains(prezime))
					korisnici.add(kor);
			}
					
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
		}else{
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());			
			Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));			
			List<Korisnik> korisnici = new ArrayList<>();			
			Iterator<Prijatelj> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
			}
			
			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/pretraziPrijatelje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Gost>> pretraziPrijatelje(@RequestBody PretragaPrijatelja pretrPrij) {	
		return neprijatelji(pretrPrij);
	}
	
	@RequestMapping(value = "/izlistajZahteveZaPrijateljstvo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izlistajZahteveZaPrijateljstvo(@RequestBody Gost gost) {
		Gost originalGost = (Gost) gostServis.findOne(gost.getId());		
		Page<ZahtevZaPrijateljstvo> prijatelji = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 10));		
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<ZahtevZaPrijateljstvo> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ukloniPrijatelja", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> izbrisiPrijatelja(@RequestBody GostPrijatelj gostPrij) {	
		gostServis.delete(gostPrij);
		
		Gost originalGost = (Gost) gostServis.findOne(gostPrij.getGost().getId());	
		Page<Prijatelj> prijatelji = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));	
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<Prijatelj> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prihvatiZahtev", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> prihvatiZahtev(@RequestBody GostPrijatelj gostPrij) {	
		gostServis.addPrijateljstvo(gostPrij);
		gostServis.removeZahtevZaPrijateljstvo(gostPrij);
		
		Gost originalGost = (Gost) gostServis.findOne(gostPrij.getGost().getId());		
		Page<ZahtevZaPrijateljstvo> prijatelji = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 10));		
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<ZahtevZaPrijateljstvo> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/odbijZahtev", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> odbijZahtev(@RequestBody GostPrijatelj gostPrij) {		
		gostServis.removeZahtevZaPrijateljstvo(gostPrij);	
		
		Gost originalGost = (Gost) gostServis.findOne(gostPrij.getGost().getId());		
		Page<ZahtevZaPrijateljstvo> prijatelji = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 10));		
		List<Korisnik> korisnici = new ArrayList<>();	
		Iterator<ZahtevZaPrijateljstvo> itr = prijatelji.iterator();
		
		while(itr.hasNext()){
			korisnici.add(gostServis.findByEmail(itr.next().getEmailPrijatelj())); 
		}
		
		return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/otkaziZahtev", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Gost>> otkaziZahtev(@RequestBody GostPrijatelj gostPrij) {		
		gostServis.removeZahtevZaPrijateljstvoByGost(gostPrij);	
		
		return neprijatelji(new PretragaPrijatelja(gostPrij.getGost(), gostPrij.getParamPretrageIme(), gostPrij.getParamPretragePrz()));	
	}
	
	@RequestMapping(value = "/dodajPrijatelja", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Gost>> dodajPrijatelja(@RequestBody GostPrijatelj gostPrij) {		
		gostServis.addZahtevZaPrijateljstvo(gostPrij, new PageRequest(0, 10));

		return neprijatelji(new PretragaPrijatelja(gostPrij.getGost(), gostPrij.getParamPretrageIme(), gostPrij.getParamPretragePrz()));
	}
	
	public ResponseEntity<List<Gost>> neprijatelji(PretragaPrijatelja pretrPrij){
		String ime = "";
		String prezime = "";
		
		if(pretrPrij.getParamPretrageIme() != null)
			ime = pretrPrij.getParamPretrageIme().trim();
			ime = ime.toLowerCase();
		
		if(pretrPrij.getParamPretragePrz() != null)
			prezime = pretrPrij.getParamPretragePrz().trim();
			prezime = prezime.toLowerCase();
		
		if(!ime.equals("") && prezime.equals("")){		
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Korisnik> prijatelji = gostServis.izlistajNeprijatelje(originalGost, new PageRequest(0, 10));		
			List<Gost> korisnici = new ArrayList<>();
			Iterator<Korisnik> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmail());
				if(kor.getIme().toLowerCase().contains(ime))
					if(!kor.getEmail().equals(originalGost.getEmail()) && kor.getTipKorisnika().equals(TipKorisnika.GOST)){
						
						Page<Prijatelj> prijateljiKorisnika = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));							
						List<Korisnik> korisniciPrij = new ArrayList<>();					
						Iterator<Prijatelj> itr2 = prijateljiKorisnika.iterator();
										
						Page<ZahtevZaPrijateljstvo> zahteviZaPrij = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 10));		
						List<Korisnik> korisniciZaht = new ArrayList<>();	
						Iterator<ZahtevZaPrijateljstvo> itrZaht = zahteviZaPrij.iterator();
								
						boolean canAdd = true;						
						
						while(itr2.hasNext())
							korisniciPrij.add(gostServis.findByEmail(itr2.next().getEmailPrijatelj())); 
						
						while(itrZaht.hasNext())
							korisniciZaht.add(gostServis.findByEmail(itrZaht.next().getEmailPrijatelj())); 			
										
						for(Korisnik korisn: korisniciPrij)
							if(korisn.getEmail().equals(kor.getEmail()))
								canAdd = false;
						
						for(Korisnik korisn: korisniciZaht)
							if(korisn.getEmail().equals(kor.getEmail())){
								Gost gost = (Gost)kor;
								gost.setCanSend(false);
								gost.setCanDecline(false);
								gost.setCanAccept(true);
								korisnici.add(gost);
								canAdd = false;
							}
								
						if(canAdd){
							Gost gost = (Gost)kor;
							if(gostServis.zahteviCount(new GostPrijatelj(originalGost, gost), new PageRequest(0, 10)) > 0){
								gost.setCanSend(false);
								gost.setCanDecline(true);
								gost.setCanAccept(false);
							}else{
								gost.setCanSend(true);
								gost.setCanDecline(false);
								gost.setCanAccept(false);
							}
							korisnici.add(gost); 
						}
					}
			}
			return new ResponseEntity<List<Gost>>(korisnici, HttpStatus.OK);
			
		}else if(ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Korisnik> prijatelji = gostServis.izlistajNeprijatelje(originalGost, new PageRequest(0, 10));		
			List<Gost> korisnici = new ArrayList<>();		
			Iterator<Korisnik> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmail());
				if(kor.getPrezime().toLowerCase().contains(prezime))
					if(!kor.getEmail().equals(originalGost.getEmail()) && kor.getTipKorisnika().equals(TipKorisnika.GOST)){
						
						Page<Prijatelj> prijateljiKorisnika = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));							
						List<Korisnik> korisniciPrij = new ArrayList<>();					
						Iterator<Prijatelj> itr2 = prijateljiKorisnika.iterator();
						
						Page<ZahtevZaPrijateljstvo> zahteviZaPrij = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 10));		
						List<Korisnik> korisniciZaht = new ArrayList<>();	
						Iterator<ZahtevZaPrijateljstvo> itrZaht = zahteviZaPrij.iterator();
						
						boolean canAdd = true;
						
						while(itr2.hasNext())
							korisniciPrij.add(gostServis.findByEmail(itr2.next().getEmailPrijatelj())); 
						
						while(itrZaht.hasNext())
							korisniciZaht.add(gostServis.findByEmail(itrZaht.next().getEmailPrijatelj())); 			
										
						for(Korisnik korisn: korisniciPrij)
							if(korisn.getEmail().equals(kor.getEmail()))
								canAdd = false;
						
						for(Korisnik korisn: korisniciZaht)
							if(korisn.getEmail().equals(kor.getEmail())){
								Gost gost = (Gost)kor;
								gost.setCanSend(false);
								gost.setCanDecline(false);
								gost.setCanAccept(true);
								korisnici.add(gost);
								canAdd = false;
							}
								
						if(canAdd){
							Gost gost = (Gost)kor;
							if(gostServis.zahteviCount(new GostPrijatelj(originalGost, gost), new PageRequest(0, 10)) > 0){
								gost.setCanSend(false);
								gost.setCanDecline(true);
								gost.setCanAccept(false);
							}else{
								gost.setCanSend(true);
								gost.setCanDecline(false);
								gost.setCanAccept(false);
							}
							korisnici.add(gost); 
						}
					}				
			}		
			return new ResponseEntity<List<Gost>>(korisnici, HttpStatus.OK);
			
		}else if(!ime.equals("") && !prezime.equals("")){
			Gost originalGost = (Gost) gostServis.findOne(pretrPrij.getGost().getId());		
			Page<Korisnik> prijatelji = gostServis.izlistajNeprijatelje(originalGost, new PageRequest(0, 10));		
			List<Gost> korisnici = new ArrayList<>();		
			Iterator<Korisnik> itr = prijatelji.iterator();
			
			while(itr.hasNext()){
				Korisnik kor = gostServis.findByEmail(itr.next().getEmail());
				if(kor.getIme().toLowerCase().contains(ime) && kor.getPrezime().toLowerCase().contains(prezime))
					if(!kor.getEmail().equals(originalGost.getEmail()) && kor.getTipKorisnika().equals(TipKorisnika.GOST)){
						
						Page<Prijatelj> prijateljiKorisnika = gostServis.izlistajPrijatelje(originalGost, new PageRequest(0, 10));							
						List<Korisnik> korisniciPrij = new ArrayList<>();					
						Iterator<Prijatelj> itr2 = prijateljiKorisnika.iterator();
						
						Page<ZahtevZaPrijateljstvo> zahteviZaPrij = gostServis.izlistajZahteveZaPrij(originalGost, new PageRequest(0, 10));		
						List<Korisnik> korisniciZaht = new ArrayList<>();	
						Iterator<ZahtevZaPrijateljstvo> itrZaht = zahteviZaPrij.iterator();
						
						boolean canAdd = true;		
						
						while(itr2.hasNext())
							korisniciPrij.add(gostServis.findByEmail(itr2.next().getEmailPrijatelj())); 
						
						while(itrZaht.hasNext())
							korisniciZaht.add(gostServis.findByEmail(itrZaht.next().getEmailPrijatelj())); 
										
						for(Korisnik korisn: korisniciPrij)
							if(korisn.getEmail().equals(kor.getEmail()))
								canAdd = false;
						
						for(Korisnik korisn: korisniciZaht)
							if(korisn.getEmail().equals(kor.getEmail())){
								Gost gost = (Gost)kor;
								gost.setCanSend(false);
								gost.setCanDecline(false);
								gost.setCanAccept(true);
								korisnici.add(gost);
								canAdd = false;
							}
								
						if(canAdd){
							Gost gost = (Gost)kor;
							if(gostServis.zahteviCount(new GostPrijatelj(originalGost, gost), new PageRequest(0, 10)) > 0){
								gost.setCanSend(false);
								gost.setCanDecline(true);
								gost.setCanAccept(false);
							}else{
								gost.setCanSend(true);
								gost.setCanDecline(false);
								gost.setCanAccept(false);
							}
							korisnici.add(gost); 
						}
					}					
			}		
			return new ResponseEntity<List<Gost>>(korisnici, HttpStatus.OK);
		}
		
		return null;
	}
	

}

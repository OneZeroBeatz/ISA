package com.isa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.GostServis;

@Controller
@Scope("session")
@RequestMapping("/contr")
@SessionAttributes({"korisnik"})
public class LogRegKontroler {

	@Autowired
	public GostServis servis;
	
	private ModelAndView modelAndView;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> getNew(Model model, @RequestBody Gost newGuest) {
		newGuest.setTipKorisnika(TipKorisnika.GOST);
		servis.save(newGuest);
		return new ResponseEntity<Korisnik>(newGuest, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/login2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> login(Model model, @RequestBody Gost newGuest){
		Korisnik temp = servis.findByEmail(newGuest.getEmail());
		if(temp != null && temp.getSifra().equals(newGuest.getSifra())){
			modelAndView = new ModelAndView();
			modelAndView.addObject("ulogovanKorisnik", temp);
			return new ResponseEntity<Korisnik>(temp, HttpStatus.ACCEPTED);
		}else
			return null;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> getKorisnik(Model model, @RequestBody Gost newGuest){	
		Korisnik korisnik = servis.findByEmail(newGuest.getEmail());
		if(korisnik != null && korisnik.getSifra().equals(newGuest.getSifra())){
			
			if (!model.containsAttribute("korisnik")) {
				model.addAttribute("korisnik", korisnik);
			}

			System.out.println("STAVIO SI NA SESIJU - " + korisnik.getIme());
			return new ResponseEntity<Korisnik>(korisnik, HttpStatus.ACCEPTED);
		}else
			return null;
	}
	
	@RequestMapping(value = "/check2", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> checkSession(){
		Map<String,Object> map = modelAndView.getModel();
		Korisnik kor = (Korisnik) map.get("ulogovanKorisnik");
		if(kor != null){
			return new ResponseEntity<Korisnik>(kor, HttpStatus.ACCEPTED);
		}else{
			return null;
		}	
			
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> checkSessions(@ModelAttribute Korisnik naSesiji){
		System.out.println("PROVERAVAS KO JE NA SESIJI - " + naSesiji.getIme());
		Korisnik kor = servis.findOne(naSesiji.getId());
		if(kor != null){
			return new ResponseEntity<Korisnik>(kor, HttpStatus.ACCEPTED);
		}else{
			return null;
		}	
			
	}
	
	
	
}

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

}

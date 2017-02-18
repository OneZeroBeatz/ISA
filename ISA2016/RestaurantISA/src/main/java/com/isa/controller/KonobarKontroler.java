package com.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.services.KonobarServis;

@Controller
@RequestMapping("/konobarKontroler")


public class KonobarKontroler {

	@Autowired
	public KonobarServis konobarServis;
}

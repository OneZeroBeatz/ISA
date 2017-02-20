package com.isa.pomocni;

import com.isa.model.korisnici.Konobar;

public class JelaPica {

	private PomocniJelo[] svaJela;
	private PomocniPice[] svaPica;
	private Konobar konobar;
	
	public JelaPica() {
		// TODO Auto-generated constructor stub
	}
	
	public JelaPica(PomocniJelo[] svaJela, PomocniPice[] svaPica, Konobar konobar) {
		super();
		this.svaJela = svaJela;
		this.svaPica = svaPica;
		this.konobar = konobar;
	}

	public PomocniJelo[] getSvaJela() {
		return svaJela;
	}
	
	public PomocniPice[] getSvaPica() {
		return svaPica;
	}
	
	public void setSvaJela(PomocniJelo[] svaJela) {
		this.svaJela = svaJela;
	}
	
	public void setSvaPica(PomocniPice[] svaPica) {
		this.svaPica = svaPica;
	}
	
	public Konobar getKonobar() {
		return konobar;
	}
	
	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}
	
}

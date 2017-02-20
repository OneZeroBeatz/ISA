package com.isa.pomocni;

public class JelaPica {

	private PomocniJelo[] svaJela;
	private PomocniPice[] svaPica;
	
	public JelaPica() {
		// TODO Auto-generated constructor stub
	}
	
	public JelaPica(PomocniJelo[] svaJela, PomocniPice[] svaPica) {
		super();
		this.svaJela = svaJela;
		this.svaPica = svaPica;
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
	
}

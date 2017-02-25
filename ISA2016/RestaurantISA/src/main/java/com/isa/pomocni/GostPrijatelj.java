package com.isa.pomocni;

import com.isa.model.korisnici.Gost;

public class GostPrijatelj {
	
	private Gost gost;
	private Gost prijatelj;
	
	public GostPrijatelj(){
	}
	
	public GostPrijatelj(Gost gost, Gost prijatelj){
		this.gost = gost;
		this.prijatelj = prijatelj;
	}
	
	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
	}

	public Gost getPrijatelj() {
		return prijatelj;
	}

	public void setPrijatelj(Gost prijatelj) {
		this.prijatelj = prijatelj;
	}
}

package com.isa.model;

import java.util.ArrayList;
import java.util.Date;

import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Sanker;

// TODO Nisam cackao to sa bazom, mada msm da bi negde trebalo cuvati spisak porudzbina. :p
public class Porudzbina {

	private Konobar konobarPrimio; // NULL ako nije primljena
	private Konobar konobarNaplatio; // NULL ako nije naplacena
	private Konobar konobarPripisana; // Konobar kome je pripisana podudzbina
	
	private ArrayList<Kuvar> kuvari; // kuvari na porudzbini
	private ArrayList<Kuvar> kuvariZavrsili; // kuvar koji je zavrsio ubacim u listu
	
	private Sanker sanker; // sanker koji preuzima deo porudzbine vezan za pice
	
	private Date vremePrimanja; 
	private Date vremeNaplate;
	
	private ArrayList<Jelo> jela; //sva jela iz porudzbine
	private ArrayList<Pice> pica; //sva pica iz porudzbine
	
	private Sto sto;
	
	public Porudzbina(Konobar primio, Sto sto, Sanker sanker) {
		vremePrimanja = new Date();
		konobarPrimio = primio;
		this.sto = sto;
		this.sanker = sanker;
	}
	
	public Konobar getKonobarNaplatio() {
		return konobarNaplatio;
	}
	public Konobar getKonobarPrimio() {
		return konobarPrimio;
	}
	public ArrayList<Kuvar> getKuvari() {
		return kuvari;
	}
	public ArrayList<Kuvar> getKuvariZavrsili() {
		return kuvariZavrsili;
	}
	public Sanker getSanker() {
		return sanker;
	}
	
	public Date getVremeNaplate() {
		return vremeNaplate;
	}
	public Date getVremePrimanja() {
		return vremePrimanja;
	}
	public Konobar getKonobarPripisana() {
		return konobarPripisana;
	}
	public void setKonobarNaplatio(Konobar konobarNaplatio) {
		this.konobarNaplatio = konobarNaplatio;
	}
	public void setKonobarPrimio(Konobar konobarPrimio) {
		this.konobarPrimio = konobarPrimio;
	}
	public void setKonobarPripisana(Konobar konobarPripisana) {
		this.konobarPripisana = konobarPripisana;
	}
	public void setKuvari(ArrayList<Kuvar> kuvari) {
		this.kuvari = kuvari;
	}
	public void setKuvariZavrsili(ArrayList<Kuvar> kuvariZavrsili) {
		this.kuvariZavrsili = kuvariZavrsili;
	}
	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}
	public void setVremeNaplate(Date vremeNaplate) {
		this.vremeNaplate = vremeNaplate;
	}
	public void setVremePrimanja(Date vremePrimanja) {
		this.vremePrimanja = vremePrimanja;
	}
	public ArrayList<Jelo> getJela() {
		return jela;
	}
	public ArrayList<Pice> getPica() {
		return pica;
	}
	
	public Sto getSto() {
		return sto;
	}
	public void setSto(Sto sto) {
		this.sto = sto;
	}
	
}

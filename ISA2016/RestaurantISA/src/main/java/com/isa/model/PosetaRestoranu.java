package com.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.model.korisnici.Gost;

@Entity
@Table(name = "poseta_restoranu")
public class PosetaRestoranu {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = true)
	private Gost gost;
	
	@ManyToOne(optional = true)
	private Restoran restoran;
	
	@ManyToOne(optional = false)
	private Sto sto; 
	
	@Column(name = "obavljena")
	private boolean obavljena;
	
	@Column(name = "ocena")
	private int ocena;

	@Column(name = "termin")
	private String termin;


	@Column(name = "br_sati")
	private int brSati;


	
	public Gost getGost() {
		return gost;
	}
	public Restoran getRestoran() {
		return restoran;
	}
	public void setGost(Gost gost) {
		this.gost = gost;
	}
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
	public boolean isObavljena() {
		return obavljena;
	}
	public void setObavljena(boolean obavljena) {
		this.obavljena = obavljena;
	}
	
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	public String getTermin() {
		return termin;
	}
	public void setTermin(String termin) {
		this.termin = termin;
	}
	public void setBrSati(int brSati) {
		this.brSati = brSati;
	}
	
	public int getBrSati() {
		return brSati;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Sto getSto() {
		return sto;
	}
	public void setSto(Sto sto) {
		this.sto = sto;
	}
	
	
	
}

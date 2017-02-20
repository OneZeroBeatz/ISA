package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.Sanker;

@Entity
@Table(name = "restoran")
public class Restoran implements Serializable{

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv")
	private String naziv;

	@Column(name = "opis")
	private String opis;

	// Jelovnik - vise jela...
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Jelo> jelovnik;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Konobar> konobar;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Kuvar> kuvar;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Sanker> sanker;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<Porudzbina> porudzbina;
	
	
	// Karta pica - vise pica

	// Konfiguracija sedenja

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restoran")
	private Set<MenadzerRestorana> menadzerrestorana;

	public Restoran() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}

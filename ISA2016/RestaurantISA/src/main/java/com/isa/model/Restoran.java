package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	// Jelovnik

	// Karta pica

	// Konfiguracija sedenja

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

package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.model.korisnici.Ponudjac;

@Entity
@Table(name = "ponuda")
public class Ponuda implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private Ponudjac ponudjac;

	@Column(name = "cena")
	private float cena;

	@Column(name = "rokIsporuke")
	private int rokIsporuke; // ako se misli na broj dana...

	@Column(name = "garancija")
	private String garancija;
	

	public Ponuda() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public int getRokIsporuke() {
		return rokIsporuke;
	}

	public void setRokIsporuke(int rokIsporuke) {
		this.rokIsporuke = rokIsporuke;
	}

	public String getGarancija() {
		return garancija;
	}

	public void setGarancija(String garancija) {
		this.garancija = garancija;
	}

}

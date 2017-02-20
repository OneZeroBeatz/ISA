package com.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("KN")
public class Konobar extends Korisnik{

	@ManyToOne(optional = true)
	private Restoran restoran;
	
	public Konobar() {
	}
	
	public Restoran getRestoran() {
		return restoran;
	}
}

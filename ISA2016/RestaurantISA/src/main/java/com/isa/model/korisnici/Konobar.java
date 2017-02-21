package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.isa.model.Porudzbina;
import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("KN")
public class Konobar extends Korisnik{

	@ManyToOne(optional = true)
	private Restoran restoran;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "konobar")
	private Set<Porudzbina> porudzbine;
	
	public Konobar() {
	}
	
	public Restoran getRestoran() {
		return restoran;
	}
}
